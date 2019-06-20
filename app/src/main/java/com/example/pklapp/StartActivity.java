package com.example.pklapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

import Adapter.PatternEditableBuilder;

public class StartActivity extends AppCompatActivity {

    Button pesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        pesan=findViewById(R.id.legalisir);
        toRegister();

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder popdialog=new AlertDialog.Builder(StartActivity.this);
                View mview=getLayoutInflater().inflate(R.layout.activity_pesanlgl,null);

                Spinner jmlIjazah = findViewById(R.id.jumlah1);
                Spinner jmlTrans = findViewById(R.id.jumlah2);
                pesan = findViewById(R.id.pesan_button);

            }
        });
    }

    private void toRegister(){
        TextView txt=(TextView)findViewById(R.id.register);
        txt.setText("Belum upload? Upload disini");
        new PatternEditableBuilder().
                addPattern(Pattern.compile("Upload"), Color.BLUE,
                new PatternEditableBuilder.SpannableClickedListener(){
                    @Override
                    public void onSpanClicked(String text) {

                        startActivity(new Intent(StartActivity.this, RegisterActivity.class));
                    }
                }).into(txt);
    }


}
