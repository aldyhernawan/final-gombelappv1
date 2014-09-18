package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.widget.ImageButton;;

public class TabActivity {

	protected List<ImageButton> m_arrButton;
	protected int m_iCurrentButtonId= -1;
	protected Activity m_currentActivity = null;
	
	public void connectToActivity(Activity activity)
	{
		m_currentActivity = activity;
		
		m_arrButton.clear();
		setupButton(R.id.imageButtoninfo);
		setupButton(R.id.imageButtonevent);
		setupButton(R.id.imageButtonsaran);
		setupButton(R.id.imageButtonuser);
		//setupButton(R.id.imageButtonlokasi);
		
		//If there's a current button set, light it up
		if (m_iCurrentButtonId != -1) 
			pressButtonById(m_iCurrentButtonId);
	}
	protected void setupButton(int id)
	{
		ImageButton but = (ImageButton)m_currentActivity.findViewById(id);
		m_arrButton.add(but);
		but.setOnTouchListener(new OnTouchListener() {		
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				if (me.getAction() == MotionEvent.ACTION_UP) 
					pressButtonById(v.getId(), true);
				
				return true;
			}
		});				
	}
	public void pressButtonById(int nIdPressed)
	{
		pressButtonById(nIdPressed, false);
	}
	public void pressButtonById(int nIdPressed, boolean bSwitchActivity)
	{
		boolean bFoundButton = false;
		
		//Unpress all the buttons except the one we passed in
		for (ImageButton bt : m_arrButton) {
			if (nIdPressed == bt.getId()) {
				bFoundButton=true;
				bt.setBackgroundResource(R.color.activity_bar_button_selected);
			}
			else {
				bt.setPressed(false);			
				bt.setBackgroundResource(android.R.color.transparent);
			}
		}
	
		
		//If we asked to switch activities and the button passed in was good, do it
		if (bSwitchActivity && bFoundButton && nIdPressed!=m_iCurrentButtonId && m_currentActivity!=null) {  
			switch (nIdPressed) {
			case R.id.imageButtoninfo: 	 switchActivity(new Intent(m_currentActivity, tab_info_activity.class)); break;
			case R.id.imageButtonsaran:  switchActivity(new Intent(m_currentActivity, tab_saran_activity.class)); break;
			case R.id.imageButtonevent:  switchActivity(new Intent(m_currentActivity, tab_event_activity.class)); break;
			//case R.id.imageButtonlokasi: switchActivity(new Intent(m_currentActivity, tab_lokasi_activity.class)); break;
			case R.id.imageButtonuser: 	 switchActivity(new Intent(m_currentActivity, tab_user_activity.class)); break;
			
			}
		}		

		m_iCurrentButtonId = bFoundButton ? nIdPressed : -1;		
	}
	protected void switchActivity(Intent i) {
		m_currentActivity.startActivity(i);
		m_currentActivity.overridePendingTransition(0,0);		//No animation
		m_currentActivity.finish();								//We want to replace the current activity, not stack them
	}
	
	private static TabActivity m_hInstance = null;

	private TabActivity() {
		m_arrButton = new ArrayList<ImageButton>();
	}


	public static synchronized TabActivity getInstance() {
		if (m_hInstance == null)						//Create on first demand
			m_hInstance = new TabActivity();
		return m_hInstance;
	}


	//Call once for clean-up ... not sure I even need this, Tom Barry thinks this will all get GC'd 
	//when the program exits.
	public void Destroy() {
		if (m_hInstance != null) {
			//Do other clean up here...

			m_hInstance = null;								//Free up for garbage collection...
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////	
	//Singleton stuff
	//////////////////////////////////////////////////////////////////////////////////////////	
}