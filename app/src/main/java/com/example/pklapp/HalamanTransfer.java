package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Model.legalisir.Transaksi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HalamanTransfer extends AppCompatActivity {
    private TextView vStatusPemesanan;
    private TextView vtotalPembayaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_legalisir);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pembayaran");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);


        vStatusPemesanan = findViewById(R.id.statusPengiriman);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void getStatusPemesanan(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Transaksi> call = service.getStatusTransaksi("165150207111084");
        call.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                Transaksi statusPemesanan= response.body();
                if (statusPemesanan.getStatusPesanan().equals("4")){
                    vStatusPemesanan.setText("Pembayaran Tervalidasi");

                }else if(statusPemesanan.getStatusPesanan().equals("6")){
                    vStatusPemesanan.setText("Proses Pengiriman");
                }
                else{
                    vStatusPemesanan.setText("");
                }
//                Log.d("asu", "  "+response.body());
            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {

            }
        });
    }

    public void getTotalPembayaran(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Transaksi> call = service.getTotalPembayaran("165150207111084");
        call.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                Transaksi totalPembayaran= response.body();
                vtotalPembayaran.setText(totalPembayaran.getTotalHarga());

            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {

            }
        });
    }


}