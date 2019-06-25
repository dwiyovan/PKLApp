package com.example.pklapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

    public void passData(View view){
        Spinner jmlIjazah = findViewById(R.id.jumlah1);
        Spinner jmlTrans = findViewById(R.id.jumlah2);
        int hitungtotijazah= (Integer.parseInt(jmlIjazah.getSelectedItem().toString()))*3000;
        int hitungtottrans=(Integer.parseInt(jmlTrans.getSelectedItem().toString()))*3000;

        String h_ijazah=String.valueOf(hitungtotijazah);
        String h_trans=String.valueOf(hitungtottrans);

        Intent intent_pass=new Intent(this, TarifActivity.class);
        intent_pass.putExtra("hargaIjazah",h_ijazah);
        intent_pass.putExtra("hargaTrans",h_trans);

        startActivity(intent_pass);

    }
}
