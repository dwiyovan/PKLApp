package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TarifActivity extends AppCompatActivity {

    TextView h_ijasah, h_trankskrip, hargaJasaP;
    Spinner jasaP;
    Button b_pembayaran, b_gantiAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif);

        h_ijasah = findViewById(R.id.hargaIjasah);
        h_trankskrip = findViewById(R.id.hargaTranskrip);
        hargaJasaP = findViewById(R.id.hargaJasa);
        jasaP = findViewById(R.id.spinnerJ);
        b_pembayaran = findViewById(R.id.b_pembayaran);
        b_gantiAlamat = findViewById(R.id.b_gantiAlamat);

        b_pembayaran.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }
}
