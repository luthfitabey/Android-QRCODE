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
import com.anjilibey.qrcode.Adapter.MateriAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.HistoryList;
import com.anjilibey.qrcode.model.Materi;
import com.anjilibey.qrcode.model.MateriList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MateriActivity extends AppCompatActivity {

    ProgressDialog loading;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private MateriAdapter adapter;
    SharedPrefManager sharedPrefManager;
    String token;
    String id_kls;
//    String coba= kls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(MateriActivity.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("materi token", token);
        //get extra
        Intent mIntent = getIntent();
        id_kls = mIntent.getStringExtra("id_kls");
        Log.d("id kelas", id_kls);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Materi Kuliah");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getResultListMateri();
    }

    private void getResultListMateri() {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.getMateri( "Bearer "+token, id_kls).enqueue(new Callback<MateriList>() {
            @Override
            public void onResponse(Call<MateriList> call, Response<MateriList> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    generateMateriList(response.body().getMateriArrayList());
                } else {
                    loading.dismiss();
                    Toast.makeText(MateriActivity.this, "Belum ada data pertemuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MateriList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(MateriActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateMateriList(ArrayList<Materi> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_materi_list);
        adapter = new MateriAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MateriActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
