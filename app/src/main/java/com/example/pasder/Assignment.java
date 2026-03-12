package com.example.pasder;

public class Assignment {
    private String namaPaket;
    private String penyewa;
    private String tanggal;
    private int gambar; // Untuk menyimpan gambar

    public Assignment(String namaPaket, String penyewa, String tanggal) {
        this.namaPaket = namaPaket;
        this.penyewa = penyewa;
        this.tanggal = tanggal;
    }

    public String getNamaPaket() { return namaPaket; }
    public String getPenyewa() { return penyewa; }
    public String getTanggal() { return tanggal; }
}