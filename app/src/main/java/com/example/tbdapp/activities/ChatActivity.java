package com.example.tbdapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Author;
import com.example.tbdapp.models.Message;
import com.example.tbdapp.models.Advisor;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private MessagesList mMessagesList;
    private MessageInput mMessageInput;
    private Author mReceiver;
    private Author mSender;
    private Date mDate;
    private MessagesListAdapter<Message> mAdapter;
    private ArrayList<Message> mMessageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Advisor contact = (Advisor) getIntent().getSerializableExtra("contact");
        mMessagesList = findViewById(R.id.messagesList);
        mMessageInput = findViewById(R.id.input);
        mReceiver = new Author("0", "Matthew", null);
        mSender = new Author("1", contact.advisorName, null);
        mDate = new Date();
        mAdapter = new MessagesListAdapter<Message>(mSender.getId(), null);
        mMessageArrayList = new ArrayList<>();

        setTitle(mSender.getName());
        mMessagesList.setAdapter(mAdapter);

        createMessages();
        loadMessages();

        mMessageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
                mAdapter.addToStart(new Message("0", input.toString(), mSender, mDate), true);
                return true;
            }
        });
    }

    private void createMessages() {
        mMessageArrayList.add(new Message("0","Hi, how are you doing?", mSender, mDate));
        mMessageArrayList.add(new Message("1","I'm doing great, how are you?", mReceiver, mDate));
        mMessageArrayList.add(new Message("0","I'm doing fine as well.", mSender, mDate));
    }

    private void loadMessages() {
        mAdapter.addToEnd(mMessageArrayList, true);
    }
}

