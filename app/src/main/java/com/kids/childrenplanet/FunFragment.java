package com.kids.childrenplanet;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FunFragment extends Fragment {


    public FunFragment() {
        // Required empty public constructor
    }

    private RecyclerView picrec;
    private Home2Adapter homeAdapter1;

    public static List<CategoryModel> picList = new ArrayList<>();

    private AdView mAdView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fun, container, false);
        mAdView = view.findViewById(R.id.adView);

        loadAds();


        picrec = view.findViewById(R.id.pic_rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        picrec.setLayoutManager(layoutManager);
        picrec.setNestedScrollingEnabled(false);
        picrec.setHasFixedSize(true);

        homeAdapter1 = new Home2Adapter(getContext(), picList);
        picrec.setAdapter(homeAdapter1);
        homeAdapter1.notifyDataSetChanged();

        return  view;

    }


    private void loadAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }



}
