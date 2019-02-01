package com.anjilibey.qrcode.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.Materi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.ViewHolder>{
    private ArrayList<Materi> result;

    public MateriAdapter(ArrayList<Materi> result) {
        this.result = result;
    }


    @Override
    public MateriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row_materi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_id.setText(result.get(i).id);
        viewHolder.tv_tgl.setText(result.get(i).tanggal);
        viewHolder.tv_hari.setText(result.get(i).hari);
        viewHolder.tv_ruang.setText(result.get(i).id_ruang);
        viewHolder.tv_cap.setText(result.get(i).capaian);
        viewHolder.tv_kes.setText(result.get(i).kesesuaian_rkps);
        viewHolder.tv_mat.setText(result.get(i).materi);
        viewHolder.tv_ket.setText(result.get(i).keterangan);
        viewHolder.tv_wakmul.setText(result.get(i).waktu_mulai);
        viewHolder.tv_waksel.setText(result.get(i).waktu_selesai);
    }

    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id, tv_tgl, tv_hari, tv_ruang, tv_cap, tv_kes,  tv_mat,  tv_ket, tv_wakmul, tv_waksel;
        public ViewHolder(View view) {
            super(view);

            tv_id = (TextView)view.findViewById(R.id.matId);
            tv_tgl = (TextView)view.findViewById(R.id.matTgl);
            tv_hari = (TextView)view.findViewById(R.id.matHari);
            tv_ruang = (TextView)view.findViewById(R.id.matRuang);
            tv_cap = (TextView)view.findViewById(R.id.matCap);
            tv_kes = (TextView)view.findViewById(R.id.matKes);
            tv_mat = (TextView)view.findViewById(R.id.matMat);
            tv_ket = (TextView)view.findViewById(R.id.matKet);
            tv_wakmul = (TextView)view.findViewById(R.id.matMul);
            tv_waksel = (TextView)view.findViewById(R.id.matSel);
        }
    }
}
