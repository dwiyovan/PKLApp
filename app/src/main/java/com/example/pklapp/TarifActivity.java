package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Model.ItemCost;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TarifActivity extends AppCompatActivity {

    TextView h_ijasah, h_trankskrip, hargaJasaP,nama_alumni,almt_kirim;
    Spinner jasaP;
    Button b_pembayaran, b_gantiAlamat;
    StartActivity tes =new StartActivity();
    private RequestQueue reqjson;

    String id_kota_asal;
    String id_provinsi_asal;
    String provinsi_asal;
    String tipe_asal;
    String kota_asal;
    String postal_code_asal;
    String id_kota_tujuan;
    String id_provinsi_tujuan;
    String provinsi_tujuan;
    String tipe_tujuan;
    String kota_tujuan;
    String postal_code_tujuan;




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


        //pass data to string variables
        String data1=bundle.getString("hargaIjazah");
        String data2=bundle.getString("hargaTrans");

        //set the value to textView
        h_ijasah.setText("Rp. "+data1+" ,00");
        h_trankskrip.setText("Rp. "+data2+" ,00");
        nama_alumni.setText("Indiana Jones");
        almt_kirim.setText("Bali");

        getCost("Yogyakarta",almt_kirim.getText().toString(),"1700","jne");





        b_pembayaran.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    public void getCost(String origin, String destination, String weight, String courier){

        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS).addConverterFactory(GsonConverterFactory.create()).build();

        APIService service=retrofit.create(APIService.class);
        Call<ItemCost> call=service.getCost("9a9d935dede69ae9232843a3d2a02e4b",origin,destination,weight,courier);


        try{
        call.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {
                Log.v("tes", "json : " + new Gson().toJson(response));


                    if (response.isSuccessful()) {
                        int statuscode = response.body().getRajaongkir().getStatus().getCode();


                        if (statuscode == 200) {

                            hargaJasaP.setText("Rp." + response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getCost().get(0).getValue().toString());


                        } else {
                            String unsuccessful_msg = response.body().getRajaongkir().getStatus().getDescription();
                            Toast.makeText(TarifActivity.this, unsuccessful_msg, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String error_msg = "Tidak bisa ambil data dari server";
                        Toast.makeText(TarifActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }



            }


            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {

                Toast.makeText(TarifActivity.this, "Gagal melakukan task", Toast.LENGTH_SHORT).show();

            }
        });}catch (Exception e){e.printStackTrace();}


    }


//    private void jsonParse(){
//        //api ongkir
//        String url="https://api.rajaongkir.com/starter/cost";
//
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try{
//                    JSONArray jsonArray=response.getJSONArray("rajaongkir");
//
//
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject nilai=jsonArray.getJSONObject(i);
//
//
//                        id_kota_asal=nilai.getString("city_id");
//                        id_provinsi_asal=nilai.getString("province_id");
//                        provinsi_asal=nilai.getString("province");
//                        tipe_asal=nilai.getString("type");
//                        kota_asal=nilai.getString("city");
//                        postal_code_asal=nilai.getString("postal_code");
//
//                        id_kota_tujuan=nilai.getString("city_id");
//                        id_provinsi_tujuan=nilai.getString("province_id");
//                        provinsi_tujuan=nilai.getString("province");
//                        tipe_tujuan=nilai.getString("type");
//                        kota_tujuan=nilai.getString("city");
//                        postal_code_tujuan=nilai.getString("postal_code");
//
//
//                        int ongkir=nilai.getInt("value");
//
//                        hargaJasaP.setText(ongkir);
//
//                    }
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        },new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        })
//        {
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                HashMap<String,String> headers=new HashMap<String, String>();
////                headers.put("Content-Type", "application/json");
////                headers.put("apiKey", "xxxxxxxxxxxxxxx");
////                return headers;
////            }
//        };
//        reqjson.add(req);
//    }
}
