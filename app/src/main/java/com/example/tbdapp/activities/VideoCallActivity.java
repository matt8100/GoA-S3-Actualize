package com.example.tbdapp.activities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tbdapp.R;
import com.example.tbdapp.views.MirrorView;

import java.util.Objects;

public class VideoCallActivity extends AppCompatActivity {
    //Default mic and camera options
    boolean recording = false;
    boolean micOn = true;
    boolean cameraIsFront = true;
    boolean cameraOn;

    boolean hadNotes = false;
    boolean hadRecording = false;

    private Camera mCam;
    private MirrorView mCamPreview;
    private int mCameraId = 0;
    private FrameLayout mPreviewLayout;
    private int videoFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        decorView.setSystemUiVisibility(uiOptions);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Show correct screen
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        findViewById(R.id.main_content).setVisibility(View.GONE);

        //Show incoming/outgoing call screen
        if(Objects.requireNonNull(getIntent().getExtras()).getBoolean("withCamera")) {
            ((TextView) findViewById(R.id.call_type)).setText(R.string.videoCall);
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
            }
        }else {
            ((TextView) findViewById(R.id.call_type)).setText(R.string.voiceCall);
        }

        ((ImageView) findViewById(R.id.avatar)).setImageResource(getResources().getIdentifier(getIntent().getExtras().getString("advisorAvatar"), "drawable", getPackageName()));
        ((TextView) findViewById(R.id.recipient_name)).setText(getIntent().getExtras().getString("caller"));

        videoFile = getIntent().getExtras().getInt("videoFile");

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
                    ((TextView) findViewById(R.id.recipient_name)).setText(R.string.callEnded);

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
                    ((TextView) findViewById(R.id.recipient_name)).setText(R.string.callEnded);

                    findViewById(R.id.end_prematurely_fab).setEnabled(false);

                    mCountDownTimer.cancel();
                    callOutgoing.stop();
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
        final String videoPath = "android.resource://" + getPackageName() + "/" + videoFile;
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

        //Use Camera
        mCameraId = findCamera(cameraIsFront);
        mPreviewLayout = findViewById(R.id.preview_camera);
        mPreviewLayout.removeAllViews();

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
        cameraOn = Objects.requireNonNull(getIntent().getExtras()).getBoolean("withCamera");
        if(cameraOn) {
            camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_on, getTheme()));
            findViewById(R.id.secondaryVideoContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.preview_camera).setVisibility(View.VISIBLE);

            mCameraId = findCamera(cameraIsFront);
            startCameraInLayout(mPreviewLayout, mCameraId);
            setCameraDisplayOrientationAndSize();

        }else {
            camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_off, getTheme()));
            findViewById(R.id.secondaryVideoContainer).setVisibility(View.GONE);
            findViewById(R.id.preview_camera).setVisibility(View.GONE);
            mPreviewLayout.removeAllViews();

            if (mCam != null) {
                mCam.release();
                mCam = null;
            }
        }

        //toggle on/off camera
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cameraOn = !cameraOn;
                if(cameraOn) {
                    camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_on, getTheme()));
                    findViewById(R.id.secondaryVideoContainer).setVisibility(View.VISIBLE);

                    mCameraId = findCamera(cameraIsFront);
                    startCameraInLayout(mPreviewLayout, mCameraId);
                    setCameraDisplayOrientationAndSize();

                }else {
                    camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_off, getTheme()));
                    findViewById(R.id.secondaryVideoContainer).setVisibility(View.GONE);

                    mPreviewLayout.removeAllViews();
                    if (mCam != null) {
                        mCam.release();
                        mCam = null;
                    }
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
                if(cameraOn) {
                    cameraIsFront = !cameraIsFront;
                    //restart camera
                    if (mCam != null) {
                        mCam.release();
                        mCam = null;
                    }
                    mCameraId = findCamera(cameraIsFront);

                    startCameraInLayout(mPreviewLayout, mCameraId);
                    setCameraDisplayOrientationAndSize();
                }
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
                }else if(hadNotes) {
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

                ((TextView) findViewById(R.id.recipient_name)).setText(R.string.callEnded);

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

                        //Play hang up sound
                        callEnd.start();

                        new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }
                            public void onFinish() {
                                //stop camera
                                if (mCam != null) {
                                    mCam.release();
                                    mCam = null;
                                }
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
        findViewById(R.id.loading).setVisibility(View.GONE);
        findViewById(R.id.main_content).setVisibility(View.VISIBLE);

    }
    public void addNote(String noteContent) {
        //Do something with the note content
    }
    private int findCamera(boolean isFrontCamera) {
        int foundId = -1;
        int numCams = Camera.getNumberOfCameras();
        for (int camId = 0; camId < numCams; camId++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(camId, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT && isFrontCamera) {
                foundId = camId;
                break;
            }else if(info.facing == Camera.CameraInfo.CAMERA_FACING_BACK && !isFrontCamera) {
                foundId = camId;
                break;
            }
        }
        return foundId;
    }
    private void startCameraInLayout(FrameLayout layout, int cameraId) {
        mCam = Camera.open(cameraId);
        if (mCam != null) {
            mCamPreview = new MirrorView(this, mCam);
            layout.addView(mCamPreview);
        }
    }
    public void setCameraDisplayOrientationAndSize() {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, info);
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = rotation * 90;

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        mCam.setDisplayOrientation(result);

        Camera.Size previewSize = mCam.getParameters().getPreviewSize();
        if (result == 90 || result == 270) {
            mCamPreview.mHolder.setFixedSize(previewSize.height, previewSize.width);
        } else {
            mCamPreview.mHolder.setFixedSize(previewSize.width, previewSize.height);

        }
    }
}

