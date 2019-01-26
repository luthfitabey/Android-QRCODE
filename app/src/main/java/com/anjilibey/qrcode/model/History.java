package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class History {
    @SerializedName("id")
    public String id;
    @SerializedName("rating")
    public String rating;
    @SerializedName("komentar")
    public String komentar;
    @SerializedName("imei")
    public String imei;
    @SerializedName("id_mhs")
    public String id_mhs;
    @SerializedName("id_status")
    public String id_status;
    @SerializedName("id_pertemuan")
    public String id_pertemuan;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("history")
    public String history;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getId_mhs() {
        return id_mhs;
    }

    public void setId_mhs(String id_mhs) {
        this.id_mhs = id_mhs;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }


    public String getId_pertemuan() {
        return id_pertemuan;
    }

    public void setId_pertemuan(String id_pertemuan) {
        this.id_pertemuan = id_pertemuan;
    }

    public String getId_status() {
        return id_status;
    }

    public void setId_status(String id_status) {
        this.id_status = id_status;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}
