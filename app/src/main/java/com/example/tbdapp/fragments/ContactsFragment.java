package com.example.tbdapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.models.Author;
import com.example.tbdapp.models.Dialog;
import com.example.tbdapp.models.Singleton;
import com.example.tbdapp.models.User;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    ArrayList<Advisor> advisorList;

    public ContactsFragment(ArrayList<Advisor> advisors) {
        advisorList = advisors;
    }

    private DialogsList mDialogsListView;
    private DialogsListAdapter mAdapter;
    private ArrayList<Dialog> mDialogs;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        mDialogsListView = root.findViewById(R.id.dialogsList);
        mDialogs = new ArrayList<>();
        createDialogs();

        mAdapter = new DialogsListAdapter<>(null);

        mDialogsListView.setAdapter(mAdapter);
        mAdapter.setItems(mDialogs);


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private void createDialogs() {
        Advisor advisor = Singleton.getInstance().advisors.get(0);
        User user = Singleton.getInstance().user;
        ArrayList<Author> users = new ArrayList<>();
        users.add(advisor);
        users.add(user);
        mDialogs.add(new Dialog(advisor.id, advisor.avatar, advisor.name, users, Singleton.getInstance().chatHistory.get("0").get(Singleton.getInstance().chatHistory.get("0").size()-1), 0));
    }
}
