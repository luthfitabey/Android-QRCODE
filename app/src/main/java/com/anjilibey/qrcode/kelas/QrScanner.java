package com.anjilibey.qrcode.kelas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Cek;
import com.anjilibey.qrcode.model.CekList;
import com.anjilibey.qrcode.model.Pertemuan;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrScanner extends AppCompatActivity implements Serializable{

    SurfaceView cameraPreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    EditText metHasil;
    final int RequestCameraPermissionID = 1001;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    String token, hasil,id;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more Details.
                            return;
                        }
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        mApiService = UtilsApi.getAPIService();
//token
        sharedPrefManager = new SharedPrefManager(QrScanner.this);
        token = sharedPrefManager.getSpToken();
        Log.d("token dari shared", token);

        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
        txtResult = (TextView) findViewById(R.id.txtResult);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this,barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();
        //Add event
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(QrScanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(QrScanner.this,
                            new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                        return;
                    }
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        getCek();
    }

    private void getCek() {
        final Intent intentA = new Intent(QrScanner.this, RatingActivity.class);
        mApiService.cekRating(
                "Bearer " + token
        ).enqueue(new Callback<CekList>() {
            @Override
            public void onResponse(Call<CekList> call, Response<CekList> response) {
                if (response.isSuccessful()) {
                    List<Cek> cek = response.body().getSementaraArrayList();
                    for (Cek data : cek) {
                       id = data.getId();
                    }
                    intentA.putExtra("idDetail", id);
                    startActivity(intentA);
                    finish();
                } else
                    generatebarcode();
            }

            @Override
            public void onFailure(Call<CekList> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(QrScanner.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generatebarcode() {
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                //string

                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if( qrcodes.size() != 0){
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            //create vibrate
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            txtResult.setText(qrcodes.valueAt(0).displayValue);
                            hasil = txtResult.getText().toString();
//                            if(!hasil.isEmpty()){
//                                Intent back = new Intent(QrScanner.this, AbsensiActivity.class);
//                                back.putExtra("hasil", hasil);
//                                startActivity(back);
//                                finish();
//                            }
                        }
                    });
                }
            }
        });
    }

    public void done(View view) {
        hasil = txtResult.getText().toString();
        Intent back = new Intent(QrScanner.this, AbsensiActivity.class);
        back.putExtra("hasil", hasil);
        startActivity(back);
        finish();
    }
}