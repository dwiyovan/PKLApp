package com.example.pklapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import com.example.pklapp.Adapter.PatternEditableBuilder;

public class StartActivity extends AppCompatActivity {

    Button pesan;
    Button tes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        pesan=findViewById(R.id.legalisir);
        toUpload();


        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, PesanLegalisirActivity.class));


            }
        });

    }

    private void toUpload(){
        TextView txt=(TextView)findViewById(R.id.register);
        txt.setText("Belum upload? Upload disini");
        new PatternEditableBuilder().
                addPattern(Pattern.compile("disini"), Color.BLUE,
                new PatternEditableBuilder.SpannableClickedListener(){
                    @Override
                    public void onSpanClicked(String text) {

                        startActivity(new Intent(StartActivity.this, MLegalisir.class));
                    }
                }).into(txt);
    }


}
