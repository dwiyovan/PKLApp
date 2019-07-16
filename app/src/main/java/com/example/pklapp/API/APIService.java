package com.example.pklapp.API;

import com.example.pklapp.Model.ItemCost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST ("cost")
    Call<ItemCost> getCost(@Field("key") String keygen,
                           @Field("origin") String origin,
                           @Field("destination") String destination,
                           @Field("weight") String weight,
                           @Field("courier") String courier);

}
