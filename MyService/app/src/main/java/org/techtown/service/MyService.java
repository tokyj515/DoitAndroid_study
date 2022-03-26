package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//서비스 클래스
//비정상 종료되어도 다시 실행될 수 있음
public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //데이터를 전달하기 위한 목적
        Log.d(TAG, "onStartCommand 호출됨");

        if(intent != null){
            processCommand(intent);
        }
        else{
            return Service.START_STICKY;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d(TAG, "command: "+command+", name:"+name);

        try{
            Thread.sleep(5000); //5초
        } catch (Exception e){
            e.printStackTrace();
        }


        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command", "name");
        showIntent.putExtra("name", name+" from service");
        startActivity(showIntent);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy 호출됨");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}