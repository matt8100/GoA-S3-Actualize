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
    public Uri photo;

    private static User myUser = new User("Jane Foster", "profile_picture", "Jane", "1996-09-04", "janefoster@tbd.com", "Ontario", "Permanent Resident", "Unemployed", "$10,000", "Tenant", "None", "A financial advisor");

    public static User getUser(){
        return myUser;
    }
    public User( String name, String avatar, String preferredName, String dateOfBirth, String email, String healthConditionText, String province, String citizenship, String employmentStatus, String expectedIncome, String housingStatus, String lookingFor) {
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
