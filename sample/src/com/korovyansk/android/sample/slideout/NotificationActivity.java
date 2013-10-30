package com.korovyansk.android.sample.slideout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.parse.signpost.http.HttpResponse;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends Activity {
	private ProgressDialog mProgressDialog;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private int FM_NOTIFICATION_ID = 0;
	RequestTask task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String message = extras != null ? extras.getString("com.parse.Data")
				: "";
		JSONObject jObject;
		try {
			jObject = new JSONObject(message);
			Log.d("MyApp", jObject.getString("alert"));

			Toast toast = Toast.makeText(NotificationActivity.this,
					jObject.getString("alert"), Toast.LENGTH_LONG);
			toast.show();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// getActionBar().hide();
		}
		findViewById(R.id.sample_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 40, getResources()
										.getDisplayMetrics());
						SlideoutActivity.prepare(NotificationActivity.this,
								R.id.inner_content, width);
						startActivity(new Intent(NotificationActivity.this,
								MenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});

		Button buyBtn = (Button) findViewById(R.id.buyBtn);

		buyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FM_NOTIFICATION_ID += 1;
			System.out.println("FM_NOTIFICATION_ID : " + FM_NOTIFICATION_ID);
				   executeAsyncTask();
			}
		});

}

	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DIALOG_DOWNLOAD_PROGRESS:
	        mProgressDialog = new ProgressDialog(this);
	        mProgressDialog.setMessage("waiting 5 minutes..");
	        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        mProgressDialog.setCancelable(false);
	        mProgressDialog.show();
	        return mProgressDialog;
	    default:
	    return null;
	    }
	}
	
	public void executeAsyncTask(){
	    new RequestTask().execute();
	}
	
	private class RequestTask extends AsyncTask<Void, View, View>{
		
		 


		@Override
		    protected void onPreExecute() {
		        // TODO Auto-generated method stub
		        super.onPreExecute();
		       // mProgressDialog.setIndeterminate(false);
//		        mProgressDialog  = new ProgressDialog (NotificationActivity.this);
//		        mProgressDialog.setMessage("Loading...");
//		        mProgressDialog.show();
		    }
		
	
	    @Override
	    protected View doInBackground(Void... params) {

	        Log.i("CustomAsyncTask", "doInBackground");
//	        HttpClient httpclient = new DefaultHttpClient();
//	        HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	        try {
//	            // Add your data
//	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//	            nameValuePairs.add(new BasicNameValuePair("id", "12345"));
//	            nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
//	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//	            // Execute HTTP Post Request
//	            HttpResponse response = httpclient.execute(httppost);
	        	HttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet("http://10.0.0.43/ycb");
	            client.execute(request);
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }

	        return null;
	    }
	    

	    @Override
	    protected void onPostExecute(View result) {
	       
//	        completed[fileNum] = true;
//	        Log.e("PDFReaderAct",String.valueOf(fileNum)+" download complete");
	       // mProgressDialog.dismiss();        
	        
	    	start(result);
	    	super.onPostExecute(result);
	    }
	    
	}
	
	 public void start(View view) {
		 Intent resultIntent = new Intent(getBaseContext(), NotificationActivity.class);
	    	PendingIntent resultPendingIntent =
	    	    PendingIntent.getActivity(
	    	    this,
	    	    0,
	    	    resultIntent,
	    	    PendingIntent.FLAG_UPDATE_CURRENT
	    	);

	    	Uri notiSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	    	
	    	Notification.Builder mBuilder = new Notification.Builder(
	    	        getApplicationContext()).setSmallIcon(R.drawable.ic_launcher)
	    	        .setContentTitle("My notification")
	    	        .setContentText("Hello World 1!")
	    	        .setSound(notiSound)
	    	        .setVibrate(new long[] { 1000, 1000 })
	    	        .setContentIntent(resultPendingIntent);
	    	
	    	mBuilder.setAutoCancel(true);
	    	
	    	mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;

	    	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    	manager.notify(FM_NOTIFICATION_ID, mBuilder.build());
	    }

}
