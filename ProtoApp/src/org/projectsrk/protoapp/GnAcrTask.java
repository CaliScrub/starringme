package org.projectsrk.protoapp;

import java.io.InvalidObjectException;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.MockData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import com.gracenote.gnsdk.Acr.GnAcr;
import com.gracenote.gnsdk.Acr.GnAcrAudioConfig;
import com.gracenote.gnsdk.Acr.GnAcrStatus;
import com.gracenote.gnsdk.Acr.IGnAcrDelegate;
import com.gracenote.gnsdk.Android.GnAudioSourceMic;
import com.gracenote.gnsdk.Android.IGnAudioSourceDelegate;
import com.gracenote.gnsdk.Epg.GnEpg;
import com.gracenote.gnsdk.FpCache.GnFpCache;
import com.gracenote.gnsdk.Manager.GnException;
import com.gracenote.gnsdk.Manager.GnSdk;
import com.gracenote.gnsdk.Manager.GnUser;
import com.gracenote.gnsdk.Metadata.GnIterator;
import com.gracenote.gnsdk.Metadata.GnResult;
import com.gracenote.gnsdk.Metadata.GnTypes.GnUserRegistrationType;
import com.gracenote.gnsdk.MetadataACR.IGnAcrMatch;
import com.gracenote.gnsdk.MetadataEPG.IGnTvAiring;
import com.gracenote.gnsdk.MetadataEPG.IGnTvProgram;
import com.gracenote.gnsdk.MetadataVideo.IGnVideoSeries;
import com.gracenote.gnsdk.MetadataVideo.IGnVideoTitle;
import com.gracenote.gnsdk.StorageSqlite.GnStorageSqlite;

public class GnAcrTask extends AsyncTask<Void, Void, EntourageMatchData> implements IGnAcrDelegate, IGnAudioSourceDelegate{

	private Context taskContext = null;
	private GnUser videoUser = null;
	private GnAcr acr = null;
	private EntourageMatchData entMatchData = null;
	
	private static final String 	PREFS_NAME 						= "GnEntouragePrefs";
	private static final String 	VIDEO_USER_FLAG					= "GnVideoAcrUser";
	
	private Button btnActivator = null;
	
	public GnAcrTask(Context context, Button btnBtn) {
		taskContext = context;
		btnActivator = btnBtn;		
	}
	
	public void initSDK() throws GnException {
		try {			
			String license_string = taskContext.getResources().getString(R.string.gracenote_license_string);
			String client_id = taskContext.getResources().getString(R.string.gracenote_client_id_video);
			String client_tag = taskContext.getResources().getString(R.string.gracenote_client_tag_video);
		
			videoUser = null;
			GnSdk.initialize(license_string, license_string.length());
			
			videoUser = loadOrCreateUser(client_id, client_tag, VIDEO_USER_FLAG);
			GnSdk.setDefaultUser(videoUser);
		} catch(GnException e) {
			throw e;
		} catch(Exception e) {
			throw new GnException("");
		}
	}
	
	private void shutdownSDK() {

		videoUser = null;
		acr = null;
		
		try {
			GnSdk.shutdown();
		} catch (GnException e) {
			e.printStackTrace();
		}			
	}
	
	private GnUser loadOrCreateUser(String clientId, String clientTag, String userIdFlag) 
			throws GnException 
	{
		if (clientId == null || clientId.isEmpty() || clientTag == null || clientTag.isEmpty())
		{
			throw new GnException("No client ID or tag provided.");
		}
		
		GnUser joe = null;
		SharedPreferences prefs = taskContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		String serializedUser = prefs.getString(userIdFlag, null);
		
		if (serializedUser == null) {
			joe = new GnUser(clientId, clientTag, "1.0", GnUserRegistrationType.newUser);

			serializedUser = joe.getSerializedUser();
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(userIdFlag, serializedUser);
			editor.commit();
		} else {
			joe = new GnUser(new String(serializedUser));
		}

		return joe;
	}
	
	private void startACR() throws GnException {		
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			throw new GnException("Unable to start ACR. The external storage is not available.");
		}
								
		// Create ACR object with user
		acr = new GnAcr(videoUser, this);
		acr.setPreferredMaxQueryInterval(5);
		
		GnAcrAudioConfig audioConfig = getNewAudioConfig();
		acr.audioInit(audioConfig);
		
		GnAudioSourceMic audioSource = new GnAudioSourceMic();
		audioSource.init(audioConfig, this);
		try {
			audioSource.start();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acr.doManualLookup();
	}

	private GnAcrAudioConfig getNewAudioConfig()
	{
		int sampleRate = 44100;
	
		int channel = 1;
	
		return new GnAcrAudioConfig(
			GnAcrAudioConfig.GnAcrAudioSourceMic,
			GnAcrAudioConfig.GnAudioSampleFormatPCM16, 
			sampleRate,
			channel);
	}
	
	@Override
	public void acrResultReady(GnResult result) {
		try {
			int nMatch = 0;

			GnIterator matches = result.getAcrMatch();
			while (matches.hasNext() && entMatchData == null) {
				IGnAcrMatch match = (IGnAcrMatch) matches.next();
				
				IGnTvAiring airing = match.getTvAiring();
				
				if (airing != null) {
					IGnTvProgram matchedProgram = airing.getTvProgram();
					
					if (matchedProgram != null) {
						GnEpg epgQuery = new GnEpg(videoUser);
						GnResult programResult = epgQuery.findPrograms(matchedProgram);
						GnIterator tvPrograms = programResult.getTvPrograms();
						while (tvPrograms.hasNext() && entMatchData == null) {
							IGnTvProgram program = (IGnTvProgram) tvPrograms.next();
							
							if (program != null) {
								try {
									EntourageMatchData prelimData = new EntourageMatchData();
									
									IGnVideoSeries series = program.getSeries();

									prelimData.set_seriesName(program.getTitle().getDisplay());
									
									prelimData.set_gnTui(series.getTUI());
									
									prelimData.set_seasonNumber(Integer.parseInt(program.getSeasonNumber()));
									prelimData.set_seasonEpisodeNumber(Integer.parseInt(program.getSeasonEpisode()));
									
									if (program.getSeriesEpisode() != null) {
										prelimData.set_seriesEpisodeNumber(Integer.parseInt(program.getSeriesEpisode()));
									}
									
									prelimData.set_adjustedMilliseconds(Integer.parseInt(match.getAdjustedPosition()));
									
									entMatchData = prelimData;
								} catch (Exception e) {
									Log.e("BLAH", "dayum");
								}
							}
						}
					}
				}
			}
			
			if (nMatch == 0) {
				Log.e("Blah", "so sad, too bad");
			}
		} catch (InvalidObjectException e) {
			Log.e("BLAH", e.getMessage());
		} catch (GnException e) {
			Log.e("BLAH", e.getMessage());
		} catch (Exception e) {
			Log.e("BLAH", e.getMessage());			
		}
	}

	@Override
	public void acrStatusReady(GnAcrStatus status) {
		// TODO Auto-generated method stub
		if (status.getStatusType() == GnAcrStatus.SILENCE_DETECTED) {
			Log.e("BLAH", "SILENCIO");
		} else {
		}
	}

	@Override
	protected EntourageMatchData doInBackground(Void... params) {
		try {
			initSDK();
			startACR();
			while(entMatchData == null) {
			}
		} catch (GnException e) {
			// handle exceptions
		}
		return entMatchData;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(EntourageMatchData result) {
		shutdownSDK();
		btnActivator.setEnabled(true);       
		if (result != null) {
			Log.e("SUCCESS", result.get_seriesName());
			IStuff provider = DataProvider.GetStuffProvider(taskContext.getResources().getString(R.string.data_provider));
			
			Scene sc = provider.GetScene(entMatchData);
			if (sc != null) {
				Bundle dataset = new Bundle();
				dataset.putString("sceneId", sc.get_sceneID());

				Intent showCastMembers = new Intent(taskContext.getApplicationContext(), CastMemberActivity.class);
				showCastMembers.putExtras(dataset);

				taskContext.startActivity(showCastMembers);
			
			}
		}
	}

	@Override
	public void audioBytesReady(byte[] data, int length) {
		try {

			if (acr != null)
			{
				acr.writeBytes(data, length);				
			}
			
		} catch (GnException e) {
			acr.release();
		}	
	}
	
}
