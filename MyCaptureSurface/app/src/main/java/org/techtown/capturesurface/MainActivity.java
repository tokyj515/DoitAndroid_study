package org.techtown.capturesurface;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.security.Permission;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CameraSurfaceView cameraSurfaceView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout container = findViewById(R.id.container);
        cameraSurfaceView = new CameraSurfaceView(this);
        container.addView(cameraSurfaceView);

        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture();
            }
        });


        /*
        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.CAMERA,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String >>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("허용된 권한 개수: "+permissions.size());
                    }
                })*/


    }

    public void capture(){
        cameraSurfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, databaseList().length);
                //찍힌 사진을 바이트에서 비트맵으로 바꿈

                imageView.setImageBitmap(bitmap);
            }
        });
    }
}