package com.example.tbdapp.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbdapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ProfileDisplayFragment extends ProfileFragment {

    private TextView NameTextView, preferredNameTextView, dateOfBirthTextView, emailTextView, provinceTextView, citizenshipTextView, employmentStatusTextView,
            expectedIncomeTextView,housingStatusTextView,lookingForTextView,healthConditionTextView;
    private ImageView profileImageView;


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
        setHasOptionsMenu(true);
        UserInformation user = User.getUser();

        profileImageView = v.findViewById(R.id.profile_imageView);
        profileImageView.setImageURI(user.photo);

        NameTextView = v.findViewById(R.id.label_name);
        NameTextView.setText(user.name);

        preferredNameTextView = v.findViewById(R.id.label_preferredName);
        preferredNameTextView.setText(user.preferredName);


        dateOfBirthTextView = v.findViewById(R.id.label_dateOfBirth);
        dateOfBirthTextView.setText(user.dateOfBirth);


        emailTextView = v.findViewById(R.id.label_email);
        emailTextView.setText(user.email);


        provinceTextView = v.findViewById(R.id.label_province);
        provinceTextView.setText(user.provinceText);


        citizenshipTextView = v.findViewById(R.id.label_citizenship);
        citizenshipTextView.setText(user.citizenshipText);


        employmentStatusTextView = v.findViewById(R.id.label_employmentStatus);
        employmentStatusTextView.setText(user.employmentStatusText);


        housingStatusTextView = v.findViewById(R.id.label_housingStatus);
        housingStatusTextView.setText(user.housingStatusText);


        expectedIncomeTextView = v.findViewById(R.id.label_expectedIncome);
        expectedIncomeTextView.setText(user.expectedIncomeText);


        healthConditionTextView = v.findViewById(R.id.label_healthCondition);
        healthConditionTextView.setText(user.healthConditionText);

        lookingForTextView = v.findViewById(R.id.label_lookingFor);
        lookingForTextView.setText(user.lookingForText);

        return v;


        }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.edit_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.edit){
            UserInformation myUser = User.getUser();

            myUser.name = NameTextView.getText().toString();
            myUser.preferredName = preferredNameTextView.getText().toString();
            myUser.dateOfBirth= dateOfBirthTextView.getText().toString();
            myUser.email = emailTextView.getText().toString();
            myUser.provinceText = provinceTextView.getText().toString();
            myUser.citizenshipText = citizenshipTextView.getText().toString();
            myUser.employmentStatusText = employmentStatusTextView.getText().toString();
            myUser.expectedIncomeText = expectedIncomeTextView.getText().toString();
            myUser.housingStatusText = housingStatusTextView.getText().toString();
            myUser.healthConditionText = healthConditionTextView.getText().toString();
            myUser.lookingForText = lookingForTextView.getText().toString();
            myUser.photo = imageUri;

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragmentProfile);
            fragmentTransaction.commit();
            return true;
        }

        return onOptionsItemSelected(item);
    }

}