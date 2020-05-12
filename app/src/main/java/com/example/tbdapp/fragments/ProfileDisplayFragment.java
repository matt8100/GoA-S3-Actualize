package com.example.tbdapp.fragments;

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
import com.example.tbdapp.models.Singleton;
import com.example.tbdapp.models.User;




public class ProfileDisplayFragment extends ProfileFragment {

    private TextView NameTextView, preferredNameTextView, dateOfBirthTextView, emailTextView, provinceTextView, citizenshipTextView, employmentStatusTextView,
            expectedIncomeTextView, housingStatusTextView, lookingForTextView, healthConditionTextView;
    private ImageView profileImageView;


    public ProfileDisplayFragment() {
        // Required empty public constructor
    }

    public static ProfileDisplayFragment newInstance() {

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
        User user = Singleton.getInstance().user;

        profileImageView = v.findViewById(R.id.profile_imageView);
        profileImageView.setImageResource(getResources().getIdentifier(user.avatar, "drawable", getActivity().getPackageName()));

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
        if (id == R.id.edit) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragmentProfile);
            fragmentTransaction.commit();
            return true;
        }

        return onOptionsItemSelected(item);
    }


}

