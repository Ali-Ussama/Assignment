package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Database.AssignmentHelper;
import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;
import com.example.aliosama.porjectandroid.R;

import java.io.File;

public class TUpdateAssignmentActivity extends AppCompatActivity {
    EditText typeOfWork , Description ,StartTime, EndTime;
    TextView AssignmentNameTV;
    byte[] bytes;
    AssignmentHelper mAssignmentHelper;
    AssignmentModel mAssignmentModel;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tupdate_assignment);
        try {
            mAssignmentHelper = new AssignmentHelper(this);
            mIntent = getIntent();
            mAssignmentModel = (AssignmentModel) mIntent.getSerializableExtra("Assignments");

            //Declaration.
            StartTime = (EditText) findViewById(R.id.UpdateStartTimeEditText);
            EndTime= (EditText) findViewById(R.id.UpdateEndTimeEditText);
            typeOfWork = (EditText) findViewById(R.id.UpdateTypeOfWorkEditText);
            Description= (EditText) findViewById(R.id.UpdateDescriptionEditText);
            AssignmentNameTV = (TextView) findViewById(R.id.UpdateAssignmentNameTextView);
            //Set Fields with Assignment data.
            StartTime.setText(mAssignmentModel.getStartTime());
            EndTime.setText(mAssignmentModel.getEndTime());
            typeOfWork.setText(mAssignmentModel.getTypeOfWork());
            Description.setText(mAssignmentModel.getDescription());
            AssignmentNameTV.setText(mAssignmentModel.getName());
            bytes = mAssignmentModel.getContent();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onDeleteAssignmentListener(View view) {
        try {
            mAssignmentHelper.DeleteAssignment(mAssignmentModel.getId());
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onUpdateAssignmentListener(View view) {
        try {
            mAssignmentHelper.UpdateAssignment(new AssignmentModel(
                    mAssignmentModel.getId(),
                    AssignmentNameTV.getText().toString(),
                    Description.getText().toString(),
                    bytes,
                    typeOfWork.getText().toString(),
                    StartTime.getText().toString(),
                    EndTime.getText().toString(),
                    mAssignmentModel.getCourse_ID()));
        }catch (Exception e){
            e.printStackTrace();
        }
        finish();
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
                AssignmentNameTV.setText(file.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
