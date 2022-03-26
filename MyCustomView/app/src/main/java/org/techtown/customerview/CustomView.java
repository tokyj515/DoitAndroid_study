package org.techtown.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

//canvas, paint 객체

public class CustomView extends View {

    Paint paint;

    public CustomView(Context context) {
        super(context);

        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) { //인플래이션에 이용할 생성자
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) { //자동으로 그려짐
        super.onDraw(canvas);

        //canvas.drawRect(100, 100, 200, 200, paint); //빨간색 정사각형

        paint.setStyle(Paint.Style.FILL); //색 채우기
        paint.setColor(Color.RED);
        canvas.drawRect(10, 10, 300, 300, paint); //픽셀단위라 단말 해상도마다 크기가 달라져 보일 수도 있음

        paint.setStyle(Paint.Style.STROKE); //선
        paint.setStrokeWidth(10.0F);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 300, 300, paint);

        paint.setAntiAlias(true); //픽셀단위라서 정사각형 모양으로 잘려있는 느낌을 받을 수 있기 때문에 조금 부드럽게 만들어줌
        canvas.drawCircle(400, 400, 200, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //화면을 눌렀을 때 찍은 좌표가 나오는 함수
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "눌렸음: " + event.getX() + ", " + event.getY(), Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }
}
