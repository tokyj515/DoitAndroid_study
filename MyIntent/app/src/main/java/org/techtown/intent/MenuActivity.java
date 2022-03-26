package org.techtown.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //main.xml과는 다른 파일이기 때문에 id를 button이라고 해도 상관은 없음
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike"); //이 문장을 통해 intent로 다른 엑티비티로 데이터 전달
                setResult(200, intent); //응답코드, RESULT_OK로 써도 됨

                finish(); //화면 없어짐
                //안드로이드는 화면이 위로 계속 쌓이는 구조라서 화면을 지워버리면 전 화면이 나오게 됨
            }
        });
    }
}