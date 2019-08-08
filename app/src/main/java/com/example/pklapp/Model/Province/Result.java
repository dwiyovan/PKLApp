package com.example.pklapp.Model.Province;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("province_id")
    @Expose
    private String province_id;
    @SerializedName("province")
    @Expose
    private String province;

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

    public Result(String province_id, String province) {
        this.province_id = province_id;
        this.province = province;
    }
}
