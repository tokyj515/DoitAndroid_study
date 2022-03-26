package org.techtown.push;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView output1;
    TextView output2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output1 = findViewById(R.id.output1);
        output2 = findViewById(R.id.output2);


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.w("Main", "토큰을 가져오는 데 실패함", task.getException());
                    return;
                }

                String newToken = task.getResult();
                println("등록id: "+newToken);
            }
        });


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String instanceId = FirebaseInstanceId.getInstance().getId();
                println("확인된 인스턴스id: "+instanceId);
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            String from = intent.getStringExtra("from");
            String contents = intent.getStringExtra("contents");

            println("DATA: "+from+", "+contents);

            output1.setText("수신데이터: "+contents);
        }
    }

    public void println(String message){
        output2.append(message+"\n");
    }
}