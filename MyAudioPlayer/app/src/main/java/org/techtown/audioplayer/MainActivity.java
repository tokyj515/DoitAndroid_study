package org.techtown.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String url = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3";
    MediaPlayer mediaPlayer;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });

    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void killPlayer() {
        if(mediaPlayer != null){
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public void playAudio() {
        showToast("음악 파일 재생 호출됨");

        killPlayer();

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void stopAudio() {
        showToast("음악 파일 재생 중지됨");

        if(mediaPlayer != null){
            try {
                mediaPlayer.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void pauseAudio() {
        showToast("음악 파일 일시정지됨");

        if(mediaPlayer != null) {
            try {
                position = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void resumeAudio() {
        showToast("음악 파일 재시작됨");

        if(mediaPlayer != null && !mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.start();
                mediaPlayer.seekTo(position);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



}