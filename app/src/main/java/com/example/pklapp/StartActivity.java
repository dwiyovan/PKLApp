package com.example.pklapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

import com.example.pklapp.Adapter.PatternEditableBuilder;

public class StartActivity extends AppCompatActivity {

    Button pesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        pesan=findViewById(R.id.legalisir);
        toUpload();


        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder popdialog=new AlertDialog.Builder(StartActivity.this);
                View mview=getLayoutInflater().inflate(R.layout.activity_pesanlgl,null);

                final Spinner jmlIjazah = findViewById(R.id.jumlah1);
                final Spinner jmlTrans = findViewById(R.id.jumlah2);
                pesan = findViewById(R.id.pesan_button);

                ArrayAdapter<String> pesanAdapter= new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.jml));
                pesanAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                jmlIjazah.setAdapter(pesanAdapter);
                jmlTrans.setAdapter(pesanAdapter);



                popdialog.setView(mview);
                AlertDialog dialog=popdialog.create();
                dialog.show();


            }
        });

    }

    private void toUpload(){
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
