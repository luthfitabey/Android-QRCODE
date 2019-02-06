package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.anjilibey.qrcode.model.Pertemuan;
import com.anjilibey.qrcode.model.PertemuanId;
import com.anjilibey.qrcode.model.PertemuanIds;
import com.anjilibey.qrcode.model.Pertemuans;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.anjilibey.qrcode.Api.UtilsApi.BASE_URL_API;

public class UpdateDetailP extends AppCompatActivity {
    String idP, rating, komentar, tanggal,  ratingan, token, idPertemuan;
    BaseApiService mApiService;
    RatingBar mRatingBar;
    EditText mFeedback;
    TextView mid, mcapaian, mkesesuaian, mmateri, mketerangan, mwaktum, mwaktus, mIDD, mRating, mKomentar, mTgl, mRatingScale, mNip, mNama, mNo;
    Button mSendFeedback;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail_p);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etKomentar);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        mIDD = findViewById(R.id.upId);
        mRating = findViewById(R.id.upRating);
        mKomentar = findViewById(R.id.upKomentar);
        mTgl = findViewById(R.id.upTgl);
        mid = findViewById(R.id.upIdp);
        mcapaian = findViewById(R.id.upCap);
        mkesesuaian = findViewById(R.id.upKes);
        mketerangan = findViewById(R.id.upKet);
        mwaktum = findViewById(R.id.upWakmul);
        mwaktus = findViewById(R.id.upWaksel);
        mNama = findViewById(R.id.upDosen);
        mNip = findViewById(R.id.upNip);
        mNo = findViewById(R.id.upNo);

        //Toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Ubah Data Rating & Komentar");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //APi
        mApiService = UtilsApi.getAPIService();

        //token
        sharedPrefManager = new SharedPrefManager(UpdateDetailP.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);

        //get Id Detail
        Intent mIntent = getIntent();
        idP = mIntent.getStringExtra("idDetail");
        rating = mIntent.getStringExtra("rating");
        komentar = mIntent.getStringExtra("komentar");
        tanggal = mIntent.getStringExtra("tanggal");
        idPertemuan = mIntent.getStringExtra("idPertemuan");
        mIDD.setText(idP);
        mRating.setText(rating);
        mKomentar.setText(komentar);
        mTgl.setText(tanggal);

        Log.d("id pertemuan", idPertemuan);


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRatingScale.setText(String.valueOf(rating));
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

        getResultPertemuan();

    }

    private void getResultPertemuan() {
        final ProgressDialog loading = ProgressDialog.show(UpdateDetailP.this, "Fetching Data","Please wait..",false,false);
        mApiService.showPertemuan("Bearer "+token,
                idPertemuan).enqueue(new Callback<PertemuanIds>() {
            @Override
            public void onResponse(Call<PertemuanIds> call, Response<PertemuanIds> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<PertemuanId> pertemuanId = response.body().getPertemuan();
                    for (PertemuanId data : pertemuanId) {
                        Log.d("id haha: ", data.getId());
                        mid.setText(data.getId());
                        mcapaian.setText(data.getCapaian());
                        mkesesuaian.setText(data.getKesesuaian_rkps());
                        mketerangan.setText(data.getKeterangan());
                        mwaktum.setText(data.getWaktu_mulai());
                        mwaktus.setText(data.getWaktu_selesai());
                        mNama.setText(data.getNama());
                        mNip.setText(data.getNip());
                        mNo.setText(data.getNo_telp());
                    }
                }
                else {
                    loading.dismiss();
                    Log.d("hasilnya: ", "gagal");
                }
            }
            @Override
            public void onFailure(Call<PertemuanIds> call, Throwable t) {
                loading.dismiss();
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }

        });
    }

    public void btnSubmit(View view) {
        mApiService.updateDetail(
                "Bearer " + token,
                idP,
                ratingan,
                mFeedback.getText().toString()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateDetailP.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                } else
                    Toast.makeText(UpdateDetailP.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(UpdateDetailP.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
