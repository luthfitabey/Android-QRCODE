package com.anjilibey.qrcode.kelas;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.anjilibey.qrcode.R;

public class MainActivity extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.activity_main, container, false);
        GridLayout mainGrid = (GridLayout) myFragmentView.findViewById(R.id.gridMain);
        setSingleEvent(mainGrid);
        return myFragmentView;
    }
    public void setSingleEvent(GridLayout mainGrid) {
        //loop all child item of maingrid
        for(int i=0; i<mainGrid.getChildCount();i++){
            //cardview merupakan bagian dari child gridview
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //toast bisa diganti dengan star new activity
//                    Toast.makeText(MainActivity.this, "Clicked at index"+finalI, Toast.LENGTH_SHORT).show();
                    if(finalI == 0 ){
                        Intent intent = new Intent(getActivity(), QrScanner.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1 ){
                        Intent intent = new Intent(getActivity(), JadwalActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2 ){
                        Intent intent = new Intent(getActivity(), HistoryActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 3 ){
                        Intent intent = new Intent(getActivity(), KelasActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), "state : Hayo lupa belum bikin activity ya :)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
