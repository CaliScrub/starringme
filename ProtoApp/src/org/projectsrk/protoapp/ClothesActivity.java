package org.projectsrk.protoapp;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.projectsrk.datalayer.MockData;
import org.projectsrk.common.*;
import org.projectsrk.webservices.*;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class ClothesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodelist);
        
        Intent intent = getIntent();
        String sceneID = intent.getStringExtra("sceneId");
        String castMemberID = intent.getStringExtra("castMemberId");
        
        GetClothes clothesAsync = new GetClothes();
        clothesAsync.execute(castMemberID, sceneID);
    }
    
    private class GetClothes extends AsyncTask<String, Void, List<Clothes>>
    {
    	private String castMemberId;
    	private String sceneId;
        protected List<Clothes> doInBackground(String... args) 
        {
        	this.castMemberId = args[0];
        	this.sceneId = args[1];
        	IStuff provider = DataProvider.GetStuffProvider(getResources().getString(R.string.data_provider));
        	List<Clothes> clothesList = provider.GetClothes(castMemberId, sceneId);
        	
            return clothesList;  
        }

        @Override
        protected void onPostExecute(List<Clothes> result)
        {
        	ListView lview = (ListView) findViewById(R.id.eplist);
            lview.setAdapter(new ClothesAdapter(ClothesActivity.this, result));     
        }
    }  
}
