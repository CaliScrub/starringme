package org.projectsrk.protoapp;

import java.util.List;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EpisodeAdapter extends BaseAdapter {
	
	private Context context;
	private final List<Episode> episodeList;

	public EpisodeAdapter(Context context, List<Episode> episodeList) {
		this.context = context;
		this.episodeList = episodeList;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View listView;
 
		if (convertView == null) {
 
			listView = new View(context);
			
			listView = inflater.inflate(R.layout.listitemlayout, null);
			 
			// set value into textview
			TextView textView = (TextView) listView
					.findViewById(R.id.listitemtext);
			textView.setText("Episode " + String.valueOf(episodeList.get(position).get_seriesEpisodeNumber()) + ": " + episodeList.get(position).get_name());
 
			textView.setTag(episodeList.get(position).get_episodeID());
			textView.setOnClickListener(buttonClickListener);			
 		} else {
			listView = (View) convertView;
		}
 
		return listView;
	}
	
	public int getCount() {
		return episodeList.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

    public OnClickListener buttonClickListener = new OnClickListener()
    {
        public void onClick(View view)
        {
            Bundle dataset = new Bundle();
            dataset.putString("episodeId", view.getTag().toString());

            Intent showScenes = new Intent(context.getApplicationContext(), SceneActivity.class);
            showScenes.putExtras(dataset);

            context.startActivity(showScenes);
        }
    };
}
