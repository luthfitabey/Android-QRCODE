package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PertemuanIds {
    @SerializedName("values")
    public List<PertemuanId> pertemuanIds;

    public List<PertemuanId> getPertemuan() {
        return pertemuanIds;
    }

    public void setPertemuan(List<PertemuanId> pertemuans) {
        this.pertemuanIds = pertemuanIds;
    }
    public PertemuanIds(List<PertemuanId> pertemuanIds ){
        this.pertemuanIds = pertemuanIds;
    }

}
