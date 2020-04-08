package com.example.tbdapp.activities;

import android.app.Application;

public class User extends Application {
    private static UserInformation myUser = new UserInformation();


    public static UserInformation getUser(){
        return myUser;
    }


    public static void setUser(UserInformation user){
        myUser = user;
    }

}
