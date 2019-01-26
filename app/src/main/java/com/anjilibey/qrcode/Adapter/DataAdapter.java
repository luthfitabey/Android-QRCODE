package com.anjilibey.qrcode.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.History;
import com.anjilibey.qrcode.model.Materi;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<History> result;

    public DataAdapter(ArrayList<History> result) {
        this.result = result;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_niu.setText(result.get(i).id);
        viewHolder.tv_waktu.setText(result.get(i).created_at);
        viewHolder.tv_imei.setText(result.get(i).rating);
        viewHolder.tv_dosen.setText(result.get(i).komentar);
        viewHolder.tv_id.setText(result.get(i).id_mhs);
    }

 public int getItemCount(){
     return result.size();
 }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_niu, tv_waktu, tv_imei, tv_dosen, tv_id;
        public ViewHolder(View view) {
            super(view);

            tv_niu = (TextView)view.findViewById(R.id.tvIdPert);
            tv_waktu = (TextView)view.findViewById(R.id.tvCreated);
            tv_imei = (TextView)view.findViewById(R.id.tvRating);
            tv_dosen= (TextView)view.findViewById(R.id.tvKomentar);
            tv_id= (TextView)view.findViewById(R.id.tvNiu);
        }
    }
}
