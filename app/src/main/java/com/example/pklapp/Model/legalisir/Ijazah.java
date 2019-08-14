package com.example.pklapp.Model.legalisir;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ijazah {

    @SerializedName("nomor_ijazah")
    @Expose
    private String nomorIjazah;
    @SerializedName("tanggal_ijazah")
    @Expose
    private String tanggalIjazah;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("dokumen_ijazah")
    @Expose
    private String dokumenIjazah;
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

    public String getTanggalIjazah() {
        return tanggalIjazah;
    }

    public void setTanggalIjazah(String tanggalIjazah) {
        this.tanggalIjazah = tanggalIjazah;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getDokumenIjazah() {
        return dokumenIjazah;
    }

    public void setDokumenIjazah(String dokumenIjazah) {
        this.dokumenIjazah = dokumenIjazah;
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