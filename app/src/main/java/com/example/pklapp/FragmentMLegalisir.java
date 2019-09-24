package com.example.pklapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentMLegalisir extends Fragment {
    private Button bUploadIjazah;
    private Button bUploadTN;
    private Button bPengiriman;
    private TextView statusIjazah;
    private TextView statusTN;
    private ImageView imgStatusIjazah;
    private ImageView imgStatusTN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View setview=inflater.inflate(R.layout.activity_upload_t_dan_i,container,false);
        imgStatusIjazah = setview.findViewById(R.id.imgsIjazah);
        imgStatusTN= setview.findViewById(R.id.imgsTN);
        statusIjazah = setview.findViewById(R.id.statusIjazah);
        statusTN = setview.findViewById(R.id.statusTN);
        bPengiriman = setview.findViewById(R.id.bPengiriman);
        bUploadIjazah = setview.findViewById(R.id.bIjazah);
        bUploadTN= setview.findViewById(R.id.bTN);



        getIjazah();
        getTranskripNilai();

        bUploadIjazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IuploadActivity.class);
                startActivity(intent);
            }
        });
        bUploadTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TuploadActivity.class);
                startActivity(intent);
            }
        });
        bPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TarifActivity.class);
                startActivity(intent);
            }
        });
        return setview;
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
