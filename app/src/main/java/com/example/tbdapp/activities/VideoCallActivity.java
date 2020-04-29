package com.example.tbdapp.activities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;

public class VideoCallActivity extends AppCompatActivity {
    //Default mic and camera options
    boolean micOn = true;
    boolean cameraOn = true;
    boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        //references
        final ImageButton microphone = findViewById(R.id.fab_mic);
        final ImageButton camera = findViewById(R.id.fab_camera);
        final ImageButton recordButton = findViewById(R.id.fab_record);
        final ImageButton switchCamera = findViewById(R.id.fab_switch_camera);
        final ImageButton endCallButton = findViewById(R.id.fab_hangUp);
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
                    findViewById(R.id.secondaryVideoContainer).setVisibility(View.INVISIBLE);
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
    }
}
