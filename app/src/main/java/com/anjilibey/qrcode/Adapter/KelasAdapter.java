package com.anjilibey.qrcode.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.kelas.MateriActivity;
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.Kelas;
import com.anjilibey.qrcode.model.Materi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder>{
    private ArrayList<Kelas> result;
//    public static String kls;
//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }

    public KelasAdapter(ArrayList<Kelas> result) {
        this.result = result;
    }

    @Override
    public KelasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KelasAdapter.ViewHolder viewHolder, int i) {
        viewHolder.kelas.setText(result.get(i).kelas);
        viewHolder.kode.setText(result.get(i).kode_mk);
        viewHolder.semester.setText(result.get(i).paket_semester);
        viewHolder.nip.setText(result.get(i).nip_dosen);
        viewHolder.id.setText(result.get(i).id_kls);

        final String kls = result.get(i).getId_kls();
        Log.d("hehe", kls);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), MateriActivity.class);
                mIntent.putExtra("id_kls", kls);
                view.getContext().startActivity(mIntent);
            }
        });
    }

    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView kelas, kode, semester, nip, id;
        public ViewHolder(View view) {
            super(view);
            id = (TextView)view.findViewById(R.id.klsId);
            kelas = (TextView)view.findViewById(R.id.klsKelas);
            kode = (TextView)view.findViewById(R.id.klsKode);
            semester = (TextView)view.findViewById(R.id.klsPaket);
            nip = (TextView)view.findViewById(R.id.klsNip);

        }
    }
}
