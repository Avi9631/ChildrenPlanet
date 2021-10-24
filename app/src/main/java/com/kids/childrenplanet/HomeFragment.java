package com.kids.childrenplanet;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView homeRecyclerView;
    private HomeAdapter homeAdapter;
    private AdView mAdView;

    private List<HomeModel> homeList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAdView = view.findViewById(R.id.adView);

        loadAds();

        homeRecyclerView = view.findViewById(R.id.learn_rec);
        homeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setNestedScrollingEnabled(false);

        homeList.add(new HomeModel(R.drawable.letters,"Alphabets"));
        homeList.add(new HomeModel(R.drawable.week,"Days of Week"));
        homeList.add(new HomeModel(R.drawable.number,"Numbers"));
        homeList.add(new HomeModel(R.drawable.months,"Months"));
        homeList.add(new HomeModel(R.drawable.tables,"Tables"));
        homeList.add(new HomeModel(R.drawable.vowels,"Vowels"));
        homeList.add(new HomeModel(R.drawable.consonants,"Consonants"));

        homeAdapter = new HomeAdapter(getContext(), homeList);
        homeRecyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();


        return view;
    }

    private void loadAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


}
