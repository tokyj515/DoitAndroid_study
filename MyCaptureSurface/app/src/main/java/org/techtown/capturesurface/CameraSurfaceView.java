package org.techtown.capturesurface;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback { // SurfaceView: 껍데기        SurfaceViewHolder: SurfaceView 컨트롤

    SurfaceHolder holder;
    Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context){
        holder = getHolder();
        holder.addCallback(this);
    }


    //SurfaceView 일반적인 것과는 다르게 작동
    //별도의 함수를 제공함
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {
        //화면에 서프스뷰가 자리를 잡으면서 크기가 바뀌는 것
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        camera.startPreview();
        camera.release();
        camera = null;
    }

    public boolean capture(Camera.PictureCallback callback){
        if(camera != null){
            camera.takePicture(null, null, callback);
            return true;
        }
        else{
            return false;
        }
    }

}
