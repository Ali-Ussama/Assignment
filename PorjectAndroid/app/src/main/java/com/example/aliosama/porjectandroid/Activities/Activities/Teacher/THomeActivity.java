package com.example.aliosama.porjectandroid.Activities.Activities.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.aliosama.porjectandroid.Adapters.TPagerAdapter;
import com.example.aliosama.porjectandroid.Database.CourseHelper;
import com.example.aliosama.porjectandroid.R;

public class THomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    Intent mIntent;
    int TeacherID;
    CourseHelper mCourseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        try {
            mCourseHelper = new CourseHelper(this.getBaseContext());
            mIntent = getIntent();
            TeacherID = mIntent.getExtras().getInt("TeacherID");
            //Toolbar
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            //TabLayout
            mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.profile));
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.courses));
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            //ViewPager
            final ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
            final TPagerAdapter mPagerAdapter = new TPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(),TeacherID,this);

            mViewPager.setAdapter(mPagerAdapter);
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
            System.out.println("Exception Main");
            e.printStackTrace();
        }
    }
}
