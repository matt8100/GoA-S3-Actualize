package com.example.tbdapp.models;

import java.io.Serializable;

public class Advisor extends Author implements Serializable {
    public String advisorType;
    public String advisorDescription;
    public String advisorSkills;

    public Advisor(String id, String name, String advisorType, String advisorDescription, String advisorSkills, String avatar) {
        super(id, name, avatar);
        this.advisorType = advisorType;
        this.advisorDescription = advisorDescription;
        this.advisorSkills = advisorSkills;
    }
}
