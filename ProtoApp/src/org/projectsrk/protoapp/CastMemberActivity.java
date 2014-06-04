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

public class CastMemberActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodelist);
        
        Intent intent = getIntent();
        String sceneId = intent.getStringExtra("sceneId");
        
        GetCastMembers cmAsync = new GetCastMembers();
        cmAsync.execute(sceneId);
    }
    
    private class GetCastMembers extends AsyncTask<String, Void, List<CastMember>>
    {
    	private String sceneId;
        protected List<CastMember> doInBackground(String... sceneId) 
        {
        	this.sceneId = sceneId[0];
        	IStuff provider = DataProvider.GetStuffProvider(getResources().getString(R.string.data_provider));
        	List<CastMember> cmList = provider.GetCastMembers(this.sceneId);
        	
            return cmList;  
        }

        @Override
        protected void onPostExecute(List<CastMember> result)
        {
        	ListView lview = (ListView) findViewById(R.id.eplist);
            lview.setAdapter(new CastMemberAdapter(CastMemberActivity.this, result, this.sceneId));     
        }
    }    
}
