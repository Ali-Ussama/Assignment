package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Database.AssignmentHelper;
import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;
import com.example.aliosama.porjectandroid.R;

import java.io.File;

public class AddAssignmentActivity extends AppCompatActivity {
    EditText typeOfWork , Description ,StartTime, EndTime;
    Button UploadButton , SubmitButton;
    TextView AssignmentName;
    byte[] bytes;
    AssignmentHelper mAssignmentHelper;
    int courseID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        Intent intent = getIntent();
        courseID = intent.getExtras().getInt("CourseID");
        mAssignmentHelper = new AssignmentHelper(this);
        StartTime = (EditText) findViewById(R.id.StartTimeEditText);
        EndTime= (EditText) findViewById(R.id.EndTimeEditText);
        typeOfWork = (EditText) findViewById(R.id.TypeOfWorkEditText);
        Description= (EditText) findViewById(R.id.DescriptionEditText);
        UploadButton = (Button) findViewById(R.id.UploadAssignmentBtn);
        SubmitButton = (Button) findViewById(R.id.SubmitAssignmentBtn);
        AssignmentName = (TextView) findViewById(R.id.AssignmentNameTextView);

    }
    public void onUploadAssignmentListener(View view) {
        try{
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri SelectedPdf = data.getData();
                File file = new File(SelectedPdf.getPath());
                bytes = file.getPath().getBytes();
                AssignmentName.setText(file.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onSubmitAssignmentListener(View view) {
        try {
            mAssignmentHelper.AddAssignment(new AssignmentModel(
                    AssignmentName.getText().toString(),
                    Description.getText().toString(),
                    bytes,
                    typeOfWork.getText().toString(),
                    StartTime.getText().toString(),
                    EndTime.getText().toString(),
                    courseID));
        }catch (Exception e){
            e.printStackTrace();
        }
        finish();
    }
}
