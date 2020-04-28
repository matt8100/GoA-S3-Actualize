package com.example.tbdapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.tbdapp.R;
import com.example.tbdapp.activities.ChatActivity;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.models.Author;
import com.example.tbdapp.models.Dialog;
import com.example.tbdapp.models.Singleton;
import com.example.tbdapp.models.User;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    ArrayList<Advisor> advisorList;
    Context context;

    public ContactsFragment(ArrayList<Advisor> advisorList, Context context) {
        this.advisorList = advisorList;
        this.context = context;

    }

    private DialogsList mDialogsListView;
    private DialogsListAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        mDialogsListView = root.findViewById(R.id.dialogsList);

        mAdapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                int image = context.getResources().getIdentifier(url, "drawable", context.getPackageName());
                imageView.setImageResource(image);
            }
        });
        mDialogsListView.setAdapter(mAdapter);

        loadDialogs();

        mAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<Dialog>() {
            @Override
            public void onDialogClick(Dialog dialog) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("contactId", dialog.getId());

                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDialogs();
    }

    private void loadDialogs() {
        User user = Singleton.getInstance().user;

        for(int i=0;i<advisorList.size();i++){
            ArrayList<Author> users = new ArrayList<>();
            Advisor advisor = advisorList.get(i);
            users.add(user);
            users.add(advisor);

            Dialog dialog = new Dialog(advisor.id, advisor.avatar, advisor.name, users, Singleton.getLastMessage(Singleton.getInstance().chatHistory.get(advisor.id)), 0);
            mAdapter.upsertItem(dialog);
        }
    }
}
