package com.example.tbdapp.views;

import java.io.Serializable;

public class ContactItem implements Serializable {
    private int mImageResource;
    private String mText;
    private String firstLetter;

    public ContactItem(int imageResource, String text, String letter) {
        mImageResource = imageResource;
        mText = text;
        firstLetter = letter;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText() {
        return mText;
    }
    public String getFirstLetter() {
        return firstLetter;
    }
}
