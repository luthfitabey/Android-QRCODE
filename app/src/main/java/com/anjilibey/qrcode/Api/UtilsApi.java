package com.anjilibey.qrcode.Api;

import com.anjilibey.qrcode.kelas.AbsensiActivity;

public class UtilsApi {
    public static final String BASE_URL_API = "http://10.203.253.239:8000";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
