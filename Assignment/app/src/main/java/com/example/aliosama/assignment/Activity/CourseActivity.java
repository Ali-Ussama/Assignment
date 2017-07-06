package com.example.aliosama.assignment.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliosama.assignment.Adapter.CourseStudentAdaper;
import com.example.aliosama.assignment.Database.Helper.LoginHelper;
import com.example.aliosama.assignment.Database.Models.CourseModel;
import com.example.aliosama.assignment.Database.Models.StudentModel;
import com.example.aliosama.assignment.R;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mCourseDescription ,mCourseName, mCoursePoints,mCourseSemester , NoStudentsToShowTV,course_imageText;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    CourseStudentAdaper mCourseStudentAdaper;
    LoginHelper mLoginHelper;
    ArrayList<StudentModel> data;
    CourseModel mCourseModel;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Intent intent = getIntent();
        mCourseModel = (CourseModel) intent.getSerializableExtra("data");

        mToolbar = (Toolbar) findViewById(R.id.Home_List_Item_toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mImageView = (ImageView) findViewById(R.id.Course_ImageView);
        course_imageText = (TextView) findViewById(R.id.Course_ImageText);
        mCourseName = (TextView) findViewById(R.id.course_name_textView);
        mCoursePoints = (TextView) findViewById(R.id.course_point_TextView);
        mCourseSemester = (TextView) findViewById(R.id.course_semester_TextView);
        mCourseDescription = (TextView) findViewById(R.id.course_description_TextView);
        mRecyclerView = (RecyclerView) findViewById(R.id.course_students_RecyclerView);
//        NoStudentsToShowTV = (TextView) findViewById(R.id.course_empty_message_TextView);

        mImageView.setImageResource(intent.getExtras().getInt("ImageColor"));
        course_imageText.setText(intent.getExtras().getString("ImageText"));
        mCourseName.setText(mCourseModel.getName());
        mCoursePoints.setText(mCourseModel.getPoint());
        mCourseSemester.setText(mCourseModel.getSemester());
        mCourseDescription.setText(mCourseModel.getDescription());

        data = new ArrayList<>();
//        mLoginHelper = new LoginHelper(this);
//        data = mLoginHelper.getStudentsForCourse(1);
        mCourseStudentAdaper = new CourseStudentAdaper(data);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        new FetchStudents().execute();

        mRecyclerView.setAdapter(mCourseStudentAdaper);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

  private class FetchStudents extends AsyncTask<Void,Void,ArrayList<StudentModel>>{

      ArrayList<StudentModel> StudentData;

      @Override
      protected ArrayList<StudentModel> doInBackground(Void... params) {
          StudentData = new ArrayList<>();
          StudentData.add(new StudentModel(R.drawable.lol,"CS","3","Ali Ussama","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol2,"IS","1","Ahmed Ali","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol3,"IT","2","Mohamed Zaki","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol4,"OR","4","Ezzat Ashraf","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol,"CS","3","Ali Ussama","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol2,"IS","1","Ahmed Ali","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol3,"IT","2","Mohamed Zaki","ali.usama.ali77@gmail.com","123456"));
          StudentData.add(new StudentModel(R.drawable.lol4,"OR","4","Ezzat Ashraf","ali.usama.ali77@gmail.com","123456"));
          return StudentData;
      }

      @Override
      protected void onPostExecute(ArrayList<StudentModel> result) {
          super.onPostExecute(result);

          CourseStudentAdaper.CSdata.clear();
          for(int i = 0 ; i < result.size() ; i++){

              CourseStudentAdaper.CSdata.add(new StudentModel(
                      result.get(i).getImageResources(),
                      result.get(i).getDepartment(),
                      result.get(i).getSection(),
                      result.get(i).getName(),
                      result.get(i).getEmail(),
                      result.get(i).getPassword()));
          }
          mCourseStudentAdaper.notifyDataSetChanged();

//          if(CourseStudentAdaper.CSdata.size() > 0) {
//              mRecyclerView.setAdapter(mCourseStudentAdaper);
//          }else{
//              mRecyclerView.setVisibility(View.GONE);
//              NoStudentsToShowTV.setVisibility(View.VISIBLE);
//          }
      }
  }

}