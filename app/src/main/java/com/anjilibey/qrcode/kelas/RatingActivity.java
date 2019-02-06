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
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.HistoryList;
import com.anjilibey.qrcode.model.PertemuanId;
import com.anjilibey.qrcode.model.PertemuanIds;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.anjilibey.qrcode.Api.UtilsApi.BASE_URL_API;

public class RatingActivity extends AppCompatActivity {
    String idDetail, ratingan, token, idPertemuan;
    BaseApiService mApiService;
    RatingBar mRatingBar;
    EditText mFeedback;
    TextView mid, mcapaian, mkesesuaian, mketerangan, mwaktum, mwaktus, mIdPertemuan, mRating, mKomentar, mTgl, mRatingScale, mNip, mNama, mNo;
    Button mSendFeedback;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etKomentar);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        mIdPertemuan = findViewById(R.id.ratId);
        mRating = findViewById(R.id.ratRating);
        mKomentar = findViewById(R.id.ratKomentar);
        mTgl = findViewById(R.id.ratTgl);
        mid = findViewById(R.id.ratIdP);
        mcapaian = findViewById(R.id.ratCap);
        mkesesuaian = findViewById(R.id.ratKes);
        mketerangan = findViewById(R.id.ratKet);
        mwaktum = findViewById(R.id.ratWakmul);
        mwaktus = findViewById(R.id.ratWaksel);
        mNama = findViewById(R.id.ratDosen);
        mNip = findViewById(R.id.ratNip);
        mNo = findViewById(R.id.ratNo);

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
        sharedPrefManager = new SharedPrefManager(RatingActivity.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);

        //get Id Detail
        Intent mIntent = getIntent();
        idDetail = mIntent.getStringExtra("idDetail");
        mid.setText(idDetail);

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


        getResultDetail();

    }

    private void getResultDetail() {
        final ProgressDialog loading = ProgressDialog.show(RatingActivity.this, "Fetching Data","Please wait..",false,false);
        mApiService.showDetail("Bearer "+token,
                idDetail).enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<History> detail = response.body().getSementaraArrayList();
                    for (History data : detail) {
                        mRating.setText(data.getRating());
                        mKomentar.setText(data.getKomentar());
                        mTgl.setText(data.getCreated_at());
                        idPertemuan = data.getId_pertemuan();
                        mIdPertemuan.setText(idPertemuan);
                    }
                    getResultPertemuan();
                }
                else {
                    loading.dismiss();
                    Log.d("hasilnya: ", "gagal");
                }
            }
            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                loading.dismiss();
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }

        });

    }

    private void getResultPertemuan() {
        final ProgressDialog loading = ProgressDialog.show(RatingActivity.this, "Fetching Data","Please wait..",false,false);
        mApiService.showPertemuan("Bearer "+token,
                idPertemuan).enqueue(new Callback<PertemuanIds>() {
            @Override
            public void onResponse(Call<PertemuanIds> call, Response<PertemuanIds> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<PertemuanId> pertemuanId = response.body().getPertemuan();
                    for (PertemuanId data : pertemuanId) {
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
        final Intent intent = new Intent(RatingActivity.this, QrScanner.class);
        mApiService.updateDetail(
                "Bearer " + token,
                idDetail,
                ratingan,
                mFeedback.getText().toString()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RatingActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(RatingActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(RatingActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
