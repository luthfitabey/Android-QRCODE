package com.anjilibey.qrcode.Api;

import com.anjilibey.qrcode.model.JSONResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BaseApiService {
    @Headers({
            "Accept:application/json",
            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4N2NiZmU0OTQwNTY5MGFjY2I5OTdmNWU3OGQ0NTYyZDg1OTg3NmI5MDc1YzBlMWFhOGI3ZDcwNjMyMTRjMzczYWNjYjBiYzhjMDZlY2E3In0.eyJhdWQiOiIxIiwianRpIjoiNzg3Y2JmZTQ5NDA1NjkwYWNjYjk5N2Y1ZTc4ZDQ1NjJkODU5ODc2YjkwNzVjMGUxYWE4YjdkNzA2MzIxNGMzNzNhY2NiMGJjOGMwNmVjYTciLCJpYXQiOjE1NDA4MzY0NjIsIm5iZiI6MTU0MDgzNjQ2MiwiZXhwIjoxNTcyMzcyNDYyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.KYaYPH72iNdGylllIO2mBaSRvaRf8HaMzdm0-fPuTu_X2qDC6vOBZMFzutxqQZiLd7izwkiqwC66DDCQh00CYHHK_0wSkTsGOpx_pQV13GoEVk7Pt8CLG4vWp0tqB44wBHj_KkJcyXfEitY5ur0RTj0jH5fehM93GLmWeyheys5bgDdATI3PId1HwLNI5RwBFsZKASkfQVPIEmY_OwkMz3wAAJnqdrID246L7qR68PSVeOn8d_9MoiH7Cf5u_5rWZp5czKWwNIqH1oKQ6kxs_X7dmFsRQulhvWNdVIGfStp5zaBT96a7e36FZKwe2aSeA6y02Wcte6O22fz-brrAAdv9PGi_udK4yTG_-ieoXWg9YHCEFWRRemc6oBXKySqQRmvPLZc__3375oyISTfAEPXiQEevawCCPyEKqe3YZOLz6P1j7Ch1Z8zBXA113ebRqiGiU77gSgwk6UIInhneKjoMb4nbu-HaU3Rwze26KB5jkYPxw2VZ81IosNppN7j7Rljd46dMqWH7nmlPdNOeP0_QpPUg3__3PoTmTrb539VCn5Z_0RNC_rkseSa9P44CiJ_61fRQQtOqbRAryXk0VcAD-zIrzrEoHjwPwcbsR5k-eLYxTFjKROIbahbG-82IugbQQPfKH1gbF7Hawm-a19VIN-YI7cKgzuGjo63IAjE"
    })
    @FormUrlEncoded
    @POST("/api/sementara")
    Call<ResponseBody> sementaraRequest(
            @Field("semester") String semester,
            @Field("matkul") String matkul,
            @Field("ruang") String ruang,
            @Field("dosen") String dosen,
            @Field("niu") String niu
    );
    @Headers({
            "Accept:application/json",
            "content-type:application/json",
            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4N2NiZmU0OTQwNTY5MGFjY2I5OTdmNWU3OGQ0NTYyZDg1OTg3NmI5MDc1YzBlMWFhOGI3ZDcwNjMyMTRjMzczYWNjYjBiYzhjMDZlY2E3In0.eyJhdWQiOiIxIiwianRpIjoiNzg3Y2JmZTQ5NDA1NjkwYWNjYjk5N2Y1ZTc4ZDQ1NjJkODU5ODc2YjkwNzVjMGUxYWE4YjdkNzA2MzIxNGMzNzNhY2NiMGJjOGMwNmVjYTciLCJpYXQiOjE1NDA4MzY0NjIsIm5iZiI6MTU0MDgzNjQ2MiwiZXhwIjoxNTcyMzcyNDYyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.KYaYPH72iNdGylllIO2mBaSRvaRf8HaMzdm0-fPuTu_X2qDC6vOBZMFzutxqQZiLd7izwkiqwC66DDCQh00CYHHK_0wSkTsGOpx_pQV13GoEVk7Pt8CLG4vWp0tqB44wBHj_KkJcyXfEitY5ur0RTj0jH5fehM93GLmWeyheys5bgDdATI3PId1HwLNI5RwBFsZKASkfQVPIEmY_OwkMz3wAAJnqdrID246L7qR68PSVeOn8d_9MoiH7Cf5u_5rWZp5czKWwNIqH1oKQ6kxs_X7dmFsRQulhvWNdVIGfStp5zaBT96a7e36FZKwe2aSeA6y02Wcte6O22fz-brrAAdv9PGi_udK4yTG_-ieoXWg9YHCEFWRRemc6oBXKySqQRmvPLZc__3375oyISTfAEPXiQEevawCCPyEKqe3YZOLz6P1j7Ch1Z8zBXA113ebRqiGiU77gSgwk6UIInhneKjoMb4nbu-HaU3Rwze26KB5jkYPxw2VZ81IosNppN7j7Rljd46dMqWH7nmlPdNOeP0_QpPUg3__3PoTmTrb539VCn5Z_0RNC_rkseSa9P44CiJ_61fRQQtOqbRAryXk0VcAD-zIrzrEoHjwPwcbsR5k-eLYxTFjKROIbahbG-82IugbQQPfKH1gbF7Hawm-a19VIN-YI7cKgzuGjo63IAjE"
    })
    @GET("/api/sementara")
    Call<JSONResponse> getJSON();
}
