package com.anjilibey.qrcode.Api;

import com.anjilibey.qrcode.model.Cek;
import com.anjilibey.qrcode.model.CekList;
import com.anjilibey.qrcode.model.JadwalList;
import com.anjilibey.qrcode.model.HistoryList;
import com.anjilibey.qrcode.model.Kelas;
import com.anjilibey.qrcode.model.KelasList;
import com.anjilibey.qrcode.model.Materi;
import com.anjilibey.qrcode.model.MateriList;
import com.anjilibey.qrcode.model.PertemuanIds;
import com.anjilibey.qrcode.model.Pertemuans;
import com.anjilibey.qrcode.model.Profiles;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {

    //login
    @Headers({
           "Accept:application/json"
    })
    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> loginRequest(
            @Field("username") String username,
            @Field("password") String password
    );


    //Detail pertemuan
//    @Headers({
//            "Accept:application/json",
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
//    })
    @FormUrlEncoded
    @POST("/api/detailPertemuan")
    Call<ResponseBody> detailRequest(
            @Header("Accept") String Accept,
            @Header("Authorization") String Authorization,
            @Field("rating") String rating,
            @Field("komentar") String komentar,
            @Field("imei") String imei,
            @Field("id_pertemuan") String id_pertemuan
    );
    //get
    @Headers({
            "Accept:application/json",
            "content-type:application/json",
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZlZjUxMzMzOGJhN2QwNmQyM2U1NjI2NWYzZjIyNzA2Y2ZlOTY4MmU1ZjZmYjU3Y2YyNDA1YWNlOTNkYjNjZDAwMDZhNDBlMGY2NDgxNGNkIn0.eyJhdWQiOiIxIiwianRpIjoiNmVmNTEzMzM4YmE3ZDA2ZDIzZTU2MjY1ZjNmMjI3MDZjZmU5NjgyZTVmNmZiNTdjZjI0MDVhY2U5M2RiM2NkMDAwNmE0MGUwZjY0ODE0Y2QiLCJpYXQiOjE1NDgxNjE5NzYsIm5iZiI6MTU0ODE2MTk3NiwiZXhwIjoxNTc5Njk3OTc2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.j9i19eDKbWlNQvHlFBvn6OrBDqoT9fcTfrDvtGNNAd3kZ-k2FILvjZJhiQZB4DI8a3UhBIwIuykarJGI10f7nIUKUMMYEbSPCr4JbMa6SxJ9sOndPK-GOPCYHKA-GksSRizn19uQx30oYMtbX9_JZ7iycAoNnG5X52_KxVO4rY6JGMUr7RWR-yy53EklY-LZ1a9H_ec4ldw88YEHMeWzsfJ3g7ymKHOqfGXF4U-GLVP56grO6Ay480VpqnSY1HhM-o7o1Q0cTFgX6WPs8Jf5A9s0OdHN3cpwS3eNFx9z4N9zUlUfOfMCzZVogVG8yVhSGZl3L2CJb1brBhmSJPMUwG6g9N2ExK_3E4er4FqiZl5Mslo_ldRQZJ9r10pHXzyKxH5dQGZ96yNrv5-cG-YvSYSGS8Bu1dkCnKvFLYou4s5wGULDem8IbXz2vdAdDlSqOfMLdM6zLZUxFzI49BEW5FKrzcGUxgND3CKxQvFLxz4Wmb6a6H98ZxafWBxtnalpr8_yQnbCuEsfVH4vnG5wKD_xiocWYyHIRe7HI3M2PtXdS2Mi3Hmv37rMpYZjg_JBw_4uzuq_w9SUpJTaskuFrSd6pn079hjGSu0X4AltKsDyU-zkQAvMqgAsy-6YV-p94PKOo9ECXRtKSYH-xvNUwMULyS5j1PV9-d-9epZusO8"
    })
    @GET("/api/history")
    Call<HistoryList> getJSON(@Header("Authorization") String Authorization);


    //profil
//    @Headers({
//            "Accept:application/json",
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
//    })
    @FormUrlEncoded
    @POST("/api/profil")
    Call<ResponseBody> profilRequest(
            @Header("Accept") String Accept,
            @Header("Authorization") String Authorization,
            @Field("nif") String nif,
            @Field("angkatan") String angkatan,
            @Field("prodi") String prodi,
            @Field("nik") String nik,
            @Field("alamat") String alamat,
            @Field("no_rek") String no_rek,
            @Field("nama_rek") String nama_rek,
            @Field("npwp") String npwp,
            @Field("no_telp") String no_telp
    );

    //get
    @Headers({
            "Accept:application/json",
            "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @GET("/api/profile")
    Call<Profiles> getProfil(@Header("Authorization") String Authorization);

    //materi with search
//    @Headers({
//            "Accept:application/json",
//            "content-type:application/json"
////            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
//    })
//    @GET("/api/materi")
//    Call<MateriList> getMateri(@Header("Authorization") String Authorization);

    //jadwal
    @Headers({
            "Accept:application/json",
            "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @GET("/api/jadwal")
    Call<JadwalList> getJadwal(@Header("Authorization") String Authorization);
    @Headers({
            "Accept:application/json",
            "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @GET("/api/pertemuan/{id}")
    Call<Pertemuans> getPertemuan(@Header("Authorization") String Authorization, @Path("id") String id);

    @Headers({
            "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @FormUrlEncoded
    @POST("/api/user/{id}")
    Call<ResponseBody> userRequest(@Header("Accept") String Accept,@Header("Authorization") String Authorization, @Path("id") String id, @Field("password") String password);


    @Headers({
            "Accept:application/json",
            "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @GET("/api/materi")
    Call<KelasList> getKelas(@Header("Authorization") String Authorization);

    @Headers({
        "Accept:application/json",
        "content-type:application/json"
//            "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU2MGJiMzU1OThmYjNhMzlhOGY4NWFmYTY5MDAxODM5NTdjYWFlNGJlMzc5Y2U3MTFkN2IwZmRlZGM0NTk4MTQ1NWIwN2RjNTFkNDQ1MWFiIn0.eyJhdWQiOiIxIiwianRpIjoiNTYwYmIzNTU5OGZiM2EzOWE4Zjg1YWZhNjkwMDE4Mzk1N2NhYWU0YmUzNzljZTcxMWQ3YjBmZGVkYzQ1OTgxNDU1YjA3ZGM1MWQ0NDUxYWIiLCJpYXQiOjE1NDgxMjU2NzAsIm5iZiI6MTU0ODEyNTY3MCwiZXhwIjoxNTc5NjYxNjcwLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FbarxS54wJQeErkdUgGVhAM2i7vZnmZ-GTmeqHiieZ8ISv-ET4XqKVGZp399qDehtDaKTqyNcoaJNAv_C8cLr1EBJXu-lN-p8I65x6Q0W5v2MvNC13I-mZ5wbBxVjjzW8AqMm_ZNxZQY3ozsYqYKzlGuOpm6YY8KPAOd5wpuod6FhpbSrlx_p4823B28vSTqJ6p3QQ6mAijO281lpzm7COpv8V3HMbc1alRWLZT_T_vi7adxXyxy41BAoAcZyWqkI_SAP2ZpHoNXPFjKah6fyVbvC2hIDNcBWfZW1DHm-923kM5vx2dcqRLgzHx-pa-jqrm8IQxf_ZBb57Mhrs_JPcr89sfRq0z6Y77pMt9VTMycOV-S_DQqnsfeBaBduOZkym6koq3oS3YbwyrmhWd7hPr3s6jr3GaKb_kfFXNML59kTLpKiKKBMTlbP3WTQDBUIbKRhZtZk9SVTG5pfBYTGW7E-Xs1HNkUfYqDdM6XSUJwwdR-CrVoMCHMvpX1SPIfwGmre3o9bVko2xfrd6sm-mER9aucnymyVU1vGjLoe_rQbcjAtoB1RJGa4BW88R2m9ie0eF17aaLkCpo8WoN2pK0p1y4ejERvHG9k_i8JHSXINkQjtbfPP3RJa8rDDW3qfmG2CGx0yb7FolmWXB9UZ5XbNLZw91J1dKlo4Dq1Yww"
    })
    @GET("/api/materiId/{id}")
    Call<MateriList> getMateri(@Header("Authorization") String Authorization, @Path("id") String id);


    @Headers({
            "Accept:application/json"
    })
    @FormUrlEncoded
    @POST("/api/detailPertemuan/{id}")
    Call<ResponseBody> updateDetail(@Header("Authorization") String Authorization,
                                   @Path("id") String id,
                                   @Field("rating") String rating,
                                   @Field("komentar") String komentar);


    @Headers({
            "Accept:application/json",
            "content-type:application/json"
    })
    @GET("/api/ShowUpdatePertemuan/{id}")
    Call<PertemuanIds> showPertemuan(@Header("Authorization") String Authorization, @Path("id") String id);

    //cek rating
    @Headers({
            "Accept:application/json",
            "content-type:application/json"
    })
    @GET("/api/cek")
    Call<CekList> cekRating(@Header("Authorization") String Authorization);

    @Headers({
            "Accept:application/json",
            "content-type:application/json"
    })
    @GET("/api/detail/{id}")
    Call<HistoryList> showDetail(@Header("Authorization") String Authorization, @Path("id") String id);

}