package com.example.aliosama.porjectandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.TCourseAssignmentsActivity;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by aliosama on 5/22/2017.
 */

public class CourseAdapter extends ArrayAdapter<CourseModel> {
    Context context;
    ArrayList<CourseModel> data;
    TextView course;
    public CourseAdapter(Context context, ArrayList<CourseModel> data,int resource) {
        super(context, resource,data);
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.courses_list_item, null);
        }
        course = (TextView) v.findViewById(R.id.CouseNameItemTextView);
        course.setText(data.get(position).getName());
        final int Course_id = data.get(position).getID();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to CourseAssignment Activity.
                Intent mIntent = new Intent(context, TCourseAssignmentsActivity.class);
                mIntent.putExtra("Course_ID",Course_id);
                context.startActivity(mIntent);
            }
        });
        return v;
    }


}
