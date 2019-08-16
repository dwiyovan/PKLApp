package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                    imgStatusIjazah.setImageResource(R.drawable.ic_check_circle_black_24dp);

                }else{
                    imgStatusIjazah.setImageResource(R.drawable.ic_failure_24dp);
                }
                statusIjazah.setText(ijazah.getValidasi());
//                Log.d("asu", "  "+response.body());
            }

            @Override
            public void onFailure(Call<Ijazah> call, Throwable t) {

            }
        });
    }
    public void getTranskripNilai(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<TranskripNilai> call=service.getStatusTranksripNilai("165152052631");
        call.enqueue(new Callback<TranskripNilai>() {
            @Override
            public void onResponse(Call<TranskripNilai> call, Response<TranskripNilai> response) {
                TranskripNilai transkripNilai = response.body();
                if (transkripNilai.getValidasi().equalsIgnoreCase("terima")){
                    imgStatusTN.setImageResource(R.drawable.ic_check_circle_black_24dp);

                }else{
                    imgStatusTN.setImageResource(R.drawable.ic_failure_24dp);
                }
                statusTN.setText(transkripNilai.getValidasi());
//                Log.d("asu", "  "+response.body());
            }

            @Override
            public void onFailure(Call<TranskripNilai> call, Throwable t) {

            }
        });
    }


}
