package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class KelasList {
    @SerializedName("values")
    private ArrayList<Kelas> values;

    public ArrayList<Kelas> getSementaraArrayList() {
        return values;
    }

    public void setSementaraArrayList(ArrayList<Kelas> sementaraArrayList) {
        this.values = sementaraArrayList;
    }

}

