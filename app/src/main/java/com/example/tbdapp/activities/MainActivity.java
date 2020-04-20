package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.tbdapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ProfileDisplayFragment fragmentProfileDisplay;
    private ProfileFragment fragmentProfile;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentProfileDisplay = ProfileDisplayFragment.newInstance();
        fragmentProfile = ProfileFragment.newInstance();
        //LaunchProfile(findViewById(R.id.tab_profile));
        setupTabLayout();
        loadFragment(0);
    }


    private void loadFragment(int position) {
        switch (position) {
            case 0: //position of first tab bar item (explore screen)
                //loadExploreFragment();
                break;
            case 1: //position of second tab bar item (contacts screen)
                //loadContactsFragment();
                break;
            case 2: //position of third tab bar item (profile screen)
                loadProfileFragment();
                break;
        }
    }


    private void setupTabLayout() {
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //Log.d(TAG, "TabLayout Tab selected: " + position);
                loadFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public void loadProfileFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentProfileDisplay);
        fragmentTransaction.commit();
        Log.d("TAG","launch profile");
    }



}

