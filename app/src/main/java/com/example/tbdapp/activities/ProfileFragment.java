package com.example.tbdapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tbdapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private EditText editTextName;
    private EditText editTextDateOfBirth;
    private EditText editTextEmail;
    private Spinner province,citizenship,employmentStatus,expectedIncome,housingStatus,healthCondition,lookingFor;
    private ImageView profileImage;
    private static final int SELECT_IMAGE = 1;
    Uri imageUri;



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

        profileImage = v.findViewById(R.id.profile_imageView);
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


        UserInformation user = User.getUser();

        profileImage.setImageURI(user.photo);
        editTextName.setText(user.name);
        editTextDateOfBirth.setText(user.dateOfBirth);
        editTextEmail.setText(user.email);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, SELECT_IMAGE);
            }
        });

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

        int provincePosition ;
        String provinceString = user.provinceText;
        provincePosition = adapter_province.getPosition(provinceString);
        province.setSelection(provincePosition);


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

        int citizenshipPosition ;
        String citizenshipString = user.citizenshipText;
        citizenshipPosition = adapter_citizenship.getPosition(citizenshipString);
        citizenship.setSelection(citizenshipPosition);

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

        int employmentStatusPosition ;
        String employmentStatusString = user.employmentStatusText;
        employmentStatusPosition = adapter_employmentStatus.getPosition(employmentStatusString);
        employmentStatus.setSelection(employmentStatusPosition);

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

        int expectedIncomePosition ;
        String expectedIncomeString = user.expectedIncomeText;
        expectedIncomePosition = adapter_expectedIncome.getPosition(expectedIncomeString);
        expectedIncome.setSelection(expectedIncomePosition);

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

        int housingStatusPosition ;
        String housingStatusString = user.housingStatusText;
        housingStatusPosition = adapter_housingStatus.getPosition(housingStatusString);
        housingStatus.setSelection(housingStatusPosition);

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

        int healthConditionPosition ;
        String healthConditionString = user.healthConditionText;
        healthConditionPosition = adapter_healthCondition.getPosition(healthConditionString);
        healthCondition.setSelection(healthConditionPosition);

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

        int lookingForPosition ;
        String lookingForString = user.lookingForText;
        lookingForPosition = adapter_lookingFor.getPosition(lookingForString);
        lookingFor.setSelection(lookingForPosition);

        FloatingActionButton doneButton = v.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInformation myUser = User.getUser();

                myUser.name = editTextName.getText().toString();
                myUser.dateOfBirth= editTextDateOfBirth.getText().toString();
                myUser.email = editTextEmail.getText().toString();
                myUser.provinceText = province.getSelectedItem().toString();
                myUser.citizenshipText = citizenship.getSelectedItem().toString();
                myUser.employmentStatusText = employmentStatus.getSelectedItem().toString();
                myUser.expectedIncomeText = expectedIncome.getSelectedItem().toString();
                myUser.housingStatusText = housingStatus.getSelectedItem().toString();
                myUser.healthConditionText = healthCondition.getSelectedItem().toString();
                myUser.lookingForText = lookingFor.getSelectedItem().toString();
                myUser.photo = imageUri;

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,profileDisplayFragment);
                fragmentTransaction.commit();


                /*String name = editTextName.getText().toString();
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
                profileDisplayFragment.setArguments(editPage);*/

            }
        });

        //Bundle changeInformation = getArguments();
        /*if (changeInformation != null){
            String name = changeInformation.getString("name");
            String dateOfBirth = changeInformation.getString("dateOfBirth");
            String email = changeInformation.getString("email");
            String provinceInfo = changeInformation.getString("province");
            String citizenship = changeInformation.getString("citizenship");
            String employmentStatus = changeInformation.getString("employmentStatus");
            String expectedIncome = changeInformation.getString("expectedIncome");
            String housingStatus = changeInformation.getString("housingStatus");
            String lookingFor = changeInformation.getString("lookingFor");
            String healthCondition = changeInformation.getString("healthCondition");
            editTextName.setText(name);
            editTextDateOfBirth.setText(dateOfBirth);
            editTextEmail.setText(email);
        }*/


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == SELECT_IMAGE){
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }


}

