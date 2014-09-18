package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends Activity  {

	Button login, register;
	Intent a;
	EditText alamat_email, password_user;
	String url, success;
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		session = new SessionManager(getApplicationContext());
		//Toast.makeText(getApplicationContext(),"User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
		
		
		login = (Button) findViewById(R.id.buttonlogin);
		alamat_email = (EditText) findViewById(R.id.inputEmail);
		password_user = (EditText) findViewById(R.id.inputPassword);
		register = (Button) findViewById(R.id.buttoncreateakun);
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), register_activity.class);
				startActivityForResult(intent,0);
			}
		});
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				url = "http://10.0.2.2/gombeldb/login.php?" + "alamat_email="
						+ alamat_email.getText().toString() + "&password_user="
						+ password_user.getText().toString();

				if (alamat_email.getText().toString().trim().length() > 0
						&& password_user.getText().toString().trim().length() > 0) 
				{
					new Masuk().execute();
				} 
				else 
				{
					Toast.makeText(getApplicationContext(), "Username/password masih kosong !!", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	
	
	
	public class Masuk extends AsyncTask<String, String, String> 
	{
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pDialog = new ProgressDialog(login_activity.this);
			pDialog.setMessage("Tunggu Bentar ya...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... arg0) {
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				success = json.getString("success");
				Log.e("error", "nilai sukses=" + success);
				JSONArray hasil = json.getJSONArray("login");
				if (success.equals("1")) {
					for (int i = 0; i < hasil.length(); i++) {
						JSONObject c = hasil.getJSONObject(i);					
						String nama_user = c.getString("nama_user").trim();
						String alamat_email = c.getString("alamat_email").trim();
						session.createLoginSession(nama_user, alamat_email);
						Log.e("ok", " ambil data");

					}
				} else {
					Log.e("error", "tidak bisa ambil data 0");
				}

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("error", "tidak bisa ambil data 1");
			}

			return null;
			
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			if (success.equals("1")) {
				Intent n = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(n);
				Context context = getApplicationContext();
				CharSequence text = "Selamat datang";
				int duration = Toast.LENGTH_LONG;
				
				final Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				finish();
				
			} else {
				
				Toast.makeText(getApplicationContext(), "Username/password salah !!", Toast.LENGTH_LONG).show();
			}
		}	
	}
}
