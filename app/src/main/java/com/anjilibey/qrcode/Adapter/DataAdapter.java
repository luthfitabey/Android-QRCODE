package com.anjilibey.qrcode.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Sementara;
import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Sementara> values;

    public DataAdapter(ArrayList<Sementara> values) {
        this.values = values;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_niu.setText(values.get(i).niu);
        viewHolder.tv_matkul.setText(values.get(i).matkul);
        viewHolder.tv_ruang.setText(values.get(i).ruang);
        viewHolder.tv_dosen.setText(values.get(i).dosen);
        viewHolder.tv_semester.setText(values.get(i).semester);
    }

 public int getItemCount(){
     return values.size();
 }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_niu, tv_matkul, tv_ruang, tv_dosen, tv_semester;
        public ViewHolder(View view) {
            super(view);

            tv_niu = (TextView)view.findViewById(R.id.tvNiu);
            tv_matkul = (TextView)view.findViewById(R.id.tvMatkul);
            tv_ruang = (TextView)view.findViewById(R.id.tvRuang);
            tv_dosen= (TextView)view.findViewById(R.id.tvDosen);
            tv_semester= (TextView)view.findViewById(R.id.tvSemester);
        }
    }

}
