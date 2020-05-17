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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Singleton;
import com.example.tbdapp.models.User;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{

    private EditText editTextName;
    private EditText editTextDateOfBirth;
    private EditText editTextPreferredName;
    private EditText editTextEmail;
    private Spinner province,citizenship,employmentStatus,expectedIncome,housingStatus,lookingFor;
    private ImageView profileImage;
    private CheckBox healthCondition1, healthCondition2, healthCondition3;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private static final int SELECT_IMAGE = 1;

    private ProfileDisplayFragment profileDisplayFragment;

    private View view;
    private ImageButton doneButton;

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    static ProfileFragment newInstance() {
        return new ProfileFragment();
    }



    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        profileDisplayFragment = ProfileDisplayFragment.newInstance();
        doneButton = view.findViewById(R.id.doneButton);
        profileImage = view.findViewById(R.id.profile_imageView);
        editTextName = view.findViewById(R.id.editText_name);
        editTextName.setFocusable(false);
        editTextPreferredName = view.findViewById(R.id.editText_preferredName);
        editTextDateOfBirth = view.findViewById(R.id.editText_dateOfBirth);
        editTextDateOfBirth.setFocusable(false);
        editTextEmail = view.findViewById(R.id.editText_email);
        editTextEmail.setFocusable(false);
        province = view.findViewById(R.id.spinner_province);
        province.setEnabled(false);
        citizenship = view.findViewById(R.id.spinner_citizenship);
        citizenship.setEnabled(false);
        employmentStatus = view.findViewById(R.id.spinner_employmentStatus);
        expectedIncome = view.findViewById(R.id.spinner_expectedIncome);
        housingStatus = view.findViewById(R.id.spinner_housingStatus);
        healthCondition1 = view.findViewById(R.id.healthCondition_1);
        healthCondition2 = view.findViewById(R.id.healthCondition_2);
        healthCondition3 = view.findViewById(R.id.healthCondition_3);
        lookingFor = view.findViewById(R.id.spinner_lookingFor);

        checkBoxes.add(healthCondition1);
        checkBoxes.add(healthCondition2);
        checkBoxes.add(healthCondition3);

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


        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveUserData();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,profileDisplayFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.done_menu, menu);
    }

    private void setupSpinner(int id, int array, String selectedText, boolean enabled) {
        Spinner spinner = view.findViewById(id);
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
            saveUserData();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout,profileDisplayFragment);
            fragmentTransaction.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveUserData() {
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
        user.lookingForText = lookingFor.getSelectedItem().toString();

        user.healthConditions.clear();

        for(CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                user.healthConditions.add(checkBox.getText().toString());
            }
        }
    }
    
}

