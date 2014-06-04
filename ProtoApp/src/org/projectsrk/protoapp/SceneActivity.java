package org.projectsrk.protoapp;

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

public class SceneActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodelist);
        
        Intent intent = getIntent();
        String episodeId = intent.getStringExtra("episodeId");
        
        GetScenes sceneAsync = new GetScenes();
        sceneAsync.execute(episodeId);   
    }
    
    private class GetScenes extends AsyncTask<String, Void, List<Scene>>
    {
        protected List<Scene> doInBackground(String... episodeId) 
        {
        	IStuff provider = DataProvider.GetStuffProvider(getResources().getString(R.string.data_provider));
        	List<Scene> scList = provider.GetScenes(episodeId[0]);
        	
            return scList;  
        }

        @Override
        protected void onPostExecute(List<Scene> result)
        {
        	ListView lview = (ListView) findViewById(R.id.eplist);
            lview.setAdapter(new SceneAdapter(SceneActivity.this, result));     
        }
    }    
}
