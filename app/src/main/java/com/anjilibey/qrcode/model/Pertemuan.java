package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Pertemuan {

    @SerializedName("values")
    public String values;
    @SerializedName("id")
    public String id;
    @SerializedName("id_jadwal")
    public String id_jadwal;

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    //    @SerializedName("id_pegawai")
//    public String id_pegawai;
    @SerializedName("nip_dosen")
    public String nip_dosen;
    @SerializedName("kesesuaian_rkps")
    public String kesesuaian_rkps;
    @SerializedName("capaian")
    public String capaian;
    @SerializedName("tanggal")
    public String tanggal;
    @SerializedName("waktu_selesai")
    public String waktu_selesai;
    @SerializedName("waktu_mulai")
    public String waktu_mulai;
    @SerializedName("keterangan")
    public String keterangan;
    @SerializedName("materi")
    public String materi;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;



    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCapaian() {
        return capaian;
    }

    public void setCapaian(String capaian) {
        this.capaian = capaian;
    }

    public String getKesesuaian_rkps() {
        return kesesuaian_rkps;
    }

    public void setKesesuaian_rkps(String kesesuaian_rkps) {
        this.kesesuaian_rkps = kesesuaian_rkps;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
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

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

//    public String getId_pegawai() {
//        return id_pegawai;
//    }
//
//    public void setId_pegawai(String id_pegawai) {
//        this.id_pegawai = id_pegawai;
//    }

}
