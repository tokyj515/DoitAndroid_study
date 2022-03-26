package org.techtown.orientation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //방향을 바꾸는 것은 기본으로 화면 생성 -> 화면 삭제 -> 화면 생성 으로 이루어 지는데
        //이렇게 화면을 삭제하지 않고 사용하는 방법도 있음

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){ //landscape: 가로
            showToast("가로 방향임");
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){ //portrait: 세로
            showToast("세로 방향임");
        }
    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}