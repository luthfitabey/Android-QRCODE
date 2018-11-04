package com.anjilibey.qrcode.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.model.Sementara;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class AdapterAbsen extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Sementara> data= Collections.emptyList();
    Sementara current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterAbsen(Context context, List<Sementara> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_adapter_absen, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Sementara current=data.get(position);
        myHolder.txtId.setText(current.id);
        myHolder.txtNiu.setText(current.niu);
        myHolder.txtSemester.setText("Semester: " + current.semester);
        myHolder.txtSemester.setText("Mata Kuliah: " + current.matkul);
        myHolder.txtMatkul.setText("Ruang: " + current.ruang);
        myHolder.txtDosen.setText("Dosen: " + current.dosen);
        myHolder.txtNiu.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView txtId, txtRuang, txtDosen, txtMatkul, txtNiu, txtSemester;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            txtId= (TextView) itemView.findViewById(R.id.txtId);
            txtRuang= (TextView) itemView.findViewById(R.id.txtRuang);
            txtDosen = (TextView) itemView.findViewById(R.id.txtDosen);
            txtMatkul = (TextView) itemView.findViewById(R.id.txtMatkul);
            txtNiu = (TextView) itemView.findViewById(R.id.txtNiu);
            txtSemester = (TextView) itemView.findViewById(R.id.txtSemester);
        }

    }
}

