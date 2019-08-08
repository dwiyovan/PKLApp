package com.example.pklapp.Model.Cost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OriginDetails {@SerializedName("city_id")
@Expose
private String city_id;
    @SerializedName("province_id")
    @Expose
    private String province_id;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("city_name")
    @Expose
    private String city_name;
    @SerializedName("postal_code")
    @Expose
    private String postal_code;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

}
