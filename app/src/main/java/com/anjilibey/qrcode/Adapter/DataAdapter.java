package com.anjilibey.qrcode.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Sementara;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DosenHolder>{
//    List<Sementara> semuadosenItemList;
//    Context mContext;
//
//    public String[] mColors = {
//            "#39add1", // light blue
//            "#3079ab", // dark blue
//            "#c25975", // mauve
//            "#e15258", // red
//            "#f9845b", // orange
//            "#838cc7", // lavender
//            "#7d669e", // purple
//            "#53bbb4", // aqua
//            "#51b46d", // green
//            "#e0ab18", // mustard
//            "#637a91", // dark gray
//            "#f092b0", // pink
//            "#b7c0c7"  // light gray
//    };
//
//    public DataAdapter(Context context, List<Sementara> dosenList){
//        this.mContext = context;
//        semuadosenItemList = dosenList;
//    }
//
//    @Override
//    public DataAdapter.DosenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
//        return new DosenHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(DosenHolder holder, int position) {
//        final Sementara semuadosenitem = semuadosenItemList.get(position);
//        holder.tv_niu.setText(semuadosenitem.getNiu());
//        holder.tv_matkul.setText(semuadosenitem.getMatkul());
//        holder.tv_ruang.setText(semuadosenitem.getRuang());
//        holder.tv_dosen.setText(semuadosenitem.getDosen());
//        holder.tv_semester.setText(semuadosenitem.getSemester());
//    }
//
//    @Override
//    public int getItemCount() {
//        return semuadosenItemList.size();
//    }
//
//    public class DosenHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.tvNiu)
//        TextView tv_niu;
//        @BindView(R.id.tvMatkul)
//        TextView tv_matkul;
//        @BindView(R.id.tvRuang)
//        TextView tv_ruang;
//        @BindView(R.id.tvDosen)
//        TextView tv_dosen;
//        @BindView(R.id.tvSemester)
//        TextView tv_semester;
//
//
//        public DosenHolder(View itemView) {
//            super(itemView);
//
//            ButterKnife.bind(this, itemView);
//        }
//    }
//
//    public int getColor() {
//        String color;
//
//        // Randomly select a fact
//        Random randomGenerator = new Random(); // Construct a new Random number generator
//        int randomNumber = randomGenerator.nextInt(mColors.length);
//
//        color = mColors[randomNumber];
//        int colorAsInt = Color.parseColor(color);
//
//        return colorAsInt;
//    }



public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Sementara> absen;

    public DataAdapter(List<Sementara> SementaraList) {
        this.absen = SementaraList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_niu.setText(absen.get(i).niu);
        viewHolder.tv_matkul.setText(absen.get(i).matkul);
        viewHolder.tv_ruang.setText(absen.get(i).ruang);
        viewHolder.tv_dosen.setText(absen.get(i).dosen);
        viewHolder.tv_semester.setText(absen.get(i).semester);
    }

    @Override
    public int getItemCount() {
        return absen.size();
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
