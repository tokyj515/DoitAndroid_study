package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private  static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //intent안에 sms메시지가 포함되어 있음
        Log.d(TAG, "onReceive 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length>0){
            String sender = messages[0].getOriginatingAddress(); //보낸 사람의 전화번호
            String contents = messages[0].getMessageBody();

            Log.d(TAG, "sender: "+sender+", contents: "+contents);
        }
    }


    //나중에 필요하면 그냥 복사해서 쓰면 됨
    //크게 바뀌지 않음
   private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0; i<smsCount; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //버전체크
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }
            else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }

        }
        return messages;
    }



}