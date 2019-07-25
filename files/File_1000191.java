package com.aigestudio.wheelpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 滚轮选择器
 * <p>
 * WheelPicker
 *
 * @author AigeStudio 2015-12-12
 * @author AigeStudio 2016-06-17
 *         更新项目结构
 *         <p>
 *         New project structure
 * @version 1.1.0
 */
public class WheelPicker extends View implements IDebug, IWheelPicker, Runnable {
    /**
     * 滚动状�?标识值
     *
     * @see OnWheelChangeListener#onWheelScrollStateChanged(int)
     */
    public static final int SCROLL_STATE_IDLE = 0, SCROLL_STATE_DRAGGING = 1,
            SCROLL_STATE_SCROLLING = 2;

    /**
     * 数�?�项对�?方�?标识值
     *
     * @see #setItemAlign(int)
     */
    public static final int ALIGN_CENTER = 0, ALIGN_LEFT = 1, ALIGN_RIGHT = 2;

    private static final String TAG = WheelPicker.class.getSimpleName();

    private final Handler mHandler = new Handler();

    private Paint mPaint;
    private Scroller mScroller;
    private VelocityTracker mTracker;
    /**
     * Determines whether the current scrolling animation is triggered by touchEvent or setSelectedItemPosition.
     * User added eventListeners will only be fired after touchEvents.
     */
    private boolean isTouchTriggered;

    /**
     * 相关监�?�器
     *
     * @see OnWheelChangeListener,OnItemSelectedListener
     */
    private OnItemSelectedListener mOnItemSelectedListener;
    private OnWheelChangeListener mOnWheelChangeListener;

    private Rect mRectDrawn;
    private Rect mRectIndicatorHead, mRectIndicatorFoot;
    private Rect mRectCurrentItem;

    private Camera mCamera;
    private Matrix mMatrixRotate, mMatrixDepth;

    /**
     * 数�?��?
     */
    private List mData;

    /**
     * 最宽的文本
     *
     * @see #setMaximumWidthText(String)
     */
    private String mMaxWidthText;

    /**
     * 滚轮选择器中�?��?的数�?�项数�?和滚轮选择器将会绘制的数�?�项数�?
     *
     * @see #setVisibleItemCount(int)
     */
    private int mVisibleItemCount, mDrawnItemCount;

    /**
     * 滚轮选择器将会绘制的Item数�?的一�?�
     */
    private int mHalfDrawnItemCount;

    /**
     * �?�个文本最大宽高
     */
    private int mTextMaxWidth, mTextMaxHeight;

    /**
     * 数�?�项文本颜色以�?�被选中的数�?�项文本颜色
     *
     * @see #setItemTextColor(int)
     * @see #setSelectedItemTextColor(int)
     */
    private int mItemTextColor, mSelectedItemTextColor;

    /**
     * 数�?�项文本尺寸
     *
     * @see #setItemTextSize(int)
     */
    private int mItemTextSize;

    /**
     * 指示器尺寸
     *
     * @see #setIndicatorSize(int)
     */
    private int mIndicatorSize;

    /**
     * 指示器颜色
     *
     * @see #setIndicatorColor(int)
     */
    private int mIndicatorColor;

    /**
     * 幕布颜色
     *
     * @see #setCurtainColor(int)
     */
    private int mCurtainColor;

    /**
     * 数�?�项之间间�?
     *
     * @see #setItemSpace(int)
     */
    private int mItemSpace;

    /**
     * 数�?�项对�?方�?
     *
     * @see #setItemAlign(int)
     */
    private int mItemAlign;

    /**
     * 滚轮选择器�?�个数�?�项高度以�?��?�个数�?�项一�?�的高度
     */
    private int mItemHeight, mHalfItemHeight;

    /**
     * 滚轮选择器内容区域高度的一�?�
     */
    private int mHalfWheelHeight;

    /**
     * 当�?被选中的数�?�项所显示的数�?�在数�?��?中的�?置
     *
     * @see #setSelectedItemPosition(int)
     */
    private int mSelectedItemPosition;

    /**
     * 当�?被选中的数�?�项所显示的数�?�在数�?��?中的�?置
     *
     * @see #getCurrentItemPosition()
     */
    private int mCurrentItemPosition;

    /**
     * 滚轮滑动时�?�以滑动到的最�?/最大的Y�??标
     */
    private int mMinFlingY, mMaxFlingY;

    /**
     * 滚轮滑动时的最�?/最大速度
     */
    private int mMinimumVelocity = 50, mMaximumVelocity = 8000;

    /**
     * 滚轮选择器中心�??标
     */
    private int mWheelCenterX, mWheelCenterY;

    /**
     * 滚轮选择器绘制中心�??标
     */
    private int mDrawnCenterX, mDrawnCenterY;

    /**
     * 滚轮选择器视图区域在Y轴方�?�上的�??移值
     */
    private int mScrollOffsetY;

    /**
     * 滚轮选择器中最宽或最高的文本在数�?��?中的�?置
     */
    private int mTextMaxWidthPosition;

    /**
     * 用户手指上一次触摸事件�?�生时事件Y�??标
     */
    private int mLastPointY;

    /**
     * 手指触摸�?幕时事件点的Y�??标
     */
    private int mDownPointY;

    /**
     * 点击与触摸的切�?�阀值
     */
    private int mTouchSlop = 8;

    /**
     * 滚轮选择器的�?一个数�?�项文本是�?�拥有相�?�的宽度
     *
     * @see #setSameWidth(boolean)
     */
    private boolean hasSameWidth;

    /**
     * 是�?�显示指示器
     *
     * @see #setIndicator(boolean)
     */
    private boolean hasIndicator;

    /**
     * 是�?�显示幕布
     *
     * @see #setCurtain(boolean)
     */
    private boolean hasCurtain;

    /**
     * 是�?�显示空气感效果
     *
     * @see #setAtmospheric(boolean)
     */
    private boolean hasAtmospheric;

    /**
     * 数�?�是�?�循环展示
     *
     * @see #setCyclic(boolean)
     */
    private boolean isCyclic;

    /**
     * 滚轮是�?�为�?�曲效果
     *
     * @see #setCurved(boolean)
     */
    private boolean isCurved;

    /**
     * 是�?�为点击模�?
     */
    private boolean isClick;

    /**
     * 是�?�为强制结�?�滑动
     */
    private boolean isForceFinishScroll;

    /**
     * Font typeface path from assets
     */
    private String fontPath;

    private boolean isDebug;

    public WheelPicker(Context context) {
        this(context, null);
    }

    public WheelPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WheelPicker);
        int idData = a.getResourceId(R.styleable.WheelPicker_wheel_data, 0);
        mData = Arrays.asList(getResources()
                .getStringArray(idData == 0 ? R.array.WheelArrayDefault : idData));
        mItemTextSize = a.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_text_size,
                getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        mVisibleItemCount = a.getInt(R.styleable.WheelPicker_wheel_visible_item_count, 7);
        mSelectedItemPosition = a.getInt(R.styleable.WheelPicker_wheel_selected_item_position, 0);
        hasSameWidth = a.getBoolean(R.styleable.WheelPicker_wheel_same_width, false);
        mTextMaxWidthPosition =
                a.getInt(R.styleable.WheelPicker_wheel_maximum_width_text_position, -1);
        mMaxWidthText = a.getString(R.styleable.WheelPicker_wheel_maximum_width_text);
        mSelectedItemTextColor = a.getColor
                (R.styleable.WheelPicker_wheel_selected_item_text_color, -1);
        mItemTextColor = a.getColor(R.styleable.WheelPicker_wheel_item_text_color, 0xFF888888);
        mItemSpace = a.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_space,
                getResources().getDimensionPixelSize(R.dimen.WheelItemSpace));
        isCyclic = a.getBoolean(R.styleable.WheelPicker_wheel_cyclic, false);
        hasIndicator = a.getBoolean(R.styleable.WheelPicker_wheel_indicator, false);
        mIndicatorColor = a.getColor(R.styleable.WheelPicker_wheel_indicator_color, 0xFFEE3333);
        mIndicatorSize = a.getDimensionPixelSize(R.styleable.WheelPicker_wheel_indicator_size,
                getResources().getDimensionPixelSize(R.dimen.WheelIndicatorSize));
        hasCurtain = a.getBoolean(R.styleable.WheelPicker_wheel_curtain, false);
        mCurtainColor = a.getColor(R.styleable.WheelPicker_wheel_curtain_color, 0x88FFFFFF);
        hasAtmospheric = a.getBoolean(R.styleable.WheelPicker_wheel_atmospheric, false);
        isCurved = a.getBoolean(R.styleable.WheelPicker_wheel_curved, false);
        mItemAlign = a.getInt(R.styleable.WheelPicker_wheel_item_align, ALIGN_CENTER);
        fontPath = a.getString(R.styleable.WheelPicker_wheel_font_path);
        a.recycle();

        // �?��?数�?�项改�?��?�更新与之相关的�?�数
        // Update relevant parameters when the count of visible item changed
        updateVisibleItemCount();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mPaint.setTextSize(mItemTextSize);

        if (fontPath != null) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            setTypeface(typeface);
        }

        // 更新文本对�?方�?
        // Update alignment of text
        updateItemTextAlign();

        // 计算文本尺寸
        // Correct sizes of text
        computeTextSize();

        mScroller = new Scroller(getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            ViewConfiguration conf = ViewConfiguration.get(getContext());
            mMinimumVelocity = conf.getScaledMinimumFlingVelocity();
            mMaximumVelocity = conf.getScaledMaximumFlingVelocity();
            mTouchSlop = conf.getScaledTouchSlop();
        }
        mRectDrawn = new Rect();

        mRectIndicatorHead = new Rect();
        mRectIndicatorFoot = new Rect();

        mRectCurrentItem = new Rect();

        mCamera = new Camera();

        mMatrixRotate = new Matrix();
        mMatrixDepth = new Matrix();
    }

    private void updateVisibleItemCount() {
        if (mVisibleItemCount < 2)
            throw new ArithmeticException("Wheel's visible item count can not be less than 2!");

        // 确�?滚轮选择器�?��?数�?�项数�?为奇数
        // Be sure count of visible item is odd number
        if (mVisibleItemCount % 2 == 0)
            mVisibleItemCount += 1;
        mDrawnItemCount = mVisibleItemCount + 2;
        mHalfDrawnItemCount = mDrawnItemCount / 2;
    }

    private void computeTextSize() {
        mTextMaxWidth = mTextMaxHeight = 0;
        if (hasSameWidth) {
            mTextMaxWidth = (int) mPaint.measureText(String.valueOf(mData.get(0)));
        } else if (isPosInRang(mTextMaxWidthPosition)) {
            mTextMaxWidth = (int) mPaint.measureText
                    (String.valueOf(mData.get(mTextMaxWidthPosition)));
        } else if (!TextUtils.isEmpty(mMaxWidthText)) {
            mTextMaxWidth = (int) mPaint.measureText(mMaxWidthText);
        } else {
            for (Object obj : mData) {
                String text = String.valueOf(obj);
                int width = (int) mPaint.measureText(text);
                mTextMaxWidth = Math.max(mTextMaxWidth, width);
            }
        }
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        mTextMaxHeight = (int) (metrics.bottom - metrics.top);
    }

    private void updateItemTextAlign() {
        switch (mItemAlign) {
            case ALIGN_LEFT:
                mPaint.setTextAlign(Paint.Align.LEFT);
                break;
            case ALIGN_RIGHT:
                mPaint.setTextAlign(Paint.Align.RIGHT);
                break;
            default:
                mPaint.setTextAlign(Paint.Align.CENTER);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算原始内容尺寸
        // Correct sizes of original content
        int resultWidth = mTextMaxWidth;
        int resultHeight = mTextMaxHeight * mVisibleItemCount + mItemSpace * (mVisibleItemCount - 1);

        // 如果开�?�弯曲效果则需�?�?新计算弯曲�?�的尺寸
        // Correct view sizes again if curved is enable
        if (isCurved) {
            resultHeight = (int) (2 * resultHeight / Math.PI);
        }
        if (isDebug)
            Log.i(TAG, "Wheel's content size is (" + resultWidth + ":" + resultHeight + ")");

        // 考虑内边�?对尺寸的影�?
        // Consideration padding influence the view sizes
        resultWidth += getPaddingLeft() + getPaddingRight();
        resultHeight += getPaddingTop() + getPaddingBottom();
        if (isDebug)
            Log.i(TAG, "Wheel's size is (" + resultWidth + ":" + resultHeight + ")");

        // 考虑父容器对尺寸的影�?
        // Consideration sizes of parent can influence the view sizes
        resultWidth = measureSize(modeWidth, sizeWidth, resultWidth);
        resultHeight = measureSize(modeHeight, sizeHeight, resultHeight);

        setMeasuredDimension(resultWidth, resultHeight);
    }

    private int measureSize(int mode, int sizeExpect, int sizeActual) {
        int realSize;
        if (mode == MeasureSpec.EXACTLY) {
            realSize = sizeExpect;
        } else {
            realSize = sizeActual;
            if (mode == MeasureSpec.AT_MOST)
                realSize = Math.min(realSize, sizeExpect);
        }
        return realSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        // 设置内容区域
        // Set content region
        mRectDrawn.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());
        if (isDebug)
            Log.i(TAG, "Wheel's drawn rect size is (" + mRectDrawn.width() + ":" +
                    mRectDrawn.height() + ") and location is (" + mRectDrawn.left + ":" +
                    mRectDrawn.top + ")");

        // 获�?�内容区域中心�??标
        // Get the center coordinates of content region
        mWheelCenterX = mRectDrawn.centerX();
        mWheelCenterY = mRectDrawn.centerY();

        // 计算数�?�项绘制中心
        // Correct item drawn center
        computeDrawnCenter();

        mHalfWheelHeight = mRectDrawn.height() / 2;

        mItemHeight = mRectDrawn.height() / mVisibleItemCount;
        mHalfItemHeight = mItemHeight / 2;

        // �?始化滑动最大�??标
        // Initialize fling max Y-coordinates
        computeFlingLimitY();

        // 计算指示器绘制区域
        // Correct region of indicator
        computeIndicatorRect();

        // 计算当�?选中的数�?�项区域
        // Correct region of current select item
        computeCurrentItemRect();
    }

    private void computeDrawnCenter() {
        switch (mItemAlign) {
            case ALIGN_LEFT:
                mDrawnCenterX = mRectDrawn.left;
                break;
            case ALIGN_RIGHT:
                mDrawnCenterX = mRectDrawn.right;
                break;
            default:
                mDrawnCenterX = mWheelCenterX;
                break;
        }
        mDrawnCenterY = (int) (mWheelCenterY - ((mPaint.ascent() + mPaint.descent()) / 2));
    }

    private void computeFlingLimitY() {
        int currentItemOffset = mSelectedItemPosition * mItemHeight;
        mMinFlingY = isCyclic ? Integer.MIN_VALUE :
                -mItemHeight * (mData.size() - 1) + currentItemOffset;
        mMaxFlingY = isCyclic ? Integer.MAX_VALUE : currentItemOffset;
    }

    private void computeIndicatorRect() {
        if (!hasIndicator) return;
        int halfIndicatorSize = mIndicatorSize / 2;
        int indicatorHeadCenterY = mWheelCenterY + mHalfItemHeight;
        int indicatorFootCenterY = mWheelCenterY - mHalfItemHeight;
        mRectIndicatorHead.set(mRectDrawn.left, indicatorHeadCenterY - halfIndicatorSize,
                mRectDrawn.right, indicatorHeadCenterY + halfIndicatorSize);
        mRectIndicatorFoot.set(mRectDrawn.left, indicatorFootCenterY - halfIndicatorSize,
                mRectDrawn.right, indicatorFootCenterY + halfIndicatorSize);
    }

    private void computeCurrentItemRect() {
        if (!hasCurtain && mSelectedItemTextColor == -1) return;
        mRectCurrentItem.set(mRectDrawn.left, mWheelCenterY - mHalfItemHeight, mRectDrawn.right,
                mWheelCenterY + mHalfItemHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != mOnWheelChangeListener)
            mOnWheelChangeListener.onWheelScrolled(mScrollOffsetY);
        if(mData.size() == 0)
            return;
        int drawnDataStartPos = -mScrollOffsetY / mItemHeight - mHalfDrawnItemCount;
        for (int drawnDataPos = drawnDataStartPos + mSelectedItemPosition,
             drawnOffsetPos = -mHalfDrawnItemCount;
             drawnDataPos < drawnDataStartPos + mSelectedItemPosition + mDrawnItemCount;
             drawnDataPos++, drawnOffsetPos++) {
            String data = "";
            if (isCyclic) {
                int actualPos = drawnDataPos % mData.size();
                actualPos = actualPos < 0 ? (actualPos + mData.size()) : actualPos;
                data = String.valueOf(mData.get(actualPos));
            } else {
                if (isPosInRang(drawnDataPos))
                    data = String.valueOf(mData.get(drawnDataPos));
            }
            mPaint.setColor(mItemTextColor);
            mPaint.setStyle(Paint.Style.FILL);
            int mDrawnItemCenterY = mDrawnCenterY + (drawnOffsetPos * mItemHeight) +
                    mScrollOffsetY % mItemHeight;

            int distanceToCenter = 0;
            if (isCurved) {
                // 计算数�?�项绘制中心�?离滚轮中心的�?离比率
                // Correct ratio of item's drawn center to wheel center
                float ratio = (mDrawnCenterY - Math.abs(mDrawnCenterY - mDrawnItemCenterY) -
                        mRectDrawn.top) * 1.0F / (mDrawnCenterY - mRectDrawn.top);

                // 计算�?��?
                // Correct unit
                int unit = 0;
                if (mDrawnItemCenterY > mDrawnCenterY)
                    unit = 1;
                else if (mDrawnItemCenterY < mDrawnCenterY)
                    unit = -1;

                float degree = (-(1 - ratio) * 90 * unit);
                if (degree < -90) degree = -90;
                if (degree > 90) degree = 90;
                distanceToCenter = computeSpace((int) degree);

                int transX = mWheelCenterX;
                switch (mItemAlign) {
                    case ALIGN_LEFT:
                        transX = mRectDrawn.left;
                        break;
                    case ALIGN_RIGHT:
                        transX = mRectDrawn.right;
                        break;
                }
                int transY = mWheelCenterY - distanceToCenter;

                mCamera.save();
                mCamera.rotateX(degree);
                mCamera.getMatrix(mMatrixRotate);
                mCamera.restore();
                mMatrixRotate.preTranslate(-transX, -transY);
                mMatrixRotate.postTranslate(transX, transY);

                mCamera.save();
                mCamera.translate(0, 0, computeDepth((int) degree));
                mCamera.getMatrix(mMatrixDepth);
                mCamera.restore();
                mMatrixDepth.preTranslate(-transX, -transY);
                mMatrixDepth.postTranslate(transX, transY);

                mMatrixRotate.postConcat(mMatrixDepth);
            }
            if (hasAtmospheric) {
                int alpha = (int) ((mDrawnCenterY - Math.abs(mDrawnCenterY - mDrawnItemCenterY)) *
                        1.0F / mDrawnCenterY * 255);
                alpha = alpha < 0 ? 0 : alpha;
                mPaint.setAlpha(alpha);
            }
            // 根�?��?�曲与�?�计算数�?�项绘制Y方�?�中心�??标
            // Correct item's drawn centerY base on curved state
            int drawnCenterY = isCurved ? mDrawnCenterY - distanceToCenter : mDrawnItemCenterY;

            // 判断是�?�需�?为当�?数�?�项绘制�?�?�颜色
            // Judges need to draw different color for current item or not
            if (mSelectedItemTextColor != -1) {
                canvas.save();
                if (isCurved) canvas.concat(mMatrixRotate);
                canvas.clipRect(mRectCurrentItem, Region.Op.DIFFERENCE);
                canvas.drawText(data, mDrawnCenterX, drawnCenterY, mPaint);
                canvas.restore();

                mPaint.setColor(mSelectedItemTextColor);
                canvas.save();
                if (isCurved) canvas.concat(mMatrixRotate);
                canvas.clipRect(mRectCurrentItem);
                canvas.drawText(data, mDrawnCenterX, drawnCenterY, mPaint);
                canvas.restore();
            } else {
                canvas.save();
                canvas.clipRect(mRectDrawn);
                if (isCurved) canvas.concat(mMatrixRotate);
                canvas.drawText(data, mDrawnCenterX, drawnCenterY, mPaint);
                canvas.restore();
            }
            if (isDebug) {
                canvas.save();
                canvas.clipRect(mRectDrawn);
                mPaint.setColor(0xFFEE3333);
                int lineCenterY = mWheelCenterY + (drawnOffsetPos * mItemHeight);
                canvas.drawLine(mRectDrawn.left, lineCenterY, mRectDrawn.right, lineCenterY,
                        mPaint);
                mPaint.setColor(0xFF3333EE);
                mPaint.setStyle(Paint.Style.STROKE);
                int top = lineCenterY - mHalfItemHeight;
                canvas.drawRect(mRectDrawn.left, top, mRectDrawn.right, top + mItemHeight, mPaint);
                canvas.restore();
            }
        }
        // 是�?�需�?绘制幕布
        // Need to draw curtain or not
        if (hasCurtain) {
            mPaint.setColor(mCurtainColor);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mRectCurrentItem, mPaint);
        }
        // 是�?�需�?绘制指示器
        // Need to draw indicator or not
        if (hasIndicator) {
            mPaint.setColor(mIndicatorColor);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(mRectIndicatorHead, mPaint);
            canvas.drawRect(mRectIndicatorFoot, mPaint);
        }
        if (isDebug) {
            mPaint.setColor(0x4433EE33);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, getPaddingLeft(), getHeight(), mPaint);
            canvas.drawRect(0, 0, getWidth(), getPaddingTop(), mPaint);
            canvas.drawRect(getWidth() - getPaddingRight(), 0, getWidth(), getHeight(), mPaint);
            canvas.drawRect(0, getHeight() - getPaddingBottom(), getWidth(), getHeight(), mPaint);
        }
    }

    private boolean isPosInRang(int position) {
        return position >= 0 && position < mData.size();
    }

    private int computeSpace(int degree) {
        return (int) (Math.sin(Math.toRadians(degree)) * mHalfWheelHeight);
    }

    private int computeDepth(int degree) {
        return (int) (mHalfWheelHeight - Math.cos(Math.toRadians(degree)) * mHalfWheelHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouchTriggered = true;
                if (null != getParent())
                    getParent().requestDisallowInterceptTouchEvent(true);
                if (null == mTracker)
                    mTracker = VelocityTracker.obtain();
                else
                    mTracker.clear();
                mTracker.addMovement(event);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    isForceFinishScroll = true;
                }
                mDownPointY = mLastPointY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mDownPointY - event.getY()) < mTouchSlop) {
                    isClick = true;
                    break;
                }
                isClick = false;
                mTracker.addMovement(event);
                if (null != mOnWheelChangeListener)
                    mOnWheelChangeListener.onWheelScrollStateChanged(SCROLL_STATE_DRAGGING);

                // 滚动内容
                // Scroll WheelPicker's content
                float move = event.getY() - mLastPointY;
                if (Math.abs(move) < 1) break;
                mScrollOffsetY += move;
                mLastPointY = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (null != getParent())
                    getParent().requestDisallowInterceptTouchEvent(false);
                if (isClick && !isForceFinishScroll) break;
                mTracker.addMovement(event);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT)
                    mTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                else
                    mTracker.computeCurrentVelocity(1000);

                // 根�?�速度判断是该滚动还是滑动
                // Judges the WheelPicker is scroll or fling base on current velocity
                isForceFinishScroll = false;
                int velocity = (int) mTracker.getYVelocity();
                if (Math.abs(velocity) > mMinimumVelocity) {
                    mScroller.fling(0, mScrollOffsetY, 0, velocity, 0, 0, mMinFlingY, mMaxFlingY);
                    mScroller.setFinalY(mScroller.getFinalY() +
                            computeDistanceToEndPoint(mScroller.getFinalY() % mItemHeight));
                } else {
                    mScroller.startScroll(0, mScrollOffsetY, 0,
                            computeDistanceToEndPoint(mScrollOffsetY % mItemHeight));
                }
                // 校正�??标
                // Correct coordinates
                if (!isCyclic)
                    if (mScroller.getFinalY() > mMaxFlingY)
                        mScroller.setFinalY(mMaxFlingY);
                    else if (mScroller.getFinalY() < mMinFlingY)
                        mScroller.setFinalY(mMinFlingY);
                mHandler.post(this);
                if (null != mTracker) {
                    mTracker.recycle();
                    mTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (null != getParent())
                    getParent().requestDisallowInterceptTouchEvent(false);
                if (null != mTracker) {
                    mTracker.recycle();
                    mTracker = null;
                }
                break;
        }
        return true;
    }

    private int computeDistanceToEndPoint(int remainder) {
        if (Math.abs(remainder) > mHalfItemHeight)
            if (mScrollOffsetY < 0)
                return -mItemHeight - remainder;
            else
                return mItemHeight - remainder;
        else
            return -remainder;
    }

    @Override
    public void run() {
        if (null == mData || mData.size() == 0) return;
        if (mScroller.isFinished() && !isForceFinishScroll) {
            if (mItemHeight == 0) return;
            int position = (-mScrollOffsetY / mItemHeight + mSelectedItemPosition) % mData.size();
            position = position < 0 ? position + mData.size() : position;
            if (isDebug)
                Log.i(TAG, position + ":" + mData.get(position) + ":" + mScrollOffsetY);
            mCurrentItemPosition = position;
            if (null != mOnItemSelectedListener && isTouchTriggered)
                mOnItemSelectedListener.onItemSelected(this, mData.get(position), position);
            if (null != mOnWheelChangeListener && isTouchTriggered) {
                mOnWheelChangeListener.onWheelSelected(position);
                mOnWheelChangeListener.onWheelScrollStateChanged(SCROLL_STATE_IDLE);
            }
        }
        if (mScroller.computeScrollOffset()) {
            if (null != mOnWheelChangeListener)
                mOnWheelChangeListener.onWheelScrollStateChanged(SCROLL_STATE_SCROLLING);
            mScrollOffsetY = mScroller.getCurrY();
            postInvalidate();
            mHandler.postDelayed(this, 16);
        }
    }

    @Override
    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    public int getVisibleItemCount() {
        return mVisibleItemCount;
    }

    @Override
    public void setVisibleItemCount(int count) {
        mVisibleItemCount = count;
        updateVisibleItemCount();
        requestLayout();
    }

    @Override
    public boolean isCyclic() {
        return isCyclic;
    }

    @Override
    public void setCyclic(boolean isCyclic) {
        this.isCyclic = isCyclic;
        computeFlingLimitY();
        invalidate();
    }

    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mOnItemSelectedListener = listener;
    }

    @Override
    public int getSelectedItemPosition() {
        return mSelectedItemPosition;
    }

    @Override
    public void setSelectedItemPosition(int position) {
        setSelectedItemPosition(position, true);
    }

    public void setSelectedItemPosition(int position, final boolean animated) {
      isTouchTriggered = false;
      if (animated && mScroller.isFinished()) { // We go non-animated regardless of "animated" parameter if scroller is in motion
        int length = getData().size();
        int itemDifference = position - mCurrentItemPosition;
        if (itemDifference == 0)
          return;
        if (isCyclic && Math.abs(itemDifference) > (length / 2)) { // Find the shortest path if it's cyclic
          itemDifference += (itemDifference > 0) ? -length : length;
        }
        mScroller.startScroll(0, mScroller.getCurrY(), 0, (-itemDifference) * mItemHeight);
        mHandler.post(this);
      } else {
        if (!mScroller.isFinished())
          mScroller.abortAnimation();
        position = Math.min(position, mData.size() - 1);
        position = Math.max(position, 0);
        mSelectedItemPosition = position;
        mCurrentItemPosition = position;
        mScrollOffsetY = 0;
        computeFlingLimitY();
        requestLayout();
        invalidate();
      }
    }

    @Override
    public int getCurrentItemPosition() {
        return mCurrentItemPosition;
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public void setData(List data) {
        if (null == data)
            throw new NullPointerException("WheelPicker's data can not be null!");
        mData = data;

        // �?置�?置
        if (mSelectedItemPosition > data.size() - 1 || mCurrentItemPosition > data.size() - 1) {
            mSelectedItemPosition = mCurrentItemPosition = data.size() - 1;
        } else {
            mSelectedItemPosition = mCurrentItemPosition;
        }
        mScrollOffsetY = 0;
        computeTextSize();
        computeFlingLimitY();
        requestLayout();
        invalidate();
    }

    public void setSameWidth(boolean hasSameWidth) {
        this.hasSameWidth = hasSameWidth;
        computeTextSize();
        requestLayout();
        invalidate();
    }

    @Override
    public boolean hasSameWidth() {
        return hasSameWidth;
    }

    @Override
    public void setOnWheelChangeListener(OnWheelChangeListener listener) {
        mOnWheelChangeListener = listener;
    }

    @Override
    public String getMaximumWidthText() {
        return mMaxWidthText;
    }

    @Override
    public void setMaximumWidthText(String text) {
        if (null == text)
            throw new NullPointerException("Maximum width text can not be null!");
        mMaxWidthText = text;
        computeTextSize();
        requestLayout();
        invalidate();
    }

    @Override
    public int getMaximumWidthTextPosition() {
        return mTextMaxWidthPosition;
    }

    @Override
    public void setMaximumWidthTextPosition(int position) {
        if (!isPosInRang(position))
            throw new ArrayIndexOutOfBoundsException("Maximum width text Position must in [0, " +
                    mData.size() + "), but current is " + position);
        mTextMaxWidthPosition = position;
        computeTextSize();
        requestLayout();
        invalidate();
    }

    @Override
    public int getSelectedItemTextColor() {
        return mSelectedItemTextColor;
    }

    @Override
    public void setSelectedItemTextColor(int color) {
        mSelectedItemTextColor = color;
        computeCurrentItemRect();
        invalidate();
    }

    @Override
    public int getItemTextColor() {
        return mItemTextColor;
    }

    @Override
    public void setItemTextColor(int color) {
        mItemTextColor = color;
        invalidate();
    }

    @Override
    public int getItemTextSize() {
        return mItemTextSize;
    }

    @Override
    public void setItemTextSize(int size) {
        mItemTextSize = size;
        mPaint.setTextSize(mItemTextSize);
        computeTextSize();
        requestLayout();
        invalidate();
    }

    @Override
    public int getItemSpace() {
        return mItemSpace;
    }

    @Override
    public void setItemSpace(int space) {
        mItemSpace = space;
        requestLayout();
        invalidate();
    }

    @Override
    public void setIndicator(boolean hasIndicator) {
        this.hasIndicator = hasIndicator;
        computeIndicatorRect();
        invalidate();
    }

    @Override
    public boolean hasIndicator() {
        return hasIndicator;
    }

    @Override
    public int getIndicatorSize() {
        return mIndicatorSize;
    }

    @Override
    public void setIndicatorSize(int size) {
        mIndicatorSize = size;
        computeIndicatorRect();
        invalidate();
    }

    @Override
    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    @Override
    public void setIndicatorColor(int color) {
        mIndicatorColor = color;
        invalidate();
    }

    @Override
    public void setCurtain(boolean hasCurtain) {
        this.hasCurtain = hasCurtain;
        computeCurrentItemRect();
        invalidate();
    }

    @Override
    public boolean hasCurtain() {
        return hasCurtain;
    }

    @Override
    public int getCurtainColor() {
        return mCurtainColor;
    }

    @Override
    public void setCurtainColor(int color) {
        mCurtainColor = color;
        invalidate();
    }

    @Override
    public void setAtmospheric(boolean hasAtmospheric) {
        this.hasAtmospheric = hasAtmospheric;
        invalidate();
    }

    @Override
    public boolean hasAtmospheric() {
        return hasAtmospheric;
    }

    @Override
    public boolean isCurved() {
        return isCurved;
    }

    @Override
    public void setCurved(boolean isCurved) {
        this.isCurved = isCurved;
        requestLayout();
        invalidate();
    }

    @Override
    public int getItemAlign() {
        return mItemAlign;
    }

    @Override
    public void setItemAlign(int align) {
        mItemAlign = align;
        updateItemTextAlign();
        computeDrawnCenter();
        invalidate();
    }

    @Override
    public Typeface getTypeface() {
        if (null != mPaint)
            return mPaint.getTypeface();
        return null;
    }

    @Override
    public void setTypeface(Typeface tf) {
        if (null != mPaint)
            mPaint.setTypeface(tf);
        computeTextSize();
        requestLayout();
        invalidate();
    }

    /**
     * 滚轮选择器Item项被选中时监�?�接�?�
     *
     * @author AigeStudio 2016-06-17
     *         新项目结构
     * @version 1.1.0
     */
    public interface OnItemSelectedListener {
        /**
         * 当滚轮选择器数�?�项被选中时回调该方法
         * 滚动选择器滚动�?�止�?�会回调该方法并将当�?选中的数�?�和数�?�在数�?�列表中对应的�?置返回
         *
         * @param picker   滚轮选择器
         * @param data     当�?选中的数�?�
         * @param position 当�?选中的数�?�在数�?�列表中的�?置
         */
        void onItemSelected(WheelPicker picker, Object data, int position);
    }

    /**
     * 滚轮选择器滚动时监�?�接�?�
     *
     * @author AigeStudio 2016-06-17
     *         新项目结构
     *         <p>
     *         New project structure
     * @since 2016-06-17
     */
    public interface OnWheelChangeListener {
        /**
         * 当滚轮选择器滚动时回调该方法
         * 滚轮选择器滚动时会将当�?滚动�?置与滚轮�?始�?置之间的�??移�?离返回，该�??移�?离有正负之分，正值表示
         * 滚轮正在往上滚动，负值则表示滚轮正在往下滚动
         * <p>
         * Invoke when WheelPicker scroll stopped
         * WheelPicker will return a distance offset which between current scroll position and
         * initial position, this offset is a positive or a negative, positive means WheelPicker is
         * scrolling from bottom to top, negative means WheelPicker is scrolling from top to bottom
         *
         * @param offset 当�?滚轮滚动�?离上一次滚轮滚动�?�止�?��??移的�?离
         *               <p>
         *               Distance offset which between current scroll position and initial position
         */
        void onWheelScrolled(int offset);

        /**
         * 当滚轮选择器�?�止�?�回调该方法
         * 滚轮选择器�?�止�?�会回调该方法并将当�?选中的数�?�项在数�?�列表中的�?置返回
         * <p>
         * Invoke when WheelPicker scroll stopped
         * This method will be called when WheelPicker stop and return current selected item data's
         * position in list
         *
         * @param position 当�?选中的数�?�项在数�?�列表中的�?置
         *                 <p>
         *                 Current selected item data's position in list
         */
        void onWheelSelected(int position);

        /**
         * 当滚轮选择器滚动状�?改�?�时回调该方法
         * 滚动选择器的状�?总是会在�?�止�?拖动和滑动三者之间切�?�，当状�?改�?�时回调该方法
         * <p>
         * Invoke when WheelPicker's scroll state changed
         * The state of WheelPicker always between idle, dragging, and scrolling, this method will
         * be called when they switch
         *
         * @param state 滚轮选择器滚动状�?，其值仅�?�能为下列之一
         *              {@link WheelPicker#SCROLL_STATE_IDLE}
         *              表示滚动选择器处于�?�止状�?
         *              {@link WheelPicker#SCROLL_STATE_DRAGGING}
         *              表示滚动选择器处于拖动状�?
         *              {@link WheelPicker#SCROLL_STATE_SCROLLING}
         *              表示滚动选择器处于滑动状�?
         *              <p>
         *              State of WheelPicker, only one of the following
         *              {@link WheelPicker#SCROLL_STATE_IDLE}
         *              Express WheelPicker in state of idle
         *              {@link WheelPicker#SCROLL_STATE_DRAGGING}
         *              Express WheelPicker in state of dragging
         *              {@link WheelPicker#SCROLL_STATE_SCROLLING}
         *              Express WheelPicker in state of scrolling
         */
        void onWheelScrollStateChanged(int state);
    }
}
