package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.R;

public class AddCourseActivity extends AppCompatActivity {

    EditText Name , Point;
    CheckBox FSemester, SSemester;
    Button SubmitCourse;
    CourseModel mCourseModel;
    CourseHelper mCourseHelper;
    Intent mIntent;
    int TeacherID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        mCourseHelper = new CourseHelper(this);
        mIntent = getIntent();
        TeacherID = mIntent.getExtras().getInt("TeacherID");
        Name = (EditText) findViewById(R.id.CourseNameEditText);
        Point = (EditText) findViewById(R.id.CoursePointEditText);
        FSemester = (CheckBox) findViewById(R.id.FirstSemesterCheckBox);
        SSemester = (CheckBox) findViewById(R.id.SecondSemesterCheckBox);
        SubmitCourse = (Button) findViewById(R.id.SubmitCourseBtn);

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
                if(SSemester.isChecked())
                    FSemester.setChecked(false);
            }
        });

    }

    public void onSubmitCourseListener(View view) {
       try {
           String Semester = null;
           if (FSemester.isChecked())
               Semester = FSemester.getText().toString();
           else if(SSemester.isChecked())
               Semester = SSemester.getText().toString();

           if(Semester != null && !Name.getText().toString().isEmpty() && !Point.getText().toString().isEmpty()) {
               mCourseModel = new CourseModel(Name.getText().toString(), Point.getText().toString(), Semester, TeacherID);
               mCourseHelper.AddCourse(mCourseModel);

           }else if (Semester != null && Name.getText().toString().isEmpty() || Point.getText().toString().isEmpty()){
               Toast.makeText(this, " Please Fill in Empty Fields. ", Toast.LENGTH_SHORT).show();
           }else if(Semester == null && !Name.getText().toString().isEmpty() && !Point.getText().toString().isEmpty()){
               Toast.makeText(this, " Select Only One Semester. ", Toast.LENGTH_SHORT).show();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
