package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Kelas {
    @SerializedName("id")
    public String id;
    @SerializedName("role")
    public String role;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("id_mhs")
    public String id_mhs;
    @SerializedName("id_kls")
    public String id_kls;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getId_mhs() {
        return id_mhs;
    }

    public void setId_mhs(String id_mhs) {
        this.id_mhs = id_mhs;
    }

    public String getId_kls() {
        return id_kls;
    }

    public void setId_kls(String id_kls) {
        this.id_kls = id_kls;
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

}
