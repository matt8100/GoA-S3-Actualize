package com.example.tbdapp.activities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tbdapp.R;

import java.util.Objects;

public class VideoCallActivity extends AppCompatActivity {
    //Default mic and camera options
    boolean recording = false;
    boolean micOn = true;
    boolean cameraOn;

    boolean hadNotes = false;
    boolean hadRecording = false;

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

        //Show incoming/outgoing call screen
        if(getIntent().getExtras().getBoolean("withCamera")) {
            ((TextView) findViewById(R.id.call_type)).setText("Video Call");
        }else {
            ((TextView) findViewById(R.id.call_type)).setText("Voice Call");
        }

        ((ImageView) findViewById(R.id.avatar)).setImageResource(getResources().getIdentifier(getIntent().getExtras().getString("advisorAvatar"), "drawable", getPackageName()));
        ((TextView) findViewById(R.id.recipient_name)).setText(getIntent().getExtras().getString("caller"));

        final MediaPlayer callIncoming = MediaPlayer.create(this, R.raw.call_incoming);
        final MediaPlayer callOutgoing = MediaPlayer.create(this, R.raw.call_outgoing);
        final MediaPlayer callEnd = MediaPlayer.create(this, R.raw.call_end);

        boolean forceCallToBeReceiving = getIntent().getExtras().getBoolean("forceCallToBeReceiving");
        if(forceCallToBeReceiving) {
            callIncoming.start();
            callIncoming.setLooping(true);


            //Incoming call screen
            findViewById(R.id.accept_fab).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    callIncoming.stop();
                    startCall();
                }
            });
            findViewById(R.id.decline_fab).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((TextView) findViewById(R.id.call_type)).setText("");
                    ((TextView) findViewById(R.id.recipient_name)).setText("Call Ended");

                    findViewById(R.id.decline_fab).setEnabled(false);
                    findViewById(R.id.accept_fab).setEnabled(false);

                    callIncoming.stop();
                    callEnd.start();
                    new CountDownTimer(1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                            finish();
                        }
                    }.start();
                }
            });

        }else {
            callOutgoing.start();
            callOutgoing.setLooping(true);

            //Outgoing call screen
            findViewById(R.id.accept_fab).setVisibility(View.GONE);
            findViewById(R.id.accept_text).setVisibility(View.GONE);
            findViewById(R.id.decline_fab).setVisibility(View.GONE);
            findViewById(R.id.decline_text).setVisibility(View.GONE);
            findViewById(R.id.end_prematurely_text).setVisibility(View.VISIBLE);
            findViewById(R.id.end_prematurely_fab).setVisibility(View.VISIBLE);

            //Show incoming/outgoing call screen for a set delay, then show main content
            final CountDownTimer mCountDownTimer = new CountDownTimer(8000, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    callOutgoing.stop();
                    findViewById(R.id.loading).setVisibility(View.GONE);
                    startCall();
                }
            }.start();

            findViewById(R.id.end_prematurely_fab).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((TextView) findViewById(R.id.call_type)).setText("");
                    ((TextView) findViewById(R.id.recipient_name)).setText("Call Ended");

                    findViewById(R.id.end_prematurely_fab).setEnabled(false);

                    mCountDownTimer.cancel();
                    callOutgoing.stop();
                    callEnd.start();

                    new CountDownTimer(2000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }
                        public void onFinish() {
                            finish();
                        }
                    }.start();
                }
            });
        }
    }
    public void startCall() {
        //references
        final LinearLayout bottomBar = findViewById(R.id.bottom_bar);
        final LinearLayout notesDismissHitbox = findViewById(R.id.notes_overlay_hitbox);
        final ImageButton microphone = findViewById(R.id.fab_mic);
        final ImageButton camera = findViewById(R.id.fab_camera);
        final ImageButton recordButton = findViewById(R.id.fab_record);
        final ImageButton switchCamera = findViewById(R.id.fab_switch_camera);
        final ImageButton endCallButton = findViewById(R.id.fab_hangUp);
        final ImageButton addNoteButton = findViewById(R.id.fab_add_notes);
        final LinearLayout notesOverlay = findViewById(R.id.notes_overlay);
        final EditText notesText = findViewById(R.id.notes_text);
        final ImageButton newNote = findViewById(R.id.new_note);
        final VideoView mainVideo = findViewById(R.id.mainVideo);
        final ConstraintLayout recordingIndicator = findViewById(R.id.recording_indicator);
        final String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.test_video1;
        final VideoView secondaryVideo = findViewById(R.id.secondaryVideo);
        final String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.test_video2;
        final MediaPlayer recordingStart = MediaPlayer.create(this, R.raw.recording_start);
        final MediaPlayer recordingEnd = MediaPlayer.create(this, R.raw.recording_end);
        final MediaPlayer callEnd = MediaPlayer.create(this, R.raw.call_end);

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
                    secondaryVideo.stopPlayback();
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
                hadRecording = true;
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

                    Context context = getApplicationContext();
                    CharSequence text = "Recording Saved";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

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
                //End recording if applicable
                if(recording) {
                    recordingIndicator.setVisibility(View.INVISIBLE);
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_record, getTheme()));
                    recordingEnd.start();

                    scaleDown.cancel();
                    resetAnimation.start();
                }

                //prepare next screen
                String newText;
                if(hadNotes && hadRecording) {
                    newText = "Your notes and recording were saved.";
                }else if(!hadNotes && hadRecording) {
                    newText = "Your recording was saved.";
                }else if(hadNotes && !hadRecording) {
                    newText = "Your notes were saved.";
                }else {
                    newText = "";
                }
                findViewById(R.id.accept_fab).setVisibility(View.GONE);
                findViewById(R.id.accept_text).setVisibility(View.GONE);
                findViewById(R.id.decline_fab).setVisibility(View.GONE);
                findViewById(R.id.decline_text).setVisibility(View.GONE);
                findViewById(R.id.end_prematurely_text).setVisibility(View.GONE);
                findViewById(R.id.end_prematurely_fab).setVisibility(View.GONE);

                ((TextView) findViewById(R.id.call_type)).setText(newText);

                ((TextView) findViewById(R.id.recipient_name)).setText("Call Ended");

                //Transition screen after timer
                new CountDownTimer(1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        //Change screens
                        findViewById(R.id.main_content).setVisibility(View.GONE);
                        findViewById(R.id.loading).setVisibility(View.VISIBLE);

                        //Stop videos
                        mainVideo.stopPlayback();
                        secondaryVideo.stopPlayback();

                        //Play hang up sound
                        callEnd.start();

                        new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                //Close activity and return to previous activity
                                finish();

                            }
                        }.start();

                    }
                }.start();
            }
        });

        //Note prompt
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                notesOverlay.setVisibility(View.VISIBLE);
                notesText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(notesText, InputMethodManager.SHOW_IMPLICIT);

                //Hide 5 buttons
                bottomBar.setVisibility(View.GONE);
            }
        });

        //Note on enter key press
        notesText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hadNotes = true;

                    //Remove notes overlay and reset
                    notesOverlay.setVisibility(View.GONE);
                    addNote(notesText.getText().toString());
                    notesText.setText("", TextView.BufferType.EDITABLE);

                    //Show 5 buttons
                    bottomBar.setVisibility(View.VISIBLE);

                    //Dismiss keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);

                    //Toast
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

        //Add new note
        newNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hadNotes = true;

                //Remove note content
                addNote(notesText.getText().toString());
                notesText.setText("", TextView.BufferType.EDITABLE);

                //Toast
                Context context = getApplicationContext();
                CharSequence text = "Your note has been saved";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //click out
        notesDismissHitbox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Dismiss notes
                notesOverlay.setVisibility(View.GONE);

                //Show 5 buttons
                bottomBar.setVisibility(View.VISIBLE);

                //Dismiss keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
            }
        });

        //Show the call screen when everything is loaded
        findViewById(R.id.main_content).setVisibility(View.VISIBLE);
    }
    public void addNote(String noteContent) {
        //Do something with the note content
    }
}

