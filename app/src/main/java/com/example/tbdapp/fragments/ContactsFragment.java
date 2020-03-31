package com.example.tbdapp.fragments;

import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.activities.MainActivity;
import com.example.tbdapp.views.ContactItem;
import com.example.tbdapp.views.adapters.ContactAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ContactsFragment extends Fragment {
    ArrayList<MainActivity.Advisor> advisorList;

    public ContactsFragment(ArrayList<MainActivity.Advisor> advisors) {
        advisorList = advisors;
    }

    final FragmentActivity thisActivity = getActivity();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Advisor lists are temp storage of type Advisor object
        ArrayList<MainActivity.Advisor> starredAdvisorList = new ArrayList<>();
        ArrayList<MainActivity.Advisor> normalAdvisorList = new ArrayList<>();
        ArrayList<ContactItem> starredContactsList = new ArrayList<>();
        ArrayList<ContactItem> normalContactsList = new ArrayList<>();

        //Put all advisor names in an array, sort the array
        ArrayList<String> advisorNames = new ArrayList<>();
        for(int i=0;i<advisorList.size();i++) {
            advisorNames.add(advisorList.get(i).getAdvisorName());
        }
        Collections.sort(advisorNames);

        //For each name, find the corresponding object through its index, then sort favourites out
        for(int i=0;i<advisorNames.size();i++) {
            int indexOfAdvisor = 0;
            for (int j=0; j<advisorList.size();j++) {
                if (advisorList.get(j).getAdvisorName() == advisorNames.get(i)) {
                    //therefore: advisorName[i] corresponds to normalAdvisorList[j]
                    indexOfAdvisor = j;
                    break;
                }
            }
            if(advisorList.get(indexOfAdvisor).isFavourite()) {
                starredAdvisorList.add(advisorList.get(indexOfAdvisor));
            }else {
                System.out.println(advisorList.get(indexOfAdvisor).getAdvisorName());
                normalAdvisorList.add(advisorList.get(indexOfAdvisor));
            }
        }

        //For all favourited advisors
        for(int i=0;i<starredAdvisorList.size();i++) {
            int image = starredAdvisorList.get(i).getAdvisorImage();
            String name = starredAdvisorList.get(i).getAdvisorName();
            String star = "★";
            if(i > 0) {
                star = "";
            }
            starredContactsList.add(new ContactItem(image, name, star));
        }

        //For all normal advisors
        ArrayList<String> usedLetters = new ArrayList<>();
        for(int i=0;i<normalAdvisorList.size();i++) {
            int image1 = normalAdvisorList.get(i).getAdvisorImage();
            String name1 = normalAdvisorList.get(i).getAdvisorName();
            String letter1 = "";
            if(usedLetters.indexOf(Character.toString(normalAdvisorList.get(i).getAdvisorName().charAt(0)).toUpperCase()) == -1) {
                letter1 = Character.toString(normalAdvisorList.get(i).getAdvisorName().charAt(0)).toUpperCase();
                usedLetters.add(letter1);
            }
            normalContactsList.add(new ContactItem(image1, name1, letter1));
        }

        //Put all the contact items in the same array list
        ArrayList<ContactItem> allContactItems = new ArrayList<>();
        for(int i=0;i<starredContactsList.size();i++) {
            allContactItems.add(starredContactsList.get(i));
        }
        for(int i=0;i<normalContactsList.size();i++) {
            allContactItems.add(normalContactsList.get(i));
        }


        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        mRecyclerView = root.findViewById(R.id.contactsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(thisActivity);

        //Put the array contents into the recyclerview via the adapter
        mAdapter = new ContactAdapter(allContactItems);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}
