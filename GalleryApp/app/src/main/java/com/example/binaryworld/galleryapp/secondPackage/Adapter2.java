package com.example.binaryworld.galleryapp.secondPackage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binaryworld.galleryapp.R;
import com.example.binaryworld.galleryapp.mainPackage.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by BinaryWorld on 01-Mar-17.
 */
public class Adapter2 extends PagerAdapter {

    Context context;
    ArrayList<Model> data;
    LayoutInflater layoutInflater;

    public Adapter2(Context context, ArrayList<Model> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(FrameLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.detailed_item, container, false);

        TextView movieNum= (TextView) itemView.findViewById(R.id.movie_number);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.detailed_image);
        TextView movieName= (TextView) itemView.findViewById(R.id.movie_name);
        TextView movieDate= (TextView) itemView.findViewById(R.id.movie_date);


        movieNum.setText((position+1)+" of 12");

        String url = data.get(position).getImg_large();
        Picasso.with(context).load(url).into(imageView);

        movieName.setText(data.get(position).getName()+"");
        movieDate.setText(data.get(position).getDate()+"");

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((FrameLayout)object);
    }


}
