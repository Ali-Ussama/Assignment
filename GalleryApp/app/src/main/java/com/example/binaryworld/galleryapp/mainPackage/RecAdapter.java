package com.example.binaryworld.galleryapp.mainPackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.binaryworld.galleryapp.secondPackage.DetailedActivity;
import com.example.binaryworld.galleryapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by BinaryWorld on 28-Feb-17.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.CustomViewHolder> {

    ArrayList<Model> data;
    Context context;

    public RecAdapter(Context context, ArrayList<Model> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view, this.context, this.data);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecAdapter.CustomViewHolder holder, int position) {
        String url = data.get(position).getImg_med();
        Picasso.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        Context context;
        ArrayList<Model> data;


        public CustomViewHolder(View itemView, Context context, ArrayList<Model> data) {
            super(itemView);
            this.context = context;
            this.data = data;
            imageView = (ImageView) itemView.findViewById(R.id.main_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int postition = getAdapterPosition();

                Intent intent = new Intent(this.context, DetailedActivity.class);

            intent.putExtra("currentItem",postition);
            intent.putExtra("allData", this.data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);


        }
    }
}
