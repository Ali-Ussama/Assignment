package com.example.binaryworld.galleryapp.secondPackage;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.binaryworld.galleryapp.R;
import com.example.binaryworld.galleryapp.mainPackage.Model;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        getSupportActionBar().hide();
        Intent i = getIntent();
        int currentPostion = i.getExtras().getInt("currentItem");

        ArrayList<Model> data = (ArrayList<Model>) i.getSerializableExtra("allData");

        Adapter2 adapter2 = new Adapter2(getBaseContext(), data);
        viewPager = (ViewPager) findViewById(R.id.pager_details);

        viewPager.setAdapter(adapter2);
        viewPager.setCurrentItem(currentPostion);

    }
}
