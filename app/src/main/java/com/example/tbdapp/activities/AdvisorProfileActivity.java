package com.example.tbdapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.classes.Advisor;

public class AdvisorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_profile);

        getActionBar().hide();

        // declare variables
        final TextView advisorName = findViewById(R.id.advisorProfile_advisorName);
        final TextView advisorType = findViewById(R.id.advisorProfile_advisorType);
        final ImageView advisorImage = findViewById(R.id.advisorProfile_advisorImage);
        final TextView advisorDescription = findViewById(R.id.advisorProfile_advisorDescription);
        final TextView advisorSkills = findViewById(R.id.advisorProfile_advisorSkills);
        final ImageButton backButton = findViewById(R.id.advisorProfile_backButton);

        // back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // receive advisor data
        Advisor advisor = (Advisor) getIntent().getSerializableExtra("advisor");

        // set text
        advisorName.setText(advisor.advisorName);
        advisorType.setText(advisor.advisorType);
        advisorImage.setImageResource(advisor.advisorImage);
        advisorDescription.setText(advisor.advisorDescription);
        advisorSkills.setText(advisor.advisorSkills);

    }

}
