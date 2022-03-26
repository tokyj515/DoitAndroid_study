package org.techtown.captureintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼을 눌렀을 때 인탠트를 이용해서 사진어플을 띄워줌
                takePicture();
            }
        });


    }


    public void takePicture(){ //사진을 찍고 그 사진을 다시 이미지뷰에서 보여줌
        //사진은 파일로 저장되고 그 위치를 입력받아서 처리

        if(file == null){
            file = createFile();
        }

        Uri fileUri = FileProvider.getUriForFile(this, "org.techtown.captureIntent.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 101);
        }

    }

    public File createFile(){ //파일을 sd카드에서 부러옴
        String filename = "capture.jpg";

        File storageDir = Environment.getExternalStorageDirectory();
        File outfile = new File(storageDir, filename);

        return outfile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 101 && resultCode == RESULT_OK){ //보통 RESULT_OK로 응답코드를 받음
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 6;

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            imageView.setImageBitmap(bitmap);

        }
    }
}