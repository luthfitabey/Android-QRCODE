package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.anjilibey.qrcode.Adapter.DataAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.model.SementaraList;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Sementara;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    ProgressDialog loading;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mApiService = UtilsApi.getAPIService();

        getResultListDosen();
    }

    private void getResultListDosen(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.getJSON().enqueue(new Callback<SementaraList>() {
            @Override
            public void onResponse(Call<SementaraList> call, Response<SementaraList> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    generateEmployeeList(response.body().getSementaraArrayList());
                } else {
                    loading.dismiss();
                    Toast.makeText(HistoryActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
        }

            @Override
            public void onFailure(Call<SementaraList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(HistoryActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateEmployeeList(ArrayList<Sementara> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_sementara_list);

        adapter = new DataAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
