package com.example.tbdapp.activities;

import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;

import java.util.ArrayList;

public class FragmentContacts extends Fragment {

    final FragmentActivity thisActivity = getActivity();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<ContactItem> contactList = new ArrayList<>();
        contactList.add(new ContactItem(R.drawable.ic_face, "Contact 1!"));
        contactList.add(new ContactItem(R.drawable.ic_face, "Contact 2!"));

        //ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_contacts, container, false);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);


        mRecyclerView = root.findViewById(R.id.contactsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(thisActivity);
        mAdapter = new ContactAdapter(contactList);

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
