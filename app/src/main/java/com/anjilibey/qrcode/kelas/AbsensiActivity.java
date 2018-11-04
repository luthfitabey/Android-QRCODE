package com.anjilibey.qrcode.kelas;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.anjilibey.qrcode.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class AbsensiActivity extends Activity implements View.OnClickListener {

    //    private Button btnSave;
//    Spinner mSpinner1;
//    Spinner mSpinner2;
    Button mBtnGetBarcode;
    EditText mEtNim;
    EditText mEtImei;
    Bitmap QRCode;
    ImageView mQrCode;
    String imei;
    String comb;
    TelephonyManager tm;
    Spinner staticSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        //findview
        mBtnGetBarcode = findViewById(R.id.btnGetBarcode);
        mEtNim = findViewById(R.id.etNim);
        mEtImei = findViewById(R.id.etImei);
        mQrCode = findViewById(R.id.QrCode);
        mBtnGetBarcode.setOnClickListener(this);

        staticSpinner = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.semester,
                        android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        //ifelse
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {
                    if(staticSpinner.getSelectedItem().equals("semester 1"))
                    {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"A", "B", "C"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
                    else if (staticSpinner.getSelectedItem().equals("semester 2"))
                    {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"D", "E", "F"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
                    else if (staticSpinner.getSelectedItem().equals("semester 3")) {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"G", "H", "I"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
                    else if (staticSpinner.getSelectedItem().equals("semester 4")) {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"J", "K", "L"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
                    else if (staticSpinner.getSelectedItem().equals("semester 5")) {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"M", "N", "O"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
                    else if (staticSpinner.getSelectedItem().equals("semester 6")) {
                        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner2);
                        String[] items = new String[]{"P", "Q", "R"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AbsensiActivity.this,
                                android.R.layout.simple_spinner_item, items);

                        dynamicSpinner.setAdapter(adapter);
                    }
            }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


        //ambil imei
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        imei = tm.getDeviceId();
        if(imei != null){
            mEtImei.setText(imei);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnSimpan:
//                break;
            case R.id.btnGetBarcode:
                String NIM = mEtNim.getText().toString();
                String IMEI = mEtImei.getText().toString();
                String semester = staticSpinner.getSelectedItem().toString();

                String comb = NIM + IMEI + semester;
                Log.d("Queued Nim", NIM);
                if (NIM.isEmpty()){
                    Toast.makeText(AbsensiActivity.this, "Masukkan Nim terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(comb, BarcodeFormat.QR_CODE, 300, 300);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        QRCode = bitmap;
                        mQrCode.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    break;
                }
        }
    }
}
