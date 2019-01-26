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


    String time = "15:30:18";

    // Create an instance of SimpleDateFormat with the specified
    // format.





    @Override
    public MateriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row_materi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_nip.setText(result.get(i).nip_dosen);
        viewHolder.tv_wakmul.setText(result.get(i).waktu_mulai);
        viewHolder.tv_waksel.setText(result.get(i).waktu_selesai);
        viewHolder.tv_mat.setText(result.get(i).materi);
        viewHolder.tv_id.setText(result.get(i).id);
        viewHolder.tv_tgl.setText(result.get(i).tanggal);
        viewHolder.tv_cap.setText(result.get(i).capaian);
        viewHolder.tv_kes.setText(result.get(i).kesesuaian_rkps);
        viewHolder.tv_ket.setText(result.get(i).keterangan);


        String a = result.get(i).waktu_mulai;
        String b = result.get(i).waktu_selesai;
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        Date d1 = null;
        try {
            d1 = sdf.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = sdf.parse(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long elapsed = d2.getTime() - d1.getTime();
        Log.d("datea", String.valueOf(d1));
        Log.d("dateb", String.valueOf(d2));


        DateFormat simple = new SimpleDateFormat("HH:mm:ss:SSS");

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(elapsed);

        simple.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = simple.format(result);

        Log.d("hasil: ", String.valueOf(result));
        viewHolder.tvDif.setText(dateFormatted);

    }

    public int getItemCount(){
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id, tv_nip, tv_wakmul, tv_waksel, tv_mat, tv_tgl, tv_cap, tv_kes, tv_ket, tvDif;
        public ViewHolder(View view) {
            super(view);

            tv_id = (TextView)view.findViewById(R.id.matId);
            tv_nip = (TextView)view.findViewById(R.id.matNip);
            tv_cap = (TextView)view.findViewById(R.id.matCap);
            tv_wakmul = (TextView)view.findViewById(R.id.matMul);
            tv_waksel = (TextView)view.findViewById(R.id.matSel);
            tv_mat = (TextView)view.findViewById(R.id.matMat);
            tv_tgl = (TextView)view.findViewById(R.id.matTgl);
            tv_cap = (TextView)view.findViewById(R.id.matCap);
            tv_kes = (TextView)view.findViewById(R.id.matKes);
            tv_ket = (TextView)view.findViewById(R.id.matKet);
            tvDif = (TextView)view.findViewById(R.id.tvEstimasi);

        }
    }
}
