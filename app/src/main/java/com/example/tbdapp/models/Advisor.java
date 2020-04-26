package com.example.tbdapp.models;

import android.net.Uri;

import java.io.Serializable;

public class Advisor extends Author implements Serializable {
    public AdvisorType advisorType;
    public String advisorDescription;
    public String advisorSkills;

    public Advisor(String id, String name, AdvisorType advisorType, String advisorDescription, String advisorSkills, String avatar) {
        super(id, name, avatar);
        this.advisorType = advisorType;
        this.advisorDescription = advisorDescription;
        this.advisorSkills = advisorSkills;
    }
}
