package com.anjilibey.qrcode.Api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefManager {
    // Shared Preferences
    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";

    public static final String SP_TOKEN = "spToken";
    public static final String SP_ID= "spId";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void deleteSPString(String keySP){
        spEditor.clear().commit();
//        spEditor.clear();
//        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpToken(){
        return sp.getString(SP_TOKEN, "");
    }
    public String getSpId(){
        return sp.getString(SP_ID, "");
    }


    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
