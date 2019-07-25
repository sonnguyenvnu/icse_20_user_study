/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xui.widget.progress.loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.xuexiang.xui.R;
import com.xuexiang.xui.UIConfig;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.Utils;

/**
 * 旋转加载动画控件
 *
 * @author xuexiang
 * @since 2019/1/11 下�?�3:58
 */
public class RotateLoadingView extends View {

    /**
     * 默认圆弧的宽度
     */
    private static final int DEFAULT_ARC_WIDTH = 6;
    /**
     * 默认旋转度数的速度
     */
    private static final int DEFAULT_SPEED_OF_DEGREE = 5;
    /**
     * 默认圆弧的颜色
     */
    private static final int DEFAULT_ARC_COLOR = Color.parseColor("#299EE3");

    /**
     * 是�?�需�?画图
     */
    private boolean mIsDraw;
    /**
     * 是�?�是自动模�?，无需人工干预
     */
    private boolean mIsAutoMode = true;

    /**
     * 圆弧画笔
     */
    private Paint mArcPaint;
    /**
     * 圆弧画笔的粗细
     */
    private int mArcWidth;
    /**
     * 圆弧的颜色
     */
    private int mArcColor = DEFAULT_ARC_COLOR;

    private boolean mIsSingleArc;
    /**
     * 圆弧所在的椭圆对象
     */
    private RectF mLoadingRectF;
    /**
     * 上弧的度数
     */
    private int mTopDegree = 10;
    /**
     * 下弧的度数
     */
    private int mBottomDegree = 190;
    /**
     * 圆弧的角度
     */
    private float mArcDegree;
    /**
     * 圆弧的最大角度
     */
    private int mMaxArcDegree;
    /**
     * 上下弧度数的�?�化速度
     */
    private int mSpeedOfDegree;
    /**
     * 圆弧角度的�?�化速度
     */
    private float mSpeedOfArc;
    /**
     * 圆弧是�?�开始�?�大
     */
    private boolean mChangeBigger = true;

    /**
     * 图标
     */
    private Bitmap mIconBitmap;
    /**
     * 图标缩放比例
     */
    private float mIconScale;
    /**
     * 绘制图标的画笔
     */
    private Paint mIconPaint;

    public RotateLoadingView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public RotateLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public RotateLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
            mArcColor = typedArray.getColor(R.styleable.LoadingView_lv_color, DEFAULT_ARC_COLOR);
            mArcWidth = typedArray.getDimensionPixelSize(R.styleable.LoadingView_lv_width, DensityUtils.dp2px(getContext(), DEFAULT_ARC_WIDTH));
            mSpeedOfDegree = typedArray.getInt(R.styleable.LoadingView_lv_speed, DEFAULT_SPEED_OF_DEGREE);
            mSpeedOfArc = mSpeedOfDegree / 4;
            mIsDraw = mIsAutoMode = typedArray.getBoolean(R.styleable.LoadingView_lv_auto, true);
            mIsSingleArc = typedArray.getBoolean(R.styleable.LoadingView_lv_arc_single, false);
            mMaxArcDegree = mIsSingleArc ? 280 : 160;
            boolean hasIcon = typedArray.getBoolean(R.styleable.LoadingView_lv_has_icon, true);
            if (hasIcon) {
                Drawable icon = typedArray.getDrawable(R.styleable.LoadingView_lv_icon);
                if (icon != null) {
                    mIconBitmap = Utils.getBitmapFromDrawable(icon);
                } else {
                    Drawable appIcon = UIConfig.getInstance().getAppIcon();
                    if (appIcon != null) {
                        mIconBitmap = Utils.getBitmapFromDrawable(appIcon);
                    }
                }
                mIconScale = typedArray.getFloat(R.styleable.LoadingView_lv_icon_scale, 0.5F);
            }
            typedArray.recycle();
        }

        initPaint();
    }

    /**
     * �?始化画笔
     */
    private void initPaint() {
        mArcPaint = new Paint();
        mArcPaint.setColor(mArcColor);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mIconPaint = new Paint();
        mIconPaint.setColor(mArcColor);
        mIconPaint.setAntiAlias(true);
        mIconPaint.setStyle(Paint.Style.STROKE);
        mIconPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mArcDegree = 10;
        mLoadingRectF = new RectF(2 * mArcWidth, 2 * mArcWidth, width - 2 * mArcWidth, height - 2 * mArcWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!mIsDraw) {
            return;
        }

        drawArc(canvas);

        changeArcDegree();

        invalidate();
    }

    /**
     * 画圆弧
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        if (mIconBitmap != null) {
            canvas.drawBitmap(mIconBitmap, null, getIconBitmapRectF(mIconBitmap), mIconPaint);
        }

        drawTopArc(canvas);

        if (!mIsSingleArc) {
            drawBottomArc(canvas);
        }
    }

    /**
     * 画上弧
     * @param canvas
     */
    private void drawTopArc(Canvas canvas) {
        canvas.drawArc(mLoadingRectF, mTopDegree, mArcDegree, false, mArcPaint);
        mTopDegree += mSpeedOfDegree;
        if (mTopDegree > 360) {
            mTopDegree = mTopDegree - 360;
        }
    }

    /**
     * 画下弧
     * @param canvas
     */
    private void drawBottomArc(Canvas canvas) {
        canvas.drawArc(mLoadingRectF, mBottomDegree, mArcDegree, false, mArcPaint);
        mBottomDegree += mSpeedOfDegree;

        if (mBottomDegree > 360) {
            mBottomDegree = mBottomDegree - 360;
        }
    }

    /**
     * 改�?�圆弧的角度
     */
    private void changeArcDegree() {
        if (mChangeBigger) {
            if (mArcDegree < mMaxArcDegree) {
                mArcDegree += mSpeedOfArc;
            }
        } else {
            if (mArcDegree > mSpeedOfDegree) {
                mArcDegree -= 2 * mSpeedOfArc;
            }
        }
        if (mArcDegree >= mMaxArcDegree || mArcDegree <= mSpeedOfDegree) {
            mChangeBigger = !mChangeBigger;
        }
    }

    /**
     * 获�?�中心图标所在的区域
     * @return
     */
    public RectF getIconBitmapRectF(Bitmap bitmap) {
        float width = bitmap.getWidth(), height = bitmap.getHeight();
        if (width >= height) {
            height = getWidth() / width * height;
            width = getWidth();
        } else {
            width = getHeight() / height * width;
            height = getHeight();
        }
        float left = (getWidth() - width * mIconScale) / 2;
        float top = (getHeight() - height * mIconScale) / 2;
        float right = getWidth() - left;
        float bottom = getHeight() - top;
        return new RectF(left, top, right, bottom);
    }

    /**
     * 设置loading的图标
     * @param icon
     * @return
     */
    public RotateLoadingView setLoadingIcon(Drawable icon) {
        if (icon != null) {
            mIconBitmap = Utils.getBitmapFromDrawable(icon);
        }
        return this;
    }

    /**
     * 设置loading的图标
     * @param iconBitmap
     * @return
     */
    public RotateLoadingView setLoadingIcon(Bitmap iconBitmap) {
        mIconBitmap = iconBitmap;
        return this;
    }

    public RotateLoadingView setLoadingColor(int color) {
        mArcColor = color;
        return this;
    }

    public int getLoadingColor() {
        return mArcColor;
    }

    /**
     * 开始旋转
     */
    public void start() {
        if (!mIsAutoMode) {
            mIsDraw = true;
            invalidate();
        }
    }

    /**
     * �?�止旋转
     */
    public void stop() {
        if (!mIsAutoMode) {
            pause();
        }
    }

    /**
     * 暂�?�旋转并消失
     */
    private void pause() {
        mIsDraw = false;
        invalidate();
    }

    public boolean isStart() {
        return mIsDraw;
    }

    /**
     * 资�?释放
     */
    public void recycle() {
        pause();

        if (mIconBitmap != null) {
            mIconBitmap.recycle();
            mIconBitmap = null;
        }
        mArcPaint = null;
        mIconPaint = null;
        mLoadingRectF = null;
    }

}
