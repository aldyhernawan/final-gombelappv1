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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class tab_saran_activity extends Activity {
	Button tambah;
	ListView lv;
	ProgressDialog pDialog;
	JSONArray contacts = null;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_saran);
		
		lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
        
		TabActivity.getInstance().connectToActivity(this);
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtonsaran, true);
		
		tambah = (Button) findViewById(R.id.buttonsaran);
		tambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), tab_tambah_saran_activity.class);
				startActivityForResult(intent,0);
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
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
			pDialog = new ProgressDialog(tab_saran_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			
			String url = "http://10.0.2.2/gombeldb/saran.php";
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			try {	
				contacts = json.getJSONArray("data");
			    for (int i = 0; i < contacts.length(); i++) {
			    	JSONObject c = contacts.getJSONObject(i);
			    	HashMap<String, String> map = new HashMap<String, String>();
			    	
			    	String id_saran = c.getString("id_saran").trim();
			    	String judul = c.getString("judul").trim();
			    	String isi_saran = c.getString("isi_saran").trim();
			    	String waktu = c.getString("waktu").trim();
			    	String user = c.getString("nama_user").trim();

			    	map.put("id_saran", id_saran);
				    map.put("judul", judul);
				    map.put("isi_saran", isi_saran);
				    map.put("waktu", waktu);
				    map.put("nama_user", user);
				    
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
		    contactList, R.layout.list_saran,
		    new String[] { "waktu","nama_user", "judul","isi_saran", "id_saran" }, new int[] { R.id.waktu, R.id.user , R.id.judul, R.id.Isidetail, R.id.kode });
			lv.setAdapter(adapter2);

		}

	}
}
