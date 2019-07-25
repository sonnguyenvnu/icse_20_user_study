package com.cgfay.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cgfay.imagelibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 涂鸦界�?�
 * Created by cain.huang on 2017/12/28.
 */

public class GraffitiView extends View {

    private Paint mPaint;
    private Path mPath;
    // 当�?存放的列表
    private List<Path> savePathList = new ArrayList<>();
    private List<Paint> paintList = new ArrayList<>();
    private boolean isDrawMode;
    private OnLineChangeListener mLineChangeListener;
    // 触摸状�?
    private boolean touchMode;
    private OnTouchListener mTouchListener;

    // 当�?颜色
    private @ColorInt int mColor = Color.WHITE;


    public GraffitiView(Context context) {
        super(context);
        init();
    }

    public GraffitiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraffitiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置颜色
     * @param color
     */
    public void setNewPaintColor(@ColorInt int color) {
        mPaint.setColor(color);
        mColor = color;
    }

    /**
     * 设置画笔大�?
     * @param size �?素值
     */
    public void setNewPaintSize(float size) {
        mPaint.setStrokeWidth(size);
    }

    /**
     * 新建一个Paint
     * @param color
     * @return
     */
    public Paint newPaint(int color) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.dp3));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);

        return paint;
    }

    /**
     * �?始化
     */
    private void init() {
        mPaint = newPaint(Color.WHITE);
        mPath = new Path();
    }

    /**
     * 设置绘制模�?
     * @param flag
     */
    public void setDrawMode(boolean flag) {
        isDrawMode = flag;
    }

    /**
     * 清除上一个绘制线�?�
     */
    public void backPath() {

        if(savePathList.size() != 0){
            if(savePathList.size() == 1){
                mPath.reset();
                savePathList.clear();
                paintList.clear();
            }else{
                savePathList.remove(savePathList.size()-1);
                paintList.remove(paintList.size()-1);
                mPath = savePathList.get(savePathList.size() - 1);
                mPaint = paintList.get(paintList.size() - 1);
            }
            if(mLineChangeListener != null) mLineChangeListener.onDeleteLine(savePathList.size());
        }
        invalidate();
    }

    /**
     * 清除所有绘制线�?�
     */
    public void clearPath() {
        mPath.reset();
        savePathList.clear();
        paintList.clear();
    }

    /**
     * 触摸状�?的回调
     */
    public interface OnTouchListener {
        void onDown();
        void onUp();
    }

    /**
     * 绘制状�?的回调
     */
    public interface OnLineChangeListener {
        /**
         * @param sum 现在总共绘制线�?�的数目
         */
        void onDrawLine(int sum);
        void onDeleteLine(int sum);
    }

    /**
     * 设置线段改�?�回调
     * @param listener
     */
    public void setOnLineChangeListener(OnLineChangeListener listener) {
        mLineChangeListener = listener;
    }

    /**
     * 设置触摸监�?�
     * @param listener
     */
    public void setOnTouchListener(OnTouchListener listener) {
        mTouchListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(isDrawMode) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchMode = true;
                    touchDown(event);
                    if (mTouchListener != null) {
                        mTouchListener.onDown();
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    touchMove(event);
                    break;

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchMode = false;
                    savePathList.add(new Path(mPath));
                    paintList.add(new Paint(mPaint));
                    if (mTouchListener != null) {
                        mTouchListener.onUp();
                    }
                    if (mLineChangeListener != null) {
                        mLineChangeListener.onDrawLine(savePathList.size());
                    }
                    break;
            }
            invalidate();
        }
        return isDrawMode;
    }

    private float mX;
    private float mY;

    /**
     * 手指点下�?幕时调用
     * @param event
     */
    private void touchDown(MotionEvent event) {

        mPath = new Path();

        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;

        // mPath绘制的绘制起点
        mPath.moveTo(x, y);
    }

    /**
     * 手指在�?幕上滑动时调用
     * @param event
     */
    private void touchMove(MotionEvent event) {

        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        // 两点之间的�?离大于等于3时，连接连接两点形�?直线
        if (dx >= 3 || dy >= 3) {
            // 两点连�?直线
            mPath.lineTo(x, y);

            // 第二次执行时，第一次结�?�调用的�??标值将作为第二次调用的�?始�??标值
            mX = x;
            mY = y;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制之�?的线�?�
        for (int x=0; x<savePathList.size(); x++){
            Path path = savePathList.get(x);
            Paint paint = paintList.get(x);
            canvas.drawPath(path, paint);
        }
        // 绘制刚画的线�?�
        if(touchMode) canvas.drawPath(mPath, mPaint);
    }
}
