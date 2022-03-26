package org.techtown.customviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        paint = new Paint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //뷰가 차지하는 영역이 결정되는 시점에 호출됨
        super.onSizeChanged(w, h, oldw, oldh);

        createCacheBitmap(w, h);
        testDrawing();
    }

    public void createCacheBitmap(int w, int h){
        //onDraw를 통해 그린다면 여러번 그리게 될 수도 있게 때문에
        //메모리에 직접 그리는 함수를 정의해봄
        //그리는 시간이 줄어듦
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);

    }


    public void testDrawing(){ //테스트 함수수
       Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        cacheCanvas.drawBitmap(srcImg, 100, 100, paint);

        Matrix matrix = new Matrix();
        matrix.setScale(1, -1); //확대 축소하기
        Bitmap inverseBitmap = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), matrix, false);
        cacheCanvas.drawBitmap(inverseBitmap, 300, 300, paint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        //canvas.drawBitmap(srcImg, 100, 100, paint);

        if(cacheCanvas != null){
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }
    }



}
