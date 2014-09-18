package com.example.gombelappv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	ImageButton info, event, lokasi, chat, user, saran;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		info = (ImageButton) findViewById(R.id.imageButtonInfoMain);	
		info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(v.getContext(), tab_info_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
				
			}
		});
		
		event = (ImageButton) findViewById(R.id.imageButtonEventMain);	
		event.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), tab_event_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			}
		});
		
		
		user = (ImageButton) findViewById(R.id.imageButtonUserMain);	
		user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), tab_user_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
			}
		});
		
		/*
		 
		lokasi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), tab_lokasi_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			}
		});
		*/
		saran = (ImageButton) findViewById(R.id.imageButtonSaranMain);	
		saran.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), tab_saran_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
			}
		});
	}
}
