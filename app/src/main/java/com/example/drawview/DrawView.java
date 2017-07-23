package com.example.drawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lihang on 2017/6/9.
 */

public class DrawView extends View {

    //初始位置；
    float preX;
    float preY;

    public Paint paint;
    public Canvas cacheCanvas=null;
    private Bitmap cacheBitmap=null;
    private Path path;

    public DrawView(Context context,int width,int height) {
        super(context);

        //设置画笔颜色
        paint=new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setDither(true);


        path=new Path();
//
        cacheCanvas=new Canvas();
        cacheBitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);

    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x=event.getX();
        float y=event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;

        }


        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint bmpPaint=new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);

        canvas.drawPath(path,paint);
    }
}
