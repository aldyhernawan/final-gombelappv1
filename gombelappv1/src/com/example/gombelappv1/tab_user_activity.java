package com.example.gombelappv1;

import java.util.HashMap;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tab_user_activity extends Activity {
	Button logout;
	SessionManager session;
	TextView status;
	JSONArray contacts = null;
	String email, nama, id;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_user);
		session = new SessionManager(getApplicationContext());
		Toast.makeText(getApplicationContext(),
				"User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG)
				.show();
		
		session.checkLogin();
		
		HashMap<String, String> user = session.getUserDetails();
		
		nama = user.get(SessionManager.KEY_NAME);
		// id = user.get(SessionManager.KEY_ID);
		TextView status = (TextView) findViewById(R.id.nama_user);
		status.setText(Html.fromHtml(nama));

		logout = (Button) findViewById(R.id.buttonlogout);
		logout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				session.logoutUser();
				finish();
			}
		});
		
		
		TabActivity.getInstance().connectToActivity(this);
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtonuser, true);
		
	}
}
