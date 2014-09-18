package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class tab_info_activity extends Activity {
	ListView lv;
	ProgressDialog pDialog;
	JSONArray contacts = null;

	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_info);
		//untuk tab activity
		TabActivity.getInstance().connectToActivity(this);		
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtoninfo, true);
		
		//aktivitas listview
		lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		// TODO Auto-generated method stub    
			    
        		String kode = ((TextView) view.findViewById(R.id.kode)).getText().toString();
        		
			    //mengirimkan nilai ke file Detail.java
			    Intent in = new Intent(getApplicationContext(), tab_info_detail_activity.class);
			    
			    in.putExtra("kode", kode);
			    
			    // menjalankan Detail.java
			    startActivity(in);
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
    
	public class AmbilData extends AsyncTask<String, String, String> {
		
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		String kode, judul, time;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(tab_info_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			
			String url = "http://10.0.2.2/gombeldb/info.php";
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			try {	
				contacts = json.getJSONArray("data");
			    for (int i = 0; i < contacts.length(); i++) {
			    	JSONObject c = contacts.getJSONObject(i);
			    	HashMap<String, String> map = new HashMap<String, String>();		     
			    	String kode = c.getString("kode").trim();
			    	String judul = c.getString("judul").trim();
			    	String time = c.getString("waktu").trim();
			     	
			    	map.put("kode", kode);
				    map.put("judul", judul);
				    map.put("waktu", time);
				    
				    contactList.add(map);
			    }
			} 
			catch (JSONException e) {
				//TODO Something
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();		   
			ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
		    contactList, R.layout.list_info,
		    new String[] { "waktu", "judul", "kode" }, new int[] { R.id.waktu, R.id.judul, R.id.kode });
			lv.setAdapter(adapter2);

		}

	}
}
