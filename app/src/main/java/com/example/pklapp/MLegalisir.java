package com.example.pklapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Model.legalisir.Ijazah;
import com.example.pklapp.Model.legalisir.TranskripNilai;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MLegalisir extends AppCompatActivity{
    private Button bUploadIjazah;
    private Button bUploadTN;
    private Button bPengiriman;
    private TextView statusIjazah;
    private TextView statusTN;
    private ImageView imgStatusIjazah;
    private ImageView imgStatusTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_legalisir);

        BottomNavigationView navbot=findViewById(R.id.simplefragment);
        navbot.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentMLegalisir()).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu Legalisir");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);


        imgStatusIjazah = findViewById(R.id.imgsIjazah);
        imgStatusTN= findViewById(R.id.imgsTN);
        statusIjazah = findViewById(R.id.statusIjazah);
        statusTN = findViewById(R.id.statusTN);
        bPengiriman = findViewById(R.id.bPengiriman);
        bUploadIjazah = findViewById(R.id.bIjazah);
        bUploadTN= findViewById(R.id.bTN);



        navbot.setOnNavigationItemSelectedListener(navlistener);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectfragment=null;

            switch (menuItem.getItemId()){
                case R.id.upload_berkas:
                    selectfragment=new FragmentMLegalisir();
                    break;
                case R.id.history:
                    selectfragment=new HistoryActivity();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectfragment).commit();
            return true;
        }
    };



}
