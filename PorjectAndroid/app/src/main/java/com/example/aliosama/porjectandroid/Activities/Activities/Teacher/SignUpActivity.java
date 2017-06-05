package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aliosama.porjectandroid.Database.LoginHelper;
import com.example.aliosama.porjectandroid.Database.Models.StudentModel;
import com.example.aliosama.porjectandroid.Database.Models.TeacherModel;
import com.example.aliosama.porjectandroid.R;

public class SignUpActivity extends AppCompatActivity {

    EditText Name, Phone , Email,Password;
    Button RegisterButton;
    CheckBox mCheckBox;
    StudentModel studentModel;
    TeacherModel teacherModel;
    LoginHelper loginHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name = (EditText) findViewById(R.id.ETSingUpName);
        Phone  = (EditText) findViewById(R.id.ETSingUpPhone);
        Email  = (EditText) findViewById(R.id.ETSingUpEmail);
        Password  = (EditText) findViewById(R.id.ETSingUpPassword);
        RegisterButton = (Button) findViewById(R.id.RegisterBtn);
        mCheckBox = (CheckBox) findViewById(R.id.CBTeacher);
        Phone.setEnabled(false);
        loginHelper = new LoginHelper(this);

    }

    public void onSignUpListener(View view) {
       try {
           Intent Teacherintent = new Intent(SignUpActivity.this, THomeActivity.class);
//           Intent Studentintent = new Intent(SignUpActivity.this,StudentActivity.class);
           Teacherintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           if (mCheckBox.isChecked()) {
               if (Name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                   Toast.makeText(this, "Fill Up Empty Fields", Toast.LENGTH_SHORT).show();
               } else {
                   teacherModel = new TeacherModel(Name.getText().toString(), Phone.getText().toString(), Email.getText().toString(), Password.getText().toString());
                   int TeacherID = loginHelper.AddTeacher(teacherModel);
                   if (TeacherID != -1) {
                       Toast.makeText(this, "Welcome " + teacherModel.getName(), Toast.LENGTH_SHORT).show();
                       Teacherintent.putExtra("TeacherID",TeacherID);
                       startActivity(Teacherintent);
                   } else {
                       Toast.makeText(this, "This User Exists", Toast.LENGTH_SHORT).show();
                   }
               }
           } else {
               if (Name.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                   Toast.makeText(this, "Fill Up Empty Fields", Toast.LENGTH_SHORT).show();
               } else {
                   studentModel = new StudentModel(Name.getText().toString(), Email.getText().toString(), Password.getText().toString());
                   int StudentID = loginHelper.AddStudent(studentModel);
                   if (StudentID != -1) {
                       Toast.makeText(this, "Welcome " + studentModel.getName(), Toast.LENGTH_SHORT).show();
//                        Studentintent.putExtra("StudentID",StudentID);
//                            startActivity(Studentintent);
                   } else {
                       Toast.makeText(this, "This User Exists", Toast.LENGTH_SHORT).show();
                   }
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void onCheckBoxListener(View view) {
        if(mCheckBox.isChecked())
            Phone.setEnabled(true);
        else
            Phone.setEnabled(false);
    }
}
