package com.kids.childrenplanet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeModel> mData ;


    public HomeAdapter(Context mContext, List<HomeModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.cat_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        int image = mData.get(position).getCatimage();
        String name = mData.get(position).getCatname();

        holder.setData(image, name);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView im;
        TextView te;

        public MyViewHolder(View itemView) {
            super(itemView);

            im = itemView.findViewById(R.id.imageView);
            te = itemView.findViewById(R.id.textView);

        }

        public void setData(int image, String name) {

            im.setImageResource(image);
            te.setText(name);

            switch (name){
                case "Alphabets":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, AlphabetsActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;

                case "Days of Week":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, DaysActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;

                case "Numbers" :
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, NumbersActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;

                case "Months":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, MonthsActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;

                case "Tables":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, TableActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;
                case "Vowels":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, VowelsActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;
                case "Consonants":
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ConsonantsActivity.class);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    break;
            }

        }
    }
}
