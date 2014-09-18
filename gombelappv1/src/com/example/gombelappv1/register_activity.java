package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
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

public class register_activity extends Activity{
	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();
	ProgressDialog pDialog;
	
	EditText input_email, input_password, input_nama;
	Button register, cancel ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		input_email = (EditText) findViewById(R.id.editTxtemail);
		input_password = (EditText) findViewById(R.id.editTxtpassword);
		input_nama = (EditText) findViewById(R.id.edittxtnama);
		
		register = (Button) findViewById(R.id.buttonregister);	
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new regis().execute();
				Context context = getApplicationContext();
				CharSequence text = "Akun Berhasil Dibuat";
				int duration = Toast.LENGTH_LONG;
				
				final Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
		
		cancel = (Button) findViewById(R.id.buttoncancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), login_activity.class);
				startActivityForResult(intent,0);
			}
		});
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
    
	public class regis extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(register_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2/gombeldb/create_akun.php";
			
			String alamat_email = input_email.getText().toString();
			String password_user = input_password.getText().toString();
			String nama_user = input_nama.getText().toString();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("alamat_email", alamat_email));
			params.add(new BasicNameValuePair("password_user", password_user));
			params.add(new BasicNameValuePair("nama_user", nama_user));
			
			JSONObject json = jsonParser.makeHttpRequest(url,
					"POST", params);
			Log.d("Create Response", json.toString());
			try {	
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					
					Intent n = new Intent(getApplicationContext(), login_activity.class);
					
					startActivity(n);
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
