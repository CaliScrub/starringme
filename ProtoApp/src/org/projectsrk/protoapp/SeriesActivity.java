package org.projectsrk.protoapp;

import java.util.*;

import org.projectsrk.common.IStuff;
import org.projectsrk.common.Series;
import org.projectsrk.datalayer.MockData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class SeriesActivity extends Activity {
	
	GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GetSeries seriesAsync = new GetSeries();
        seriesAsync.execute();   
    }
    
    private class GetSeries extends AsyncTask<Void, Void, List<Series>>
    {
        protected List<Series> doInBackground(Void... arg0) 
        {
        	List<Series> sList = new ArrayList<Series>();

        	IStuff provider = DataProvider.GetStuffProvider(getResources().getString(R.string.data_provider));
			sList = provider.GetSerii();
        	
            return sList;  
        }

        @Override
        protected void onPostExecute(List<Series> result)
        {
        	gridView = (GridView) findViewById(R.id.gridView1);
            gridView.setAdapter(new SeriesAdapter(SeriesActivity.this, result));     
        }
    }
}
