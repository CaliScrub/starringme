package org.projectsrk.protoapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
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

public class SimilarClothesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodelist);
        
        Intent intent = getIntent();
        String searchTerms = intent.getStringExtra("searchTerms");
        
        GetSimilarClothes simClothes = new GetSimilarClothes();
        simClothes.execute(searchTerms);
    }
    
    private class GetSimilarClothes extends AsyncTask<String, Void, List<Clothes>>
    {
        protected List<Clothes> doInBackground(String... searchTerms) 
        {
        	List<Clothes> clothesList = new ArrayList<Clothes>();

        	GoogleShoppingService g = new GoogleShoppingService();
        	try {
				clothesList = g.Search(searchTerms[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	
            return clothesList;  
        }

        @Override
        protected void onPostExecute(List<Clothes> result)
        {
            ListView lview = (ListView) findViewById(R.id.eplist);
            lview.setAdapter(new SimilarClothesAdapter(SimilarClothesActivity.this, result));
        }
    }
}
