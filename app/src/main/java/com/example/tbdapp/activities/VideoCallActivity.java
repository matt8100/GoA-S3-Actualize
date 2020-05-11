package com.example.tbdapp.activities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class VideoCallActivity extends AppCompatActivity {
    //Default mic and camera options
    boolean recording = false;
    boolean micOn = true;
    boolean cameraOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        Objects.requireNonNull(getSupportActionBar()).hide();

        final LinearLayout bottomBar = findViewById(R.id.bottom_bar);

        decorView.setOnSystemUiVisibilityChangeListener
            (new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        //The system bars are visible.

                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) bottomBar.getLayoutParams();
                        p.setMargins(0, 0, 0, 200);
                        bottomBar.requestLayout();

                    } else {
                        //The system bars are NOT visible.

                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) bottomBar.getLayoutParams();
                        p.setMargins(0, 0, 0, 0);
                        bottomBar.requestLayout();

                    }
                }
            });

        //Show incoming/outgoing call screen
        if(getIntent().getExtras().getBoolean("withCamera")) {
            ((TextView) findViewById(R.id.call_type)).setText("Video Call");
        }else {
            ((TextView) findViewById(R.id.call_type)).setText("Voice Call");
        }

        boolean forceCallToBeReceiving = getIntent().getExtras().getBoolean("forceCallToBeReceiving");
        if(forceCallToBeReceiving) {
            //Incoming call screen
            findViewById(R.id.accept_fab).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startCall();
                }
            });
            findViewById(R.id.decline_fab).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

        }else {
            //Outgoing call screen
            findViewById(R.id.accept_fab).setVisibility(View.GONE);
            findViewById(R.id.accept_text).setVisibility(View.GONE);
            findViewById(R.id.decline_fab).setVisibility(View.GONE);
            findViewById(R.id.decline_text).setVisibility(View.GONE);

            //Show incoming/outgoing call screen for a set delay, then show main content
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    startCall();
                }
            }.start();
        }
    }
    public void startCall() {
        //references
        final ImageButton microphone = findViewById(R.id.fab_mic);
        final ImageButton camera = findViewById(R.id.fab_camera);
        final ImageButton recordButton = findViewById(R.id.fab_record);
        final ImageButton switchCamera = findViewById(R.id.fab_switch_camera);
        final ImageButton endCallButton = findViewById(R.id.fab_hangUp);
        final ImageButton addNoteButton = findViewById(R.id.fab_add_notes);
        final LinearLayout notesOverlay = findViewById(R.id.notes_overlay);
        final EditText notesText = findViewById(R.id.notes_text);
        final VideoView mainVideo = findViewById(R.id.mainVideo);
        final TextView recordingIndicator = findViewById(R.id.recording_indicator);
        final String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.test_video1;
        final VideoView secondaryVideo = findViewById(R.id.secondaryVideo);
        final String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.test_video2;
        final MediaPlayer recordingStart = MediaPlayer.create(this, R.raw.recording_start);
        final MediaPlayer recordingEnd = MediaPlayer.create(this, R.raw.recording_end);

        //set the video of primary video (advisor's video)
        mainVideo.setVideoURI(Uri.parse(videoPath));
        mainVideo.start();
        mainVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        //set the video of secondary video (client's video)
        secondaryVideo.setVideoURI(Uri.parse(videoPath2));
        secondaryVideo.start();
        secondaryVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        //toggle on/off mic
        microphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                micOn = !micOn;
                if(micOn) {
                    microphone.setImageDrawable(getResources().getDrawable(R.drawable.ic_unmute, getTheme()));
                }else {
                    microphone.setImageDrawable(getResources().getDrawable(R.drawable.ic_mute, getTheme()));
                }
            }
        });


        //Camera state on create
        cameraOn = getIntent().getExtras().getBoolean("withCamera");
        if(cameraOn) {
            camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_on, getTheme()));
            findViewById(R.id.secondaryVideoContainer).setVisibility(View.VISIBLE);
            secondaryVideo.setVideoURI(Uri.parse(videoPath2));
            secondaryVideo.start();
            secondaryVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
        }else {
            camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_off, getTheme()));
            findViewById(R.id.secondaryVideoContainer).setVisibility(View.GONE);
        }

        //toggle on/off camera
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cameraOn = !cameraOn;
                if(cameraOn) {
                    camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_on, getTheme()));
                    findViewById(R.id.secondaryVideoContainer).setVisibility(View.VISIBLE);
                    secondaryVideo.setVideoURI(Uri.parse(videoPath2));
                    secondaryVideo.start();
                    secondaryVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
                }else {
                    camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_off, getTheme()));
                    findViewById(R.id.secondaryVideoContainer).setVisibility(View.GONE);
                }
            }
        });

        //Record
        final ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                recordButton,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(930);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        final ObjectAnimator resetAnimation = ObjectAnimator.ofPropertyValuesHolder(
                recordButton,
                PropertyValuesHolder.ofFloat("scaleX", 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f));
        resetAnimation.setDuration(100);

        recordingIndicator.setVisibility(View.INVISIBLE);
        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recording = !recording;

                if(recording) {
                    recordingIndicator.setVisibility(View.VISIBLE);
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_recording, getTheme()));
                    recordingStart.start();

                    Context context = getApplicationContext();
                    CharSequence text = "Now Recording – Visible to Advisor";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    resetAnimation.cancel();
                    scaleDown.start();

                }else {
                    recordingIndicator.setVisibility(View.INVISIBLE);
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_record, getTheme()));
                    recordingEnd.start();

                    scaleDown.cancel();
                    resetAnimation.start();
                }
            }
        });

        //switch camera
        switchCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Do something when camera is switched
            }
        });

        //end call
        endCallButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Close activity and return to previous activity
                finish();
            }
        });
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                notesOverlay.setVisibility(View.VISIBLE);
                notesText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(notesText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        notesText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    notesOverlay.setVisibility(View.GONE);
                    notesText.setText("", TextView.BufferType.EDITABLE);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(notesText.getWindowToken(), 0);
//                    Snackbar.make(mainVideo, "Your note has been saved", 3000).show();
                    Context context = getApplicationContext();
                    CharSequence text = "Your note has been saved";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return true;
                }
                return false;
            }
        });

        //Show the call screen when everything is loaded
        findViewById(R.id.main_content).setVisibility(View.VISIBLE);
    }
}

