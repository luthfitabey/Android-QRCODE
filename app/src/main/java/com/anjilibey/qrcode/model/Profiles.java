package com.anjilibey.qrcode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Profiles {
    @SerializedName("profiles")
    public List<Profil> profiles;

    public List<Profil> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profil> profiles) {
        this.profiles = profiles;
    }
    public Profiles(List<Profil> profiles ){
        this.profiles = profiles;
    }

}

