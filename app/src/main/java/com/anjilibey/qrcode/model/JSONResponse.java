package com.anjilibey.qrcode.model;

import com.anjilibey.qrcode.model.Sementara;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResponse {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Sementara> listSementara;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Sementara> getListSementara() {
        return listSementara;
    }
    public void setListDataKontak(List<Sementara> listSementara) {
        this.listSementara = listSementara;
    }
//  private Sementara[] absen;
//    public Sementara[] getAbsen() {
//        return absen;
//    }

}

