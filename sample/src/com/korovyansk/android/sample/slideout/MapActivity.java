package com.korovyansk.android.sample.slideout;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.korovyansk.android.slideout.SlideoutActivity;

public class MapActivity extends Activity{
	  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
	 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	 setUpMap();
	 //			    Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
//			        .title("Hamburg"));
//			    Marker kiel = map.addMarker(new MarkerOptions()
//			        .position(KIEL)
//			        .title("Kiel")
//			        .snippet("Kiel is cool")
//			        .icon(BitmapDescriptorFactory
//			            .fromResource(R.drawable.ic_launcher)));
//
//			    // Move the camera instantly to hamburg with a zoom of 15.
//			    map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
//
//			    // Zoom in, animating the camera.
//			    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//		
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
						SlideoutActivity.prepare(MapActivity.this,
								R.id.inner_content, width);
						startActivity(new Intent(MapActivity.this,
								MenuActivity.class));
						overridePendingTransition(0, 0);
					}
				});
	}

private void setUpMap(){
		
		map.setMyLocationEnabled(true);
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		
		String provider = locationManager.getBestProvider(criteria, true);
		
		Location currentLocation = locationManager.getLastKnownLocation(provider);
	
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		double latitude = currentLocation.getLatitude();
		
		double longitude = currentLocation.getLongitude();
		
		LatLng latlng = new LatLng(latitude,longitude);
		
		map.moveCamera(CameraUpdateFactory.newLatLng(latlng));
		
		map.animateCamera(CameraUpdateFactory.zoomTo(20));
		
		map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!"));
}
}
