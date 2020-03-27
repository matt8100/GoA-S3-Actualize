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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the advisors

        ArrayList<Advisor> advisorList = new ArrayList<>();
        advisorList.add(new Advisor("Brittany Williams", "Financial", R.drawable.ic_face, true));
        advisorList.add(new Advisor("Thomas Edwards", "Career", R.drawable.ic_face, true));
        advisorList.add(new Advisor("Daniel Wilson", "Career", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Jaimie Miller", "Career", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Raiqah Johal", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Chris Peacock", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("George London", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Alexei Dubrivonich", "Financial", R.drawable.ic_face, true));
        advisorList.add(new Advisor("Aetherbald the Great", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Pyńęł Rziąćic", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Cornelius II of Rome", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Rünerig Pfochmännördt", "Financial", R.drawable.ic_face, false));
        advisorList.add(new Advisor("Grzegorz Brzęczyszczykiewicz", "Financial", R.drawable.ic_face, false));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new FragmentContacts(advisorList));
        ft.commit();
    }


    //Advisor object variable, given advisor's name, advisor type, and image
    public class Advisor {
        String advisorName;
        String advisorType;
        int advisorImage;
        boolean isFavourite;

        public Advisor(String name, String type, int image, boolean favourite) {
            advisorName = name;
            advisorType = type;
            advisorImage = image;
            isFavourite = favourite;
        }
        public String getAdvisorName() {
            return advisorName;
        }
        public String getAdvisorType() {
            return advisorType;
        }
        public int getAdvisorImage() {
            return advisorImage;
        }
        public boolean isFavourite() {
            return isFavourite;
        }
    }
}
