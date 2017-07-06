package com.example.aliosama.porjectandroid.Fragments.Student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aliosama.porjectandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentCoursesFragment extends Fragment {


    public StudentCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_courses, container, false);
    }

}
