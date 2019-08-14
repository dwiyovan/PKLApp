package com.example.pklapp.Model.legalisir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranskripNilai {
    @SerializedName("nomor_ijazah")
    @Expose
    private String nomorIjazah;
    @SerializedName("tanggal_transkrip")
    @Expose
    private String tanggalTranskrip;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("dokumen_transkrip")
    @Expose
    private String dokumenTranskrip;
    @SerializedName("validasi")
    @Expose
    private String validasi;
    @SerializedName("catatan")
    @Expose
    private String catatan;

    public String getNomorIjazah() {
        return nomorIjazah;
    }

    public void setNomorIjazah(String nomorIjazah) {
        this.nomorIjazah = nomorIjazah;
    }

    public String getTanggalTranskrip() {
        return tanggalTranskrip;
    }

    public void setTanggalTranskrip(String tanggalTranskrip) {
        this.tanggalTranskrip = tanggalTranskrip;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getDokumenTranskrip() {
        return dokumenTranskrip;
    }

    public void setDokumenTranskrip(String dokumenTranskrip) {
        this.dokumenTranskrip = dokumenTranskrip;
    }

    public String getValidasi() {
        return validasi;
    }

    public void setValidasi(String validasi) {
        this.validasi = validasi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

}