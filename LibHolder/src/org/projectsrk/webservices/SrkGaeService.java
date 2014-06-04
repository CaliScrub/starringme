package org.projectsrk.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.params.*;
import org.apache.http.*;
import org.json.*;
import org.projectsrk.common.*;

import android.net.Uri;


public class SrkGaeService implements IStuff {
	
	public String GetJsonResponseString(Uri.Builder builder) {
		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpGet getRequest = new HttpGet(builder.build().toString());
			getRequest.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();

				return responseString;
			} else{
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				//..more logic
				responseString.toString();
				//Closes the connection.
				response.getEntity().getContent().close();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String GetJsonResponseString(String desiredPath) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String myUri = String.format("%s%s", "http://1.projectshoryuken.appspot.com/rest/", desiredPath);

			Uri.Builder builder = Uri.parse(myUri).buildUpon();
			return GetJsonResponseString(builder);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String GetJsonResponseString(String desiredPath, Map<String, String> queryParams) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String myUri = String.format("%s%s", "http://1.projectshoryuken.appspot.com/rest/", desiredPath);

			Uri.Builder builder = Uri.parse(myUri).buildUpon();
			
			for (String key : queryParams.keySet()) {
				builder.appendQueryParameter(key, queryParams.get(key));
			}
			return GetJsonResponseString(builder);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Series> GetSerii(){
		try {
			String responseString = GetJsonResponseString("Series");
			if (responseString != null) {
				return getSeriiFromJsonString(responseString);
			} else{
				return new ArrayList<Series>();
			}
		} catch (Exception e) {
			return new ArrayList<Series>();
		}
	}

	private List<Series> getSeriiFromJsonString(String responseString)
			throws JSONException {
		List<Series> slist = new ArrayList<Series>();

		JSONObject jobject = new JSONObject(responseString);
		JSONArray jarray = jobject.getJSONObject("list").getJSONArray("Series");
		for(int i=0; i<jarray.length(); i++) {
			JSONObject jSeries = jarray.getJSONObject(i);
			String key = jSeries.getString("key");
			String sName = jSeries.getString("series_name");
			String imgUrl = jSeries.getString("image_location");
			Series s = new Series();
			s.set_seriesID(key);
			s.set_imageloc(imgUrl);
			s.set_name(sName);
			// Next session is for optional vars
			try {
				String gnTui = jSeries.getString("gn_Tui");
				s.set_gnTui(gnTui);
			} catch (JSONException e) {
				// print some message here
			}
			slist.add(s);
		}
		return slist;
	}
	
	public Series GetSeriesBySeriesName(String seriesName) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_series_name", seriesName);
			String responseString = GetJsonResponseString("Series", queryParams);
			if (responseString != null) {
				List<Series> sList = getSeriiFromJsonString(responseString);
				if (sList != null && sList.size() > 0) {
					return sList.get(0);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Series GetSeriesByGnTui(String gnTui) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_gn_Tui", gnTui);
			String responseString = GetJsonResponseString("Series", queryParams);
			if (responseString != null) {
				List<Series> sList = getSeriiFromJsonString(responseString);
				if (sList != null && sList.size() > 0) {
					return sList.get(0);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<Episode> GetEpisodes(Series mySer) {
		return GetEpisodes(mySer.get_seriesID());
	}

	public List<Episode> GetEpisodes(String seriesID) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_series", seriesID);
			String responseString = GetJsonResponseString("Episode", queryParams);
			if (responseString != null) {
				return getEpisodeListFromJsonString(responseString);
			} else {
				return new ArrayList<Episode>();
			}
		} catch (Exception e) {
			return new ArrayList<Episode>();
		}
	}

	private List<Episode> getEpisodeListFromJsonString(String responseString)
			throws JSONException {
		{
			List<Episode> elist = new ArrayList<Episode>();

			JSONObject jobject = new JSONObject(responseString);
			JSONArray jarray = jobject.getJSONObject("list").getJSONArray("Episode");
			for(int i=0; i<jarray.length(); i++) {
				JSONObject jEpisode = jarray.getJSONObject(i);
				String key = jEpisode.getString("key");
				String eName = jEpisode.getString("episode_name");
				String eDescription = jEpisode.getString("episode_description");
				int epiNum = jEpisode.optInt("series_episode_number");
				int seasonNum = jEpisode.getInt("season_number");
				int seasonEpiNum = jEpisode.getInt("season_episode_number");
				Episode e = new Episode();
				e.set_episodeID(key);
				e.set_name(eName);
				e.set_description(eDescription);
				if (epiNum > 0) {
					e.set_seriesEpisodeNumber(epiNum);
				}
				e.set_seasonNumber(seasonNum);
				e.set_seasonEpisodeNumber(seasonEpiNum);
				elist.add(e);
			}
			
			return elist;
		}
	}

	public Episode GetEpisode(String seriesID, int seasonNumber,
			int seasonEpisode) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_series", seriesID);
			queryParams.put("feq_season_number", Integer.toString(seasonNumber));
			queryParams.put("feq_season_episode_number", Integer.toString(seasonEpisode));
			String responseString = GetJsonResponseString("Episode", queryParams);
			if (responseString != null) {
				List<Episode> eList = getEpisodeListFromJsonString(responseString);
				if (eList != null && eList.size() > 0) {
					return eList.get(0);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	private List<Scene> getSceneListFromJsonString(String responseString)
			throws JSONException {
		{
			List<Scene> scList = new ArrayList<Scene>();

			JSONObject jobject = new JSONObject(responseString);
			JSONArray jarray = jobject.getJSONObject("list").getJSONArray("Scene");
			for(int i=0; i<jarray.length(); i++) {
				JSONObject jScene = jarray.getJSONObject(i);
				String key = jScene.getString("key");
				String scDescription = jScene.getString("scene_description");
				int sceneNum = jScene.getInt("scene_number");
				int sceneStartMin = jScene.getInt("scene_start_minute");
				int sceneEndMin = jScene.getInt("scene_end_minute");
				Scene sc = new Scene();
				sc.set_sceneID(key);
				sc.set_sceneNumber(sceneNum);
				sc.set_sceneDescription(scDescription);
				sc.set_sceneStart(sceneStartMin);
				sc.set_sceneEnd(sceneEndMin);
				scList.add(sc);
			}
			
			return scList;
		}
	}

	public List<Scene> GetScenes(Episode myEp) {
		return GetScenes(myEp.get_episodeID());
	}

	public List<Scene> GetScenes(String episodeID) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_episode", episodeID);
			String responseString = GetJsonResponseString("Scene", queryParams);
			if (responseString != null) {
				return getSceneListFromJsonString(responseString);
			} else {
				return new ArrayList<Scene>();
			}
		} catch (Exception e) {
			return new ArrayList<Scene>();
		}
	}

	public boolean IsMatchWithinScene(int matchMilli, Scene sc) {
		return ((matchMilli > (1000 * 60 * sc.get_sceneStart()) &&
				matchMilli < (1000 * 60 * sc.get_sceneEnd())));
	}

	public Scene GetScene(EntourageMatchData match) {
		Series s = GetSeriesByGnTui(match.get_gnTui());
		
		if (s == null) {
			s = GetSeriesBySeriesName(match.get_seriesName());
		}
		
		if (s != null) {
			Episode e = GetEpisode(s.get_seriesID(), match.get_seasonNumber(), match.get_seasonEpisodeNumber());
			if (e != null) {
				List<Scene> sceneList = GetScenes(e.get_episodeID());
				for (Scene sc: sceneList) {
					if (IsMatchWithinScene(match.get_adjustedMilliseconds(), sc)) {
						return sc;
					}
				}
			}
		}
		return null;
	}
	
	private List<CastMember> getCastMemberListFromJsonString(String responseString)
			throws JSONException {
		{
			List<CastMember> cmList = new ArrayList<CastMember>();

			JSONObject jobject = new JSONObject(responseString);
			JSONArray jarray = jobject.getJSONObject("list").getJSONArray("CastMember");
			for(int i=0; i<jarray.length(); i++) {
				JSONObject jCastMember = jarray.getJSONObject(i);
				String key = jCastMember.getString("key");
				String charName = jCastMember.getString("character_name");
				String charDescription = jCastMember.getString("character_description");
				String imgUrl = jCastMember.getString("image_location");
				CastMember cm = new CastMember();
				cm.set_castmemberID(key);
				cm.set_characterName(charName);
				cm.set_description(charDescription);
				cm.set_imgUrl(imgUrl);
				
				cmList.add(cm);
			}
			
			return cmList;
		}
	}

	public List<CastMember> GetCastMembers(String sceneID) {
		try {
			List<Clothes> clList = GetClothes(sceneID);
			String keyString = "";
			for (Clothes cl: clList) {
				keyString += cl.get_castMemberID().concat(",");
			}
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_key", keyString);
			String responseString = GetJsonResponseString("CastMember", queryParams);
			if (responseString != null) {
				return getCastMemberListFromJsonString(responseString);
			} else {
				return new ArrayList<CastMember>();
			}
		} catch (Exception e) {
			return new ArrayList<CastMember>();
		}
	}

	public List<Clothes> GetClothes(String castMemberID, String sceneID) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_castMember", castMemberID);
			queryParams.put("feq_scene", sceneID);
			String responseString = GetJsonResponseString("Clothes", queryParams);
			if (responseString != null) {
				return getClothesListFromJsonString(responseString);
			} else {
				return new ArrayList<Clothes>();
			}
		} catch (Exception e) {
			return new ArrayList<Clothes>();
		}
	}
	
	private List<Clothes> GetClothes(String sceneID) {
		try {
			Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.put("feq_scene", sceneID);
			String responseString = GetJsonResponseString("Clothes", queryParams);
			if (responseString != null) {
				return getClothesListFromJsonString(responseString);
			} else {
				return new ArrayList<Clothes>();
			}
		} catch (Exception e) {
			return new ArrayList<Clothes>();
		}
	}

	private List<Clothes> getClothesListFromJsonString(String responseString) throws JSONException {
		{
			List<Clothes> clothesList = new ArrayList<Clothes>();

			JSONObject jobject = new JSONObject(responseString);
			JSONArray jarray = jobject.getJSONObject("list").getJSONArray("Clothes");
			for(int i=0; i<jarray.length(); i++) {
				JSONObject jClothes = jarray.getJSONObject(i);
				String key = jClothes.getString("key");
				String clDesc = jClothes.getString("clothes_description");
				String imgUrl = jClothes.getString("clothes_imgUrl");
				String link = jClothes.getString("clothes_link");
				String searchTerms = jClothes.getString("search_terms");
				String castMember = jClothes.getString("castMember");
				
				Clothes cl = new Clothes();
				cl.set_clothesID(key);
				cl.set_description(clDesc);
				cl.set_link(link);
				cl.set_imgUrl(imgUrl);
				cl.set_castMemberID(castMember);
				cl.set_searchTerms(searchTerms);
				clothesList.add(cl);
			}
			
			return clothesList;
		}
	}
}
