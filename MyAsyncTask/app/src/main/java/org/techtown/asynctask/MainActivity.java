package org.techtown.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //AsyncTask는 하나의 job 자체를 하나의 클래스로 정의해서 처리
    //스레드(백그라운드) 동작부분과 그렇지 않은 부분을 동시에 처리 가능

    ProgressBar progressBar;

    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask task = new BackgroundTask();
                task.execute();
            }
        });
    }


    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{

        @Override
        protected void onPreExecute() { //스레드 실행 전 상태
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected void onPostExecute(Integer integer) { //스레드 실행 후 상태
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //스레드 실행하면서 중간중간 UI업데이트
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected Integer doInBackground(Integer... integers) { //스레드 실행 상태
            while(isCancelled() == false){
                value++;

                if(value >= 100){
                    break;
                }

                publishProgress(value); //publishProgress()에서 onProgressUpdate() 호출하게 됨

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }

            return value; //작업이 끝나면 onPostExecute()호출하게 됨
        }
    }
}







