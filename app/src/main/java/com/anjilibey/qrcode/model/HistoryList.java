package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class HistoryList {
    @SerializedName("values")
    private ArrayList<History> values;

    public ArrayList<History> getSementaraArrayList() {
        return values;
    }

    public void setSementaraArrayList(ArrayList<History> sementaraArrayList) {
        this.values = sementaraArrayList;
    }

}

