package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.tbdapp.R;
import com.example.tbdapp.fragments.ProfileDisplayFragment;
import com.example.tbdapp.models.Singleton;
import com.google.android.material.tabs.TabLayout;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.fragments.ContactsFragment;
import com.example.tbdapp.fragments.ExploreFragment;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private ProfileDisplayFragment fragmentProfileDisplay;
    private TabLayout mTabLayout;

    private ArrayList<Advisor> advisorList = Singleton.getInstance().advisors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

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
        final int tabTranslation = 12;

        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // Log.d(//TAG, "TabLayout Tab selected: " + position);
                loadFragment(position);

                //(ImageView) tab.getCustomView().findViewById(R.id.icon)

                tab.getCustomView().animate().translationY(0);
                AlphaAnimation animation1 = new AlphaAnimation(0.8f, 1.0f);
                animation1.setDuration(1000);
                animation1.setFillAfter(true);
                tab.getCustomView().startAnimation(animation1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().animate().translationY(tabTranslation);
                AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.8f);
                animation1.setDuration(1000);
                animation1.setFillAfter(true);
                tab.getCustomView().startAnimation(animation1);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        View view1 = getLayoutInflater().inflate(R.layout.layout_custom_tabs, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_explore_icon);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(view1));

        View view2 = getLayoutInflater().inflate(R.layout.layout_custom_tabs, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_message_icon);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(view2));

        View view3 = getLayoutInflater().inflate(R.layout.layout_custom_tabs, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_profile_icon);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(view3));

        mTabLayout.getTabAt(1).getCustomView().animate().translationY(tabTranslation);
        mTabLayout.getTabAt(2).getCustomView().animate().translationY(tabTranslation);
        AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.8f);
        animation1.setDuration(1);
        animation1.setFillAfter(true);
        mTabLayout.getTabAt(1).getCustomView().startAnimation(animation1);
        mTabLayout.getTabAt(2).getCustomView().startAnimation(animation1);
    }
}





