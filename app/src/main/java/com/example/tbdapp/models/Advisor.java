package com.example.tbdapp.models;

import java.io.Serializable;

public class Advisor implements Serializable {
    public String advisorName;
    public String advisorType;
    public String advisorDescription;
    public String advisorSkills;
    public int advisorImage;
    public boolean isFavourite;

    public Advisor(String advisorName, String advisorType, String advisorDescription, String advisorSkills, int advisorImage, boolean isFavourite) {
        this.advisorName = advisorName;
        this.advisorType = advisorType;
        this.advisorDescription = advisorDescription;
        this.advisorSkills = advisorSkills;
        this.advisorImage = advisorImage;
        this.isFavourite = isFavourite;
    }
}
