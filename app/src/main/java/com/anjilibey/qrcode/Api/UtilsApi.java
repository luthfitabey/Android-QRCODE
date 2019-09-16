package com.anjilibey.qrcode.Api;


public class UtilsApi {
    public static final String BASE_URL_API = "http://10.33.35.204/PRESENSI/public/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
