package com.example.tbdapp.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
                    findViewById(R.id.secondaryVideo).setVisibility(View.VISIBLE);
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
                    findViewById(R.id.secondaryVideo).setVisibility(View.INVISIBLE);
                }
            }
        });

        //Record
        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recording = !recording;
                if(recording) {
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_recording, getTheme()));
                    recordingStart.start();
                }else {
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_record, getTheme()));
                    recordingEnd.start();
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
                //Do something that ends the call
            }
        });
    }
}
