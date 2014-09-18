package com.example.gombelappv1;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class tab_lokasi_activity extends FragmentActivity{
	private GoogleMap googleMap;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_lokasi);
		
		try{
		      _SetMap();
		 }catch(Exception e){
		    e.printStackTrace();
		 }
		
		TabActivity.getInstance().connectToActivity(this);
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		//hapus komen biar bisa jalan
        //TA.pressButtonById(R.id.imageButtonlokasi, true);
	}
	private void _SetMap() {
	    if (googleMap == null)
	        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
}
