package com.anjilibey.qrcode.kelas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;


import java.io.Serializable;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AbsensiActivity extends AppCompatActivity implements Serializable{
    BaseApiService mApiService;
    TextView mid, mcapaian, mkesesuaian, mmateri, mketerangan, mwaktum, mwaktus, mniu, mnama, mprodi, mangakatan;
    TelephonyManager tm;
    String imei, hasil, ratingan;
    RatingBar mRatingBar;
    TextView mRatingScale;
    TextView mLink;
    EditText mFeedback;
    Button mSendFeedback;
    SharedPrefManager sharedPrefManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        //find view
        mid = findViewById(R.id.txtid);
        mcapaian = findViewById(R.id.txtcapaian);
        mkesesuaian = findViewById(R.id.txtkesesuaian);
        mmateri = findViewById(R.id.txtmateri);
        mketerangan = findViewById(R.id.txtketerangan);
        mwaktum = findViewById(R.id.txtwaktua);
        mwaktus = findViewById(R.id.txtwaktub);
        mniu = findViewById(R.id.txtniu);
        mnama = findViewById(R.id.txtnama);
        mprodi = findViewById(R.id.txtprodi);
        mangakatan = findViewById(R.id.txtangkatan);
        mLink = findViewById(R.id.txtLink);
        //penting api
        mApiService = UtilsApi.getAPIService();
        //shared pref
        sharedPrefManager = new SharedPrefManager(AbsensiActivity.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);

        //ambil id pertemuan dari qrscaner
        Intent intent = getIntent();
        hasil = intent.getStringExtra("hasil");
        mid.setText(hasil);

        //ambil imei
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        imei = tm.getDeviceId();

        //rating
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etKomentar);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Sangat Buruk");
                        ratingan = "1";
                        break;
                    case 2:
                        mRatingScale.setText("Buruk");
                        ratingan = "2";
                        break;
                    case 3:
                        mRatingScale.setText("Cukup");
                        ratingan = "3";
                        break;
                    case 4:
                        mRatingScale.setText("Bagus");
                        ratingan = "4";
                        break;
                    case 5:
                        mRatingScale.setText("Sangat Bagus, saya suka");
                        ratingan = "5";
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

    //clickable link
        mLink.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void btnScan(View view) {
        Intent intent = new Intent(AbsensiActivity.this, QrScanner.class);
        startActivity(intent);
    }

    public void btnSubmit(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(AbsensiActivity.this, "Pastikan anda telah menuliskan komentar dan melekukan rating", Toast.LENGTH_LONG).show();
                }
                else {
                    mApiService.detailRequest(
                            "Application/json",
                            "Bearer "+token,
                            ratingan,
                            mFeedback.getText().toString(),
                            imei,
                            mid.getText().toString()
                    ).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AbsensiActivity.this, "Berhasil Disimpan, Terimakasih atas feedback anda", Toast.LENGTH_SHORT).show();
                                mFeedback.setText("");
                                mRatingBar.setRating(0);
                            } else
                                Toast.makeText(AbsensiActivity.this, "Gagal Disimpan, pastikan anda tidak melakukan presensi di handphone teman anda", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                            Toast.makeText(AbsensiActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
    }
}
