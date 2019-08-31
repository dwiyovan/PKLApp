package com.example.pklapp.API;

import com.example.pklapp.Model.City.ItemCity;
import com.example.pklapp.Model.Cost.ItemCost;
import com.example.pklapp.Model.InsertResponse;
import com.example.pklapp.Model.Province.ItemProvince;
import com.example.pklapp.Model.legalisir.Ijazah;
import com.example.pklapp.Model.legalisir.TranskripNilai;
import com.example.pklapp.Model.legalisir.Transaksi;


import java.util.List;

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

    @FormUrlEncoded
    @POST("insert_legalisir.php")
    Call<InsertResponse> savetransaction(
           @Field("id_pemesan") String id_pemesan,
           @Field("total_berat") int berat,
           @Field("ongkos_kirim") int ongkir,
           @Field("total_harga") int total_harga,
           @Field("total_pembayaran") int total_bayar,
           @Field("provinsi") String provinsi,
           @Field("kota") String kota,
           @Field("jalan") String jalan,
           @Field("kode_pos") String kode_pos
    );



    @GET("ijazah.php")
    Call<Ijazah> getStatusIjazah(
            @Query("key") String parameter
    );

    @GET("transkripNilai.php")
    Call<TranskripNilai> getStatusTranksripNilai(
            @Query("key") String parameter
    );

    @GET("transaksi.php")
    Call<Transaksi> getStatusTransaksi(
            @Query("key") String parameter
    );
    @GET("transaksi.php")
    Call<List<Transaksi>> getStatusTransaksiAll(

    );

}

