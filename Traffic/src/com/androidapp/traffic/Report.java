package com.androidapp.traffic;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Report extends Activity
{
	
	
	
	Spinner sp1;
	Spinner sp2;
	ImageView iv;
	
	
	//String str3="";
	

	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        
        
        
      
        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        iv = (ImageView) findViewById(R.id.imageView1);
        
       
        
        
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timearray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        
        
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.weatherarray, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        
        
        iv.setImageResource(android.R.color.transparent);
        
        
        
        
        
        
    }
	
	/*
	
	class getImage extends AsyncTask<String, Void, String> {

	    private Exception exception;

	    protected String doInBackground(String... urls) {
	    	//Zip code
	    	final EditText nameField = (EditText) findViewById(R.id.editText1);  
	        String zipcode = nameField.getText().toString(); 
	        
	        //Time
	        final Spinner feedbackSpinner = (Spinner) findViewById(R.id.spinner2);  
	        String time = feedbackSpinner.getSelectedItem().toString();
	        
	    	
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = AndroidHttpClient.newInstance("Android");
	        HttpPost httppost = new HttpPost("http://traffichistory.co.nf/mapImaging.php");
	        

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("zipcode", zipcode));
	            nameValuePairs.add(new BasicNameValuePair("time", time));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity result = response.getEntity();
	            
	            InputStream is = result.getContent();
	       
	            byte[] data = new byte[256];

	            StringBuffer buffer = new StringBuffer();
	            int len = 0;
	           
	            while (-1 != (len = is.read(data))) {
	                    buffer.append(new String(data, 0, len));
	            }

	            is.close();
	            
	            String str = buffer.toString();
	            
	            String str2 = str.substring(24);
	            
	            int temp = str2.indexOf("'");
	            
	            str3 = str2.substring(0,temp);
	            
	            
	      
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }
			return null;
	    }

	    protected void onPostExecute(String url) {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
	    }
	 }
	

	*/

	






public void postData(View v) 
{
	
	
	//Zip code
	final EditText nameField = (EditText) findViewById(R.id.editText1);  
    String zipcode = nameField.getText().toString(); 
    
    //Time
    final Spinner feedbackSpinner = (Spinner) findViewById(R.id.spinner1);  
    String time = feedbackSpinner.getSelectedItem().toString();
    
    if(time=="12AM-3AM")
    {
    	time="0";
    }
    else if(time=="3AM-6AM")
    {
    	time="1";
    }
    else if(time=="6AM-9AM")
    {
    	time="2";
    }
    else if(time=="9AM-12PM")
    {
    	time="3";
    }
    else if(time=="12PM-3PM")
    {
    	time="4";
    }
    else if(time=="3PM-6PM")
    {
    	time="5";
    }
    else if(time=="6PM-9PM")
    {
    	time="6";
    }
    else if(time=="9PM-12PM")
    {
    	time="7";
    }
    else
    {
    	time="0";
    }
    
    
    
    
    
    
    //Weather
    final Spinner feedbackSpinner2 = (Spinner) findViewById(R.id.spinner2);  
    String weather = feedbackSpinner2.getSelectedItem().toString();
    
 
    
	
    // Create a new HttpClient and Post Header
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://traffichistory.co.nf/mapImaging.php");

    try {
        // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("zipcode", zipcode));
        nameValuePairs.add(new BasicNameValuePair("time", time));
        nameValuePairs.add(new BasicNameValuePair("weather", weather));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Execute HTTP Post Request
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity result = response.getEntity();
        
        InputStream is = result.getContent();
   
        byte[] data = new byte[256];

        StringBuffer buffer = new StringBuffer();
        int len = 0;
       
        while (-1 != (len = is.read(data))) {
                buffer.append(new String(data, 0, len));
        }

        is.close();
        
        String str = buffer.toString();
        
        int temp1 = str.indexOf("h");
        
        String str2 = str.substring(temp1+1);
        
        int temp2 = str2.indexOf("h");
        
        String str3 = str2.substring(temp2);
        
        int temp3 = str3.indexOf("'");
        
        String str4 = str3.substring(0,temp3);
        
        

	//String s1="";
	//new getImage().execute(s1,null,str3);
	
        
        try {
        	  ImageView i = (ImageView)findViewById(R.id.imageView1);
        	  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(str4).getContent());
        	  i.setImageBitmap(bitmap); 
        	} catch (MalformedURLException e) {
        	  e.printStackTrace();
        	} catch (IOException e) {
        	  e.printStackTrace();
        	}
        	
        	
        
      
        
       
        
    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
    } catch (IOException e) {
        // TODO Auto-generated catch block
    }
    
    
    
    
    
	
    
} 


/*
public void postData2(View v){
	
	class getImage extends AsyncTask<String, Void, String> {

	    private Exception exception;

	    protected String doInBackground(String... urls) {
	    	//Zip code
	    	final EditText nameField = (EditText) findViewById(R.id.editText1);  
	        String zipcode = nameField.getText().toString(); 
	        
	        //Time
	        final Spinner feedbackSpinner = (Spinner) findViewById(R.id.spinner2);  
	        String time = feedbackSpinner.getSelectedItem().toString();
	        
	    	
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://traffichistory.co.nf/mapImaging.php");
	        

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("zipcode", zipcode));
	            nameValuePairs.add(new BasicNameValuePair("time", time));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity result = response.getEntity();
	            
	            InputStream is = result.getContent();
	       
	            byte[] data = new byte[256];

	            StringBuffer buffer = new StringBuffer();
	            int len = 0;
	           
	            while (-1 != (len = is.read(data))) {
	                    buffer.append(new String(data, 0, len));
	            }

	            is.close();
	            
	            String str = buffer.toString();
	            
	            String str2 = str.substring(24);
	            
	            int temp = str2.indexOf("'");
	            
	            String str3 = str2.substring(0,temp);
	            
	            try {
	          	  ImageView i = (ImageView)findViewById(R.id.imageView1);
	          	  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(str3).getContent());
	          	  i.setImageBitmap(bitmap); 
	          	} catch (MalformedURLException e) {
	          	  e.printStackTrace();
	          	} catch (IOException e) {
	          	  e.printStackTrace();
	          	}
	            
	      
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }
			return null;
	    }

	    protected void onPostExecute(String url) {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
	    }
	 }
	
}

*/





}