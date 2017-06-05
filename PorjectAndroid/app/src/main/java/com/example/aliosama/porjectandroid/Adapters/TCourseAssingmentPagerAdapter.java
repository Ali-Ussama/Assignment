package com.example.aliosama.porjectandroid.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aliosama.porjectandroid.Fragments.Teacher.TAssignmentsFragment;
import com.example.aliosama.porjectandroid.Fragments.Teacher.TCourseFragment;
import com.example.aliosama.porjectandroid.Fragments.Teacher.TCoursesFragment;
import com.example.aliosama.porjectandroid.Fragments.Teacher.TProfileFragment;

/**
 * Created by aliosama on 5/22/2017.
 */

public class TCourseAssingmentPagerAdapter extends FragmentStatePagerAdapter {

    private int NumberOfTabs;
    private Context context;
    private int CourseID;
    public TCourseAssingmentPagerAdapter(FragmentManager fm,int NumberOfTabs,int Course_ID,Context context) {
        super(fm);
        this.context = context;
        this.CourseID = Course_ID;
        this.NumberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TCourseFragment mCourseFragment = new TCourseFragment(this.CourseID, this.context);
                return mCourseFragment;
            case 1:
                TAssignmentsFragment mAssignmentsFragment = new TAssignmentsFragment(this.CourseID, this.context);
                return mAssignmentsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumberOfTabs;
    }
}
