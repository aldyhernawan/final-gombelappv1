package com.example.gombelappv1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class tab_event_detail_activity extends Activity{
	ProgressDialog pDialog;
	JSONArray contacts = null;
	JSONArray contactk = null;
	ListView lv;
	private static final String AR_ID = "id_event";
	
	String id_event;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_event_detail);
		Intent i = getIntent();
		id_event = i.getStringExtra(AR_ID);
		
		lv=(ListView)findViewById(R.id.list);
		new AmbilDataEvent().execute();
		
		//untuk tab activity
		TabActivity.getInstance().connectToActivity(this);
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtonevent, true);
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
    
	public class AmbilDataEvent extends AsyncTask<String, String, String> {
		
		ArrayList<HashMap<String, String>> contactListevent = new ArrayList<HashMap<String, String>>();
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(tab_event_detail_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2/gombeldb/detail_event.php?id_event="+id_event;
			
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			try {	
				contacts = json.getJSONArray("data");
			    for (int i = 0; i < contacts.length(); i++) {
			    	JSONObject c = contacts.getJSONObject(i);
			    	HashMap<String, String> map = new HashMap<String, String>();		     
			    	String id_event = c.getString("id_event").trim();
			    	String judul = c.getString("judul").trim();
			    	String isi_event = c.getString("isi_event").trim();
			     	String waktu_event = c.getString("waktu_event").trim();
			     	String tanggal_event = c.getString("tanggal_event").trim();
			     	String tempat_event = c.getString("tempat_event").trim();
			    	
			     	map.put("id_event", id_event);
			     	map.put("judul", judul);
				    map.put("isi_event", isi_event);
				    map.put("waktu_event", waktu_event);
				    map.put("tanggal_event", tanggal_event);
				    map.put("tempat_event", tempat_event);
				    
				    contactListevent.add(map);
				    
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
		
			ListAdapter adapterevent = new SimpleAdapter(getApplicationContext(),
		    contactListevent, R.layout.list_event_detail,
		    new String[] { "judul", "isi_event",  "tanggal_event", "waktu_event", "tempat_event", "id_event" }, new int[] { R.id.judul, R.id.Isidetail,  R.id.Tanggal_event, R.id.waktu_event, R.id.tempat_event, R.id.kode });
			lv.setAdapter(adapterevent);
			
		}

	}
}
