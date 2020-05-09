package com.example.tbdapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

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
    private View searchView;

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

        mAdapter = new ExploreAdapter(getContext(), advisorList);
        recyclerView.setAdapter(mAdapter);

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
}
