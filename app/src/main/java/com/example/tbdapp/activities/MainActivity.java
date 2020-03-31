package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tbdapp.R;
import com.example.tbdapp.fragments.ContactsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Advisor> advisorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the advisors
        createAdvisorProfiles();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ContactsFragment(advisorList));
        ft.commit();
    }


    //Advisor object variable, given advisor's name, advisor type, and image
    public class Advisor {
        public String advisorName;
        public String advisorType;
        public int advisorImage;
        public boolean isFavourite;

        public Advisor(String advisorName, String advisorType, int advisorImage, boolean isFavourite) {
            this.advisorName = advisorName;
            this.advisorType = advisorType;
            this.advisorImage = advisorImage;
            this.isFavourite = isFavourite;
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

    public void createAdvisorProfiles() {
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
    }
}
