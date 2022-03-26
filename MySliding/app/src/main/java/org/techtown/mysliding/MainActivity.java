package org.techtown.mysliding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Animation translateLeftAnim;
    Animation trandlateRightAnim;

    LinearLayout page;
    Button button;

    boolean isPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        trandlateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingAnimationListener animListener = new SlidingAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        trandlateRightAnim.setAnimationListener(animListener);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen){
                    page.startAnimation(trandlateRightAnim);
                }
                else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);

                }
            }
        });
    }

    class SlidingAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //끝나는 시점에 버튼에 써있는 문자를 바꿈
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);

                button.setText("열기");
                isPageOpen = false;
            }
            else{
                button.setText("닫기");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}