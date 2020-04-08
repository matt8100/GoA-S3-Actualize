package com.example.tbdapp.activities;

public class UserInformation {
    public String name ;
    public String dateOfBirth;
    public String email ;
    public String provinceText ;
    public String citizenshipText ;
    public String employmentStatusText;
    public String expectedIncomeText ;
    public String housingStatusText ;
    public String healthConditionText ;
    public String lookingForText ;

    public UserInformation(){
    }

    public UserInformation(String name,String dateOfBirth, String email,String province, String citizenship, String employmentStatus, String expectedIncome, String housingStatus, String healthCondition, String lookingFor
    ){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.employmentStatusText = employmentStatus;
        this.provinceText = province;
        this.citizenshipText = citizenship;
        this.healthConditionText = healthCondition;
        this.housingStatusText = housingStatus;
        this.lookingForText = lookingFor;
        this.expectedIncomeText = expectedIncome;
    }

    public String getName(){

        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getCitizenship() {
        return citizenshipText;
    }

    public String getEmploymentStatus() {
        return employmentStatusText;
    }

    public String getExpectedIncome() {
        return expectedIncomeText;
    }

    public String getHealthCondition() {
        return healthConditionText;
    }

    public String getHousingStatus() {
        return housingStatusText;
    }

    public String getLookingFor() {
        return lookingForText;
    }

    public String getProvince() {
        return provinceText;
    }
}
