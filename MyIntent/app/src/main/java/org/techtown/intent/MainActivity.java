package org.techtown.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                //ex. button의 context: 버튼이 어떤 레이아웃 안에 포함되어있고 등등 여러 정보를 갖고옴

                startActivityForResult(intent, 101); //화면 띄우기  //요청코드로부터 어떤 화면에서 응답이 왔는지 확인할 수 있음
            }
        });
    }

    @Override //menu->main 돌아올 때 자동호출되는 함수
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(data != null){
                String name = data.getStringExtra("name");
                if(name != null){
                    Toast.makeText(this, "응답으로 받은 데이터: " + name, Toast.LENGTH_LONG).show();
                }
            }
        }
    }





}