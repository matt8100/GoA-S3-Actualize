package com.example.tbdapp.models;

import android.net.Uri;

import com.stfalcon.chatkit.commons.models.IUser;

import java.io.Serializable;

public class Author implements IUser, Serializable {
    public String id;
    public String name;
    public String avatar;

    public Author(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getAvatar() {
        return avatar.toString();
    }
}
