package com.example.pklapp.model;

public class Legalisir {
    private String nama;
    private String nim;
  //  private String date;
    private String berkas;
    private String imageUrl;

    public Legalisir() {

    }

    public Legalisir(String nama, String nim, String berkas, String imageUrl) {
        this.nama = nama;
        this.nim = nim;
        this.berkas = berkas;
        this.imageUrl = imageUrl;
    }

    public String getNama() {
        return nama;
    }

    public String getnim() {
        return nim;
    }

    public String getBerkas() {
        return berkas;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setnim(String nim) {
        this.nim = nim;
    }

    public void setBerkas(String berkas) {
        this.berkas = berkas;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

