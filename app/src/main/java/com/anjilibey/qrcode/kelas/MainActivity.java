package com.anjilibey.qrcode.kelas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.anjilibey.qrcode.R;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.gridMain);
        setSingleEvent(mainGrid);
//        setToggleEvent(mainGrid);
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
                        Intent intent = new Intent(MainActivity.this, AbsensiActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1 ){
                        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2 ){
                        Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 3 ){
                        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 4 ){
                        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 5 ){
                        finish();
                        System.exit(0);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "state : Hayo lupa belum bikin activity ya :)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void setToggleEvent(GridLayout toggleEvent) {
        //loop all child item of maingrid
        for(int i=0; i<mainGrid.getChildCount();i++){
            //cardview merupakan bagian dari child gridview
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor()==-1) {
                        //change bg color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6f00"));
                        Toast.makeText(MainActivity.this, "state : True", Toast.LENGTH_SHORT).show();
                    } else {
                        //change bg color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "state : false", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
