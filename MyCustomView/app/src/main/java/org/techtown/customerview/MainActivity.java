package org.techtown.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); 이걸 없애버린다고 치면

        CustomView view  =  new CustomView(this);
        setContentView(view);

    }
}