package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPass extends AppCompatActivity {
EditText mPass, mCPass;
Button mSimpan;
BaseApiService mApiService;
ProgressDialog loading;
SharedPrefManager sharedPrefManager;
String token, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pass);
        mApiService = UtilsApi.getAPIService();
        mPass = findViewById(R.id.ubahPass);
        mCPass = findViewById(R.id.ubahCPass);
        mSimpan = findViewById(R.id.btnUbah);

        sharedPrefManager = new SharedPrefManager(UbahPass.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token ubah", token);
//
        Intent intent = getIntent();
        id = intent.getStringExtra("nganu");
        Log.d("ikilo", id);
    }

    public void btnUbah(View view) {
        String a = mPass.getText().toString().trim();
        String b = mCPass.getText().toString().trim();
        String c = mCPass.getText().toString();
        Log.d("hoho pass", c);
        if (a.isEmpty()) {
            mPass.setError("Harap masukkan Password");
            mPass.requestFocus();
            return;
        }
        if (b.isEmpty()) {
            mCPass.setError("Harap masukkan password");
            mCPass.requestFocus();
            return;
        }
        if (b.length() < 4) {
            mPass.setError("Password anda terlalu pendek");
            mPass.requestFocus();
            return;
        }
        if (!b.equals(a)) {
            mPass.setError("Password anda tidak sama");
            mPass.requestFocus();
            return;
        }
        else {
            loading = ProgressDialog.show(UbahPass.this, null, "Harap Tunggu...", true, false);
            mApiService.userRequest(
                    "application/json",
                    "Bearer " + token,
                    id,
                    c
            ).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        loading.dismiss();
                        Toast.makeText(UbahPass.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UbahPass.this, Dashboard.class));
                        finish();
                    } else {
                        loading.dismiss();
                        Toast.makeText(UbahPass.this, "Maaf data anda salah", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                    loading.dismiss();
                }
            });
        }
    }
}
