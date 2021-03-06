package com.anjilibey.qrcode.kelas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.anjilibey.qrcode.model.Pertemuan;
import com.anjilibey.qrcode.model.Pertemuans;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.anjilibey.qrcode.Api.UtilsApi.BASE_URL_API;

public class AbsensiActivity extends AppCompatActivity {
    BaseApiService mApiService;
    TextView mid, mcapaian, mkesesuaian, mmateri, mketerangan, mwaktum, mwaktus, mniu, mnama, mprodi, mangakatan, mRatingScale, mLink, msisawaktu;
    TelephonyManager tm;
    String ratingan, hasil, token;
    static String imei;
    RatingBar mRatingBar;
    EditText mFeedback;
    Button mSendFeedback;
    SharedPrefManager sharedPrefManager;
    String base_url = BASE_URL_API;
    private int PHONE_STATE_CODE=1;
    String waktuMulai, waktuSelesai;
//    @SuppressLint({"HardwareIds", "MissingPermission"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
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
        msisawaktu = findViewById(R.id.txtsisawaktu);
//        mnip = findViewById(R.id.txtnip);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etKomentar);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Data Presensi");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
        //ambil id pertemuan dari qrscaner
        Intent intent = getIntent();
        hasil = intent.getStringExtra("hasil");
        mid.setText(hasil);

        //penting api
        mApiService = UtilsApi.getAPIService();
        //token
        sharedPrefManager = new SharedPrefManager(AbsensiActivity.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);

        //imei
        generateImei();

        //clickable link
        mLink.setMovementMethod(LinkMovementMethod.getInstance());
        //untuk retrofit
        getResultPertemuan();
//      api get untuk asynctask
        String url = base_url+"/api/profile";
        FetchData fetchData = new FetchData();
        fetchData.execute(url);
    }

    private void generateImei(){
        //ambil imei
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more Details.
            reqPhonePermission();
            return;
        }
        imei = tm.getDeviceId();
    }

    private void reqPhonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)){
            new AlertDialog.Builder(this).setTitle("permission needed").setMessage("this permission needed")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(AbsensiActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},PHONE_STATE_CODE);

                        }
                    }) .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},PHONE_STATE_CODE);
        }
    }

    private void getResultPertemuan() {
        final ProgressDialog loading = ProgressDialog.show(AbsensiActivity.this, "Fetching Data","Please wait..",false,false);
        mApiService.getPertemuan("Bearer "+token,
                hasil).enqueue(new Callback<Pertemuans>() {
            @Override
            public void onResponse(Call<Pertemuans> call, Response<Pertemuans> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<Pertemuan> pertemuan = response.body().getPertemuan();
                    for (Pertemuan data : pertemuan) {
                        Log.d("id haha: ", data.getId());
                        mcapaian.setText(data.getCapaian());
                        mkesesuaian.setText(data.getKesesuaian_rkps());
                        mmateri.setText(data.getMateri());
                        mketerangan.setText(data.getKeterangan());
                        mwaktum.setText(data.getWaktu_mulai());
                        mwaktus.setText(data.getWaktu_selesai());
                        mLink.setText(data.getMateri());
                        waktuMulai=data.getWaktu_mulai();
                        waktuSelesai=data.getWaktu_selesai();
                    }
                    getWaktu();
                }
                else {
                    loading.dismiss();
                    Intent intent = new Intent(AbsensiActivity.this, QrScanner.class);
                    startActivity(intent);
                    Log.d("hasilnya: ", "gagal");
                }
            }
            @Override
            public void onFailure(Call<Pertemuans> call, Throwable t) {
                loading.dismiss();
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }

        });
    }
    public class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.connect();

                int response = connection.getResponseCode();
                Log.d("DEBUG1", "RESPONSE CODE : " + response);

                //mendownload data yang berupa String
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                StringBuilder total = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();
                Log.d("DEBUG1", "RESULT :" + result);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String niu, angkatan, nama, prodi;
            try {
                JSONObject jsonObject = new JSONObject(s);
                niu = jsonObject.getJSONObject("profiles").getString("niu");
                angkatan = jsonObject.getJSONObject("profiles").getString("angkatan");
                nama = jsonObject.getJSONObject("profiles").getString("nama");
                prodi = jsonObject.getJSONObject("profiles").getString("prodi");
                Log.d("GetNiu", niu);
                mnama.setText(nama);
                mniu.setText(niu);
                mprodi.setText(prodi);
                mangakatan.setText(angkatan);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void btnScan(View view) {
        Intent intent = new Intent(AbsensiActivity.this, QrScanner.class);
        startActivity(intent);
        finish();
    }

    public void btnSubmit(View view) {
        cekWaktu();
    }
    public void getWaktu(){
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        String strDateFormat = "HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");

        Date date1 = null, date2 = null;
        try {
           date1 = df.parse(formattedDate);
           date2 = df.parse(waktuSelesai);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = date2.getTime()-date1.getTime();
        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes, seconds;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;
        String diffTime = (hours<10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        msisawaktu.setText(diffTime);
    }
    private void cekWaktu(){
        String sisa = msisawaktu.getText().toString();
        java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
        Date date1 = null, date2 =null;
        try {
            date1 = df.parse(sisa);
            date2 = df.parse("00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("sisa bosku", sisa);
        if(sisa.contains("-") || date1.getTime() == date2.getTime()){
            AlertDialog.Builder abc = new AlertDialog.Builder(this);
            abc.setTitle("Peringatan !");
            abc.setMessage("Waktu presensi anda telah habis, silahkan hubungi dosen");

            // add the buttons
            abc.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AbsensiActivity.this, QrScanner.class);
                    startActivity(intent);
                    finish();
                }
            });
            // create and show the alert dialog
            AlertDialog dialog = abc.create();
            dialog.show();
        }
        else if(date1.getTime() > date2.getTime()){
            if (mFeedback.getText().toString().isEmpty() || ratingan == null) {
                showAlertDialogButtonClicked();
            } else {
                submitUser();
            }
        }
    }


    private void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Peringatan !");
        builder.setMessage("Anda belum mengisi kolom rating dan komentar, jika anda memilih untuk melanjutkan maka rating dan komentar adalah null \n\n**Anda juga dapat melakukan update kolom rating dan komentar melalui riwayat presensi");

        // add the buttons
        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                submitDefault();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void submitDefault(){
        mApiService.detailRequest(
                "Application/json",
                "Bearer " + token,
                "",
                "",
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
                    Toast.makeText(AbsensiActivity.this, "Gagal Disimpan, pastikan anda: \n 1. Terdaftar di KRS \n 2. Menggunakan Handphone anda sendiri \n 3. Tidak melakukan presensi 2 kali ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(AbsensiActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void submitUser(){
        mApiService.detailRequest(
                "Application/json",
                "Bearer " + token,
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