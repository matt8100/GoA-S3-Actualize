package com.example.tbdapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.views.adapters.ExploreAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;

public class ExploreFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View searchView;

    private ArrayList<Advisor> advisorList;
    private ArrayList<Advisor> careerAdvisorList = new ArrayList<>();
    private ArrayList<Advisor> financialAdvisorList = new ArrayList<>();


    private ArrayList<String> advisorNames = new ArrayList<>();
    private ArrayList<Advisor> sortedAdvisors =  new ArrayList<>();

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

        for(Advisor advisor : advisorList) {
            switch (advisor.advisorType) {
                case CAREER:
                    careerAdvisorList.add(advisor);
                    break;
                case FINANCIAL:
                    financialAdvisorList.add(advisor);
                    break;
            }

            advisorNames.add(advisor.name);
        }

        Collections.sort(advisorNames);
        for(String advisorName : advisorNames) {
            for(Advisor advisor : advisorList) {
                if(advisor.name.equals(advisorName)) {
                    sortedAdvisors.add(advisor);
                }
            }
        }
        
        recyclerView = rootView.findViewById(R.id.exploreRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ExploreAdapter(getContext(), sortedAdvisors);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        setupTabLayout(rootView);
        setupSearchBar(rootView);

        return rootView;
    }

    private void setupSearchBar(View rootView) {
        searchView = rootView.findViewById(R.id.searchView);
        final EditText et_search = searchView.findViewById(R.id.edit_text_search);
        final ImageButton ib_clear = searchView.findViewById(R.id.ib_clear_text);

        ib_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });

        TextWatcher searchViewTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0) {
                    ib_clear.setVisibility(View.GONE);
                } else {
                    ib_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        et_search.addTextChangedListener(searchViewTextWatcher);
    }

    private void setupTabLayout(View rootView) {
        TabLayout tabLayout = rootView.findViewById(R.id.filterTabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                sortedAdvisors.clear();
                for(String advisorName : advisorNames) {
                    switch (position) {
                        case 0:
                            for(Advisor advisor : advisorList) {
                                if(advisor.name.equals(advisorName)) {
                                    sortedAdvisors.add(advisor);
                                }
                            }
                            break;
                        case 1:
                            for(Advisor advisor : careerAdvisorList) {
                                if(advisor.name.equals(advisorName)) {
                                    sortedAdvisors.add(advisor);
                                }
                            }
                            break;
                        case 2:
                            for(Advisor advisor : financialAdvisorList) {
                                if(advisor.name.equals(advisorName)) {
                                    sortedAdvisors.add(advisor);
                                }
                            }
                            break;
                    }
                }
                ExploreAdapter adapter = new ExploreAdapter(getContext(), sortedAdvisors);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
