package org.techtown.inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container = findViewById(R.id.container);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLayOut();
            }
        });
    }



    public void addLayOut(){ //inflation하기 위한 함수
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //new연산자로 새로운 객체를 만드는 것이 아니라
        //시스템에 있는 것을 그대로 갖다 씀. 형변환 필요

        inflater.inflate(R.layout.sub1, container, true);
        Toast.makeText(this, "부분 화면이 추가되었습니다.", Toast.LENGTH_LONG).show();

    }
}