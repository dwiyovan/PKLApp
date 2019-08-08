package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Model.legalisir.Ijazah;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MLegalisir extends AppCompatActivity {
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
        imgStatusIjazah = findViewById(R.id.imgsIjazah);
        statusIjazah = findViewById(R.id.statusIjazah);
        statusTN = findViewById(R.id.statusTN);
        bPengiriman = findViewById(R.id.bPengiriman);
        getIjazah();

    }

    public void getIjazah(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Ijazah> call=service.getStatusIjazah("165152052631");
        call.enqueue(new Callback<Ijazah>() {
            @Override
            public void onResponse(Call<Ijazah> call, Response<Ijazah> response) {
                Ijazah ijazah = response.body();
                if (ijazah.getValidasi().equalsIgnoreCase("terima")){
                    imgStatusIjazah.setImageResource(R.drawable.ic_notifications_black_24dp);

                }else{
                    imgStatusIjazah.setImageResource(R.drawable.ic_search_black_24dp);
                }
                statusIjazah.setText(ijazah.getValidasi());
//                Log.d("asu", "  "+response.body());
            }

            @Override
            public void onFailure(Call<Ijazah> call, Throwable t) {

            }
        });
    }

}
