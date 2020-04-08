package com.example.tbdapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Author;
import com.example.tbdapp.models.Message;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private MessagesList messagesList;
    private Author receiver;
    private Author sender;
    private Date date;
    private MessagesListAdapter<Message> mAdapter;
    private ArrayList<Message> messageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        messagesList = findViewById(R.id.messagesList);
        receiver = new Author("0", "Matthew", null);
        sender = new Author("1", "Hod", null);
        date = new Date();
        mAdapter = new MessagesListAdapter<Message>(sender.getId(), null);
        messageArrayList = new ArrayList<>();

        messagesList.setAdapter(mAdapter);

        createMessages();

        loadMessages();

    }

    private void createMessages() {
        messageArrayList.add(new Message("0","Hi, how are you doing?", sender, date));
        messageArrayList.add(new Message("1","I'm doing great, how are you?", receiver, date));
        messageArrayList.add(new Message("0","I'm doing fine as well.", sender, date));
    }

    private void loadMessages() {
        for (int i=0;i<messageArrayList.size();i++) {
            mAdapter.addToStart(messageArrayList.get(i), false);
        }
    }
}

