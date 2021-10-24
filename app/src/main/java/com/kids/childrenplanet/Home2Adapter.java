package com.kids.childrenplanet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Home2Adapter extends RecyclerView.Adapter<Home2Adapter.MyViewHolder> {

    private Context mContext;
    private List<CategoryModel> mData ;


    public Home2Adapter(Context mContext, List<CategoryModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.cat2_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String image = mData.get(position).getCatimage();
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

            im = itemView.findViewById(R.id.imageView1);
            te = itemView.findViewById(R.id.textView1);

        }

        public void setData(String image, final String name) {

            Glide.with(mContext).load(image).into(im);
            te.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ThingsActivity.class);
                    i.putExtra("node", name);
                    itemView.getContext().startActivity(i);
                }
            });

        }
    }
}
