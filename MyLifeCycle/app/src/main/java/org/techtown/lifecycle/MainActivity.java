package org.techtown.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);


        Log.d("Main", "onCreate 호출됨"); //logcat에서 확인할 수 있음음
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main", "onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main", "onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main", "onDestroy 호출됨");
    }

    @Override
    protected void onPause() { //갑자기 정지
        super.onPause();

        saveState(); //일시중지된 상태를 저장하기 위한 함수

        Log.d("Main", "onPause 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadState(); //일시중지된 상태를 다시 불러오는 함수
        Log.d("Main", "onResume 호출됨");
    }


    public void saveState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", editText.getText().toString());
        editor.commit();
    }

    public void loadState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null){
            String name = pref.getString("name", "");
            editText.setText(name);
        }
    }

}