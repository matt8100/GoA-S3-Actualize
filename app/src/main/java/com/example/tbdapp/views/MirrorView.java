package com.example.tbdapp.views;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MirrorView extends SurfaceView implements
        SurfaceHolder.Callback {
    public SurfaceHolder mHolder;
    private Camera mCamera;

    public MirrorView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception error) {
            Log.d("Camera",
                    "Error starting mPreviewLayout: " + error.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w,
                               int h) {
        if (mHolder.getSurface() == null) {
            return;
        }

        // can't make changes while mPreviewLayout is active
        try {
            mCamera.stopPreview();
        } catch (Exception e) {

        }

        try {

            // start up the mPreviewLayout
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception error) {
            Log.d("Camera",
                    "Error starting mPreviewLayout: " + error.getMessage());
        }
    }
}
