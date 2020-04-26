package com.example.tbdapp.models;

import android.net.Uri;

public class User {
    public Uri photo ;
    public String name = "Jane Foster";
    public String preferredName = "Jane";
    public String dateOfBirth = "1996-09-04";
    public String email = "Janefoster@tdb.com" ;
    public String provinceText = "Ontario";
    public String citizenshipText = "Permanent Resident";
    public String employmentStatusText = "Unemployed";
    public String expectedIncomeText = "$10,000";
    public String housingStatusText = "Tenant" ;
    public String healthConditionText = "None";
    public String lookingForText = "A financial adviser";

    public User(Uri photo, String name, String preferredName, String dateOfBirth, String email, String healthConditionText, String province, String citizenship, String employmentStatus, String expectedIncome, String housingStatus, String lookingFor) {
        this.photo = photo;
        this.name = name;
        this.preferredName = preferredName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.employmentStatusText = employmentStatus;
        this.provinceText = province;
        this.citizenshipText = citizenship;
        this.healthConditionText = healthConditionText;
        this.housingStatusText = housingStatus;
        this.lookingForText = lookingFor;
        this.expectedIncomeText = expectedIncome;
    }

    User() {
    }
}
