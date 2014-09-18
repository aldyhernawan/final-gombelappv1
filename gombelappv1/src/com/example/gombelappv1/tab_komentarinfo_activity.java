package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class tab_komentarinfo_activity extends Activity{
	private static final String AR_ID = "kode";
	private static final String TAG_SUCCESS = "success";
	//private static String url = "http://10.0.2.2/gombeldb/insert_komen.php";
	JSONParser jsonParser = new JSONParser();
	String kode, nama;
	ProgressDialog pDialog;
	SessionManager session;
	EditText inputkom;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_komentar);
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		HashMap<String, String> user = session.getUserDetails();
		nama = user.get(SessionManager.KEY_NAME);
		TextView nam = (TextView) findViewById(R.id.nama_user);
		nam.setText(nama);
		
		Intent i = getIntent();
		kode = i.getStringExtra(AR_ID);
		TextView id = (TextView) findViewById(R.id.kode);
		id.setText(kode);
		inputkom = (EditText) findViewById(R.id.inputkomen);
		
		
		Button submitkomen = (Button) findViewById(R.id.buttonsubmitkomentar);
		submitkomen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AmbilData().execute();
			}
		});
		//untuk tab activity
		TabActivity.getInstance().connectToActivity(this);		
		TabActivity TA = TabActivity.getInstance();
		TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtoninfo, true);
		
	}
    private void dismissProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }
    
	public class AmbilData extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(tab_komentarinfo_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2/gombeldb/insert_komen2.php?kode="+kode;
			String komentar = inputkom.getText().toString();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("info_id", kode));
			params.add(new BasicNameValuePair("komentar", komentar));
			params.add(new BasicNameValuePair("nama_user", nama));
			JSONObject json = jsonParser.makeHttpRequest(url,
					"POST", params);
			Log.d("Create Response", json.toString());
			try {	
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					Intent i = new Intent(getApplicationContext(), tab_info_detail_activity.class);
					i.putExtra("kode", kode);
					startActivity(i);
					finish();
				}
				else {
					// failed to create product
				}
			} 
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();		   
			
			
		}

	}
}
