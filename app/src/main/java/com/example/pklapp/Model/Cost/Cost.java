package com.example.pklapp.Model.Cost;

import  java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cost {

    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private List<CostSuccess> cost=null;


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CostSuccess> getCost() {
        return cost;
    }

    public void setCost(List<CostSuccess> cost) {
        this.cost = cost;
    }
}



