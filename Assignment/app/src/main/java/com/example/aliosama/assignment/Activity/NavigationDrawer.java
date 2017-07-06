package com.example.aliosama.assignment.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aliosama.assignment.Fragment.HomeFragment;
import com.example.aliosama.assignment.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_ADD_COURSE = "addcourse";
    private static final String TAG_LOGOUT = "logout";
    private static  String CURRENT_TAG = TAG_HOME;

   public static int navItemIndex = 0;
    View navigation_header;
    String [] activityTitles;
    CircleImageView account_image;
    NavigationView navigationView;
    DrawerLayout drawer;
    TextView account_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        try {
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            Toolbar toolbar = (Toolbar) findViewById(R.id.Drawer_toolbar);
            setSupportActionBar(toolbar);

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigation_header = navigationView.getHeaderView(0);
            account_image = (CircleImageView) navigation_header.findViewById(R.id.nav_header_image);
            account_name = (TextView) navigation_header.findViewById(R.id.nave_header_account_name);
            account_image.setImageResource(R.drawable.lol);
            account_name.setText("Ali Ussama");

            activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

            if (savedInstanceState == null) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadFragment();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadActivity(){
        try {
            Intent intent = getActivity();
            this.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadFragment() {
        try {
            selectNavMenu();
            setToolbarTitle();
            FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction().replace(R.id.navigation_drawer_content,getFragment()).commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setToolbarTitle() throws Exception {
        try {
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void selectNavMenu() throws Exception{
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private Intent getActivity() {
        Intent intent = null;
        switch (navItemIndex){
            case 1:
                intent =  new Intent(this,AddCourseActivity.class);
                break;
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("Start Add Course !!!!!!!!!!");
        return intent;
    }

    private Fragment getFragment() throws Exception {
        switch (navItemIndex){
            case 0:
                return new HomeFragment();
            default:
                return new HomeFragment();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadFragment();
            } else {
                super.onBackPressed();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
            int id = item.getItemId();
            if (id == R.id.nav_home){
                CURRENT_TAG = TAG_HOME;
                navItemIndex = 0;
                drawer.closeDrawer(GravityCompat.START);
                loadFragment();
            }
            if (id == R.id.nav_add_course) {
                CURRENT_TAG = TAG_ADD_COURSE;
                navItemIndex = 1;
                drawer.closeDrawer(GravityCompat.START);
                loadActivity();
            } else if (id == R.id.nav_profile) {
                CURRENT_TAG = TAG_PROFILE;
                navItemIndex = 2;
            } else if (id == R.id.nav_logout) {
                CURRENT_TAG = TAG_LOGOUT;
                navItemIndex = 3;
            }
        return true;
    }


}
