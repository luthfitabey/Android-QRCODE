package com.anjilibey.qrcode.kelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;

import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanActivity extends AppCompatActivity implements Serializable{
    BaseApiService mApiService;
    Spinner staticSpinner;
    Spinner dynamicSpinner;
    EditText mEtDosen, mEtMhs, mEtRuang;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        //find view
        mEtDosen = findViewById(R.id.etDosen);
        mEtMhs = findViewById(R.id.etMhs);
        mEtRuang = findViewById(R.id.etRuang);
        mApiService = UtilsApi.getAPIService();


        staticSpinner = (Spinner) findViewById(R.id.spinnerSmt);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.semester,
                        android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {

                if (staticSpinner.getSelectedItem().equals("Semester 1")) {
                    dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"A001", "A002", "A003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                } else if (staticSpinner.getSelectedItem().equals("Semester 2")) {
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"B001", "B002", "B003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                } else if (staticSpinner.getSelectedItem().equals("Semester 3")) {
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"C001", "C002", "C003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                } else if (staticSpinner.getSelectedItem().equals("Semester 4")) {
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"D001", "D002", "D003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                } else if (staticSpinner.getSelectedItem().equals("Semester 5")) {
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"E001", "E002", "E003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                } else if (staticSpinner.getSelectedItem().equals("Semester 6")) {
                    Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinnerMatkul);
                    String[] items = new String[]{"F001", "F002", "F003"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ScanActivity.this,
                            android.R.layout.simple_spinner_item, items);

                    dynamicSpinner.setAdapter(adapter);
                }
            }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


        //String text hasil
//        ArrayList<String> hasil = (ArrayList<String>) intent.getSerializable("hasil");
        if (mEtMhs == null){
            mEtMhs.setText(getIntent().getStringExtra("hasil"));
        }
        else mEtMhs.setText(getIntent().getStringExtra("hasil") + ", ");

    }


    public void buttonA(View view) {
        Intent intent = new Intent(this, QrScanner.class);
        this.startActivity(intent);
    }

    public void buttonB(View view) {
        mApiService.sementaraRequest(
                staticSpinner.getSelectedItem().toString(),
                dynamicSpinner.getSelectedItem().toString(),
                mEtRuang.getText().toString(),
                mEtDosen.getText().toString(),
                mEtMhs.getText().toString())
//                "abc",
//                "sss",
//"sdsa",
//"sdsdsd",
//"66666"

//        )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ScanActivity.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(ScanActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(ScanActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
