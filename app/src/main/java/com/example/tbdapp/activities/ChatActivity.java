package com.example.tbdapp.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Author;
import com.example.tbdapp.models.Dialog;
import com.example.tbdapp.models.Message;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.models.Singleton;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private MessagesList mMessagesList;
    private MessageInput mMessageInput;
    private MessagesListAdapter<Message> mAdapter;
    private Date mDate;
    private String mContactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMessagesList = findViewById(R.id.messagesList);
        mMessageInput = findViewById(R.id.input);

        mContactId = getIntent().getStringExtra("contactId");
        mAdapter = new MessagesListAdapter<>("0", new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                int image = getApplicationContext().getResources().getIdentifier(url, "drawable", getApplicationContext().getPackageName());
                Picasso.get().load(image).into(imageView);
            }
        });

        setTitle(Singleton.getInstance().advisors.get(Integer.parseInt(mContactId)-1).name);
        mMessagesList.setAdapter(mAdapter);

        loadMessages();

        mMessageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
                mDate = new Date();
                Message message = new Message("0", input.toString(), Singleton.getInstance().user, mDate);
                mAdapter.addToStart(message, true);
                Singleton.getInstance().chatHistory.get(mContactId).add(0, message);
                return true;
            }
        });
    }

    private void loadMessages() {
        mAdapter.addToEnd(Singleton.getInstance().chatHistory.get(mContactId), false);
    }
}

