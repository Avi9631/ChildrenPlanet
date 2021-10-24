package com.kids.childrenplanet;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnjoyFragment extends Fragment {


    public EnjoyFragment() {
        // Required empty public constructor
    }

    private Button drawBnt;
    private AdView mAdView;

    private InterstitialAd interstitialAd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_enjoy , container, false);
        mAdView = view.findViewById(R.id.adView);

        loadAds();

        drawBnt= view.findViewById(R.id.drawBtn);

        drawBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                else{
                    Intent i = new Intent(getContext(), DrawingActivity.class);
                    startActivity(i);
                }

            }
        });

        return view;
    }

    private void loadAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent i = new Intent(getContext(), DrawingActivity.class);
                startActivity(i);
                return;
            }
        });
    }

}
