package com.kids.childrenplanet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;

public class AlphabetsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private CountDownTimer t;
    int value = 65;
    int value1 = 97;
    char val, val1;
    private TextView text, text1;

    private TextToSpeech textToSpeech;

    int j = 0;

    private Button left, right;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);

        loadAds();

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alphabets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text= findViewById(R.id.num);
        text1= findViewById(R.id.num_word);
        left= findViewById(R.id.left);
        right= findViewById(R.id.right);


        textToSpeech= new TextToSpeech(this,this);
        textToSpeech.setSpeechRate(0.2f);

        t= new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (value <= 90 && value1<= 122){
                    val = (char) value;
                    val1= (char) value1;
                    text.setText(String.valueOf(val));
                    text1.setText(String.valueOf(val1));
                    speak(String.valueOf(val), j);
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
                j =0;
                value++;
                value1++;
                t.start();
            }
        }.start();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value != 65 ){
                    t.cancel();
                    value--;
                    value1--;
                    j =0;
                    t.start();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value < 90){
                    t.cancel();
                    value++;
                    value1++;
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
                speak(String.valueOf(val), 0);
            }

        }
    }

    private void speak(String s, int j ){
        if (j ==0) {
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

    private void loadAds()
    {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }




}
