package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Materi {
    @SerializedName("id")
    public String id;
    @SerializedName("id_jdwl")
    public String id_jdwl;
    @SerializedName("id_pegawai")
    public String id_pegawai;
    @SerializedName("kesesuaian_rkps")
    public String kesesuaian_rkps;
    @SerializedName("capaian")
    public String capaian;
    @SerializedName("tanggal")
    public String tanggal;
    @SerializedName("waktu_mulai")
    public String waktu_mulai;
    @SerializedName("waktu_selesai")
    public String waktu_selesai;
    @SerializedName("keterangan")
    public String keterangan;
    @SerializedName("materi")
    public String materi;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("id_jadwal")
    public String id_jadwal;
    @SerializedName("hari")
    public String hari;
    @SerializedName("id_kls")
    public String id_kls;
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

    public String getId_kls() {
        return id_kls;
    }

    public void setId_kls(String id_kls) {
        this.id_kls = id_kls;
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

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getKesesuaian_rkps() {
        return kesesuaian_rkps;
    }

    public void setKesesuaian_rkps(String kesesuaian_rkps) {
        this.kesesuaian_rkps = kesesuaian_rkps;
    }

    public String getCapaian() {
        return capaian;
    }

    public void setCapaian(String capaian) {
        this.capaian = capaian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
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




}
