package com.zinc.velocitytracker_scroller.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zinc
 * email : 56002982@qq.com
 * time : 2019/3/1 下�?�5:04
 * desc : 带滑动效果的柱形图
 * version :
 */
public class BarChart extends View {

    private static final String TAG = "BarChart";
    /**
     * 内圆的颜色
     */
    private static final String INNER_DOT_COLOR = "#99E35B5B";
    /**
     * 外圆的颜色
     */
    private static final String OUTER_DOT_COLOR = "#28E35B5B";
    /**
     * 柱的颜色
     */
    private static final String BAR_COLOR = "#bb434343";
    /**
     * 文字颜色
     */
    private static final String TEXT_COLOR = "#64C5C5C5";
    /**
     * 动画时长
     */
    private static final int ANIM_DURATION = 2000;

    /**
     * 柱�?的数�?�
     */
    private List<BarInfo> mBarInfoList = new ArrayList<>();
    /**
     * �??述字体的大�?
     */
    private float mDescTextSize;
    /**
     * 点的内�?�径
     */
    private float mDotInnerRadius;
    /**
     * 点的外�?�径
     */
    private float mDotOuterRadius;
    /**
     * 底部边�?
     */
    private float mBottomSpacing;
    /**
     * 柱与文字的�?离
     */
    private float mBarTextSpacing;
    /**
     * 柱�?与柱�?的间隔
     */
    private float mBarInterval;
    /**
     * 柱�?与上边�?的�?离
     */
    private float mTopSpacing;
    /**
     * 柱�?的高度
     */
    private float mBarHeight;
    /**
     * �?根柱�?的宽度
     */
    private float mBarWidth;
    /**
     * 有数�?�的画布宽
     */
    private float mCanvasWidth;
    /**
     * 用户�?��?的视图宽
     */
    private float mViewWidth;
    /**
     * 柱�?路径
     */
    private Path mBarPath;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当�?动画的进度
     */
    private float mAnimRate = 0;
    /**
     * 柱�?颜色
     */
    private int mBarColor;
    /**
     * 内圆颜色
     */
    private int mInnerDotColor;
    /**
     * 外圆颜色
     */
    private int mOuterDotColor;
    /**
     * 字体大�?
     */
    private int mTextColor;
    /**
     * 最�?�触碰的x�??标
     */
    private float mLastTouchX;
    /**
     * 动画
     */
    private ValueAnimator mAnim;

    /**
     * 滑动速度追踪
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 滑动的最大速度
     */
    private int mMaximumVelocity;
    /**
     * 滑动的最�?速度
     */
    private int mMinimumVelocity;

    /**
     * 滑动线程
     */
    private FlingRunnable mFling;

    public BarChart(Context context) {
        this(context, null, 0);
    }

    public BarChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        setClickable(true);

        mDescTextSize = dip2px(context, 12f);
        mDotInnerRadius = dip2px(context, 3.5f);
        mDotOuterRadius = dip2px(context, 5f);
        mBarInterval = dip2px(context, 40f);
        mBottomSpacing = dip2px(context, 10f);
        mBarTextSpacing = dip2px(context, 12f);
        mTopSpacing = dip2px(context, 10f);
        mBarWidth = dip2px(context, 1.25f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mBarPath = new Path();

        mBarColor = Color.parseColor(BAR_COLOR);
        mInnerDotColor = Color.parseColor(INNER_DOT_COLOR);
        mOuterDotColor = Color.parseColor(OUTER_DOT_COLOR);
        mTextColor = Color.parseColor(TEXT_COLOR);

        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        mFling = new FlingRunnable(context);

        mAnim = ValueAnimator.ofFloat(0, 1);
        mAnim.setDuration(ANIM_DURATION);
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimRate = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
    }

    /**
     * 设置动画数�?�
     *
     * @param barInfoList
     */
    public void setBarInfoList(List<BarInfo> barInfoList) {
        this.mBarInfoList.clear();
        this.mBarInfoList.addAll(barInfoList);
        this.mCanvasWidth = (this.mBarInfoList.size() + 1) * this.mBarInterval;

        // �?�止正在执行的动画
        if (mAnim != null && mAnim.isRunning()) {
            mAnim.cancel();
        }

        // �?�止滚动
        if (mFling != null) {
            mFling.stop();
        }

        // �?置动画进度
        mAnimRate = 0;

        // 滚回最开始的�??标
        scrollTo(0, 0);

        // �??交刷新
        postInvalidate();
    }

    /**
     * �?�动动画
     */
    public void start() {
        if (mBarInfoList == null || mBarInfoList.size() == 0) {
            Log.e(TAG, "�?�动动画�?，请先设置数�?�");
            return;
        }

        mAnimRate = 0;

        if (mAnim.isRunning()) {
            mAnim.cancel();
        }

        mAnim.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 柱�?的高度 = 控件高度 - 上内边�? - 下内边�? - 字体大�? - 字体与柱�?的间�?
        this.mBarHeight = h - mTopSpacing - mBottomSpacing - mDescTextSize - mBarTextSpacing;
        this.mViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBar(canvas);
        drawDot(canvas);
        drawText(canvas);
    }

    /**
     * 控制�?幕�?越界
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 当数�?�的长度�?足以滑动时，�?�?�滑动处�?�
        if (mCanvasWidth < mViewWidth) {
            return true;
        }

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            mLastTouchX = event.getX();

            mFling.stop();
        } else if (MotionEvent.ACTION_MOVE == event.getAction()) {
            // 滑动的�?离
            float scrollLengthX = event.getX() - mLastTouchX;
            // getScrollX() �?于0，说明画布�?�移了
            // getScrollX() 大于0，说明画布左移了
            float endX = getScrollX() - scrollLengthX;

            if (scrollLengthX > 0) {    // 画布往�?�移动 -->

                // 注�?：这里的等�?��?能去除，�?�则会有闪动
                if (endX <= 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy((int) -scrollLengthX, 0);
                }

            } else if (scrollLengthX < 0) {                    // 画布往左移动  <--

                if (endX >= mCanvasWidth - mViewWidth) {     // 需�?考虑是�?��?�越界
                    scrollTo((int) (mCanvasWidth - mViewWidth), 0);
                } else {
                    scrollBy((int) -scrollLengthX, 0);
                }

            }
            mLastTouchX = event.getX();
        } else if (MotionEvent.ACTION_UP == event.getAction()) {
            // 计算当�?速度， 1000表示�?秒�?素数等
            mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);

            // 获�?�横�?�速度
            int velocityX = (int) mVelocityTracker.getXVelocity();

            // 速度�?大于最�?的速度值，�?开始滑动
            if (Math.abs(velocityX) > mMinimumVelocity) {

                int initX = getScrollX();

                int maxX = (int) (mCanvasWidth - mViewWidth);
                if (maxX > 0) {
                    mFling.start(initX, velocityX, initX, maxX);
                }
            }

            if (mVelocityTracker != null) {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }

        }

        return super.onTouchEvent(event);

    }

    /**
     * 画柱
     *
     * @param canvas
     */
    private void drawBar(Canvas canvas) {
        mBarPath.reset();
        for (int i = 0; i < mBarInfoList.size(); ++i) {

            float x = (i + 1) * mBarInterval;

            if (isInVisibleArea(x)) {
                mBarPath.moveTo(x, mTopSpacing);
                mBarPath.lineTo(x, mBarHeight + mTopSpacing);
            }

        }

        mPaint.setColor(mBarColor);
        mPaint.setStrokeWidth(mBarWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mBarPath, mPaint);
    }

    /**
     * 画数�?�点
     *
     * @param canvas
     */
    private void drawDot(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < mBarInfoList.size(); ++i) {
            float x = (i + 1) * mBarInterval;

            if (isInVisibleArea(x)) {

                BarInfo barInfo = mBarInfoList.get(i);

                float curBarDotY = (float) (mBarHeight * (1 - barInfo.percent * mAnimRate) + mTopSpacing);

                // 画外圆
                mPaint.setColor(mOuterDotColor);
                canvas.drawCircle(x, curBarDotY, mDotOuterRadius, mPaint);

                // 画内圆
                mPaint.setColor(mInnerDotColor);
                canvas.drawCircle(x, curBarDotY, mDotInnerRadius, mPaint);
            }

        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {

        float textY = mTopSpacing + mBarHeight + mBarTextSpacing + mDescTextSize / 2;

        for (int i = 0; i < mBarInfoList.size(); ++i) {
            float x = (i + 1) * mBarInterval;

            if (isInVisibleArea(x)) {
                BarInfo barInfo = mBarInfoList.get(i);

                mPaint.setColor(mTextColor);
                mPaint.setTextSize(mDescTextSize);
                mPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(barInfo.desc, x, textY, mPaint);
            }
        }
    }

    /**
     * 是�?�在�?�视的范围内
     *
     * @param x
     * @return true：在�?�视的范围内；false：�?在�?�视的范围内
     */
    private boolean isInVisibleArea(float x) {
        float dx = x - getScrollX();

        return -mBarInterval <= dx && dx <= mViewWidth + mBarInterval;
    }

    private int dip2px(Context context, float dipValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }

    /**
     * author : Jiang zinc
     * email : 56002982@qq.com
     * time : 2019/3/1 下�?�5:08
     * desc : 柱形图的数�?�
     * version :
     */
    public static final class BarInfo {
        /**
         * 该柱的�??述
         */
        private String desc;
        /**
         * 该柱的�?�比
         */
        private double percent;

        public BarInfo(String desc, double percent) {
            this.desc = desc;
            this.percent = percent;
        }
    }

    /**
     * 滚动线程
     */
    private class FlingRunnable implements Runnable {

        private Scroller mScroller;

        private int mInitX;
        private int mMinX;
        private int mMaxX;
        private int mVelocityX;

        FlingRunnable(Context context) {
            this.mScroller = new Scroller(context, null, false);
        }

        void start(int initX,
                   int velocityX,
                   int minX,
                   int maxX) {
            this.mInitX = initX;
            this.mVelocityX = velocityX;
            this.mMinX = minX;
            this.mMaxX = maxX;

            // 先�?�止上一次的滚动
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }

            // 开始 fling
            mScroller.fling(initX, 0, velocityX,
                    0, 0, maxX, 0, 0);
            post(this);
        }

        @Override
        public void run() {

            // 如果已�?结�?�，就�?�?进行
            if (!mScroller.computeScrollOffset()) {
                return;
            }

            // 计算�??移�?
            int currX = mScroller.getCurrX();
            int diffX = mInitX - currX;

            Log.i(TAG, "run: [currX: " + currX + "]\n"
                    + "[diffX: " + diffX + "]\n"
                    + "[initX: " + mInitX + "]\n"
                    + "[minX: " + mMinX + "]\n"
                    + "[maxX: " + mMaxX + "]\n"
                    + "[velocityX: " + mVelocityX + "]\n"
            );

            // 用于记录是�?�超出边界，如果已�?超出边界，则�?�?进行回调，�?�使滚动还没有完�?
            boolean isEnd = false;

            if (diffX != 0) {

                // 超出�?�边界，进行修正
                if (getScrollX() + diffX >= mCanvasWidth - mViewWidth) {
                    diffX = (int) (mCanvasWidth - mViewWidth - getScrollX());
                    isEnd = true;
                }

                // 超出左边界，进行修正
                if (getScrollX() <= 0) {
                    diffX = -getScrollX();
                    isEnd = true;
                }

                if (!mScroller.isFinished()) {
                    scrollBy(diffX, 0);
                }
                mInitX = currX;
            }

            if (!isEnd) {
                post(this);
            }
        }

        /**
         * 进行�?�止
         */
        void stop() {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
        }
    }
}
