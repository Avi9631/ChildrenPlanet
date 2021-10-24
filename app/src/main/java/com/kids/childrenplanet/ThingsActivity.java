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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThingsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private ImageView image;
    private TextView name;

    private int i = 0;
    private int j =0;

    private CountDownTimer t;

    public static List<ThingsModel> thingsModelList;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextToSpeech textToSpeech;

    private Button left, right;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things);

        mAdView = findViewById(R.id.adView);

        loadAds();

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("node"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image= findViewById(R.id.image);
        name= findViewById(R.id.name);
        left= findViewById(R.id.left);
        right= findViewById(R.id.right);

        thingsModelList= new ArrayList<>();

        textToSpeech= new TextToSpeech(ThingsActivity.this,this);
        textToSpeech.setSpeechRate(0.2f);

        myRef.child("Home").child(getIntent().getStringExtra("node")).child("con").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    thingsModelList.add(new ThingsModel(String.valueOf(dataSnapshot1.child("image").getValue()),
                            String.valueOf(dataSnapshot1.child("name").getValue())));
                }

                t= new CountDownTimer(6000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if ( i < thingsModelList.size()){
                            setU(j);
                            j++;
                        }
                        else if (i > thingsModelList.size()){
                            i=0;
                            setU(j);
                            j++;
                        }
                        else
                        {
                        }
                    }

                    @Override
                    public void onFinish() {
                        i++;
                        j=0;
                        t.start();
                    }
                }.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ThingsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 0 ){
                    t.cancel();
                    i--;
                    j =0;
                    t.start();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < thingsModelList.size()){
                    t.cancel();
                    i++;
                    j =0;
                    t.start();
                }
            }
        });

    }


    private void setU(int j){
        if (thingsModelList.size() != 0) {
            Glide.with(getApplicationContext()).load(thingsModelList.get(i).getImage()).into(image);
            name.setText(thingsModelList.get(i).getName());
            if (j == 0) {
                speak(name.getText().toString(), j);
            }
        }else {
            finish();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Unsupproted", Toast.LENGTH_SHORT).show();
            }else{
//                speak(String.valueOf(""), j);
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
//        i =0; j=0;
        finish();

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
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

}
