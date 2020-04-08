package com.example.tbdapp.activities;

public class UserInformation {
    public String name = "Cathy";
    public String dateOfBirth = "2002-06-27";
    public String email = "Cathy@gmail.com";
    public String province = "British Columbia";
    public String citizenship = "Citizen";
    public String employmentStatus = "apple";
    public String expectedIncome = "20";
    public String housingStatus = "cathy";
    public String healthCondition = "Cathy";
    public String lookingFor = "Cathy";

    public UserInformation(){
    }

    public UserInformation(String name,String dateOfBirth, String email,String province, String citizenship, String employmentStatus, String expectedIncome, String housingStatus, String healthCondition, String lookingFor
    ){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.province = province;
        this.citizenship = citizenship;
        this.healthCondition = healthCondition;
        this.housingStatus = housingStatus;
        this.lookingFor = lookingFor;
        this.expectedIncome = expectedIncome;
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
        return citizenship;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getExpectedIncome() {
        return expectedIncome;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public String getHousingStatus() {
        return housingStatus;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public String getProvince() {
        return province;
    }
}
