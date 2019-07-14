package com.vondear.rxui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.vondear.rxtool.RxDataTool;
import com.vondear.rxui.R;


/**
 * 弧形进度�?�
 *
 * @author Vondear
 * @date 2015/12/03
 */
public class RxRoundProgress extends View {
    public static final int STROKE = 0;
    /**
     * 画笔对象的引用
     */
    private Paint paint;
    private Paint textPaint;
    private Paint moneyPaint;
    private Paint moneyDPaint;
    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;
    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;
    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 最大进度
     */
    private double max;
    /**
     * 当�?进度
     */
    private double progress;
    /**
     * 是�?�显示中间的进度
     */
    private boolean textIsDisplayable;
    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    public RxRoundProgress(Context context) {
        this(context, null);
    }

    public RxRoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RxRoundProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //进度�?�画笔
        paint = new Paint();
        //文字画笔
        textPaint = new Paint();
        //文字画笔
        moneyPaint = new Paint();
        //文字画笔
        moneyDPaint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RxRoundProgress);

        //获�?�自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RxRoundProgress_roundColor, Color.WHITE);
        roundProgressColor = mTypedArray.getColor(R.styleable.RxRoundProgress_roundProgressColor, Color.parseColor("#F6B141"));
        textColor = mTypedArray.getColor(R.styleable.RxRoundProgress_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RxRoundProgress_textSize1, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RxRoundProgress_roundWidth, 20);
        max = mTypedArray.getInteger(R.styleable.RxRoundProgress_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RxRoundProgress_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RxRoundProgress_style, 0);

        mTypedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        //获�?�圆心的x�??标
        int centre = getWidth() / 2 - 90;
        //圆环的�?�径
        int radius = (int) (centre - roundWidth / 2);
        //用于定义的圆弧的形状和大�?的界�?
        RectF oval = new RectF(centre - radius + 90, centre - radius + 90, centre + radius + 90, centre + radius + 90);
        //设置圆环的颜色
        paint.setColor(roundColor);
        //设置空心
        paint.setStyle(Paint.Style.STROKE);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //消除锯齿
        paint.setAntiAlias(true);
        //设置边缘为圆角
        paint.setStrokeCap(Paint.Cap.ROUND);
//		canvas.drawRect(0, 0, getWidth(), getWidth(), paint);// 正方形
        textPaint.setColor(roundColor);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(36);
        moneyPaint.setColor(roundColor);
        moneyPaint.setAntiAlias(true);
        moneyPaint.setTextSize(65);
        moneyDPaint.setColor(roundColor);
        moneyDPaint.setAntiAlias(true);
        moneyDPaint.setTextSize(48);
        //左边最�?值
        canvas.drawText("0元", (float) (radius - Math.sqrt(2) * (radius / 2) + 10), (float) (2 * radius - Math.sqrt(2) * (radius / 4) + 130), textPaint);
        //�?�边最大值
        canvas.drawText(getMax() + "元", (float) (radius + Math.sqrt(2) * (radius / 2) + 138), (float) (2 * radius - Math.sqrt(2) * (radius / 4) + 130), textPaint);
		/*if(progress<50){
			double money = progress*1+(Math.floor(Math.random()*getMax()));
			canvas.drawText(money+"", (centre+90) - moneyPaint.measureText(money+"")/2-15, centre+165, moneyPaint);//�?�边最大值
		}else{*/
        //�?�边最大值
        canvas.drawText(RxDataTool.format2Decimals(getProgress() + ""), (centre + 90) - moneyPaint.measureText(RxDataTool.format2Decimals(getProgress() + "")) / 2 - 15, centre + 105, moneyPaint);
        //}
        //�?�边最大值
        canvas.drawText("元", (centre + 90) + moneyPaint.measureText(RxDataTool.format2Decimals(getProgress() + "")) / 2 - 10, centre + 105, moneyDPaint);
        //根�?�进度画圆弧
        canvas.drawArc(oval, 135, 270, false, paint);

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        //设置字体
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //中间的进度百分比，先转�?��?float在进行除法�?算，�?然都为0
        int percent = (int) (((float) progress / (float) max) * 100);
        //测�?字体宽度，我们需�?根�?�字体的宽度设置在圆环中间
        float textWidth = paint.measureText(percent + "%");

        if (textIsDisplayable && percent != 0 && style == STROKE) {
            //canvas.drawText(percent + "%", centre+90 - textWidth / 2, centre + 90 + textSize/2, paint); //画出进度百分比
        }

        /**
         * 画圆弧 ，画圆环的进度
         */
        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色

        switch (style) {
            case STROKE: {
                paint.setStyle(Paint.Style.STROKE);
                if (progress >= 0) {
                    canvas.drawArc(oval, 135, 270 * ((float) progress / (float) max), false, paint);  //根�?�进度画圆弧
                }
                break;
            }
            default:
                break;
        }
    }

    public synchronized double getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(double max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获�?�进度.需�?�?�步
     *
     * @return
     */
    public synchronized double getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需�?�?�步
     * 刷新界�?�调用postInvalidate()能在�?�UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(double progress) {
        if (progress < 0) {
            this.progress = progress;
            //throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }


    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }


}
