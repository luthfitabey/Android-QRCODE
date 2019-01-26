package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JadwalList {
    @SerializedName("values")
    private ArrayList<Jadwal> values;

    public ArrayList<Jadwal> getJadwalArrayList() {
        return values;
    }

    public void setJadwalArrayList(ArrayList<Jadwal> jadwalArrayList) {
        this.values = jadwalArrayList;
    }
}
