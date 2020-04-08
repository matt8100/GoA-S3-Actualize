package com.example.tbdapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tbdapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private EditText editTextName;
    private EditText editTextDateOfBirth;
    private EditText editTextEmail;
    private Spinner province,citizenship,employmentStatus,expectedIncome,housingStatus,healthCondition,lookingFor;



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel_province = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    private ProfileDisplayFragment profileDisplayFragment;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        profileDisplayFragment = ProfileDisplayFragment.newInstance();

        editTextName = v.findViewById(R.id.editText_name);
        editTextDateOfBirth = v.findViewById(R.id.editText_dateOfBirth);
        editTextEmail = v.findViewById(R.id.editText_email);
        province = v.findViewById(R.id.spinner_province);
        citizenship = v.findViewById(R.id.spinner_citizenship);
        employmentStatus = v.findViewById(R.id.spinner_employmentStatus);
        expectedIncome = v.findViewById(R.id.spinner_expectedIncome);
        housingStatus = v.findViewById(R.id.spinner_housingStatus);
        healthCondition = v.findViewById(R.id.spinner_healthCondition);
        lookingFor = v.findViewById(R.id.spinner_lookingFor);


        //Spinner for province

        if (province != null) {
            province.setOnItemSelectedListener(this);
        }
        final ArrayAdapter<CharSequence> adapter_province = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_province, android.R.layout.simple_spinner_item);
        adapter_province.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (province != null) {
            province.setAdapter(adapter_province);
        }

        //Spinner code for citizenship

        if (citizenship != null) {
            citizenship.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_citizenship = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_citizenship, android.R.layout.simple_spinner_item);
        adapter_citizenship.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (citizenship != null) {
            citizenship.setAdapter(adapter_citizenship);
        }

        //Spinner code for employment status

        if (employmentStatus != null) {
            employmentStatus.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_employmentStatus = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_employmentStatus, android.R.layout.simple_spinner_item);
        adapter_employmentStatus.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (employmentStatus != null) {
            employmentStatus.setAdapter(adapter_employmentStatus);
        }

        //Spinner code for expected income

        if (expectedIncome != null) {
            expectedIncome.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_expectedIncome = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_expectedIncome, android.R.layout.simple_spinner_item);
        adapter_expectedIncome.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (expectedIncome != null) {
            expectedIncome.setAdapter(adapter_expectedIncome);
        }

        //Spinner code for housing status

        if (housingStatus != null) {
            housingStatus.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_housingStatus = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_housingStatus, android.R.layout.simple_spinner_item);
        adapter_housingStatus.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (housingStatus != null) {
            housingStatus.setAdapter(adapter_housingStatus);
        }

        //Spinner code for health condition

        if (healthCondition != null) {
            healthCondition.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_healthCondition = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_healthCondition, android.R.layout.simple_spinner_item);
        adapter_healthCondition.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (healthCondition != null) {
            healthCondition.setAdapter(adapter_healthCondition);
        }

        //Spinner code for looking for

        if (lookingFor != null) {
            lookingFor.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter_lookingFor = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_lookingFor, android.R.layout.simple_spinner_item);
        adapter_lookingFor.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (lookingFor != null) {
            lookingFor.setAdapter(adapter_lookingFor);
        }

        FloatingActionButton doneButton = v.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                String name = editTextName.getText().toString();
                String dateOfBirth= editTextDateOfBirth.getText().toString();
                String email = editTextEmail.getText().toString();
                String provinceText = province.getSelectedItem().toString();
                String citizenshipText = citizenship.getSelectedItem().toString();
                String employmentStatusText = employmentStatus.getSelectedItem().toString();
                String expectedIncomeText = expectedIncome.getSelectedItem().toString();
                String housingStatusText = housingStatus.getSelectedItem().toString();
                String healthConditionText = healthCondition.getSelectedItem().toString();
                String lookingForText = lookingFor.getSelectedItem().toString();
                ProfileDisplayFragment profileDisplayFragment = new ProfileDisplayFragment();
                Bundle editPage = new Bundle();
                editPage.putString("name", name);
                editPage.putString("dateOfBirth",dateOfBirth);
                editPage.putString("email", email);
                editPage.putString("province",provinceText);
                editPage.putString("citizenship",citizenshipText);
                editPage.putString("employmentStatus",employmentStatusText);
                editPage.putString("expectedIncome",expectedIncomeText);
                editPage.putString("housingStatus",housingStatusText);
                editPage.putString("healthCondition",healthConditionText);
                editPage.putString("lookingFor",lookingForText);
                profileDisplayFragment.setArguments(editPage);
                fragmentTransaction.replace(R.id.fragment_container,profileDisplayFragment);
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }


}

