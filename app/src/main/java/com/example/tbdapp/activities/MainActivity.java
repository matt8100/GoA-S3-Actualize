package com.example.tbdapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tbdapp.R;
import com.example.tbdapp.fragments.ContactsFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Advisor> advisorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the advisors
        createAdvisorProfiles();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdvisorProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("advisor", advisorList.get(2));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new ContactsFragment(advisorList));
        ft.commit();
    }


    //Advisor object variable, given advisor's name, advisor type, and image
    public class Advisor implements Serializable {
        public String advisorName;
        public String advisorType;
        public String advisorDescription;
        public String advisorSkills;
        public int advisorImage;
        public boolean isFavourite;

        public Advisor(String advisorName, String advisorType, String advisorDescription, String advisorSkills, int advisorImage, boolean isFavourite) {
            this.advisorName = advisorName;
            this.advisorType = advisorType;
            this.advisorDescription = advisorDescription;
            this.advisorSkills = advisorSkills;
            this.advisorImage = advisorImage;
            this.isFavourite = isFavourite;
        }

        public String getAdvisorName() {
            return advisorName;
        }
        public String getAdvisorType() {
            return advisorType;
        }
        public String getAdvisorDescription() {
            return advisorDescription;
        }
        public String getAdvisorSkills() {
            return advisorSkills;
        }
        public int getAdvisorImage() {
            return advisorImage;
        }
        public boolean isFavourite() {
            return isFavourite;
        }
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
