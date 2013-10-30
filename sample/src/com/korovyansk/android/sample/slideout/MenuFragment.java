package com.korovyansk.android.sample.slideout;

import com.korovyansk.android.slideout.SlideoutActivity;

import android.R.mipmap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MenuFragment extends ListFragment {
	private String url;
	private Intent intent;
	private String menuFlag;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, new String[] { "Google Webview", "ICTV Webview", " Notifications", " Map", " Fifth", " Sixth","Seventh","Eighth","nineth","tenth"}));
		getListView().setCacheColorHint(0);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		  switch(position){
		    case 0:
		    	intent = new Intent(getActivity().getApplicationContext(), TopActivity.class);
		    	break;
		    case 1:
		    	intent = new Intent(getActivity().getApplicationContext(), TopActivity.class);
		      break;
		    case 2:
		    	intent = new Intent(getActivity().getApplicationContext(), NotificationActivity.class);
		      break;
		    case 3:
		    	intent = new Intent(getActivity().getApplicationContext(), MapActivity.class);
		      break;
		    case 4:
		    	url = "http://www.nowplanet.tv/index.html";
		      break;
		    case 5:
		    	url = "http://www.facebook.com";
		      break;
		    case 6:
		    	url = "http://www.nowplanet.tv/index.html";
		      break;
		    case 7:
		    	url = "http://www.nowplanet.tv/index.html";
		      break;
		    case 8:
		    	url = "http://www.nowplanet.tv/index.html";
		      break;
		    case 9:
		    	url = "http://www.nowplanet.tv/index.html";
		      break;
		    default:
		    	url = "http://www.nowplanet.tv/index.html"; 	  
		
	}
		 
		  //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		  // startActivity(intent);
//		  if()
		  ((MenuActivity)getActivity()).getSlideoutHelper().close();
		  intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		  startActivity(intent);
			
//			overridePendingTransition(0, 0);
	}
	
	public String getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(String menuStr) {
        this.menuFlag = menuStr;
    }

}
