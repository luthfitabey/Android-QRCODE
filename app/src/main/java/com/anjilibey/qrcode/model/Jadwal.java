package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("id")
    public String id;
    @SerializedName("kelas")
    public String kelas;
    @SerializedName("kapasitas")
    public String kapasitas;
    @SerializedName("paket_semester")
    public String paket_semester;
    @SerializedName("id_th")
    public String id_th;
    @SerializedName("kode_mk")
    public String kode_mk;
    @SerializedName("nip_dosen")
    public String nip_dosen;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("id_jadwal")
    public String id_jadwal;
    @SerializedName("hari")
    public String hari;
    @SerializedName("id_kelas")
    public String id_kelas;
    @SerializedName("id_sesi")
    public String id_sesi;
    @SerializedName("id_ruang")
    public String id_ruang;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getPaket_semester() {
        return paket_semester;
    }

    public void setPaket_semester(String paket_semester) {
        this.paket_semester = paket_semester;
    }

    public String getId_th() {
        return id_th;
    }

    public void setId_th(String id_th) {
        this.id_th = id_th;
    }

    public String getKode_mk() {
        return kode_mk;
    }

    public void setKode_mk(String kode_mk) {
        this.kode_mk = kode_mk;
    }

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getId_sesi() {
        return id_sesi;
    }

    public void setId_sesi(String id_sesi) {
        this.id_sesi = id_sesi;
    }

    public String getId_ruang() {
        return id_ruang;
    }

    public void setId_ruang(String id_ruang) {
        this.id_ruang = id_ruang;
    }
}
