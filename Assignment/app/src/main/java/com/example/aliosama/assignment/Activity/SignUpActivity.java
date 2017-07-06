package com.example.aliosama.assignment.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aliosama.assignment.Database.Helper.LoginHelper;
import com.example.aliosama.assignment.Database.Models.StudentModel;
import com.example.aliosama.assignment.Database.Models.TeacherModel;
import com.example.aliosama.assignment.R;

public class SignUpActivity extends AppCompatActivity {
    EditText Name, Phone , Email,Password;
    Button RegisterButton;
    CheckBox CBTeacher, CBStudent;
    StudentModel studentModel;
    TeacherModel teacherModel;
    LoginHelper loginHelper;
    TextView LoginLinker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name = (EditText) findViewById(R.id.ETSingUpName);
        Phone  = (EditText) findViewById(R.id.ETSingUpPhone);
        Email  = (EditText) findViewById(R.id.ETSingUpEmail);
        Password  = (EditText) findViewById(R.id.ETSingUpPassword);
        RegisterButton = (Button) findViewById(R.id.RegisterBtn);
        CBTeacher = (CheckBox) findViewById(R.id.checkBoxTeacherSignUp);
        CBStudent = (CheckBox) findViewById(R.id.checkBoxStudentSignUp);
        LoginLinker = (TextView) findViewById(R.id.LoginLinkTV);
        Phone.setEnabled(false);
        loginHelper = new LoginHelper(this);

        CBStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBTeacher.isChecked())
                    CBTeacher.setChecked(false);
                CBStudent.setChecked(true);
                Phone.setEnabled(false);
            }
        });
        CBTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBStudent.isChecked())
                    CBStudent.setChecked(false);
                CBTeacher.setChecked(true);
                Phone.setEnabled(true);
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CBTeacher.isChecked()){
                    if(TeacherValidation()){
                        //Progress Dialog
                        ProgressDialog("Teacher");
                    }
                }else if(CBStudent.isChecked()){
                    if(StudentValidation()){
                        //Progress Dialog
                        ProgressDialog("Student");

                    }
                }
            }
        });

        LoginLinker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    public void onStudentSignUpListener(){
        try{
////            Intent Studentintent = new Intent(SignUpActivity.this,StudentHomeActivity.class);
////            Studentintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            studentModel = new StudentModel(Name.getText().toString(), Email.getText().toString(), Password.getText().toString());
//            int StudentID = loginHelper.AddStudent(studentModel);
//            if (StudentID != -1) {
//                Toast.makeText(this, "Welcome " + studentModel.getName(), Toast.LENGTH_SHORT).show();
////                Studentintent.putExtra("StudentID",StudentID);
////                startActivity(Studentintent);
//                finish();
//            } else {
//                Toast.makeText(this, "This User Exists", Toast.LENGTH_SHORT).show();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void onTeacherSignUpListener() {
        try {
//            Intent Teacherintent = new Intent(SignUpActivity.this, THomeActivity.class);
//            Teacherintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            teacherModel = new TeacherModel(Name.getText().toString(), Phone.getText().toString(), Email.getText().toString(), Password.getText().toString());
            int TeacherID = loginHelper.AddTeacher(teacherModel);
            if (TeacherID != -1) {
                Toast.makeText(this, "Welcome " + teacherModel.getName(), Toast.LENGTH_SHORT).show();
//                Teacherintent.putExtra("TeacherID",TeacherID);
//                startActivity(Teacherintent);
                finish();
            } else {
                Toast.makeText(this, "This User Exists", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean TeacherValidation(){
        if (Name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fill Up Empty Fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean StudentValidation(){
        if (Name.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fill Up Empty Fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void ProgressDialog(String UserType){
        final String User = UserType;
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(User.matches("Student"))
                            onStudentSignUpListener();
                        else
                            onTeacherSignUpListener();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }
}
