package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.tbdapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ProfileDisplayFragment fragmentProfileDisplay;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentProfileDisplay = ProfileDisplayFragment.newInstance();
        setupTabLayout();
        loadFragment(0);
    }


    private void loadFragment(int position) {
        switch (position) {
            case 0: //position of first tab bar item (explore screen)
                loadExploreFragment();
                break;
            case 1: //position of second tab bar item (contacts screen)
                loadContactsFragment();
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
                Log.d(TAG, "TabLayout Tab selected: " + position);
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

