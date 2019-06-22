package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class PesanLegalisirActivity extends AppCompatActivity {

    Spinner jmlIjazah, jmlTrans;
    Button pesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanlgl);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pesan Legalisir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        jmlIjazah = findViewById(R.id.jumlah1);
        jmlTrans = findViewById(R.id.jumlah2);
        pesan = findViewById(R.id.pesan_button);


        ArrayAdapter<String> pesanAdapter= new ArrayAdapter<String>(PesanLegalisirActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jml));
        pesanAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        jmlIjazah.setAdapter(pesanAdapter);
        jmlTrans.setAdapter(pesanAdapter);


    }
}
