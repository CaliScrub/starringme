package org.projectsrk.protoapp;

import java.util.List;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;

import android.content.*;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private final List<Series> seriesList;
 
	public ImageAdapter(Context context, List<Series> seriesList) {
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
 
			//String mobile = mobileValues[position];
			if (seriesList.get(position).get_imageloc() == "simpsons") {
				imageView.setImageResource(R.drawable.simpsons);
			} else if (seriesList.get(position).get_imageloc() == "familyguy") {
				imageView.setImageResource(R.drawable.familyguy);
			} 
			
 
//			if (mobile.equals("Windows")) {
//				imageView.setImageResource(R.drawable.windows_logo);
//			} else if (mobile.equals("iOS")) {
//				imageView.setImageResource(R.drawable.ios_logo);
//			} else if (mobile.equals("Blackberry")) {
//				imageView.setImageResource(R.drawable.blackberry_logo);
//			} else {
//				imageView.setImageResource(R.drawable.android_logo);
//			}
			
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
            dataset.putInt("seriesId", Integer.parseInt(view.getTag().toString()));

            Intent showEpisodes = new Intent(context.getApplicationContext(), EpisodeActivity.class);
            showEpisodes.putExtras(dataset);

            context.startActivity(showEpisodes);
        }
    };
}