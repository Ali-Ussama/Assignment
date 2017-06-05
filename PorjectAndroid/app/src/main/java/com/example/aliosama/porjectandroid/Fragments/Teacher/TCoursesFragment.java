package com.example.aliosama.porjectandroid.Fragments.Teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.AddCourseActivity;
import com.example.aliosama.porjectandroid.Adapters.CourseAdapter;
import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/10/2017.
 */

public class TCoursesFragment extends Fragment {

    int TeacherID;
    Context context;
    ListView CoursesListView;
    Button Add_course_btn;
    ArrayList<CourseModel> Courses;
    CourseHelper mCourseHelper;
    CourseAdapter mCourseAdapter;

    public TCoursesFragment (int ID, Context context){
        this.TeacherID = ID;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tcourses_fragment, container, false);
        Add_course_btn = (Button) view.findViewById(R.id.AddCourseBtn);

        CoursesListView = (ListView) view.findViewById(R.id.CoueseListView);

        Add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, AddCourseActivity.class);
                mIntent.putExtra("TeacherID",TeacherID);
                context.startActivity(mIntent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCourseHelper = new CourseHelper(context);
        Courses = new ArrayList<>();
        Courses = mCourseHelper.getCourses(TeacherID);
        //        System.out.println("OnStart Course Name : " + Courses.get(Courses.size()-1).getName());
        mCourseAdapter = new CourseAdapter(context,Courses,R.layout.courses_list_item);
        CoursesListView.setAdapter(mCourseAdapter);
        mCourseAdapter.notifyDataSetChanged();

    }
}
