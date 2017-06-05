package com.example.aliosama.porjectandroid.Fragments.Teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.aliosama.porjectandroid.Activities.Activities.Teacher.AddAssignmentActivity;
import com.example.aliosama.porjectandroid.Adapters.CourseAdapter;
import com.example.aliosama.porjectandroid.Adapters.TAssignmentsAdapter;
import com.example.aliosama.porjectandroid.Database.AssignmentHelper;
import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;
import com.example.aliosama.porjectandroid.R;

import java.util.ArrayList;

public class TAssignmentsFragment extends Fragment {
    int CourseID;
    Context context;
    ArrayList<AssignmentModel> data;
    AssignmentHelper mAssignmentHelper;
    TAssignmentsAdapter mTAssignmentAdapter;
    ListView AssignsListView;
    Button AddNewAssignment;
    public TAssignmentsFragment (int ID, Context context){
        this.CourseID = ID;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tassignments_fragment, container, false);
        AssignsListView =(ListView) view.findViewById(R.id.AssignmentsListView);
        AddNewAssignment = (Button) view.findViewById(R.id.AddAssignmentBtn);
        AddNewAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAssignmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CourseID",CourseID);
                context.startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mAssignmentHelper = new AssignmentHelper(context);
        data = new ArrayList<>();
        data = mAssignmentHelper.getAssignments(CourseID);
        mTAssignmentAdapter = new TAssignmentsAdapter(context,R.layout.assignment_list_item,data);
        AssignsListView.setAdapter(mTAssignmentAdapter);
        mTAssignmentAdapter.notifyDataSetChanged();

    }

}
