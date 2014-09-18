package com.example.gombelappv1;

import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class tab_info_detail_activity extends Activity {
	ProgressDialog pDialog;
	JSONArray contacts = null;
	JSONArray contactk = null;
	
	ListView lv, lk;
	
	private static final String AR_ID = "kode";
	Button komen;
	String kode;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_info_detail);
				
		Intent i = getIntent();
		kode = i.getStringExtra(AR_ID);
		
		lv=(ListView)findViewById(R.id.list);
		lk=(ListView)findViewById(R.id.listView1);
        new AmbilData().execute();
        new AmbilKomen().execute();
        
		//untuk tab activity
		TabActivity.getInstance().connectToActivity(this);		
		TabActivity TA = TabActivity.getInstance();
        TA.connectToActivity(this);
		TA.pressButtonById(R.id.imageButtoninfo, true);
		
		komen = (Button) findViewById(R.id.buttonkomentar);
		komen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kirimparams();
			}
		});
	}
	
	private void kirimparams(){
		String kode = ((TextView) findViewById(R.id.kode)).getText().toString();
		
		Intent in = new Intent(getApplicationContext(), tab_komentarinfo_activity.class);
		in.putExtra("kode", kode);
		startActivity(in);
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
		
		String judul, time , isi;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(tab_info_detail_activity.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2/gombeldb/detail_info.php?kode="+kode;
			//String url = "http://10.0.2.2/gombeldb/detail_info.php";
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
			     	String isi = c.getString("isi").trim();
			    	
			     	map.put("judul", judul);
				    map.put("waktu", time);
				    map.put("isi", isi);
				    map.put("kode", kode);
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
		
			ListAdapter adapter3 = new SimpleAdapter(getApplicationContext(),
		    contactList, R.layout.list_info_detail,
		    new String[] { "waktu", "judul",  "isi", "kode" }, new int[] { R.id.waktu, R.id.judul,  R.id.Isidetail, R.id.kode });
			lv.setAdapter(adapter3);
			
		}

	}
	
	public class AmbilKomen extends AsyncTask<String, String, String> {
			
			ArrayList<HashMap<String, String>> contactListkomen = new ArrayList<HashMap<String, String>>();
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				
			}
	
			@Override
			protected String doInBackground(String... arg0) {
				
				String url_komen = "http://10.0.2.2/gombeldb/show_komentar.php?kode="+kode;
				JSONParser jParserkomen = new JSONParser();
				JSONObject jsonkomen = jParserkomen.getJSONFromUrl(url_komen);
				try {	
					contactk = jsonkomen.getJSONArray("data");
				    for (int i = 0; i < contactk.length(); i++) {
				    	JSONObject k = contactk.getJSONObject(i);
				    	HashMap<String, String> map_k = new HashMap<String, String>();		     
				    	
				    	String komentar = k.getString("komentar").trim();
				    	String waktu = k.getString("waktu").trim();
				    	String nama_user = k.getString("nama_user").trim();
				     	
				    	map_k.put("komentar", komentar);
					    map_k.put("waktu", waktu);
					    map_k.put("nama_user", nama_user);
					    
					    contactListkomen.add(map_k);
					    
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
			
				ListAdapter adapter4 = new SimpleAdapter(getApplicationContext(),
				contactListkomen, R.layout.list_komentar_info,
				new String[] { "nama_user","komentar", "waktu"}, new int[] { R.id.nama_user ,R.id.isikomen, R.id.waktukomen });
				lk.setAdapter(adapter4);
			}
	
		}	
}
