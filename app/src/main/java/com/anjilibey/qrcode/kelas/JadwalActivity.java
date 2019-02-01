package com.anjilibey.qrcode.kelas;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.anjilibey.qrcode.Adapter.JadwalAdapter;
import com.anjilibey.qrcode.Adapter.MateriAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Jadwal;
import com.anjilibey.qrcode.model.JadwalList;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
    ProgressDialog loading;
    BaseApiService mApiService;
    private RecyclerView recyclerView;
    private JadwalAdapter adapter;
    SharedPrefManager sharedPrefManager;
    String token;
    Toolbar mActionBarToolbar;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(JadwalActivity.this);
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("jadwal token", token);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarText = (TextView)
                findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText("Jadwal");
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getResultListJadwal();
    }

    private void getResultListJadwal() {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
        mApiService.getJadwal("Bearer "+token).enqueue(new Callback<JadwalList>() {
            @Override
            public void onResponse(Call<JadwalList> call, Response<JadwalList> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    generateJadwalList(response.body().getJadwalArrayList());
                } else {
                    loading.dismiss();
                    Toast.makeText(JadwalActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JadwalList> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(JadwalActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateJadwalList(ArrayList<Jadwal> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_jadwal_list);
        adapter = new JadwalAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(JadwalActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

}
