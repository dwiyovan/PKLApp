package com.example.pklapp.API;

import com.example.pklapp.Model.City.ItemCity;
import com.example.pklapp.Model.Cost.ItemCost;
import com.example.pklapp.Model.Province.ItemProvince;
import com.example.pklapp.Model.legalisir.Ijazah;
import com.example.pklapp.Model.legalisir.TranskripNilai;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("province")
    @Headers("key:9a9d935dede69ae9232843a3d2a02e4b")
    Call<ItemProvince> getProvince();

    @GET("city")
    @Headers("key:9a9d935dede69ae9232843a3d2a02e4b")
    Call<ItemCity> getCity(@Query("province")String province);

    @FormUrlEncoded
    @POST ("cost")
    Call<ItemCost> getCost(@Field("key") String keygen,
                           @Field("origin") String origin,
                           @Field("destination") String destination,
                           @Field("weight") String weight,
                           @Field("courier") String courier);



    @GET("ijazah")
    Call<Ijazah> getStatusIjazah(
            @Query("key") String parameter
    );

    @GET("transkripNilai")
    Call<TranskripNilai> getStatusTranksripNilai(
            @Query("key2") String parameter
    );

}

