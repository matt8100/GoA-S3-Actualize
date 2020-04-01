package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tbdapp.R;
import com.example.tbdapp.classes.Advisor;
import com.example.tbdapp.fragments.ContactsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Advisor> advisorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAdvisorProfiles();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ContactsFragment(advisorList));
        ft.commit();
    }

    public void createAdvisorProfiles() {
        advisorList.add(new Advisor("Brittany Williams", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, true));
        advisorList.add(new Advisor("Thomas Edwards", "Career", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, true));
        advisorList.add(new Advisor("Daniel Wilson", "Career", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Jaimie Miller", "Career", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Raiqah Johal", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Chris Peacock", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("George London", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Alexei Dubrivonich", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, true));
        advisorList.add(new Advisor("Aetherbald the Great", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Pyńęł Rziąćic", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Cornelius II of Rome", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Rünerig Pfochmännördt", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
        advisorList.add(new Advisor("Grzegorz Brzęczyszczykiewicz", "Financial", getString(R.string.placeholderText), getString(R.string.placeholderText), R.drawable.ic_face, false));
    }
}
