package com.korovyansk.android.sample.slideout;

import android.annotation.TargetApi;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.korovyansk.android.slideout.SlideoutActivity;

public class TopActivity extends Activity {

	static WebView webview;
	private String Url = "http://www.google.com";
	private String menuFlag;
	ParseUser user ;
	@TargetApi(11)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample);

		 Parse.initialize(this, "WXaEDiJ7XebG5gYlDjQeJ8qWDGBTTGs1TkNhInPi", "gSKXzBsPf5wfXqOnVprPWZnySbfYhObvC7X8rpf5"); 
		 PushService.setDefaultPushCallback(this, NotificationActivity.class);
		 ParseInstallation.getCurrentInstallation().saveInBackground();
		 ParseAnalytics.trackAppOpened(getIntent());
		 
		 ParseInstallation install = ParseInstallation.getCurrentInstallation();

		 install.add("channels", "user2_channel");

		 install.saveInBackground();
		 
		 
//		 ParseUser user = new ParseQ 
//	     ParseUser.logOut();
//		 user = new ParseUser();
//		 user.setUsername("sonydevice2");
//		 user.setPassword("123456");
		  
//		 ParseInstallation.getCurrentInstallation().saveInBackground();
//		 ParseAnalytics.trackAppOpened(getIntent());

//		 ParseObject testObject = new ParseObject("TestObject");
//		 testObject.put("foo", "bar");
//		 testObject.saveInBackground();
		// 	 PushService.subscribe(this, "user_sonydevice2",TopActivity.class);
	//	PushService.unsubscribe(this, "user_sonydevice2");
//		  user = new ParseUser();
//		  user.setUsername("sonydevice2");
//		  user.setPassword("123456");
//		  
//		  user.signUpInBackground(new SignUpCallback() {
//			
//			@Override
//			public void done(ParseException e) {
//				// TODO Auto-generated method stub
//		          if (e == null) {
////		              Intent intent = new Intent(RegisterActivity.this, TodoActivity.class);
////		              startActivity(intent);
////		              finish();
//		        		System.out.println("done");
//		        
//		       			
//		          } else {
////		               Sign up didn't succeed. Look at the ParseException
////		               to figure out what went wrong
////		              v.setEnabled(true);
//		        		System.out.println("not done");
//		          }
//
//			}
//		});
		
//ParseUser.logInInBackground("sonydevice2", "123456", new LogInCallback() {
//	
//	@Override
//	public void done(ParseUser user, ParseException e) {
//		// TODO Auto-generated method stub
//		System.out.println("OBJECT ID : " + ParseUser.getCurrentUser().getUsername());
//	
//	}
//});
//
//ParseUser.logOut();
//		 
	//	PushService.unsubscribe(this, "user_5neUsLQZnZ");
	//	System.out.println("OBJECT ID : " + ParseUser.getCurrentUser().getUsername());
		//ParseRelation relation = user.getRelation("installation") ;
		//ParseObject obj = relation.getQuery().get(ParseInstallation.getCurrentInstallation().getObjectId());
		//ParseInstallation.getCurrentInstallation().getList("channels");

		webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new WebViewClient());

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//			getActionBar().hide();
		}
		findViewById(R.id.sample_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						int width = (int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 40, getResources()
										.getDisplayMetrics());
						SlideoutActivity.prepare(TopActivity.this,
								R.id.inner_content, width);
						startActivity(new Intent(TopActivity.this,
								MenuActivity.class));
						overridePendingTransition(-3, 0);
						
					}
				});
		
		findViewById(R.id.sample_button_2).setOnClickListener(
				new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		openURL(Url);
	}

	/** Opens the URL in a browser */
	private void openURL(String url) {
		webview.loadUrl(url);
		webview.requestFocus();
	}
	
	public void createTask(View v) {
		Task t = new Task();
		t.setACL(new ParseACL(ParseUser.getCurrentUser()));
		t.setUser(ParseUser.getCurrentUser());
		t.setDescription("test desc");
		t.setCompleted(false);
		t.saveEventually();
		
	} 

	
	
}
