package org.projectsrk.protoapp;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CastMemberAdapter extends BaseAdapter {
	
	private Context context;
	private final List<CastMember> castMemberList;
	private String sceneId;

	public CastMemberAdapter(Context context, List<CastMember> castMemberList, String sceneId) {
		this.context = context;
		this.castMemberList = castMemberList;
		this.sceneId = sceneId;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {			
			gridView = new View(context);
			 

			Map<String, String> sceneCharTag = new HashMap<String, String>();
			sceneCharTag.put("sceneId", sceneId);
			sceneCharTag.put("castMemberId", castMemberList.get(position).get_castmemberID());
			
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.griditemlayout, null);
 
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(castMemberList.get(position).get_characterName() + ": " + castMemberList.get(position).get_description());
			
			textView.setTag(sceneCharTag);
			textView.setOnClickListener(buttonClickListener);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
			
			LoadThumbnailImageTask thumbTask = new LoadThumbnailImageTask(imageView, Uri.parse(castMemberList.get(position).get_imgUrl()));
			thumbTask.execute();
			
			imageView.setTag(sceneCharTag);
			imageView.setOnClickListener(buttonClickListener);
						
 		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
	
	public int getCount() {
		return castMemberList.size();
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
            Map<String, String> sceneCharTag = (Map<String, String>) view.getTag();
            dataset.putString("castMemberId", sceneCharTag.get("castMemberId"));
            dataset.putString("sceneId", sceneCharTag.get("sceneId"));

            Intent showClothes = new Intent(context.getApplicationContext(), ClothesActivity.class);
            showClothes.putExtras(dataset);

            context.startActivity(showClothes);
        }
    };
}
