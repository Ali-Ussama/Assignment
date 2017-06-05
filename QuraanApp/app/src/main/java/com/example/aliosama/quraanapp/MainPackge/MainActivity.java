package com.example.aliosama.quraanapp.MainPackge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aliosama.quraanapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     RecyclerAdapter mAdapter;
     RecyclerView mRecyclerView;
     LinearLayoutManager mLinearLayoutManager;
     ArrayList<Model> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            data = new ArrayList<>();
//        System.out.println(R.drawable.alfatihah);
                data.add(new Model(R.drawable.alfatihah,R.raw.a001,getResources().getString(R.string.Alfatihah),"1"));
                data.add(new Model(R.drawable.alkawsar,R.raw.a108,getResources().getString(R.string.AlKauthar),"2"));
                data.add(new Model(R.drawable.ekhlas,R.raw.a112,getResources().getString(R.string.Alekhlas),"3"));
                data.add(new Model(R.drawable.alfalaq,R.raw.a113,getResources().getString(R.string.Alfalaq),"4"));
                data.add(new Model(R.drawable.alnas,R.raw.a114,getResources().getString(R.string.Annas),"114"));

            mAdapter = new RecyclerAdapter(getBaseContext(),data);
            mRecyclerView.setAdapter(mAdapter);

   }
}
