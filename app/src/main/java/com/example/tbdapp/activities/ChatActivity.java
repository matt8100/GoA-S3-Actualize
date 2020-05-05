package com.example.tbdapp.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;
import com.example.tbdapp.models.Message;
import com.example.tbdapp.models.Singleton;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private MessagesList mMessagesList;
    private MessageInput mMessageInput;
    private MessagesListAdapter<Message> mAdapter;
    private Date mDate;
    private String mContactId;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        mContactId = getIntent().getStringExtra("contactId");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);

        Advisor currentAdvisor = Singleton.getInstance().advisors.get(Integer.parseInt(mContactId)-1);
        getSupportActionBar().setTitle(currentAdvisor.name);
        getSupportActionBar().setSubtitle(currentAdvisor.advisorType);

        mMessagesList = findViewById(R.id.messagesList);
        mMessageInput = findViewById(R.id.input);

        MessageHolders messageHolders = new MessageHolders();
        messageHolders.setIncomingTextLayout(R.layout.item_custom_incoming_text_message);
        messageHolders.setOutcomingTextLayout(R.layout.item_custom_outcoming_text_message);

        mAdapter = new MessagesListAdapter<>("0", messageHolders, new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                int image = getApplicationContext().getResources().getIdentifier(url, "drawable", getApplicationContext().getPackageName());
                imageView.setImageResource(image);
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    private void loadMessages() {
        mAdapter.addToEnd(Singleton.getInstance().chatHistory.get(mContactId), false);
    }
}

