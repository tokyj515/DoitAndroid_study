package org.techtown.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView textView;
    TextView textView2;

    public Layout1(Context context) {
        super(context);

        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }


    public void init(Context context){
        //xml과 소스코드 연결하기
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true); //Layout1파일을 this(현재 코드파일)과 연결

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

    }


    public void setImage(int resId){
        imageView.setImageResource(resId);
    }


    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }


}
