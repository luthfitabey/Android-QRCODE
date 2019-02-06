package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

public class Cek {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    public String id;
}
