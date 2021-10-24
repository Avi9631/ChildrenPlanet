package com.kids.childrenplanet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.UserDictionary;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;

public class NumbersActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private CountDownTimer t;
    int number = 0;
    private TextView num;
    int j =0;

    private TextToSpeech textToSpeech;
    private Button left, right;
    private AdView mAdView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAdView = findViewById(R.id.adView);

        loadAds();

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Numbers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        num= findViewById(R.id.num);
        left= findViewById(R.id.left);
        right= findViewById(R.id.right);

        textToSpeech= new TextToSpeech(this,this);
        textToSpeech.setSpeechRate(0.2f);

        t= new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (number <= 100){
                    num.setText(String.valueOf(number));
                    speak(String.valueOf(number), j);
                    j++;
                }else{
                    if (interstitialAd.isLoaded()){
                        interstitialAd.show();
                        return;
                    }
                    finish();
                }
            }

            @Override
            public void onFinish() {
                number++;
                j=0;
                t.start();
            }
        }.start();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number != 0 ){
                    t.cancel();
                    number -- ;
                    j =0;
                    t.start();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number < 100){
                    t.cancel();
                    number++;
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
                speak(String.valueOf(number), 0);
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
