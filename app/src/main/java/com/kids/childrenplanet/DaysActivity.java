package com.kids.childrenplanet;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;

public class DaysActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView num;
    private CountDownTimer t;

    private TextToSpeech textToSpeech;
    private String days[]= {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int i= 0;
    int j =0;

    private Button left, right;
    private AdView mAdView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        mAdView = findViewById(R.id.adView);

        loadAds();
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Days of the Week");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        num = findViewById(R.id.daymon);
        left= findViewById(R.id.left);
        right= findViewById(R.id.right);

        textToSpeech= new TextToSpeech(this,this);
        textToSpeech.setSpeechRate(0.2f);

        t= new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (i <= 6) {
                    num.setText(String.valueOf(days[i]));
                    speak(String.valueOf(days[i]), j);
                    j++;
                }
                else {
                    if (interstitialAd.isLoaded()){
                        interstitialAd.show();
                        return;
                    }
                    finish();
                }

            }

            @Override
            public void onFinish() {
                i++;
                j =0;
                t.start();
            }
        }.start();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 0 ){
                    left.setEnabled(true);
                    t.cancel();
                    i -- ;
                    j =0;
                    t.start();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < 6){
                    right.setEnabled(true);
                    t.cancel();
                    i++;
                    j =0;
                    t.start();
                }
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Unsupproted", Toast.LENGTH_SHORT).show();
            }else{
                speak(String.valueOf(days[i]), 0);
            }

        }
    }

    private void speak(String s, int j) {
        if (j == 0) {
            textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }
    private void loadAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
                return;
            }
        });
    }

}
