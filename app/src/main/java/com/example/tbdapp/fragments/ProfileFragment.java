package com.example.tbdapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Singleton;
import com.example.tbdapp.models.User;


import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private EditText editTextName;
    private EditText editTextDateOfBirth;
    private EditText editTextPreferredName;
    private EditText editTextEmail;
    private Spinner province,citizenship,employmentStatus,expectedIncome,housingStatus,lookingFor;
    private ImageView profileImage;
    private CheckBox healthCondition1, healthCondition2, healthCondition3;
    private static final int SELECT_IMAGE = 1;

    View v;

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

        v = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        profileDisplayFragment = ProfileDisplayFragment.newInstance();

        profileImage = v.findViewById(R.id.profile_imageView);
        editTextName = v.findViewById(R.id.editText_name);
        editTextName.setFocusable(false);
        editTextPreferredName = v.findViewById(R.id.editText_preferredName);
        editTextDateOfBirth = v.findViewById(R.id.editText_dateOfBirth);
        editTextDateOfBirth.setFocusable(false);
        editTextEmail = v.findViewById(R.id.editText_email);
        editTextEmail.setFocusable(false);
        province = v.findViewById(R.id.spinner_province);
        province.setEnabled(false);
        citizenship = v.findViewById(R.id.spinner_citizenship);
        citizenship.setEnabled(false);
        employmentStatus = v.findViewById(R.id.spinner_employmentStatus);
        expectedIncome = v.findViewById(R.id.spinner_expectedIncome);
        housingStatus = v.findViewById(R.id.spinner_housingStatus);
        healthCondition1 = v.findViewById(R.id.healthCondition_1);
        healthCondition2 = v.findViewById(R.id.healthCondition_2);
        healthCondition3 = v.findViewById(R.id.healthCondition_3);
        lookingFor = v.findViewById(R.id.spinner_lookingFor);

        User user = Singleton.getInstance().user;

        profileImage.setImageResource(getResources().getIdentifier(user.avatar, "drawable", getActivity().getPackageName()));
        editTextName.setText(user.name);
        editTextPreferredName.setText((user.preferredName));
        editTextDateOfBirth.setText(user.dateOfBirth);
        editTextEmail.setText(user.email);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, SELECT_IMAGE);
            }
        });


        setupSpinner(R.id.spinner_province, R.array.labels_province, user.provinceText, false);
        setupSpinner(R.id.spinner_citizenship, R.array.labels_citizenship, user.citizenshipText, false);
        setupSpinner(R.id.spinner_employmentStatus, R.array.labels_employmentStatus, user.employmentStatusText, true);
        setupSpinner(R.id.spinner_expectedIncome, R.array.labels_expectedIncome, user.expectedIncomeText, true);
        setupSpinner(R.id.spinner_housingStatus, R.array.labels_housingStatus, user.housingStatusText, true);
        setupSpinner(R.id.spinner_lookingFor, R.array.labels_lookingFor, user.lookingForText, true);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.done_menu, menu);
    }

    private void setupSpinner(int id, int array, String selectedText, boolean enabled) {
        Spinner spinner = v.findViewById(id);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setEnabled(enabled);
        spinner.setSelection(arrayAdapter.getPosition(selectedText));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.done){
            User user = Singleton.getInstance().user;

            user.name = editTextName.getText().toString();
            user.preferredName = editTextPreferredName.getText().toString();
            user.dateOfBirth= editTextDateOfBirth.getText().toString();
            user.email = editTextEmail.getText().toString();
            user.provinceText = province.getSelectedItem().toString();
            user.citizenshipText = citizenship.getSelectedItem().toString();
            user.employmentStatusText = employmentStatus.getSelectedItem().toString();
            user.expectedIncomeText = expectedIncome.getSelectedItem().toString();
            user.housingStatusText = housingStatus.getSelectedItem().toString();

            if (healthCondition2.isChecked() && healthCondition3.isChecked()&& healthCondition1.isChecked()) {
                user.healthConditionText = "Living with a mental or physical disability, Living with an addiction, In need of medical equipment or other assistance";
            }else if (healthCondition1.isChecked() && healthCondition2.isChecked()){
                user.healthConditionText = "Living with a mental or physical disability, Living with an addiction";
            }else if (healthCondition1.isChecked() && healthCondition3.isChecked()){
                user.healthConditionText = "Living with a mental or physical disability, In need of medical equipment or other assistance";
            }else if (healthCondition2.isChecked() && healthCondition3.isChecked()) {
                user.healthConditionText = "Living with an addiction, In need of medical equipment or other assistance";
            }else if (healthCondition1.isChecked()){
                user.healthConditionText = "Living with a mental or physical disability";
            }else if (healthCondition2.isChecked()){
                user.healthConditionText = "Living with an addiction";
            }else if (healthCondition3.isChecked()) {
                user.healthConditionText = "In need of medical equipment or other assistance";
            }else{
                user.healthConditionText = "None";
            }

            user.lookingForText = lookingFor.getSelectedItem().toString();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout,profileDisplayFragment);
            fragmentTransaction.commit();

            return true;

        }

        return super.onOptionsItemSelected(item);
    }



}

