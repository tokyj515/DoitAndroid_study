package org.techtown.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView); //onCreate에 꼭 넣어줘야 함
        imageView2 = findViewById(R.id.imageView2); //onCreate에 꼭 넣어줘야 함

    }


    public void onButton1Clicked(View v){
        changeImage();
    }

    //xml에서 속성을 통해 vis or invis를 선택할 수 있지만
    //버튼을 눌렀을 때 작동하게 하려면
    //소스 코드로 실행시켜줘야 함
    public void changeImage(){ //버튼을 눌렀을 때 이미지가 번갈아서 나오게 됨

        if(imageIndex == 0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        }
        else if(imageIndex == 1){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }


    }
}