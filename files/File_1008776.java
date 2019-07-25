package com.allen.androidcustomview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by xiaoyao on 2017/9/7.
 * 仿QQ消�?�红点拖拽效果view
 */

public class DragBallView extends View {

    private Paint circlePaint;
    private Paint textPaint;

    private int circleColor = Color.RED;
    private float radiusStart;
    private float radiusEnd;

    private Path path;

    private int startX;
    private int startY;

    //是�?��?�拖拽
    private boolean mIsCanDrag = false;
    //是�?�超过最大�?离
    private boolean isOutOfRang = false;
    //最终圆是�?�消失
    private boolean disappear = false;

    //两圆相离最大�?离
    private float maxDistance;

    //�?塞尔曲线需�?的点
    private PointF pointA;
    private PointF pointB;
    private PointF pointC;
    private PointF pointD;
    //控制点�??标
    private PointF pointO;

    //起始�?置点
    private PointF pointStart;
    //拖拽�?置点
    private PointF pointEnd;

    //根�?�滑动�?置动�?改�?�圆的�?�径
    private float currentRadiusStart;
    private float currentRadiusEnd;

    private Rect textRect = new Rect();
    //消�?�数
    private int msgCount = 0;

    private OnDragBallListener onDragBallListener;

    public DragBallView(Context context) {
        this(context, null);
    }

    public DragBallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPoint();

    }

    /**
     * �?始化所有点
     */
    private void initPoint() {
        pointStart = new PointF(startX, startY);

        pointEnd = new PointF(startX, startY);

        pointA = new PointF();
        pointB = new PointF();
        pointC = new PointF();
        pointD = new PointF();

        pointO = new PointF();

    }

    /**
     * �?始化画笔
     */
    private void initPaint() {

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        path = new Path();
        initTextPaint();
    }

    /**
     * �?始化文字画笔
     */
    private void initTextPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(sp2px(13));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = w / 2;
        startY = h / 2;
        maxDistance = dp2px(100);
        radiusStart = dp2px(15);
        radiusEnd = dp2px(15);

        currentRadiusEnd = radiusEnd;
        currentRadiusStart = radiusStart;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pointStart.set(startX, startY);
        if (isOutOfRang) {
            if (!disappear) {
                drawEndBall(canvas, pointEnd, currentRadiusEnd);
            }
        } else {
            drawStartBall(canvas, pointStart, currentRadiusStart);
            if (mIsCanDrag) {
                drawEndBall(canvas, pointEnd, currentRadiusEnd);
                drawBezier(canvas);
            }

        }

        if (!disappear) {
            if (msgCount > 0) {
                if (pointEnd.x==0||pointEnd.y==0){
                    drawText(canvas, msgCount, pointStart);
                }else {
                    drawText(canvas, msgCount, pointEnd);
                }
            }
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas, int msgCount, PointF point) {
        textRect.left = (int) (point.x - radiusStart);
        textRect.top = (int) (point.y - radiusStart);
        textRect.right = (int) (point.x + radiusStart);
        textRect.bottom = (int) (point.y + radiusStart);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心�?置
        canvas.drawText(msgCount > 99 ? "99+" : msgCount + "", textRect.centerX(), baseline, textPaint);
    }

    /**
     * 画起始�?�?�
     *
     * @param canvas 画布
     * @param pointF 点�??标
     * @param radius �?�径
     */
    private void drawStartBall(Canvas canvas, PointF pointF, float radius) {
        canvas.drawCircle(pointF.x, pointF.y, radius, circlePaint);
    }

    /**
     * 画拖拽结�?�的�?�?�
     *
     * @param canvas 画布
     * @param pointF 点�??标
     * @param radius �?�径
     */
    private void drawEndBall(Canvas canvas, PointF pointF, float radius) {
        canvas.drawCircle(pointF.x, pointF.y, radius, circlePaint);
    }

    /**
     * 画�?塞尔曲线
     *
     * @param canvas 画布
     */
    private void drawBezier(Canvas canvas) {
        path.reset();
        path.moveTo(pointA.x, pointA.y);
        path.quadTo(pointO.x, pointO.y, pointB.x, pointB.y);
        path.lineTo(pointC.x, pointC.y);
        path.quadTo(pointO.x, pointO.y, pointD.x, pointD.y);
        path.lineTo(pointA.x, pointA.y);
        path.close();

        canvas.drawPath(path, circlePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float currentX;
        float currentY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                setIsCanDrag(event);
                break;
            case MotionEvent.ACTION_MOVE:

                if (mIsCanDrag) {

                    currentX = event.getX();
                    currentY = event.getY();
                    //设置拖拽圆的�??标
                    pointEnd.set(currentX, currentY);

                    if (!isOutOfRang) {
                        setCurrentRadius();
                        setABCDOPoint();
                    }

                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                if (mIsCanDrag) {
                    if (isOutOfRang) {
                        //消失动画
                        disappear = true;
                        if (onDragBallListener != null) {
                            onDragBallListener.onDisappear();
                        }
                        invalidate();
                    } else {
                        disappear = false;
                        //回弹动画
                        final float a = (pointEnd.y - pointStart.y) / (pointEnd.x - pointStart.x);
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(pointEnd.x, pointStart.x);
                        valueAnimator.setDuration(500);
                        valueAnimator.setInterpolator(new BounceInterpolator());
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float x = (float) animation.getAnimatedValue();

                                float y = pointStart.y + a * (x - pointStart.x);

                                pointEnd.set(x, y);
                                setCurrentRadius();

                                setABCDOPoint();

                                invalidate();

                            }
                        });
                        valueAnimator.start();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 设置当�?计算的到的�?�径
     */
    private void setCurrentRadius() {
        //两个圆心之间的�?离
        float distance = (float) Math.sqrt(Math.pow(pointStart.x - pointEnd.x, 2) + Math.pow(pointStart.y - pointEnd.y, 2));

        //拖拽�?离在设置的最大值范围内�?绘制�?塞尔图形
        if (distance <= maxDistance) {
            //比例系数  控制两圆�?�径缩放
            float percent = distance / maxDistance;

            //之所以*0.6和0.2�?�为了放置拖拽过程圆�?�化的过大和过�?这个系数是多次�?试的出的
            //你也�?�以适当调整系数达到自己想�?的效果
            currentRadiusStart = (1 - percent * 0.6f) * radiusStart;
            currentRadiusEnd = (1 + percent * 0.2f) * radiusEnd;

            isOutOfRang = false;
        } else {
            isOutOfRang = true;
            currentRadiusStart = radiusStart;
            currentRadiusEnd = radiusEnd;
        }
    }

    /**
     * 判断是�?��?�以拖拽
     *
     * @param event event
     */
    private void setIsCanDrag(MotionEvent event) {
        Rect rect = new Rect();
        rect.left = (int) (startX - radiusStart);
        rect.top = (int) (startY - radiusStart);
        rect.right = (int) (startX + radiusStart);
        rect.bottom = (int) (startY + radiusStart);

        //触摸点是�?�在圆的�??标域内
        mIsCanDrag = rect.contains((int) event.getX(), (int) event.getY());
    }

    /**
     * 设置�?塞尔曲线的相关点�??标  计算方�?�?�照结算图�?��?�看明白
     * （ps为了画个清楚这个图花了�?少功夫哦）
     */
    private void setABCDOPoint() {
        //控制点�??标
        pointO.set((pointStart.x + pointEnd.x) / 2.0f, (pointStart.y + pointEnd.y) / 2.0f);

        float x = pointEnd.x - pointStart.x;
        float y = pointEnd.y - pointStart.y;

        //斜率 tanA=rate
        double rate;
        rate = x / y;
        //角度  根�?��??正切函数算角度
        float angle = (float) Math.atan(rate);

        pointA.x = (float) (pointStart.x + Math.cos(angle) * currentRadiusStart);
        pointA.y = (float) (pointStart.y - Math.sin(angle) * currentRadiusStart);

        pointB.x = (float) (pointEnd.x + Math.cos(angle) * currentRadiusEnd);
        pointB.y = (float) (pointEnd.y - Math.sin(angle) * currentRadiusEnd);

        pointC.x = (float) (pointEnd.x - Math.cos(angle) * currentRadiusEnd);
        pointC.y = (float) (pointEnd.y + Math.sin(angle) * currentRadiusEnd);

        pointD.x = (float) (pointStart.x - Math.cos(angle) * currentRadiusStart);
        pointD.y = (float) (pointStart.y + Math.sin(angle) * currentRadiusStart);
    }

    /**
     * 设置消�?�数
     *
     * @param count 消�?�个数
     */
    public void setMsgCount(int count) {
        msgCount = count;
        invalidate();
    }

    public void reset() {
        msgCount = 0;
        mIsCanDrag = false;
        isOutOfRang = false;
        disappear = false;
        pointStart.set(startX, startY);
        pointEnd.set(startX, startY);

        setABCDOPoint();
        invalidate();
    }

    public void setOnDragBallListener(OnDragBallListener onDragBallListener) {
        this.onDragBallListener = onDragBallListener;
    }

    /**
     * 回调事件
     */
    public interface OnDragBallListener {
        void onDisappear();
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }
}
