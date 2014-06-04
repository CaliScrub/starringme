package org.projectsrk.protoapp;

import java.util.*;

import org.projectsrk.common.Series;
import org.projectsrk.datalayer.MockData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private Button btnDetectShow;
	private Button btnBrowseSeries;
	private Button btnTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_choice);
        
        btnDetectShow = (Button) findViewById(R.id.btnDetectShow);
        btnDetectShow.setEnabled(true);       
        
        btnDetectShow.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
            	Button btnActivator = MainActivity.this.btnDetectShow;
                GnAcrTask entourageTask = new GnAcrTask(MainActivity.this, btnActivator);
                entourageTask.execute();
                btnActivator.setEnabled(false);
            }
        });
        
        
        btnBrowseSeries = (Button) findViewById(R.id.btnBrowseSeries);
        btnBrowseSeries.setEnabled(true);
        
        btnBrowseSeries.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                Intent showSeries = new Intent(getApplicationContext(), SeriesActivity.class);

                startActivity(showSeries);
            }
        });        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
