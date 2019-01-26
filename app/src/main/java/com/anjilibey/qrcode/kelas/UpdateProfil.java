package com.anjilibey.qrcode.kelas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;

import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateProfil extends AppCompatActivity implements Serializable {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    String token;
    EditText metangkatan, metnif, metalamat, metprodi, metnik, metnpwp, metnorek, metnarek, metnotelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);
        metalamat = findViewById(R.id.pfalamat);
        metangkatan = findViewById(R.id.pfangkatan);
        metnarek = findViewById(R.id.pfnarek);
        metprodi = findViewById(R.id.pfprodi);
        metnif = findViewById(R.id.pfnif);
        metnik = findViewById(R.id.pfnik);
        metnpwp = findViewById(R.id.pfnpwp);
        metnorek = findViewById(R.id.pfnorek);
        metnotelp = findViewById(R.id.pfnotelp);

        //penting
        mApiService = UtilsApi.getAPIService();
        //token
        sharedPrefManager = new SharedPrefManager(UpdateProfil.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);
    }

    public void pfsave(View view) {
//        mApiService.profilRequest(
//                metnif.getText().toString(),
//                metangkatan.getText().toString(),
//                metprodi.getText().toString(),
//                metnik.getText().toString(),
//                metalamat.getText().toString(),
//                metnorek.getText().toString(),
//                metnarek.getText().toString(),
//                metnpwp.getText().toString(),
//                metnotelp.getText().toString())
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(UpdateProfil.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(UpdateProfil.this, ProfilFragment.class);
//                            startActivity(intent);
//                        } else
//                            Toast.makeText(UpdateProfil.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
//                        Toast.makeText(UpdateProfil.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                    }
//                });

        mApiService.profilRequest(
                "Application/json",
                "Bearer "+token,
                metnif.getText().toString(),
                metangkatan.getText().toString(),
                metprodi.getText().toString(),
                metnik.getText().toString(),
                metalamat.getText().toString(),
                metnorek.getText().toString(),
                metnarek.getText().toString(),
                metnpwp.getText().toString(),
                metnotelp.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateProfil.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(UpdateProfil.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(UpdateProfil.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}