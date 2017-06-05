package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.aliosama.porjectandroid.Adapters.TCourseAssingmentPagerAdapter;
import com.example.aliosama.porjectandroid.Adapters.TPagerAdapter;
import com.example.aliosama.porjectandroid.R;

public class TCourseAssignmentsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    int Course_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcourse_assignments);
        try {
            //Intent
            Intent mIntent = getIntent();
            Course_ID = mIntent.getExtras().getInt("Course_ID");
            //Toolbar
            mToolbar = (Toolbar) findViewById(R.id.CourseAssign_toolbar);
            setSupportActionBar(mToolbar);

            //TabLayout
            mTabLayout = (TabLayout) findViewById(R.id.CourseAssign_tab_layout);
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.course));
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.assignments));
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            //ViewPager
            final ViewPager mViewPager = (ViewPager) findViewById(R.id.CourseAssign_pager);
            final TCourseAssingmentPagerAdapter mTCourseAssingmentPagerAdapter = new TCourseAssingmentPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount(),Course_ID,this);
            mViewPager.setAdapter(mTCourseAssingmentPagerAdapter);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
