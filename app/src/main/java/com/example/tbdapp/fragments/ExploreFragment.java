package com.example.tbdapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.views.adapters.ExploreAdapter;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Advisor> advisorList;

    public ExploreFragment(ArrayList<Advisor> advisorList) {
        this.advisorList = advisorList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerView = rootView.findViewById(R.id.exploreRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ExploreAdapter(advisorList);
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }
}
