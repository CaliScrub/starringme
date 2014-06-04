package org.projectsrk.protoapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;
import org.projectsrk.webservices.GoogleShoppingService;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ClothesAdapter extends BaseAdapter {
	
	private Context context;
	private final List<Clothes> clothesList;

	public ClothesAdapter(Context context, List<Clothes> clothesList) {
		this.context = context;
		this.clothesList = clothesList;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View listView;
 
		if (convertView == null) {
 
			listView = new View(context);
			
			listView = inflater.inflate(R.layout.clothesdisplay, null);
			 
			// set value into textview
			TextView textView = (TextView) listView
					.findViewById(R.id.clothes_description);
			textView.setText(clothesList.get(position).get_description());
			textView.setTag(clothesList.get(position).get_link());
			textView.setOnClickListener(buttonClickListener);
			
			ImageView imageView = (ImageView) listView.findViewById(R.id.clothes_image);
			
			imageView.setTag(clothesList.get(position).get_link());
			imageView.setOnClickListener(buttonClickListener);
			
			LoadThumbnailImageTask thumbTask = new LoadThumbnailImageTask(imageView, Uri.parse(clothesList.get(position).get_imgUrl()));
			thumbTask.execute();
			
			TextView similarItemsTextView = (TextView) listView.findViewById(R.id.clothes_othertext);
			similarItemsTextView.setText("See similar items");
			
			similarItemsTextView.setTag(clothesList.get(position).get_searchTerms());
			similarItemsTextView.setOnClickListener(similarItemsClickListener);
			
 		} else {
			listView = (View) convertView;
		}
 
		return listView;
	}
	
	public int getCount() {
		return clothesList.size();
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
            String link = view.getTag().toString();
            
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        	context.startActivity(browserIntent);
        }
    };
    
    public OnClickListener similarItemsClickListener = new OnClickListener()
    {
        public void onClick(View view)
        {        	
            Bundle dataset = new Bundle();
            dataset.putString("searchTerms", view.getTag().toString());

            Intent showSimilarClothes = new Intent(context.getApplicationContext(), SimilarClothesActivity.class);
            showSimilarClothes.putExtras(dataset);

            context.startActivity(showSimilarClothes);
        }
    };
}
