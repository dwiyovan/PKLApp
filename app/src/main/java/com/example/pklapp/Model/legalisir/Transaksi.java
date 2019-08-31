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
    @SerializedName("total_pembayaran")
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
    private Object nomorResi;
    @SerializedName("id_status")
    @Expose
    private String idStatus;
    @SerializedName("keterangan_status")
    @Expose
    private String keteranganStatus;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getTotalBerat() {
        return totalBerat;
    }

    public void setTotalBerat(String totalBerat) {
        this.totalBerat = totalBerat;
    }

    public String getOngkosKirim() {
        return ongkosKirim;
    }

    public void setOngkosKirim(String ongkosKirim) {
        this.ongkosKirim = ongkosKirim;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(String totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getTanggalTransfer() {
        return tanggalTransfer;
    }

    public void setTanggalTransfer(String tanggalTransfer) {
        this.tanggalTransfer = tanggalTransfer;
    }

    public String getJamTransfer() {
        return jamTransfer;
    }

    public void setJamTransfer(String jamTransfer) {
        this.jamTransfer = jamTransfer;
    }

    public String getJumlahTransfer() {
        return jumlahTransfer;
    }

    public void setJumlahTransfer(String jumlahTransfer) {
        this.jumlahTransfer = jumlahTransfer;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(String buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }

    public String getLayananPengiriman() {
        return layananPengiriman;
    }

    public void setLayananPengiriman(String layananPengiriman) {
        this.layananPengiriman = layananPengiriman;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }

    public void setStatusPesanan(String statusPesanan) {
        this.statusPesanan = statusPesanan;
    }

    public String getEstimasiPengiriman() {
        return estimasiPengiriman;
    }

    public void setEstimasiPengiriman(String estimasiPengiriman) {
        this.estimasiPengiriman = estimasiPengiriman;
    }

    public Object getNomorResi() {
        return nomorResi;
    }

    public void setNomorResi(Object nomorResi) {
        this.nomorResi = nomorResi;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getKeteranganStatus() {
        return keteranganStatus;
    }

    public void setKeteranganStatus(String keteranganStatus) {
        this.keteranganStatus = keteranganStatus;
    }

}