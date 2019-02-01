package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Adapter.DataAdapter;
import com.anjilibey.qrcode.Adapter.KelasAdapter;
import com.anjilibey.qrcode.Adapter.MateriAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.HistoryList;
import com.anjilibey.qrcode.model.Kelas;
import com.anjilibey.qrcode.model.KelasList;
import com.anjilibey.qrcode.model.Materi;
import com.anjilibey.qrcode.model.MateriList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelasActivity extends AppCompatActivity  {
//    public static final String EXTRA_KELAS = "kelas";
//    public static final String EXTRA_KODE = "kode";
//    public static final String EXTRA_SEMESTER = "semester";
//    public static final String EXTRA_NIP = "nip";
    private ArrayList<Kelas> result;
    ProgressDialog loading;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private KelasAdapter adapter;
    SharedPrefManager sharedPrefManager;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(KelasActivity.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("kelas token", token);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Pilih Kelas");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        getResultListKelas();
    }

    private void getResultListKelas() {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.getKelas( "Bearer "+token).enqueue(new Callback<KelasList>() {
            @Override
            public void onResponse(Call<KelasList> call, Response<KelasList> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    generateKelasList(response.body().getSementaraArrayList());
                } else {
                    loading.dismiss();
                    Toast.makeText(KelasActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KelasList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(KelasActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateKelasList(ArrayList<Kelas> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_kelas);
        adapter = new KelasAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KelasActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener(KelasActivity.this);
    }
//    @Override
//    public void onItemClick(int position) {
//        Intent detailIntent = new Intent(this, MateriActivity.class);
//        Kelas clickedItem = result.get(position);
//
//        detailIntent.putExtra(EXTRA_KELAS, clickedItem.getKelas());
//        detailIntent.putExtra(EXTRA_KODE, clickedItem.getKode_mk());
//        detailIntent.putExtra(EXTRA_SEMESTER, clickedItem.getPaket_semester());
//        detailIntent.putExtra(EXTRA_NIP, clickedItem.getNip_dosen());
//        startActivity(detailIntent);
//    }
}
