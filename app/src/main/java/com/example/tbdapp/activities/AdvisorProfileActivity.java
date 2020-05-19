package com.example.tbdapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;

public class AdvisorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_profile);

        getSupportActionBar().hide();

        // declare variables
        final TextView advisorName = findViewById(R.id.advisorProfile_advisorName);
        final TextView advisorType = findViewById(R.id.advisorProfile_advisorType);
        final ImageView advisorImage = findViewById(R.id.advisorProfile_advisorImage);
        final TextView advisorDescription = findViewById(R.id.advisorProfile_advisorDescription);
        final TextView advisorSkills = findViewById(R.id.advisorProfile_advisorSkills);
        final ImageButton backButton = findViewById(R.id.advisorProfile_backButton);
        final Button requestButton = findViewById(R.id.requestButton);

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
        advisorName.setText(advisor.name);
        advisorType.setText(advisor.advisorType.toString());
        advisorImage.setImageResource(getResources().getIdentifier(advisor.avatar, "drawable", this.getPackageName()));
        advisorDescription.setText(advisor.advisorDescription);
        advisorSkills.setText(advisor.advisorSkills);

        requestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requestButton.setBackgroundResource(R.drawable.layout_request_sent_button_colour);
                requestButton.setText(R.string.requestSent);
            }
        });

    }

}
