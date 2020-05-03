package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.tbdapp.R;
import com.example.tbdapp.fragments.ProfileDisplayFragment;
import com.example.tbdapp.models.Singleton;
import com.google.android.material.tabs.TabLayout;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.fragments.ContactsFragment;
import com.example.tbdapp.fragments.ExploreFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ProfileDisplayFragment fragmentProfileDisplay;
    private TabLayout mTabLayout;

    private ArrayList<Advisor> advisorList = Singleton.getInstance().advisors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentProfileDisplay = new ProfileDisplayFragment();

        setupTabLayout();
        loadFragment(0); //load fragment for first tab layout item
    }

    private void loadContactsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ContactsFragment(advisorList, this));
        ft.commit();
    }

    private void loadExploreFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ExploreFragment(advisorList));
        ft.commit();
    }

    public void loadProfileFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentProfileDisplay);
        fragmentTransaction.commit();
        Log.d("TAG","launch profile");
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
                // Log.d(//TAG, "TabLayout Tab selected: " + position);
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
}





