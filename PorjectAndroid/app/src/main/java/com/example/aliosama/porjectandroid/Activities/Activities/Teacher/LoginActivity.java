package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aliosama.porjectandroid.Database.LoginHelper;
import com.example.aliosama.porjectandroid.R;

public class LoginActivity extends AppCompatActivity{

    EditText Email;
    EditText Password;
    LoginHelper loginHelper;
    Button loginbtn , singupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText) findViewById(R.id.ETEmail);
        Password = (EditText) findViewById(R.id.EDPassword);
        loginbtn = (Button) findViewById(R.id.LoginBtn);
        singupbtn = (Button) findViewById(R.id.SignUpBtn);
        loginHelper = new LoginHelper(this);
    }


    public void onLoginListener(View view) {
        try {
            String email = Email.getText().toString();
            String password = Password.getText().toString();
            Intent Teacherintent = new Intent(LoginActivity.this, THomeActivity.class);
            Intent SignUpintent = new Intent(LoginActivity.this, SignUpActivity.class);
            Teacherintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            int id = view.getId();
            if (id == R.id.SignUpBtn)
                startActivity(SignUpintent);
            else {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                } else {
                    int TeacherID = loginHelper.CheckUserLogin(email, password);
                    if (TeacherID != -1) {
                        if (id == R.id.LoginBtn) {
                            Teacherintent.putExtra("TeacherID",TeacherID);
                            startActivity(Teacherintent);
                        }
                    } else {
                        Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

