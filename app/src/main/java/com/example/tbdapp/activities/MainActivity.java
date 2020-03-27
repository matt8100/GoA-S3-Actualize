package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.tbdapp.R;
import com.google.android.material.tabs.TabItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ConstraintLayout messagesTab = findViewById(R.id.pageContent);
//
//        messagesTab.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Fragment selectedFragment = new FragmentContacts();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
//            }
//        });
        //Fragment selectedFragment = new FragmentContacts();
        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.frameLayout, new FragmentContacts());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }
}
