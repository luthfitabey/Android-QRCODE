package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CekList {
    @SerializedName("result")
    private ArrayList<Cek> result;

    public ArrayList<Cek> getSementaraArrayList() {
        return result;
    }

    public void setSementaraArrayList(ArrayList<Cek> sementaraArrayList) {
        this.result = sementaraArrayList;
    }
}
