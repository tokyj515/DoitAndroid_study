package org.techtown.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;
    EditText editText;
    TextView textView2;

    //생명주기 life cycle
    //방향이 바뀌는 것이 화면이 생겼다 사라졌다 하면서 나오는 것
    @Override
    protected void onCreate(Bundle savedInstanceState) { //화면이 시작될 때 생성됨
        //savedInstanceState는 전에 저장이 되어있던 변수값들을 불러오는 역할을 함
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate 호출됨");

        editText = findViewById(R.id.editTextTextPersonName);
        textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        if(button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText != null) {
                        name = editText.getText().toString();
                        showToast("사용자 입력값을 name 변수에 할당함");
                    }
                }
            });
        }

        if(savedInstanceState != null){
            if(textView2 != null){
                name = savedInstanceState.getString("name");
                textView2.setText(name);

                showToast("값을 복원했습니다 : " + name);
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
        //액티비티가 없어지는 순간에 우리가 저장했던 값이 어딘가에 저장이 되게 함

    }

    @Override
    protected void onDestroy() { //화면이 없어질 때 호출됨
        super.onDestroy();

        showToast("onDestroy 호출됨");
    }


    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}