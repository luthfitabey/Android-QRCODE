package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    ProgressDialog loading;
    EditText metNiu, metPassword;
    Button btnLogin;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        metNiu = findViewById(R.id.etNomorInduk);
        metPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
    //penting
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(LoginActivity.this);


        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, Dashboard.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    public void btnlogin(View view) {
        String a = metNiu.getText().toString().trim();
        String b = metPassword.getText().toString().trim();
        if (a.isEmpty()) {
            metNiu.setError("Harap masukkan NIU");
            metNiu.requestFocus();
            return;
        }
        if (b.isEmpty()) {
            metPassword.setError("Harap masukkan password");
            metPassword.requestFocus();
            return;
        }
        if (b.length() < 4) {
            metPassword.setError("Password anda terlalu pendek");
            metPassword.requestFocus();
            return;
        }
        loading = ProgressDialog.show(LoginActivity.this, null, "Harap Tunggu...", true, false);
        mApiService.loginRequest("396283", "1234567890")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            String token = null;
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                token = jsonRESULTS.getJSONObject("success").getString("token");
                                Log.d("token", "berhasil");
                                Log.d("hasil token", token);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, token);
                                // Shared Pref ini berfungsi untuk menjadi trigger session login
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                startActivity(new Intent(LoginActivity.this, Dashboard.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Maaf data anda salah", Toast.LENGTH_SHORT).show();
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
