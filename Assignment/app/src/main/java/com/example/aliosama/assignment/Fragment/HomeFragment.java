package com.example.aliosama.assignment.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aliosama.assignment.Adapter.HomeAdapter;
import com.example.aliosama.assignment.Database.Models.CourseModel;
import com.example.aliosama.assignment.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<CourseModel> data;
    LinearLayoutManager mLinearLayoutManager;
    HomeAdapter homeAdapter;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        try {

            recyclerView = (RecyclerView) view.findViewById(R.id.Home_RecyclerView);
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            data = new ArrayList<>();
            data.add(new CourseModel("CS101",
                                    "100",
                                    "Second",
                                    "This Course has 5 Chapters : \n" +
                                    " 1- Introduction.\n" +
                                    " 2- Basics.\n" +
                                    " 3- Layouts Types and difference.\n" +
                                    " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                                    1));
            data.add(new CourseModel("AI102",
                                    "150",
                                    "First",
                                    "This Course has 5 Chapters : \n" +
                                    " 1- Introduction.\n" +
                                    " 2- Basics.\n" +
                                    " 3- Layouts Types and difference.\n" +
                                    " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                                    1));
            data.add(new CourseModel("SA 105",
                                    "120",
                                    "Second",
                                    "This Course has 5 Chapters : \n" +
                                    " 1- Introduction.\n" +
                                    " 2- Basics.\n" +
                                    " 3- Layouts Types and difference.\n" +
                                    " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                                     1));
            data.add(new CourseModel("CL101",
                                    "100",
                                    "First",
                                    "This Course has 5 Chapters : \n" +
                                    " 1- Introduction.\n" +
                                    " 2- Basics.\n" +
                                    " 3- Layouts Types and difference.\n" +
                                    " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                                     1));
            data.add(new CourseModel("CL102",
                                    "100",
                                    "Second",
                                    "This Course has 5 Chapters : \n" +
                                    " 1- Introduction.\n" +
                                    " 2- Basics.\n" +
                                    " 3- Layouts Types and difference.\n" +
                                    " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                                     1));
            data.add(new CourseModel("CS101",
                            "100",
                            "Second",
                            "This Course has 5 Chapters : \n" +
                            " 1- Introduction.\n" +
                            " 2- Basics.\n" +
                            " 3- Layouts Types and difference.\n" +
                            " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                    1));
            data.add(new CourseModel("AI102",
                            "150",
                            "First",
                            "This Course has 5 Chapters : \n" +
                            " 1- Introduction.\n" +
                            " 2- Basics.\n" +
                            " 3- Layouts Types and difference.\n" +
                            " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                    1));
            data.add(new CourseModel("CL102",
                            "100",
                            "Second",
                            "This Course has 5 Chapters : \n" +
                            " 1- Introduction.\n" +
                            " 2- Basics.\n" +
                            " 3- Layouts Types and difference.\n" +
                            " 4- AsyncTask and it's Advantages and\n Disadvantages.",
                    1));
            homeAdapter = new HomeAdapter(data);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            recyclerView.setAdapter(homeAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

}
