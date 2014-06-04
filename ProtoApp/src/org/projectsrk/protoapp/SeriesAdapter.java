package org.projectsrk.protoapp;

import java.util.List;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;

import android.content.*;
import android.net.Uri;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SeriesAdapter extends BaseAdapter {

	private Context context;
	private final List<Series> seriesList;
 
	public SeriesAdapter(Context context, List<Series> seriesList) {
		this.context = context;
		this.seriesList = seriesList;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.griditemlayout, null);
 
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(seriesList.get(position).get_name());
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
			
			gridView.setTag(seriesList.get(position).get_seriesID());
 
			LoadThumbnailImageTask thumbTask = new LoadThumbnailImageTask(imageView, Uri.parse(seriesList.get(position).get_imageloc()));
			thumbTask.execute();
			
			gridView.setOnClickListener(buttonClickListener);
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return seriesList.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}

	
    public OnClickListener buttonClickListener = new OnClickListener()
    {
        public void onClick(View view)
        {
            Bundle dataset = new Bundle();
            dataset.putString("seriesId", view.getTag().toString());

            Intent showEpisodes = new Intent(context.getApplicationContext(), EpisodeActivity.class);
            showEpisodes.putExtras(dataset);

            context.startActivity(showEpisodes);
        }
    };
}