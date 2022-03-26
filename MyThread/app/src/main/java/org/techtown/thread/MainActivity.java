package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    //MainHandler handler; //2번
    Handler handler; //3번

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start(); //start안에서 run실행됨
            }
        });


        //handler = new MainHandler();
        handler = new Handler();

    }


    class BackgroundThread extends Thread{
        int value = 0;

        public void run(){
            for(int i=0; i<100; i++){
                try{
                    Thread.sleep(1000); //1초 동안 sleep
                } catch (Exception e){

                }
                value += 1;
                Log.d("MyThread", "value: "+value);

                //1번
                //textView.setText("값: "+value);
                //내가 만든 스레드는 메인스레드에서 관리하는 스레드를 건들 수 없음 동시접근 문제
                //이를 해결하기 위해 핸들러 필요


                /*2번
                //핸들러를 통과하게 만들어줌
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);

                handler.sendMessage(message);
                */

                //3번 메인 핸들러로 전달해줌
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값: "+value);
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값: "+value);
                    }
                }, 5000);
            }
        }

    }

    class MainHandler extends Handler{ //2번

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg); //메인스레드에서 실행됨

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            textView.setText("값: "+value); //메인스레드에서 실행할 수 있게 해줌
        }
    }

}