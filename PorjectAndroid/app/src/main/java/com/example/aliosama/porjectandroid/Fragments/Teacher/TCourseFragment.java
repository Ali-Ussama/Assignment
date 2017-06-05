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
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.AddAssignmentActivity;
import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.UpdateCourseActivity;
import com.example.aliosama.porjectandroid.Adapters.CourseStudentAdapter;
import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.Database.LoginHelper;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.Database.Models.StudentModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;


public class TCourseFragment extends Fragment {

    TextView CName, CPoint,CSemester;
    ListView ListViewStudent;
    Button DeleteButton  , UpdateButton;

    ArrayList<StudentModel> data;
    CourseStudentAdapter mCourseStudentAdapter;
    CourseHelper mCourseHelper;
    CourseModel mCourseModel;
    LoginHelper mLoginHelper;
    Context context;
    int Course_ID;
    int Teacher_ID;
    public TCourseFragment(int Course_id,Context context) {
        this.context = context;
        this.Course_ID = Course_id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tcourse_fragment, container, false);
        try {
            mLoginHelper = new LoginHelper(context);
            mCourseHelper = new CourseHelper(context);
            mCourseModel = mCourseHelper.getCourse(Course_ID);

            CName = (TextView) view.findViewById(R.id.CourseNameTextView);
            CPoint =  (TextView) view.findViewById(R.id.CoursePointTextView);
            CSemester =  (TextView) view.findViewById(R.id.CourseSemesterTextView);
            DeleteButton = (Button) view.findViewById(R.id.DeleteCourseBtn);
            UpdateButton = (Button) view.findViewById(R.id.UpdateCourseBtn);

            //Course Information
            CName.setText(mCourseModel.getName());
            CPoint.setText(mCourseModel.getPoint());
            CSemester.setText(mCourseModel.getSemester());
            Teacher_ID = mCourseModel.getTeacher_ID();

            //ListView
            ListViewStudent = (ListView) view.findViewById(R.id.StudentListView);

            //Buttons Actions
            DeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mCourseHelper.DeleteCourse(mCourseModel.getID());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            UpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pass course model
                    Intent intent = new Intent(context, UpdateCourseActivity.class);
                    intent.putExtra("CourseModel",mCourseModel);
                    context.startActivity(intent);
                }
            });

        }catch (Exception e){

        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //ListView
        data = mLoginHelper.getStudents();
        mCourseStudentAdapter = new CourseStudentAdapter(context,R.layout.student_list_item,data);
        ListViewStudent.setAdapter(mCourseStudentAdapter);
    }
}
