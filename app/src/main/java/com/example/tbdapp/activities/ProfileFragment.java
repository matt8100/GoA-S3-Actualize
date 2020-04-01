package com.example.tbdapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tbdapp.R;

public class ProfileFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        //Spinner for province
        Spinner province = v.findViewById(R.id.spinner_province);
        if (province != null) {
            province.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter_province = ArrayAdapter.createFromResource(getContext(),
                R.array.labels_province, android.R.layout.simple_spinner_item);
        adapter_province.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (province != null) {
            province.setAdapter(adapter_province);
        }

        //Spinner code for citizenship
        Spinner citizenship = v.findViewById(R.id.spinner_citizenship);
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
        Spinner employmentStatus = v.findViewById(R.id.spinner_employmentStatus);
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
        Spinner expectedIncome = v.findViewById(R.id.spinner_expectedIncome);
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
        Spinner housingStatus = v.findViewById(R.id.spinner_housingStatus);
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
        Spinner healthCondition = v.findViewById(R.id.spinner_healthCondition);
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
        Spinner lookingFor = v.findViewById(R.id.spinner_lookingFor);
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

        // Inflate the layout for this fragment
        return v;
    }


}

