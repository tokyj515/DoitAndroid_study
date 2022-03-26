package org.techtown.album;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101); //사진 선택을 위한 응답코드
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(requestCode == RESULT_OK){
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver();
                try{
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(instream); //사진이나 이미지가 메모리에 만들어지면 비트맵으로 만들어짐
                    imageView.setImageBitmap(bitmap);

                    instream.close();

                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        }

    }










}