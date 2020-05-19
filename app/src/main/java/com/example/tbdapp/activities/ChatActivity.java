package com.example.tbdapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

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
import java.util.Objects;

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
        getSupportActionBar().setSubtitle(currentAdvisor.advisorType.toString());

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

        final int[] numOfUserMessagesSent = {0};
        mMessageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
                mDate = new Date();
                Message message = new Message("0", input.toString(), Singleton.getInstance().user, mDate);
                mAdapter.addToStart(message, true);
                Singleton.getInstance().chatHistory.get(mContactId).add(0, message);
                numOfUserMessagesSent[0]++;
                startDelayForNewMessage(numOfUserMessagesSent[0]);
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
        mAdapter.addToEnd(Objects.requireNonNull(Singleton.getInstance().chatHistory.get(mContactId)), false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, VideoCallActivity.class);
        switch(item.getItemId()) {
            case R.id.startVideo:
                intent.putExtra("withCamera", true);
                break;
            case R.id.startCall:
                intent.putExtra("withCamera", false);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        //IMPORTANT: Hardcoded value to simulate if call is incoming rather than outgoing
        final boolean forceCallToBeReceiving = false;

        //IMPORTANT: Hardcoded value to force which video will be played as the "advisor" on the other end
        final int videoFile = R.raw.example_video_2;

        Advisor advisor = Singleton.getInstance().advisors.get(Integer.parseInt(getIntent().getStringExtra("contactId"))-1);
        String advisorName = advisor.name;
        String resDirectory = advisor.avatar;

        intent.putExtra("videoFile", videoFile);
        intent.putExtra("forceCallToBeReceiving", forceCallToBeReceiving);
        intent.putExtra("caller", advisorName);
        intent.putExtra("advisorAvatar", resDirectory);
        this.startActivity(intent);
        return true;
    }
    public void createNewMessage(String senderID, String message) {
        mDate = new Date();
        Message newMessage = new Message("1", message, Singleton.getInstance().advisors.get(Integer.parseInt(senderID) - 1), mDate);
        mAdapter.addToStart(newMessage, true);
        Singleton.getInstance().chatHistory.get(mContactId).add(0, newMessage);
    }
    public void startDelayForNewMessage(int numOfExistingUserMessages) {
        switch(numOfExistingUserMessages) {
            case 1:
                new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        createNewMessage(mContactId, "Sounds good!");
                    }
                }.start();
                break;
            default:
                break;
        }
    }
}

