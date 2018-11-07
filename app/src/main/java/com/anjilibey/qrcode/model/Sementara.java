package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Sementara {
    @SerializedName("id")
    public String id;
    @SerializedName("semester")
    public String semester;
    @SerializedName("matkul")
    public String matkul;
    @SerializedName("ruang")
    public String ruang;
    @SerializedName("dosen")
    public String dosen;
    @SerializedName("niu")
    public String niu;

    public String getNiu() {
        return niu;
    }
    public void setNiu(String niu) {
        this.niu = niu;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMatkul() {
        return matkul;
    }
    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getDosen() {
        return dosen;
    }
    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getRuang() {
        return ruang;
    }
    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getSemester() {
        return semester;
    }

}
