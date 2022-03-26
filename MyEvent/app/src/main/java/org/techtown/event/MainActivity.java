package org.techtown.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    GestureDetector detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        //view1이벤트 touch
        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) { //클릭,
                int action = event.getAction();
                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN){ //눌린 상태
                    println("손가락 눌림 : " + curX + ", "+ curY);
                }
                else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락 움직임" + curX + ", "+ curY);
                }
                else if(action == MotionEvent.ACTION_UP){
                    println("손가락 뗌" + curX + ", "+ curY);
                }

                return true;
            }
        });



        //view2이벤트 gesture
        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
               println("onDown 호출됨");
               return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
                println("onFling 호출됨 : " + velocityX + ", " + velocityY);
                return true;
            }
        });




    }

    //핸드폰 뒤로가기 키 누르기 
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //키가 눌렸을 때 작동 키패드
        if(keyCode == KeyEvent.KEYCODE_BACK){
            println("시스템 [BACK] 버튼이 눌렸어요");
            return true;
        }
        return false;
    }


    //그냥 출력문문
    public void println(String data){
        textView.append(data + "\n");

    }



}