package com.example.pklapp.Model.legalisir;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaksi {

    @SerializedName("id_transaksi")
    @Expose
    private String idTransaksi;
    @SerializedName("id_pemesan")
    @Expose
    private String idPemesan;
    @SerializedName("tanggal_transaksi")
    @Expose
    private String tanggalTransaksi;
    @SerializedName("total_berat")
    @Expose
    private String totalBerat;
    @SerializedName("ongkos_kirim")
    @Expose
    private String ongkosKirim;
    @SerializedName("total_harga")
    @Expose
    private String totalHarga;
    @SerializedName("total_Pembayaran")
    @Expose
    private String totalPembayaran;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("jalan")
    @Expose
    private String jalan;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("tanggal_transfer")
    @Expose
    private String tanggalTransfer;
    @SerializedName("jam_transfer")
    @Expose
    private String jamTransfer;
    @SerializedName("jumlah_transfer")
    @Expose
    private String jumlahTransfer;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("bukti_transfer")
    @Expose
    private String buktiTransfer;
    @SerializedName("layanan_pengiriman")
    @Expose
    private String layananPengiriman;
    @SerializedName("status_pesanan")
    @Expose
    private String statusPesanan;
    @SerializedName("estimasi_pengiriman")
    @Expose
    private String estimasiPengiriman;
    @SerializedName("nomor_resi")
    @Expose
    private String nomorResi;



    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void getTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(String buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }

    public void setStatusPesanan(String statusPesanan) {
        this.statusPesanan = statusPesanan;
    }

    public String getCatatan() {
        return nomorResi;
    }

    public void setCatatan(String nomorResi) { this.nomorResi = nomorResi;
    }

}