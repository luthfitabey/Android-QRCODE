package com.anjilibey.qrcode.Adapter;


import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.anjilibey.qrcode.R;
import com.anjilibey.qrcode.kelas.MateriActivity;
import com.anjilibey.qrcode.kelas.UpdateDetailP;
import com.anjilibey.qrcode.model.History;
import com.google.android.gms.nearby.messages.internal.Update;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
        String dateInString = result.get(i).created_at;

        DateFormat readFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss");
        Date date = null;
        try {
            date = readFormat.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            String formattedDate = writeFormat.format(date);

//        viewHolder.tv_id.setText(result.get(i).id_pertemuan);
        viewHolder.tv_waktu.setText(formattedDate);
//        viewHolder.tv_imei.setText(result.get(i).rating);
        viewHolder.tv_dosen.setText(result.get(i).komentar);
//        viewHolder.tv_niu.setText(result.get(i).id_mhs);
        viewHolder.tv_matkul.setText(result.get(i).kelas);
        viewHolder.tv_kode.setText(result.get(i).kode_mk);
        viewHolder.ratingBar.setRating(Float.parseFloat(result.get(i).rating));

        final String idDetail = result.get(i).getId();
        final String rating = result.get(i).getRating();
        final String komentar = result.get(i).getKomentar();
        final String tanggal = result.get(i).getCreated_at();
        final String idPertemuan = result.get(i).getId_pertemuan();



        Log.d("idPertemuan", idPertemuan);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), UpdateDetailP.class);
                mIntent.putExtra("idDetail", idDetail);
                mIntent.putExtra("rating", rating);
                mIntent.putExtra("komentar", komentar);
                mIntent.putExtra("tanggal", tanggal);
                mIntent.putExtra("idPertemuan", idPertemuan);
                view.getContext().startActivity(mIntent);
            }
        });
    }

 public int getItemCount(){
     return result.size();
 }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_niu, tv_waktu, tv_rating, tv_dosen, tv_id, tv_matkul, tv_kode;
        private RatingBar ratingBar;
        public ViewHolder(View view) {
            super(view);

//            tv_id = (TextView)view.findViewById(R.id.tvIdPert);
            tv_waktu = (TextView)view.findViewById(R.id.tvCreated);
//            tv_rating = (TextView)view.findViewById(R.id.tvRating);
            tv_dosen= (TextView)view.findViewById(R.id.tvKomentar);
//            tv_niu= (TextView)view.findViewById(R.id.tvNiu);
            tv_kode = (TextView) view.findViewById(R.id.tvKodeMatkul);
            tv_matkul = (TextView) view.findViewById(R.id.tvNamaMatkul);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingbarhistory);
        }
    }
}
