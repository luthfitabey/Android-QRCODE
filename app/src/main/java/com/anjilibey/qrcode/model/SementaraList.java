package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SementaraList {
    @SerializedName("values")
    private ArrayList<Sementara> values;

    public ArrayList<Sementara> getSementaraArrayList() {
        return values;
    }

    public void setSementaraArrayList(ArrayList<Sementara> sementaraArrayList) {
        this.values = sementaraArrayList;
    }

}

