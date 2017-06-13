package com.example.binaryworld.galleryapp.mainPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.binaryworld.galleryapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Model> data = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.main_list);

        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        recAdapter = new RecAdapter(getBaseContext(), data);

        FetchData fetchData = new FetchData();
        fetchData.execute();
        recyclerView.setAdapter(recAdapter);

    }
}


