package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Adapter.DataAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.model.HistoryList;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.History;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    ProgressDialog loading;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    SharedPrefManager sharedPrefManager;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(HistoryActivity.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("history token", token);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Riwayat Presensi");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getResultListDosen();
    }

    private void getResultListDosen(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.getJSON("Bearer "+token).enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    generateEmployeeList(response.body().getSementaraArrayList());
                } else {
                    loading.dismiss();
                    Toast.makeText(HistoryActivity.this, "Data riwayat kosong", Toast.LENGTH_SHORT).show();
                }
        }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(HistoryActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateEmployeeList(ArrayList<History> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_sementara_list);

        adapter = new DataAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
