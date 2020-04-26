package com.example.tbdapp.models;

import android.net.Uri;

public class User extends Author{
    public String preferredName;
    public String dateOfBirth;
    public String email;
    public String provinceText;
    public String citizenshipText;
    public String employmentStatusText;
    public String expectedIncomeText;
    public String housingStatusText;
    public String healthConditionText;
    public String lookingForText;

    public User(String name, String avatar, String preferredName, String dateOfBirth, String email, String healthConditionText, String province, String citizenship, String employmentStatus, String expectedIncome, String housingStatus, String lookingFor) {
        super("0", name, avatar);
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
}
