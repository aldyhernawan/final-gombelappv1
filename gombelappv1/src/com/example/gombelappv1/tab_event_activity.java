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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class tab_event_activity extends Activity {
	ListView lv;
	ProgressDialog pDialog;
	JSONArray contacts = null;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_event);
		
		TabActivity.getInstance().connectToActivity(this);
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtonevent, true);
		
		lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		// TODO Auto-generated method stub    
			    
        		String id_event = ((TextView) view.findViewById(R.id.kode)).getText().toString();
        		
			    //mengirimkan nilai ke file Detail.java
			    Intent ev = new Intent(getApplicationContext(), tab_event_detail_activity.class);
			    
			    ev.putExtra("id_event", id_event);
			    
			    // menjalankan Detail.java
			    startActivity(ev);
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
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(tab_event_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			
			String url = "http://10.0.2.2/gombeldb/event.php";
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			try {	
				contacts = json.getJSONArray("data");
			    for (int i = 0; i < contacts.length(); i++) {
			    	JSONObject c = contacts.getJSONObject(i);
			    	HashMap<String, String> map = new HashMap<String, String>();		     
			    	String id_event = c.getString("id_event").trim();
			    	String judul = c.getString("judul").trim();
			    	String waktu = c.getString("tanggal_event").trim();
			     	
			    	map.put("tanggal_event", waktu);
				    map.put("judul", judul);
				    map.put("id_event", id_event);
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
		    contactList, R.layout.list_event,
		    new String[] { "tanggal_event", "judul", "id_event" }, new int[] { R.id.waktu, R.id.judul, R.id.kode });
			lv.setAdapter(adapter2);

		}

	}
}
