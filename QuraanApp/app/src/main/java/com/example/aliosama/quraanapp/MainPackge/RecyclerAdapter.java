package com.example.aliosama.quraanapp.MainPackge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aliosama.quraanapp.R;

import java.util.ArrayList;

/**
 * Created by aliosama on 4/23/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    ArrayList<Model> data;
    Context context;
    static int pos;
    View inflatedView;

    public RecyclerAdapter(Context context, ArrayList<Model> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(inflatedView,context,data);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.CustomViewHolder holder, int position) {
        holder.itemTitle.setText(data.get(position).getItemTitle());
        holder.itemNumber.setText(data.get(position).getItemNumber());

    }
    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button BrowseButton;
        TextView itemTitle;
        TextView itemNumber;
        Context context;
        ArrayList<Model> data;
        public CustomViewHolder(View v,Context context,ArrayList<Model> data) {
            super(v);
            BrowseButton = (Button) v.findViewById(R.id.Browes);
            itemTitle = (TextView) v.findViewById(R.id.Title);
            itemNumber = (TextView) v.findViewById(R.id.Item_number);
            this.context = context;
            this.data = data;
            BrowseButton.setOnClickListener(this);
        }
    @Override
    public void onClick(View v){
        Intent intent = new Intent(this.context,SoraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Title",data.get(getAdapterPosition()).getItemTitle());
        intent.putExtra("Number",data.get(getAdapterPosition()).getItemNumber());
        intent.putExtra("Drawable",data.get(getAdapterPosition()).getSoraImage());
        intent.putExtra("Audio",data.get(getAdapterPosition()).getSoraAudio());
        this.context.startActivity(intent);
    }

    }
}
