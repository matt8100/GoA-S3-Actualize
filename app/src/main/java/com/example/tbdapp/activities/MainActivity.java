package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tbdapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ProfileDisplayFragment fragmentProfileDisplay;
    private ProfileFragment fragmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentProfileDisplay = ProfileDisplayFragment.newInstance();
        fragmentProfile = ProfileFragment.newInstance();
        TabLayout tabItem = findViewById(R.id.tab_profile);
        LaunchProfile(findViewById(R.id.tab_profile));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;

    }


    public boolean onEditSelected(MenuItem item){
        if(item.getItemId()== R.id.buttonEdit) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragmentProfile);
            fragmentTransaction.commit();
        }return true;
    }


    public void LaunchProfile(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentProfileDisplay);
        fragmentTransaction.commit();
        Log.d("TAG","launch profile");
    }

}






/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        }

    public void LaunchProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
*/
