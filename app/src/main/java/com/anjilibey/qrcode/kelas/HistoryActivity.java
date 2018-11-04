package com.anjilibey.qrcode.kelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.anjilibey.qrcode.Adapter.AdapterAbsen;
import com.anjilibey.qrcode.Adapter.DataAdapter;
import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.model.JSONResponse;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Sementara;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

////    RecyclerView rvDosen;
//    ProgressDialog loading;
////    Context mContext;
//    List<Sementara> semuadosenItemList = new ArrayList<>();
////    DataAdapter dosenAdapter;
//    BaseApiService mApiService;
//
//    private RecyclerView recyclerView;
////    private ArrayList<Sementara> data;
//    private DataAdapter adapter;

EditText mEtNiu, mEtRuang, mEtSemester, mEtDosen, mEtMatkul,mEtId;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    private AdapterAbsen mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //Make call to AsyncTask
        new AsyncFetch().execute();
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(HistoryActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://10.203.194.147:8000/api/sementara");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
//                conn.setRequestProperty("Content-type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4N2NiZmU0OTQwNTY5MGFjY2I5OTdmNWU3OGQ0NTYyZDg1OTg3NmI5MDc1YzBlMWFhOGI3ZDcwNjMyMTRjMzczYWNjYjBiYzhjMDZlY2E3In0.eyJhdWQiOiIxIiwianRpIjoiNzg3Y2JmZTQ5NDA1NjkwYWNjYjk5N2Y1ZTc4ZDQ1NjJkODU5ODc2YjkwNzVjMGUxYWE4YjdkNzA2MzIxNGMzNzNhY2NiMGJjOGMwNmVjYTciLCJpYXQiOjE1NDA4MzY0NjIsIm5iZiI6MTU0MDgzNjQ2MiwiZXhwIjoxNTcyMzcyNDYyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.KYaYPH72iNdGylllIO2mBaSRvaRf8HaMzdm0-fPuTu_X2qDC6vOBZMFzutxqQZiLd7izwkiqwC66DDCQh00CYHHK_0wSkTsGOpx_pQV13GoEVk7Pt8CLG4vWp0tqB44wBHj_KkJcyXfEitY5ur0RTj0jH5fehM93GLmWeyheys5bgDdATI3PId1HwLNI5RwBFsZKASkfQVPIEmY_OwkMz3wAAJnqdrID246L7qR68PSVeOn8d_9MoiH7Cf5u_5rWZp5czKWwNIqH1oKQ6kxs_X7dmFsRQulhvWNdVIGfStp5zaBT96a7e36FZKwe2aSeA6y02Wcte6O22fz-brrAAdv9PGi_udK4yTG_-ieoXWg9YHCEFWRRemc6oBXKySqQRmvPLZc__3375oyISTfAEPXiQEevawCCPyEKqe3YZOLz6P1j7Ch1Z8zBXA113ebRqiGiU77gSgwk6UIInhneKjoMb4nbu-HaU3Rwze26KB5jkYPxw2VZ81IosNppN7j7Rljd46dMqWH7nmlPdNOeP0_QpPUg3__3PoTmTrb539VCn5Z_0RNC_rkseSa9P44CiJ_61fRQQtOqbRAryXk0VcAD-zIrzrEoHjwPwcbsR5k-eLYxTFjKROIbahbG-82IugbQQPfKH1gbF7Hawm-a19VIN-YI7cKgzuGjo63IAjE");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //this method will be running on UI thread

            pdLoading.dismiss();
            List<Sementara> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Sementara sementara = new Sementara();
                    String id = json_data.getString("id");
                    String semester= json_data.getString("semester");
                    String matkul= json_data.getString("matkul");
                    String ruang= json_data.getString("ruang");
                    String dosen= json_data.getString("dosen");
                    String niu = json_data.getString("niu");

                    sementara.setId(id);
                    sementara.setSemester(semester);
                    sementara.setMatkul(matkul);
                    sementara.setRuang(ruang);
                    sementara.setDosen(dosen);
                    sementara.setNiu(niu);

                    data.add(sementara);
                }

                // Setup and Handover data to recyclerview
                mRVFishPrice = (RecyclerView)findViewById(R.id.absenList);
                mAdapter = new AdapterAbsen(HistoryActivity.this, data);
                mRVFishPrice.setAdapter(mAdapter);
                mRVFishPrice.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

            } catch (JSONException e) {
                Toast.makeText(HistoryActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

//    private void getResultListDosen(){
//        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
//        mApiService.getJSON().enqueue(new Callback<JSONResponse>() {
//            @Override
//            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
//                if (response.isSuccessful()){
//                    loading.dismiss();
////
//////                    final List<Sementara> semuaDosenItems = response.body().getListSementara();
//////
//////                    rvDosen.setAdapter(new DataAdapter(HistoryActivity.this, semuaDosenItems));
//////                    dosenAdapter.notifyDataSetChanged();
////
//////                    data = new ArrayList<>(Arrays.asList(response.body().getAbsen()));
//////                    adapter = new DataAdapter(data);
//////                    recyclerView.setAdapter(adapter);
////
//                    List<Sementara> SementaraList = response.body().getListSementara();
//                    Log.d("Retrofit Get", "Jumlah data Sementara: " +
//                            String.valueOf(SementaraList.size()));
//                    adapter = new DataAdapter(SementaraList);
//                    recyclerView.setAdapter(adapter);
//
//                } else {
//                    loading.dismiss();
//                    Toast.makeText(HistoryActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();
//                }
//        }
//
//            @Override
//            public void onFailure(Call<JSONResponse> call, Throwable t) {
//                loading.dismiss();
//                Toast.makeText(HistoryActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
