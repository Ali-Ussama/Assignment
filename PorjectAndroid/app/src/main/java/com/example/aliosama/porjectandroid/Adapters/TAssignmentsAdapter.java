package com.example.aliosama.porjectandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.TUpdateAssignmentActivity;
import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/23/2017.
 */

public class TAssignmentsAdapter extends ArrayAdapter<AssignmentModel> {
    ArrayList<AssignmentModel> data;
    Context context;
    TextView AssignTextView;
    public TAssignmentsAdapter(Context context, int resource , ArrayList<AssignmentModel> data) {
        super(context, resource, data);
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.assignment_list_item,null);
        }
        AssignTextView = (TextView) v.findViewById(R.id.AssignNameTextView);
        AssignTextView.setText(data.get(position).getName());
        final AssignmentModel mAssignmentModel = data.get(position);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, TUpdateAssignmentActivity.class);
                mIntent.putExtra("Assignments",mAssignmentModel);
                context.startActivity(mIntent);
            }
        });
        return v;
    }
}
