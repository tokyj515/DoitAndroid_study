package org.techtown.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //httpConnection은 보통 volley라는 라이브러리를 이용하지만 한 번 실습해보는 경험
    //스레드 사용, 코드가 많다는 단점
    //이를 보안한 것이 volley
    

    EditText editText;
    TextView textView;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String urlStr = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });

    }


    public void request(String urlStr){
        try {
            StringBuilder builder = new StringBuilder();
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while(true){
                    line = reader.readLine();
                    if(line == null){
                        break;
                    }

                    builder.append(line + "\n");
                }

                reader.close();
                conn.disconnect();
            }
            println("응답 -> "+builder.toString());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });

    }

}