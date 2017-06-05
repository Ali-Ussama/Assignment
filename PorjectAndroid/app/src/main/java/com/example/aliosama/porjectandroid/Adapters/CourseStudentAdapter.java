package com.example.aliosama.porjectandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Database.Models.StudentModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/22/2017.
 */

public class CourseStudentAdapter extends ArrayAdapter<StudentModel> implements View.OnClickListener{
    ArrayList<StudentModel> data;
    Context context;
    TextView StudentNameTextView;
    public CourseStudentAdapter(Context context, int resource, ArrayList<StudentModel> data) {
        super(context, resource,data);
        this.data = data;
        this.context = context;

    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.student_list_item,null);
        }
        StudentNameTextView = (TextView) v.findViewById(R.id.StudentNameItemTextView);
        StudentNameTextView.setText(data.get(position).getName());


        v.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //Download Student Solution.
    }
}
