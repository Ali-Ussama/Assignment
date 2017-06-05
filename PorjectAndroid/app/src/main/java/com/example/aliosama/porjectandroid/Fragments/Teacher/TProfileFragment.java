package com.example.aliosama.porjectandroid.Fragments.Teacher;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Database.LoginHelper;
import com.example.aliosama.porjectandroid.Database.Models.TeacherModel;
import com.example.aliosama.porjectandroid.R;

/**
 * Created by aliosama on 5/10/2017.
 */

public class TProfileFragment extends Fragment implements View.OnClickListener{
    Button UpdateBtn;
    EditText Name,Phone,Email , Password;
    CheckBox UpdateCheckBox;
    int TeacherID;
    LoginHelper mLoginHelper;
    Context context;
    TeacherModel mTeacherModel;
    public TProfileFragment(int teacherID, Context context) {
        this.TeacherID = teacherID;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tprofile_fragment,container,false);
       try {
           mLoginHelper = new LoginHelper(this.context);
           mTeacherModel = mLoginHelper.getTeacher(this.TeacherID);
           Name = (EditText) view.findViewById(R.id.ProfileNameEditText);
           Phone = (EditText) view.findViewById(R.id.ProfilePhoneEditText);
           Email = (EditText) view.findViewById(R.id.ProfileEmailEditText);
           Password = (EditText) view.findViewById(R.id.ProfilePasswordEditText);
           UpdateCheckBox = (CheckBox) view.findViewById(R.id.ProfileUpdateCheckBox);

           UpdateBtn = (Button) view.findViewById(R.id.ProfileUpdateBtn);
           Name.setText(mTeacherModel.getName());
           Phone.setText(mTeacherModel.getPhone());
           Email.setText(mTeacherModel.getEmail());
           Password.setText(mTeacherModel.getPassword());

           Name.setEnabled(false);
           Phone.setEnabled(false);
           Email.setEnabled(false);
           Password.setEnabled(false);
           UpdateBtn.setEnabled(false);

           UpdateCheckBox.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(UpdateCheckBox.isChecked()){
                       Name.setEnabled(true);
                       Phone.setEnabled(true);
                       Email.setEnabled(true);
                       Password.setEnabled(true);
                       UpdateBtn.setEnabled(true);
                   }else{
                       Name.setEnabled(false);
                       Phone.setEnabled(false);
                       Email.setEnabled(false);
                       Password.setEnabled(false);
                       UpdateBtn.setEnabled(false);
                   }
               }
           });

           UpdateBtn.setOnClickListener(this);
       }catch (Exception e){
           e.printStackTrace();
       }

        return view;
    }


    @Override
    public void onClick(View v) {
        mTeacherModel = new TeacherModel(this.TeacherID,Name.getText().toString(),Phone.getText().toString(),
                                        Email.getText().toString(),Password.getText().toString());
        mLoginHelper.UpdateTeacher(mTeacherModel);
    }
}
