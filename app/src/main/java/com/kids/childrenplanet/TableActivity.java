package com.kids.childrenplanet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class TableActivity extends AppCompatActivity {

    private TextView table, title;
    private int p =2;
    int i;

    private Button left, right;

    String data= "";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        mAdView = findViewById(R.id.adView);

        loadAds();

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table= findViewById(R.id.table);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        title = findViewById(R.id.title);

        setTable();

        if (p == 0){
            left.setEnabled(false);
        }

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p > 0){
                  p--;
                  data= "";
                  setTable();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p++;
                data = "";
                setTable();
            }
        });

    }

    private void setTable() {

        title.setText("Table of "+p);

        for (i = 0; i<11; i++){
            int pro = p*i;
            data = data+"\n"+p+"  x  "+i+"    =    "+pro;
        }
        table.setText(data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    private void loadAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
