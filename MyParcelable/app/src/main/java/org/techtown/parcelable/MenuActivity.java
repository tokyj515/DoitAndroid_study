package org.techtown.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);


    }


    public void processIntent(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras(); //부가데이터를 관리하는 것이 bundle
            SimpleData data = bundle.getParcelable("data");

            if(data != null){
                Toast.makeText(this, "전달받은 객체: "+data.code+", "+data.message, Toast.LENGTH_LONG).show();
            }
        }
    }




}