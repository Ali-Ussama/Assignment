package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.R;

public class UpdateCourseActivity extends AppCompatActivity {
    Intent mIntent;
    CourseModel mCourseModel;
    CourseHelper mCourseHelper;
    EditText Name,Point;
    CheckBox FSemester,SSemester;
    Button UpdateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);
        try {
            //Objects Helper & CourseModel
            mCourseHelper = new CourseHelper(this);
            mIntent = getIntent();
            mCourseModel = (CourseModel) mIntent.getSerializableExtra("CourseModel");

            //set Resources.
            Name = (EditText) findViewById(R.id.UpdateCourseNameEditText);
            Point= (EditText) findViewById(R.id.UpdateCoursePointEditText);
            FSemester = (CheckBox) findViewById(R.id.UpdateFirstSemesterCheckBox);
            SSemester = (CheckBox) findViewById(R.id.UpdateSecondSemesterCheckBox);
            UpdateBtn = (Button) findViewById(R.id.UpdateCourseBtn);

            //Set Fields With data
            Name.setText(mCourseModel.getName());
            Point.setText(mCourseModel.getPoint());

            if(mCourseModel.getSemester().matches("First Semester")){
                FSemester.setChecked(true);
            }else{
                SSemester.setChecked(true);
            }


            //only Select one Semester
            FSemester.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FSemester.isChecked())
                        SSemester.setChecked(false);
                }
            });

            SSemester.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SSemester.isChecked())
                        FSemester.setChecked(false);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onUpdateCourseListener(View view) {
        try {
            mCourseModel.setName(Name.getText().toString());
            mCourseModel.setPoint(Point.getText().toString());
            if (FSemester.isChecked())
                mCourseModel.setSemester(FSemester.getText().toString());
            else
                mCourseModel.setSemester(SSemester.getText().toString());

            mCourseHelper.UpdateCourse(mCourseModel);
            this.finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
