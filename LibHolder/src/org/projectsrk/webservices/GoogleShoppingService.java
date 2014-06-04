package org.projectsrk.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.params.*;
import org.apache.http.*;
import org.json.*;
import org.projectsrk.common.Clothes;

import android.net.Uri;

public class GoogleShoppingService {
	
	static private String androidKey = "AIzaSyAvij2jaug5jgPKZeaMtj9TbDDDrCakPMU";
	static private String googleShoppingSearchUrl = "https://www.googleapis.com/shopping/search/v1/public/products";
	static private String stupidUrl = "https://www.googleapis.com/shopping/search/v1/public/products?key=AIzaSyAvij2jaug5jgPKZeaMtj9TbDDDrCakPMU&alt=atom&country=US&q=white+polo+shirt";
	
	public List<Clothes> Search(List<String> searchItems) throws ClientProtocolException, IOException, JSONException {
		String searchParam = BuildSearchParam(searchItems);
		
		return Search(searchParam);
	}
	
	public List<Clothes> Search(String searchParam) throws ClientProtocolException, IOException, JSONException {
		HttpClient httpclient = new DefaultHttpClient();
		
		Uri.Builder searchBuilder = Uri.parse(googleShoppingSearchUrl).buildUpon()
				.appendQueryParameter("key", androidKey)
				.appendQueryParameter("alt", "json")
				.appendQueryParameter("country", "US")
				.appendQueryParameter("q", searchParam);
		
		HttpGet getRequest = new HttpGet(searchBuilder.build().toString());
		
	    HttpResponse response = httpclient.execute(getRequest);
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	    	List<Clothes> clist = new ArrayList<Clothes>();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        String responseString = out.toString();
	        //..more logic
	        JSONObject jobject = new JSONObject(responseString);
	        JSONArray jarray = jobject.getJSONArray("items");
	        for(int i=0; i<jarray.length(); i++) {
	        	JSONObject product = jarray.getJSONObject(i).getJSONObject("product");
	            String description = product.getString("title");
	            String link = product.getString("link");
	            String imgUrl = product.getJSONArray("images").getJSONObject(0).getString("link");
	            Clothes c = new Clothes("0", "0", "0", description, "", imgUrl, link, "", "");
	            clist.add(c);
	        }
	        return clist;
	    } else{
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        String responseString = out.toString();
	        //..more logic
	        responseString.toString();
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
	}

	private String BuildSearchParam(List<String> searchItems) {
		String result = "";
		
		for (String s: searchItems) {
			result += s + "+";
		}
		return result;
	}
	
	
}
