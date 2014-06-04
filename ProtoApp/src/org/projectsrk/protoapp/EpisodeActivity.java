package org.projectsrk.protoapp;

import java.util.ArrayList;
import java.util.List;

import org.projectsrk.datalayer.MockData;
import org.projectsrk.common.*;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class EpisodeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodelist);
        
        Intent intent = getIntent();
        String seriesId = intent.getStringExtra("seriesId");
        
        GetEpisodes epiAsync = new GetEpisodes();
        epiAsync.execute(seriesId);   
    }
    
    private class GetEpisodes extends AsyncTask<String, Void, List<Episode>>
    {
        protected List<Episode> doInBackground(String... seriesId) 
        {
        	IStuff provider = DataProvider.GetStuffProvider(getResources().getString(R.string.data_provider));
        	List<Episode> eList = provider.GetEpisodes(seriesId[0]);
        	
            return eList;  
        }

        @Override
        protected void onPostExecute(List<Episode> result)
        {
        	ListView lview = (ListView) findViewById(R.id.eplist);
            lview.setAdapter(new EpisodeAdapter(EpisodeActivity.this, result));     
        }
    }    
}
