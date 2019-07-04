package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TarifActivity extends AppCompatActivity {

    TextView h_ijasah, h_trankskrip, hargaJasaP,nama_alumni,almt_kirim;
    Spinner jasaP;
    Button b_pembayaran, b_gantiAlamat;
    StartActivity tes =new StartActivity();
    private RequestQueue reqjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tarif");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        h_ijasah = findViewById(R.id.hargaIjasah);
        h_trankskrip = findViewById(R.id.hargaTranskrip);
        hargaJasaP = findViewById(R.id.hargaJasa);
        nama_alumni=findViewById(R.id.nama_alumni);
        almt_kirim=findViewById(R.id.almt_kirim);
        jasaP = findViewById(R.id.spinnerJ);
        b_pembayaran = findViewById(R.id.b_pembayaran);
        b_gantiAlamat = findViewById(R.id.b_gantiAlamat);
        reqjson= Volley.newRequestQueue(this);
        Bundle bundle=getIntent().getExtras();
        jsonParse();

        //pass data to string variables
        String data1=bundle.getString("hargaIjazah");
        String data2=bundle.getString("hargaTrans");

        //set the value to textView
        h_ijasah.setText("Rp. "+data1+" ,00");
        h_trankskrip.setText("Rp. "+data2+" ,00");




        b_pembayaran.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    private void jsonParse(){
        //api ongkir
        String url="https://api.rajaongkir.com/starter/cost";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray jsonArray=response.getJSONArray("rajaongkir");
                    hargaJasaP.setText("test");
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject nilai=jsonArray.getJSONObject(i);
//
//                        int ongkir=nilai.getInt("value");
//
//
//                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        reqjson.add(req);
    }



}
