package com.example.pklapp.Model.Province;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemProvince {
    @SerializedName("rajaongkir")
    @Expose
    private Rajaongkir rajaongkir;

    public ItemProvince(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    public Rajaongkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }
}
