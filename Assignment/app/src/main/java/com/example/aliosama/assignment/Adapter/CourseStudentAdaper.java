package com.example.aliosama.assignment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aliosama.assignment.Database.Models.StudentModel;
import com.example.aliosama.assignment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aliosama on 6/30/2017.
 */

public class CourseStudentAdaper extends RecyclerView.Adapter<CourseStudentAdaper.customViewHolder>{
    public static ArrayList<StudentModel> CSdata;
    public CourseStudentAdaper(ArrayList<StudentModel> data) {
    this.CSdata = data;
    }

    @Override
    public CourseStudentAdaper.customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View inflateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_students_list_item,parent,false);
        return new customViewHolder(inflateView,CSdata);
    }

    @Override
    public void onBindViewHolder(CourseStudentAdaper.customViewHolder holder, int position) {
        holder.StudentImage.setImageResource(CSdata.get(position).getImageResources());
        holder.Name.setText(CSdata.get(position).getName());
        holder.Department.setText(CSdata.get(position).getDepartment());
        holder.Section.setText(CSdata.get(position).getSection());
    }

    @Override
    public int getItemCount() {
        return CSdata.size();
    }
    public static class customViewHolder extends RecyclerView.ViewHolder{
        CircleImageView StudentImage;
        TextView Name,Department,Section;
        ArrayList<StudentModel> CSdata;
        public customViewHolder(View itemView,ArrayList<StudentModel> data) {
            super(itemView);
            this.CSdata = data;
            StudentImage = (CircleImageView) itemView.findViewById(R.id.CSLT_student_image);
            Name = (TextView) itemView.findViewById(R.id.CSLT_student_name);
            Department = (TextView) itemView.findViewById(R.id.CSLT_student_department);
            Section = (TextView) itemView.findViewById(R.id.CSLT_student_section);

        }
    }
}
