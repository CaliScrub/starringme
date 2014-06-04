package org.projectsrk.protoapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadThumbnailImageTask extends AsyncTask<Void, Void, Bitmap>
{
	private ImageView imgView;
	private Uri imgUri;
	
	public LoadThumbnailImageTask(ImageView view, Uri uri) {
		imgView = view;
		imgUri = uri;
	}
    protected Bitmap doInBackground(Void... params) 
    {
    	 Bitmap bm = null;
    	 try {
    	        URL aURL = new URL(imgUri.toString());
    	        URLConnection conn = aURL.openConnection();
    	        conn.connect();
    	        InputStream is = conn.getInputStream();
    	        BufferedInputStream bis = new BufferedInputStream(is);
    	        bm = BitmapFactory.decodeStream(bis);
    	        bis.close();
    	        is.close();
    	    } catch (IOException e) {
    	        android.util.Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
    	    } 
    	 return bm;
    }

    @Override
    protected void onPostExecute(Bitmap result)
    {
    	if (result != null) {
    		imgView.setImageBitmap(result);
    	}
    }
}