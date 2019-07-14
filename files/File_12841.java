package com.gcssloop.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;

/**
 * Author: GcsSloop
 * <p>
 * Created Date: 16/8/26
 * <p>
 * Copyright (C) 2016 GcsSloop.
 * <p>
 * GitHub: https://github.com/GcsSloop
 */
public class SetPolyToPoly extends View{
    private static final String TAG = "SetPolyToPoly";

    private int testPoint = 0;
    private int triggerRadius = 180;    // è§¦å?‘å?Šå¾„ä¸º180px

    private Bitmap mBitmap;             // è¦?ç»˜åˆ¶çš„å›¾ç‰‡
    private Matrix mPolyMatrix;         // æµ‹è¯•setPolyToPolyç”¨çš„Matrix

    private float[] src = new float[8];
    private float[] dst = new float[8];

    private Paint pointPaint;

    public SetPolyToPoly(Context context) {
        this(context, null);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.poly_test2);

        float[] temp = {0, 0,                                    // å·¦ä¸Š
                mBitmap.getWidth(), 0,                          // å?³ä¸Š
                mBitmap.getWidth(), mBitmap.getHeight(),        // å?³ä¸‹
                0, mBitmap.getHeight()};                        // å·¦ä¸‹
        src = temp.clone();
        dst = temp.clone();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(0xffd19165);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPolyMatrix = new Matrix();
        mPolyMatrix.setPolyToPoly(src, 0, src, 0, 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX();
                float tempY = event.getY();

                // æ ¹æ?®è§¦æŽ§ä½?ç½®æ”¹å?˜dst
                for (int i=0; i<testPoint*2; i+=2 ) {
                    if (Math.abs(tempX - dst[i]) <= triggerRadius && Math.abs(tempY - dst[i+1]) <= triggerRadius){
                        dst[i]   = tempX-100;
                        dst[i+1] = tempY-100;
                        break;  // é˜²æ­¢ä¸¤ä¸ªç‚¹çš„ä½?ç½®é‡?å?ˆ
                    }
                }

                resetPolyMatrix(testPoint);
                invalidate();
                break;
        }

        return true;
    }

    public void resetPolyMatrix(int pointCount){
        mPolyMatrix.reset();
        // æ ¸å¿ƒè¦?ç‚¹
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100,100);

        // ç»˜åˆ¶å??æ ‡ç³»
        CanvasAidUtils.setCoordinateLen(900, 0, 1200, 0);
        CanvasAidUtils.drawCoordinateSpace(canvas);

        // æ ¹æ?®Matrixç»˜åˆ¶ä¸€ä¸ªå?˜æ?¢å?Žçš„å›¾ç‰‡
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);

        float[] dst = new float[8];
        mPolyMatrix.mapPoints(dst,src);

        // ç»˜åˆ¶è§¦æŽ§ç‚¹
        for (int i=0; i<testPoint*2; i+=2 ) {
            canvas.drawPoint(dst[i], dst[i+1],pointPaint);
        }
    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint > 4 || testPoint < 0 ? 4 : testPoint;
        dst = src.clone();
        resetPolyMatrix(this.testPoint);
        invalidate();
    }
}
