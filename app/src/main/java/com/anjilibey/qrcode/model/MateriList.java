package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MateriList {
    @SerializedName("values")
    private ArrayList<Materi> values;

    public ArrayList<Materi> getMateriArrayList() {
        return values;
    }

    public void setSementaraArrayList(ArrayList<Materi> sementaraArrayList) {
        this.values = sementaraArrayList;
    }
}
