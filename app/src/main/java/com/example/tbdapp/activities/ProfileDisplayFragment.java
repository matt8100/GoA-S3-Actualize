package com.example.tbdapp.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tbdapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ProfileDisplayFragment extends ProfileFragment {

    private TextView NameTextView, dateOfBirthTextView, emailTextView, provinceTextView, citizenshipTextView, employmentStatusTextView,
            expectedIncomeTextView,housingStatusTextView,lookingForTextView,healthConditionTextView;


    public ProfileDisplayFragment() {
        // Required empty public constructor
    }

    static ProfileDisplayFragment newInstance() {

        return new ProfileDisplayFragment();
    }

    private ProfileFragment fragmentProfile;



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_display, container, false);
        fragmentProfile = ProfileFragment.newInstance();
        Bundle displayPage = getArguments();
        if(displayPage != null){
            String name = displayPage.getString("name");
            String dateOfBirth = displayPage.getString("dateOfBirth");
            String email = displayPage.getString("email");
            String province = displayPage.getString("province");
            String citizenship = displayPage.getString("citizenship");
            String employmentStatus = displayPage.getString("employmentStatus");
            String expectedIncome = displayPage.getString("expectedIncome");
            String housingStatus = displayPage.getString("housingStatus");
            String lookingFor = displayPage.getString("lookingFor");
            String healthCondition = displayPage.getString("healthCondition");
            NameTextView = v.findViewById(R.id.label_name);
            NameTextView.setText(name);
            dateOfBirthTextView = v.findViewById(R.id.label_dateOfBirth);
            dateOfBirthTextView.setText(dateOfBirth);
            emailTextView = v.findViewById(R.id.label_email);
            emailTextView.setText(email);
            provinceTextView = v.findViewById(R.id.label_province);
            provinceTextView.setText(province);
            citizenshipTextView = v.findViewById(R.id.label_citizenship);
            citizenshipTextView.setText(citizenship);
            employmentStatusTextView = v.findViewById(R.id.label_employmentStatus);
            employmentStatusTextView.setText(employmentStatus);
            housingStatusTextView = v.findViewById(R.id.label_housingStatus);
            housingStatusTextView.setText(housingStatus);
            expectedIncomeTextView = v.findViewById(R.id.label_expectedIncome);
            expectedIncomeTextView.setText(expectedIncome);
            healthConditionTextView = v.findViewById(R.id.label_healthCondition);
            healthConditionTextView.setText(healthCondition);
            lookingForTextView = v.findViewById(R.id.label_lookingFor);
            lookingForTextView.setText(lookingFor);
        }


        FloatingActionButton editButton = v.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentProfile);
                fragmentTransaction.commit();
            }
        });
        return v;
        }



}