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
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.views.ContactItem;
import com.example.tbdapp.views.adapters.ContactAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ContactsFragment extends Fragment {
    ArrayList<Advisor> advisorList;

    public ContactsFragment(ArrayList<Advisor> advisors) {
        advisorList = advisors;
    }

    final FragmentActivity thisActivity = getActivity();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Advisor lists are temp storage of type Advisor object
        ArrayList<Advisor> starredAdvisorList = new ArrayList<>();
        ArrayList<Advisor> normalAdvisorList = new ArrayList<>();
        ArrayList<ContactItem> starredContactsList = new ArrayList<>();
        ArrayList<ContactItem> normalContactsList = new ArrayList<>();

        //Put all advisor names in an array, sort the array
        ArrayList<String> advisorNames = new ArrayList<>();
        for(int i=0;i<advisorList.size();i++) {
            advisorNames.add(advisorList.get(i).advisorName);
        }
        Collections.sort(advisorNames);

        //For each name, find the corresponding object through its index, then sort favourites out
        for(int i=0;i<advisorNames.size();i++) {
            int indexOfAdvisor = 0;
            for (int j=0; j<advisorList.size();j++) {
                if (advisorList.get(j).advisorName == advisorNames.get(i)) {
                    //therefore: advisorName[i] corresponds to normalAdvisorList[j]
                    indexOfAdvisor = j;
                    break;
                }
            }
            if(advisorList.get(indexOfAdvisor).isFavourite) {
                starredAdvisorList.add(advisorList.get(indexOfAdvisor));
            }else {
                normalAdvisorList.add(advisorList.get(indexOfAdvisor));
            }
        }

        //For all favourited advisors
        for(int i=0;i<starredAdvisorList.size();i++) {
            int image = starredAdvisorList.get(i).advisorImage;
            String name = starredAdvisorList.get(i).advisorName;
            String star = "★";
            if(i > 0) {
                star = "";
            }
            starredContactsList.add(new ContactItem(image, name, star));
        }

        //For all normal advisors
        ArrayList<String> usedLetters = new ArrayList<>();
        for(int i=0;i<normalAdvisorList.size();i++) {
            int image1 = normalAdvisorList.get(i).advisorImage;
            String name1 = normalAdvisorList.get(i).advisorName;
            String letter1 = "";
            if(usedLetters.indexOf(Character.toString(normalAdvisorList.get(i).advisorName.charAt(0)).toUpperCase()) == -1) {
                letter1 = Character.toString(normalAdvisorList.get(i).advisorName.charAt(0)).toUpperCase();
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
