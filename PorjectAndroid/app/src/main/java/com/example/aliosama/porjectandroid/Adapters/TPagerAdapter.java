package com.example.aliosama.porjectandroid.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aliosama.porjectandroid.Fragments.Teacher.TAssignmentsFragment;
import com.example.aliosama.porjectandroid.Fragments.Teacher.TCoursesFragment;
import com.example.aliosama.porjectandroid.Fragments.Teacher.TProfileFragment;

/**
 * Created by aliosama on 5/21/2017.
 */

public class TPagerAdapter extends FragmentStatePagerAdapter {
    int NumberOfTabs;
    int TeacherID;
    Context context;
    public TPagerAdapter(FragmentManager fm,int NumOfTabs,int teacherID,Context context) {
        super(fm);
        this.NumberOfTabs = NumOfTabs;
        this.TeacherID = teacherID;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TProfileFragment mTProfileFragment = new TProfileFragment(this.TeacherID,this.context);
                return mTProfileFragment;
            case 1:
                TCoursesFragment mTCoursesFragment = new TCoursesFragment(this.TeacherID,this.context);
                return mTCoursesFragment;
//            case 2:
//                TAssignmentsFragment mTAssignmentsFragment = new TAssignmentsFragment(this.TeacherID,this.context);
//                return mTAssignmentsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumberOfTabs;
    }
}
