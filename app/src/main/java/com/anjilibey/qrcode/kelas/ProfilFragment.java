package com.anjilibey.qrcode.kelas;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.anjilibey.qrcode.Api.UtilsApi.BASE_URL_API;

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
    Button btnOut, btnUbah;
    String idP, no_rek, na_rek, nik, idUser;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    String token;
    String base_url = BASE_URL_API;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //penting api
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getActivity());
        // get user data from session
        mContext=getActivity();
        token = sharedPrefManager.getSpToken();
        Log.d("profil token", token);

        String url = base_url+"/api/profile";

        FetchData fetchData = new FetchData();
        fetchData.execute(url);
    }

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
        btnUbah = view.findViewById(R.id.btnUbh);
        ImageView ubahProfil = (ImageView) view.findViewById(R.id.buttonUbah);
//        ImageView writeProfil = (ImageView) view.findViewById(R.id.btnWrite);
        sharedPrefManager = new SharedPrefManager(getActivity());
        btn.setOnClickListener(this);

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                sharedPrefManager.deleteSPString(SharedPrefManager.SP_TOKEN);
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(mContext, UbahPass.class);
                abc.putExtra("nganu", idUser);
                Log.d("id profil", idUser);
                startActivity(abc);
            }
        });
//                if (tvphone.getText().toString() == "null"){
//                    writeProfil.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext, WriteProfil.class);
//                            intent.putExtra("nganu", idP);
//                            Log.d("id profil", idP);
//                            startActivity(intent);
//                        }
//                    });
//                }
//                else{
//                    writeProfil.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                        }
//                    });
//                }

        ubahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateProfil.class);
                intent.putExtra("id_profil", idP);
                intent.putExtra("nik", nik);
                intent.putExtra("no_rek", no_rek);
                intent.putExtra("na_rek", na_rek);
                intent.putExtra("nama", tvname.getText().toString());
                intent.putExtra("address", tvaddress.getText().toString());
                intent.putExtra("nif", tvnif.getText().toString());
                intent.putExtra("npwp", tvnpwp.getText().toString());
                intent.putExtra("niu", tvniu.getText().toString());
                intent.putExtra("phone", tvphone.getText().toString());
                intent.putExtra("prodi", tvprodi.getText().toString());
                intent.putExtra("angkatan", tvangkatan.getText().toString());
                startActivity(intent);
                onPause();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    public class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = null;

            try {
                URL url = new URL(strings[0]);

                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.connect();

                int response = connection.getResponseCode();
                Log.d("DEBUG1", "RESPONSE CODE : " + response);

                //mendownload data yang berupa String
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                StringBuilder total = new StringBuilder();
                String line;

                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();
                Log.d("DEBUG1", "RESULT :" + result);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String niu, nif, alamat, angkatan, nama, prodi, npwp, no_telp;
            try {
                JSONObject jsonObject = new JSONObject(s);
                idP = jsonObject.getJSONObject("profiles").getString("id");
                Log.d("anu", idP);
                niu = jsonObject.getJSONObject("profiles").getString("niu");
                nif = jsonObject.getJSONObject("profiles").getString("nif");
                alamat = jsonObject.getJSONObject("profiles").getString("alamat");
                angkatan = jsonObject.getJSONObject("profiles").getString("angkatan");
                nama = jsonObject.getJSONObject("profiles").getString("nama");
                prodi = jsonObject.getJSONObject("profiles").getString("prodi");
                nik = jsonObject.getJSONObject("profiles").getString("nik");
                no_rek = jsonObject.getJSONObject("profiles").getString("no_rek");
                na_rek = jsonObject.getJSONObject("profiles").getString("nama_rek");
                npwp = jsonObject.getJSONObject("profiles").getString("npwp");
                no_telp = jsonObject.getJSONObject("profiles").getString("no_telp");
                idUser = jsonObject.getJSONObject("profiles").getString("user_id");


                tvname.setText(nama);
                tvaddress.setText(alamat);
                tvnif.setText(nif);
                tvniu.setText(niu);
                tvnpwp.setText(npwp);
                tvphone.setText(no_telp);
                tvprodi.setText(prodi);
                tvangkatan.setText(angkatan);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}



//        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Fetching Data","Please wait..",false,false);
//        mApiService.getProfil("Bearer "+token
//        ).enqueue(new Callback<Profiles>() {
//            @Override
//            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
//                if (response.isSuccessful()) {
//                    loading.dismiss();
////                    List<Profil> profile = response.body().getProfiles();
//                    Profil profil = (Profil) response.body().getProfiles();
//                    String nama = profil.getNama();
//                    Log.d("nama: ", nama);
////                    for (Profil data : profile) {
////
////                        tvname.setText(data.getNama());
////                        Log.d("nama: ", data.getNama());
////                        tvaddress.setText(data.getAlamat());
////                        tvprodi.setText(data.getProdi());
////                        tvangkatan.setText(data.getAngkatan());
////                        tvphone.setText(data.getNo_telp());
////                        tvniu.setText(data.getNiu());
////                        tvnif.setText(data.getNif());
////                        tvnpwp.setText(data.getNpwp());
////                    }
//
//                }
//                else {
//                    loading.dismiss();
//                    Log.d("hasilnya: ", "gagal");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Profiles> call, Throwable t) {
//                loading.dismiss();
//                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
//            }
//        });
