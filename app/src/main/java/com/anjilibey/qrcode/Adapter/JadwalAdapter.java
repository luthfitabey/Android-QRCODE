package com.anjilibey.qrcode.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Jadwal;

import java.util.ArrayList;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder>{
    private ArrayList<Jadwal> result;

    public JadwalAdapter(ArrayList<Jadwal> result) {
        this.result = result;
    }

    @Override
    public JadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row_jadwal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.tv_nip.setText(result.get(i).nip_dosen);
        viewHolder.tv_matkul.setText(result.get(i).kelas);
        viewHolder.tv_kode.setText(result.get(i).kode_mk);
        viewHolder.tv_kelas.setText(result.get(i).id_ruang);
        viewHolder.tv_kapasitas.setText(result.get(i).kapasitas);
        viewHolder.tv_sem.setText(result.get(i).paket_semester);
        viewHolder.tv_hari.setText(result.get(i).hari);
        viewHolder.tv_sesi.setText(result.get(i).sesi);
        viewHolder.tv_mulai.setText(result.get(i).waktu_mulai);
        viewHolder.tv_selesai.setText(result.get(i).waktu_selesai);

    }

    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nip, tv_matkul, tv_kode, tv_kelas, tv_kapasitas, tv_sem, tv_hari, tv_mulai, tv_selesai, tv_sesi;
        public ViewHolder(View view) {
            super(view);

//            tv_nip = (TextView)view.findViewById(R.id.jadNip);
            tv_matkul = (TextView)view.findViewById(R.id.jadMat);
            tv_kode = (TextView)view.findViewById(R.id.jadKode);
            tv_kelas = (TextView)view.findViewById(R.id.jadKel);
            tv_kapasitas = (TextView)view.findViewById(R.id.jadKap);
            tv_sem = (TextView)view.findViewById(R.id.jadSem);
            tv_hari = (TextView)view.findViewById(R.id.jadHar);
            tv_sesi = (TextView) view.findViewById(R.id.sesi);
            tv_mulai = (TextView) view.findViewById(R.id.mulai);
            tv_selesai = (TextView) view.findViewById(R.id.selesai);

        }
    }
}
