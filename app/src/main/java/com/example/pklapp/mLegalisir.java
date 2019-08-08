package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mLegalisir extends AppCompatActivity {
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

    statusIjazah = findViewById(R.id.statusIjazah);
    statusTN = findViewById(R.id.statusTN);
    bPengiriman = findViewById(R.id.bPengiriman);


    }

}
