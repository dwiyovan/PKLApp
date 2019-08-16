package com.example.pklapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Adapter.ListCityAdapter;
import com.example.pklapp.Adapter.ListProvinceAdapter;
import com.example.pklapp.Model.City.ItemCity;
import com.example.pklapp.Model.Cost.ItemCost;
import com.example.pklapp.Model.InsertResponse;
import com.example.pklapp.Model.Province.ItemProvince;
import com.example.pklapp.Model.Province.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TarifActivity extends AppCompatActivity {

    String idProvince;
    TextView h_ijasah, h_trankskrip, nama_alumni;
    EditText province, city, almt_compl;
    private EditText searchList;
    Spinner jasaP,jmlITN,jmlijz;
    Button b_proses, b_gantiAlamat;
    StartActivity tes = new StartActivity();
    private RequestQueue reqjson;

    private ListProvinceAdapter provinceAdapter;
    private ListCityAdapter cityAdapter;

    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;

    private ListView listView;

    private List<Result> ListProvince = new ArrayList<Result>();

    private List<com.example.pklapp.Model.City.Result> ListCity = new ArrayList<com.example.pklapp.Model.City.Result>();

    int totalweight,data1,data2;
    String getProvinceValue,getCityValue,getAddressValue;
    String[] getArrayjml;
    ArrayAdapter<String> jml_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemesanan_legalisir);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tarif");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        h_ijasah = findViewById(R.id.vHrgaijz);
        h_trankskrip = findViewById(R.id.vHargaTN);
        nama_alumni = findViewById(R.id.nama_alumni);
        almt_compl = findViewById(R.id.almt_compl);
        province = (EditText) findViewById(R.id.province2_option);
        city = (EditText) findViewById(R.id.city2_option);
        jasaP = findViewById(R.id.spinnerJ);
        jmlijz=findViewById(R.id.jmlIjz);
        jmlITN=findViewById(R.id.jmlTN);
        b_proses = (Button) findViewById(R.id.b_proses);
//        Bundle bundle = getIntent().getExtras();


        //data1=harga ijazah, data2=harga transkrip
         data1=(Integer.parseInt(jmlijz.getSelectedItem().toString()))*500;
         data2=(Integer.parseInt(jmlITN.getSelectedItem().toString()))*300;
        totalweight = (Integer.parseInt(jmlijz.getSelectedItem().toString())+Integer.parseInt(jmlITN.getSelectedItem().toString()))* 80;

        getArrayjml=getResources().getStringArray(R.array.jml);


        //set the value to textView

        jml_adapter=new ArrayAdapter<String>(TarifActivity.this,android.R.layout.simple_list_item_1,getArrayjml);
        jmlijz.setAdapter(jml_adapter);
        jmlITN.setAdapter(jml_adapter);

        jmlijz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        h_ijasah.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 1:
                        h_ijasah.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 2:
                        h_ijasah.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 3:
                        h_ijasah.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 4:
                        h_ijasah.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jmlITN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        h_trankskrip.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 1:
                        h_trankskrip.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 2:
                        h_trankskrip.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 3:
                        h_trankskrip.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                    case 4:
                        h_trankskrip.setText("Rp. "+((Integer.parseInt(getArrayjml[position]))*500)+",00");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        nama_alumni.setText("Indiana Jones");
//
//        getCost("Yogyakarta","Denpasar","1700","jne");


        province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogProvince(province, city);

            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (province.getTag().equals("")) {
                        province.setError("Silahkan pilih provinsi");
                    } else {
                        AlertDialogCity(city, province);
                    }

                } catch (NullPointerException e) {
                    province.setError("Silahkan pilih provinsi");

                }
            }
        });
        b_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parseweight = String.valueOf(totalweight);

                getCost("255", city.getTag().toString(), parseweight, jasaP.getSelectedItem().toString());



            }
        });


    }


    public void getProvince() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS).addConverterFactory(GsonConverterFactory.create()).build();

        APIService service = retrofit.create(APIService.class);
        Call<ItemProvince> call = service.getProvince();

        call.enqueue(new Callback<ItemProvince>() {
            @Override
            public void onResponse(Call<ItemProvince> call, Response<ItemProvince> response) {

                Log.v("province", "json : " + new Gson().toJson(response));
                if (response.isSuccessful()) {
                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int i = 0; i <= count_data - 1; i++) {
                        Result itemProvince = new Result(
                                response.body().getRajaongkir().getResults().get(i).getProvince_id(),
                                response.body().getRajaongkir().getResults().get(i).getProvince()
                        );
                        ListProvince.add(itemProvince);
                        listView.setAdapter(provinceAdapter);

                    }
                    provinceAdapter.setList(ListProvince);
                    provinceAdapter.search("");

                } else {
                    Toast.makeText(TarifActivity.this, "Tidak bisa ambil data dari Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemProvince> call, Throwable t) {
                Toast.makeText(TarifActivity.this, "Error Message:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AlertDialogProvince(final EditText EtProvince, final EditText EtCity) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.search_layout, null);

        alert = new AlertDialog.Builder(TarifActivity.this);
        alert.setTitle("List Province");
        alert.setMessage("Pilih Provinsi");
        alert.setView(alertLayout);
        alert.setCancelable(true);

        alertDialog = alert.show();

        searchList = (EditText) alertLayout.findViewById(R.id.searchItem);
        searchList.addTextChangedListener(new TextDetectorProvince(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        listView = (ListView) alertLayout.findViewById(R.id.list_item);

        ListProvince.clear();
        provinceAdapter = new ListProvinceAdapter(TarifActivity.this, ListProvince);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                Object i = listView.getItemAtPosition(position);
                Result lp = (Result) i;

                EtProvince.setError(null);
                EtProvince.setText(lp.getProvince());
                EtProvince.setTag(lp.getProvince_id());

                EtCity.setText("");
                EtCity.setTag("");

                alertDialog.dismiss();

            }
        });

        getProvince();

    }

    private class TextDetectorProvince implements TextWatcher {
        private View view;

        private TextDetectorProvince(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.searchItem:
                    provinceAdapter.search(s.toString());
                    break;
            }
        }
    }


    private class TextDetectorCity implements TextWatcher {
        private View view;

        private TextDetectorCity(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {
                case (R.id.search_bar):
                    cityAdapter.search(s.toString());
                    break;

            }

        }
    }

    public void getCity(String id_province) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS).addConverterFactory(GsonConverterFactory.create()).build();

        APIService service = retrofit.create(APIService.class);
        Call<ItemCity> call = service.getCity(id_province);

        call.enqueue(new Callback<ItemCity>() {
            @Override
            public void onResponse(Call<ItemCity> call, Response<ItemCity> response) {

                Log.v("city", "json: " + new Gson().toJson(response));

                if (response.isSuccessful()) {
                    int count_data = response.body().getRajaongkir().getResults().size();
                    for (int i = 0; i <= count_data - 1; i++) {
                        com.example.pklapp.Model.City.Result itemProvince = new com.example.pklapp.Model.City.Result(
                                response.body().getRajaongkir().getResults().get(i).getCity_id(),
                                response.body().getRajaongkir().getResults().get(i).getProvince_id(),
                                response.body().getRajaongkir().getResults().get(i).getProvince(),
                                response.body().getRajaongkir().getResults().get(i).getType(),
                                response.body().getRajaongkir().getResults().get(i).getCity_name(),
                                response.body().getRajaongkir().getResults().get(i).getPostal_code()
                        );
                        ListCity.add(itemProvince);
                        listView.setAdapter(cityAdapter);
                    }
                    cityAdapter.setList(ListCity);
                    cityAdapter.search("");

                } else {
                    Toast.makeText(TarifActivity.this, "Tidak bisa ambil data dari Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemCity> call, Throwable t) {
                Toast.makeText(TarifActivity.this, "Error Message:" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void AlertDialogCity(final EditText EtCity, final EditText EtProvince) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.search_layout, null);

        alert = new AlertDialog.Builder(TarifActivity.this);
        alert.setTitle("List City");
        alert.setMessage("Pilih Kota");
        alert.setView(alertLayout);
        alert.setCancelable(true);

        alertDialog = alert.show();

        searchList = (EditText) alertLayout.findViewById(R.id.searchItem);
        searchList.addTextChangedListener(new TextDetectorCity(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        listView = (ListView) alertLayout.findViewById(R.id.list_item);

        ListCity.clear();
        cityAdapter = new ListCityAdapter(TarifActivity.this, ListCity);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                Object i = listView.getItemAtPosition(position);
                com.example.pklapp.Model.City.Result lc = (com.example.pklapp.Model.City.Result) i;

                EtCity.setError(null);
                EtCity.setText(lc.getCity_name());
                EtCity.setTag(lc.getCity_id());

                alertDialog.dismiss();

            }
        });

        getCity(EtProvince.getTag().toString());

    }


    public void getCost(String origin, String destination, final String weight, String courier) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS).addConverterFactory(GsonConverterFactory.create()).build();

        APIService service = retrofit.create(APIService.class);
        Call<ItemCost> call = service.getCost("9a9d935dede69ae9232843a3d2a02e4b", origin, destination, weight, courier);


        call.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {
                Log.v("tes", "json : " + new Gson().toJson(response));


                if (response.isSuccessful()) {
                    int statuscode = response.body().getRajaongkir().getStatus().getCode();
                    ItemCost itemCost = response.body();

                    if (statuscode == 200) {

                        final int total_harga= data1 + data2;
                        getProvinceValue =province.getText().toString();
                        getCityValue =city.getText().toString();
                        getAddressValue =almt_compl.getText().toString();

                        TextView asal, tujuan, agen, ongkir, lama, alamat, total_bayar;
                        Button fix_pesan;

                        String resultasal = itemCost.getRajaongkir().getOriginDetails().getCity_name() + " (" + response.body().getRajaongkir().
                                getDestinationDetails().getPostal_code() + ")";


                        String resulttujuan = itemCost.getRajaongkir().getDestinationDetails().getCity_name() + "(" + response.body().getRajaongkir().
                                getDestinationDetails().getPostal_code() + ")";

                        final int resultongkir = response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getCost().get(0).getValue();

                        String resultagen = response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getDescription() + "(" +
                                response.body().getRajaongkir().getResults().get(0).getName() + ")";
                        final String postal_code=response.body().getRajaongkir().getDestinationDetails().getPostal_code();

                        String resultlamakirim = response.body().getRajaongkir().getResults().get(0).getCosts().get(0).getCost().get(0).getEtd() + "";

                        final int totalbayar=(total_harga+resultongkir);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View alertlayout = inflater.inflate(R.layout.detail_pembayaran, null);
                        alert = new AlertDialog.Builder(TarifActivity.this);
                        alert.setTitle("Detail Pembayaran");
                        alert.setMessage("Silahkan cek kembali. Tekan tombol pembayaran jika anda sudah yakin");
                        alert.setView(alertlayout);
                        alert.setCancelable(true);

                        alertDialog = alert.show();

                        asal = alertlayout.findViewById(R.id.origin_city);
                        tujuan = alertlayout.findViewById(R.id.destination_city);
                        agen = alertlayout.findViewById(R.id.agent_del);
                        ongkir = alertlayout.findViewById(R.id.ongkir_value);
                        alamat = alertlayout.findViewById(R.id.compl_address);
                        total_bayar = alertlayout.findViewById(R.id.total_price);
                        lama = alertlayout.findViewById(R.id.time_del);
                        fix_pesan = alertlayout.findViewById(R.id.fixpesan);


                        asal.setText(resultasal);
                        tujuan.setText(resulttujuan);
                        alamat.setText(almt_compl.getText().toString());
                        agen.setText(resultagen);
                        ongkir.setText("Rp." + resultongkir);
                        total_bayar.setText("Rp" + totalbayar);
                        lama.setText(resultlamakirim);
                        fix_pesan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                saveTransaction("6757686",totalweight,resultongkir,total_harga,totalbayar,
                                        getProvinceValue,getCityValue,getAddressValue,postal_code);
//                                Log.d("ppppp", "tes data: "+totalweight+" and "+totalbayar+" and "+province.getText()+
//                                        " and "+city.getText()+" and "+almt_compl.getText()+" and "+postal_code);
//                                startActivity(new Intent(TarifActivity.this,StartActivity.class));
                            }
                        });


                        province.setText("");
                        city.setText("");
                        almt_compl.setText("");

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
        });


    }

    private void saveTransaction(final String id_pemesan, final Integer berat, final int ongkir,
                                 final int total_harga, final int total_bayar, final String provinsi,
                                 final String kota, final String jalan, final String kode_pos) {

        Log.d("ppppp", "1 ");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESSLegalisir).addConverterFactory(GsonConverterFactory.create(gson)).build();

        APIService apiService = retrofit.create(APIService.class);
        Call<InsertResponse> call = apiService.savetransaction(id_pemesan, berat, ongkir, total_harga, total_bayar, provinsi, kota, jalan, kode_pos);

        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                Boolean success=response.body().getSuccess();
                Log.d("ppppp", "2");
                if(success) {
                    Log.v("tes","mysql");
                    Toast.makeText(TarifActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT);

                }
                else{
                    Toast.makeText(TarifActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(@NonNull Call<InsertResponse> call, @NonNull Throwable t) {
                Log.d("ppppp", "error");
                Log.d("ppppp", t.getMessage());

//                Toast.makeText(TarifActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT);

            }
        });
    }
}
