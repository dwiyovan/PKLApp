package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TarifActivity extends AppCompatActivity {

    TextView h_ijasah, h_trankskrip, hargaJasaP,nama_alumni,almt_kirim;
    Spinner jasaP;
    Button b_pembayaran, b_gantiAlamat;
    StartActivity tes =new StartActivity();
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

        Bundle bundle=getIntent().getExtras();

        //pass data to string variables
        String data1=bundle.getString("hargaIjazah");
        String data2=bundle.getString("hargaTrans");

        //set the value to textView
        h_ijasah.setText(data1);
        h_trankskrip.setText(data2);



        b_pembayaran.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }


}
