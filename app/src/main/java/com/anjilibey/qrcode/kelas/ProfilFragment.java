package com.anjilibey.qrcode.kelas;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjilibey.qrcode.Api.BaseApiService;
import com.anjilibey.qrcode.Api.SharedPrefManager;
import com.anjilibey.qrcode.Api.UtilsApi;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Profil;
import com.anjilibey.qrcode.model.Profiles;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment implements View.OnClickListener{
    BaseApiService mApiService;
    ImageView btn;
    TextView tvname;
    TextView tvaddress;
    TextView tvniu;
    TextView tvnif;
    TextView tvprodi;
    TextView tvangkatan;
    TextView tvphone;
    TextView tvnpwp;
    Button btnOut;

    SharedPrefManager sharedPrefManager;
    String token;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //penting api
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getActivity());
        // get user data from session

        token = sharedPrefManager.getSpToken();
        Log.d("jadwal token", token);
    }

//    private void loadData(List<Profil> profile) {
//
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profil_fragment, container, false);

        btn = view.findViewById(R.id.buttonUbah);
        tvname = view.findViewById(R.id.tv_name);
        tvaddress = view.findViewById(R.id.tv_address);
        tvniu = view.findViewById(R.id.niu);
        tvnif = view.findViewById(R.id.nif);
        tvprodi = view.findViewById(R.id.prodi);
        tvangkatan = view.findViewById(R.id.angkatan);
        tvphone = view.findViewById(R.id.phone);
        tvnpwp = view.findViewById(R.id.npwp);
        btnOut = view.findViewById(R.id.btnKeluar);
        sharedPrefManager = new SharedPrefManager(getActivity());
        btn.setOnClickListener(this);

        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Fetching Data","Please wait..",false,false);
        mApiService.getProfil("Bearer "+token
        ).enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<Profil> profile = response.body().getProfiles();
//                loadData(profile);
                    for (Profil data : profile) {
                        Log.d("nama: ", data.getNama());
//                        tvname.setText(data.getNama());
//                        tvaddress.setText(data.getAlamat());
//                        tvprodi.setText(data.getProdi());
//                        tvangkatan.setText(data.getAngkatan());
//                        tvphone.setText(data.getNo_telp());
//                        tvniu.setText(data.getNiu());
//                        tvnif.setText(data.getNif());
//                        tvnpwp.setText(data.getNpwp());
                    }
                }
                else {
                    loading.dismiss();
                    Log.d("hasilnya: ", "gagal");
                }
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                loading.dismiss();
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }
        });

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                sharedPrefManager.deleteSPString(SharedPrefManager.SP_TOKEN);

                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), UpdateProfil.class);
        startActivityForResult(intent, 123);
    }

}
