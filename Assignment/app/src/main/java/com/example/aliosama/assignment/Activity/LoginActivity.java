package com.example.aliosama.assignment.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aliosama.assignment.Database.Helper.LoginHelper;
import com.example.aliosama.assignment.R;

public class LoginActivity extends AppCompatActivity  {

    EditText Email;
    EditText Password;
    LoginHelper loginHelper;
    Button loginbtn;
    TextView SignUpLinkTV;
//    CheckBox CBTeacher;
//    CheckBox CBStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try{
            Email = (EditText) findViewById(R.id.ETEmail);
            Password = (EditText) findViewById(R.id.EDPassword);
            loginbtn = (Button) findViewById(R.id.LoginBtn);
            SignUpLinkTV = (TextView) findViewById(R.id.SignUpLinkBtn);
            loginHelper = new LoginHelper(this);
//
//            CBStudent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(CBTeacher.isChecked())
//                        CBTeacher.setChecked(false);
//                    CBStudent.setChecked(true);
//                }
//            });
//            CBTeacher.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(CBStudent.isChecked())
//                        CBStudent.setChecked(false);
//                    CBTeacher.setChecked(true);
//
//                }
//            });
            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Validation()) {
                        //Progress Dialog
                        Progress_Dialog(v.getId());
                    }

                }
            });
            SignUpLinkTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLoginListener(v.getId());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean Validation(){
        //Get Data.
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String UserType = "";
//        if (CBStudent.isChecked())
//            UserType = CBStudent.getText().toString();
//        else if (CBTeacher.isChecked())
//            UserType = CBTeacher.getText().toString();
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Email.setError("enter a valid email address");
//            return false;
//        }else if( password.isEmpty() || password.length() < 3 || password.length() > 10){
//            Password.setError("between 3 and 10 alphanumeric characters");
//            return false;
//        } else if ( !CBStudent.isChecked() && !CBTeacher.isChecked()){
//            Toast.makeText(this, "Please Select User Type.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }
    public void onLoginListener(int id) {
        try {

            //Intent
//            Intent Teacherintent = new Intent(LoginActivity.this, THomeActivity.class);
//            Intent Studentintent = new Intent(LoginActivity.this, StudentHomeActivity.class);
            Intent SignUpintent = new Intent(LoginActivity.this, SignUpActivity.class);
//            Teacherintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Studentintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SignUpintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //actions buttons.

            if (id == R.id.SignUpLinkBtn){
                startActivity(SignUpintent);
                finish();
            }else if(id == R.id.LoginBtn){
                Intent m = new Intent(this,NavigationDrawer.class);
                m.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(m);
                finish();

                //Get Data.
//                String email = Email.getText().toString();
//                String password = Password.getText().toString();
//                String UserType = "";
//                if (CBStudent.isChecked())
//                    UserType = CBStudent.getText().toString();
//                else if (CBTeacher.isChecked())
//                    UserType = CBTeacher.getText().toString();
//
//
//                int UserID = loginHelper.CheckUserLogin(UserType, email, password);
//                if (UserID != -1) {
//                    if (UserType.matches("Teacher")) {
////                        Teacherintent.putExtra("TeacherID", UserID);
////                        startActivity(Teacherintent);
//                        finish();
//                    } else {
////                        Studentintent.putExtra("StudentID", UserID);
////                        startActivity(Studentintent);
//                        finish();
//                    }
//                } else {
//                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
//                }
//

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Progress_Dialog(int id) {
        final int ID = id;
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginListener(ID);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


}

