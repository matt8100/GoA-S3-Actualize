package com.example.tbdapp.models;

import java.util.ArrayList;

public class Singleton {
    public ArrayList<Advisor> advisors;
    public User user;

    private static Singleton instance;

    private Singleton(){
        advisors = new ArrayList<Advisor>();
        user = new User();
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

//    public ArrayList<Advisor> getAdvisors() {
//        return advisors;
//    }

}
