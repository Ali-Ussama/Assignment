package com.example.aliosama.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliosama.assignment.Activity.CourseActivity;
import com.example.aliosama.assignment.Database.Models.CourseModel;
import com.example.aliosama.assignment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by aliosama on 6/19/2017.
 */

 public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder>{
    private ArrayList<CourseModel> data;
    private ArrayList<Integer> Colors ;
    private int colorsCount = 0;
    public HomeAdapter(ArrayList<CourseModel> data) {
        this.data = data;
    }

    @Override
    public HomeAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item,parent,false);
        Colors = new ArrayList<>();
        Colors.add(R.color.Cyan);
        Colors.add(R.color.Orange);
        Colors.add(R.color.GreenLight);
        Colors.add(R.color.Red);
        Colors.add(R.color.Lime);
        Colors.add(R.color.Grey);
        Colors.add(R.color.Yellow);
        return new CustomViewHolder(inflateView,data);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.CustomViewHolder holder, int position) {
        try {
            if(colorsCount >= Colors.size()){
                colorsCount = 0;
            }
            String Image_text = "" ;
            Image_text += data.get(position).getName().charAt(0);
            holder.mImageView.setImageResource(Colors.get(colorsCount));
            holder.ImageText.setText(Image_text);
            System.out.println();
            holder.mImageView.setId(Colors.get(colorsCount));
            holder.Title.setText(data.get(position).getName());
            holder.Semester.setText(data.get(position).getSemester());
            holder.description.setText(data.get(position).getDescription());
            colorsCount++;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView Title , description,Semester , ImageText;
        ArrayList<CourseModel> data;
        public CustomViewHolder(View itemView, final ArrayList<CourseModel> data ) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.Home_List_Item_imageView);
            ImageText = (TextView) itemView.findViewById(R.id.Home_List_Item_ImageText);
            Title = (TextView) itemView.findViewById(R.id.Home_List_Item_name);
            Semester = (TextView) itemView.findViewById(R.id.Home_List_Item_Semester);
            description = (TextView) itemView.findViewById(R.id.Home_List_Item_description);
            this.data = data;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   try {
                       Context context = v.getContext();
                       Intent intent = new Intent(context, CourseActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.putExtra("data", data.get(getAdapterPosition()));
                       intent.putExtra("ImageText",ImageText.getText());
                       intent.putExtra("ImageColor",mImageView.getId());
                       context.startActivity(intent);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                }
            });

        }

    }
}
