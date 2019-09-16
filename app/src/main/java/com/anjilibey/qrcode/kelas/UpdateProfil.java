package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.anjilibey.qrcode.Api.UtilsApi.BASE_URL_API;

public class UpdateProfil extends AppCompatActivity {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    String token;
    EditText metangkatan, metnif, metalamat, metprodi, metnik, metnpwp, metnorek, metnarek, metnotelp, metnama;
    String id_profil, angkatan, prodi, alamat, npwp, phone, nama, nik, na_rek, no_rek;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);
        metalamat = findViewById(R.id.upAlamat);
        metangkatan = findViewById(R.id.upAngkatan);
        metnarek = findViewById(R.id.upNarek);
        metprodi = findViewById(R.id.upProdi);
//        metnif = findViewById(R.id.upNif);
        metnik = findViewById(R.id.upNik);
        metnpwp = findViewById(R.id.upNpwp);
        metnorek = findViewById(R.id.upNorek);
        metnotelp = findViewById(R.id.upPhone);
        metnama = findViewById(R.id.upNama);
        //penting
        mApiService = UtilsApi.getAPIService();
        //token
        sharedPrefManager = new SharedPrefManager(UpdateProfil.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token update", token);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Ubah Profil");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //get data profil
        Intent mIntent = getIntent();
        id_profil  = mIntent.getStringExtra("id_profil");
//        nif = mIntent.getStringExtra("nif");
        angkatan = mIntent.getStringExtra("angkatan");
        prodi = mIntent.getStringExtra("prodi");
        alamat = mIntent.getStringExtra("address");
        npwp = mIntent.getStringExtra("npwp");
        phone = mIntent.getStringExtra("phone");
        nama = mIntent.getStringExtra("nama");
        nik = mIntent.getStringExtra("nik");
        no_rek = mIntent.getStringExtra("no_rek");
        na_rek = mIntent.getStringExtra("na_rek");
//        metnif.setText(nif);
        metangkatan.setText(angkatan);
        metprodi.setText(prodi);
        metalamat.setText(alamat);
        metnpwp.setText(npwp);
        metnotelp.setText(phone);
        metnama.setText(nama);
        metnik.setText(nik);
        metnorek.setText(no_rek);
        metnarek.setText(na_rek);


    }

    public void upSave(View view) {
        cek();
        loading = ProgressDialog.show(UpdateProfil.this, null, "Harap Tunggu...", true, false);
        mApiService.updateProfil(
                "Bearer "+token,
                id_profil,
//                metnif.getText().toString(),
                metangkatan.getText().toString(),
                metnama.getText().toString(),
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
                            loading.dismiss();
                            Intent intent = new Intent(UpdateProfil.this, ProfilFragment.class);
                            startActivity(intent);
//                            finish();
                        } else
                            Toast.makeText(UpdateProfil.this, "Gagal Disimpan, pastikan data tidak ada yang null", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(UpdateProfil.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void cek(){
        String nama2 = metnama.getText().toString().trim();
        String angkatan2 = metangkatan.getText().toString();
        String prodi2 = metprodi.getText().toString().trim();
        String notelp2 = metnotelp.getText().toString().trim();
        String alamat2 = metalamat.getText().toString().trim();
        String nik2 = metnik.getText().toString().trim();
        String npwp2 = metnpwp.getText().toString().trim();
        String norek2 = metnorek.getText().toString().trim();
        String narek2 = metnarek.getText().toString().trim();

        if (nama2.isEmpty() ) {
            metnama.setError("Harap masukkan data nama");
            metnama.requestFocus();
            return;
        }
        if (angkatan2.isEmpty() ) {
            metangkatan.setError("Harap masukkan data angkatan");
            metangkatan.requestFocus();
            return;
        }
        if (prodi2.isEmpty() ) {
            metprodi.setError("Harap masukkan data prodi");
            metprodi.requestFocus();
            return;
        }
        if (notelp2.isEmpty() ) {
            metnotelp.setError("Harap masukkan data no. telp");
            metnotelp.requestFocus();
            return;
        }
        if (alamat2.isEmpty() ) {
            metalamat.setError("Harap masukkan data alamat");
            metalamat.requestFocus();
            return;
        }
        if (nik2.isEmpty() ) {
            metnik.setError("Jika ingin mengosongkan isi dengan 'kosong'");
            metnik.requestFocus();
            return;
        }
        if (npwp2.isEmpty()) {
            metnpwp.setError("Jika ingin mengosongkan isi dengan 'kosong'");
            metnpwp.requestFocus();
            return;
        }
        if (norek2.isEmpty() ) {
            metnorek.setError("Jika ingin mengosongkan isi dengan 'kosong'");
            metnorek.requestFocus();
            return;
        }
        if (narek2.isEmpty() ) {
            metnarek.setError("Jika ingin mengosongkan isi dengan 'kosong'");
            metnarek.requestFocus();
            return;
        }
    }
}
