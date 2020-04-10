package com.example.tbdapp.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbdapp.R;
import com.example.tbdapp.models.Advisor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VideoCallActivity extends AppCompatActivity {
    //Default mic and camera options
    boolean micOn = true;
    boolean cameraOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);


        //set the video of primary video (advisor's video)
        VideoView mainVideo = findViewById(R.id.mainVideo);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.test_video1;
        mainVideo.setVideoURI(Uri.parse(videoPath));
        mainVideo.start();
        mainVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        //set the video of secondary video (client's video)
        final VideoView secondaryVideo = findViewById(R.id.secondaryVideo);
        String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.test_video2;
        secondaryVideo.setVideoURI(Uri.parse(videoPath2));
        secondaryVideo.start();
        secondaryVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        //references to fabs
        final FloatingActionButton microphone = findViewById(R.id.fab_mic);
        final FloatingActionButton camera = findViewById(R.id.fab_camera);
        final FloatingActionButton endCallButton = findViewById(R.id.fab_hangUp);

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
                }else {
                    camera.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_off, getTheme()));
                    findViewById(R.id.secondaryVideo).setVisibility(View.INVISIBLE);
                }
            }
        });

        endCallButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Do something that ends the call
            }
        });

    }

}
