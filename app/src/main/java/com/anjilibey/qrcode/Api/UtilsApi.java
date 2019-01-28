package com.anjilibey.qrcode.Api;

import com.anjilibey.qrcode.kelas.AbsensiActivity;

public class UtilsApi {
    AbsensiActivity absen = new AbsensiActivity();
    String id = absen.getHs();

    public static final String BASE_URL_API = "http://10.203.246.152:8000";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
