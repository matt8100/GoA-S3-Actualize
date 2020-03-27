package com.example.tbdapp.activities;

public class ContactItem {
    private int mImageResource;
    private String mText;

    public ContactItem(int imageResource, String text) {
        mImageResource = imageResource;
        mText = text;

    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText() {
        return mText;
    }
}
