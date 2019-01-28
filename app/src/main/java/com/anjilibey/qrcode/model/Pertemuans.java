package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pertemuans {
    @SerializedName("values")
    public List<Pertemuan> pertemuans;

    public List<Pertemuan> getPertemuan() {
        return pertemuans;
    }

    public void setPertemuan(List<Pertemuan> pertemuans) {
        this.pertemuans = pertemuans;
    }
    public Pertemuans(List<Pertemuan> pertemuans ){
        this.pertemuans = pertemuans;
    }

}

