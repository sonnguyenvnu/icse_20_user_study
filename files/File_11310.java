package com.vondear.rxui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.vondear.rxui.R;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Vondear
 * @date 2016/7/10 17:59:00
 */
public class RxShoppingView extends View {

    private final static int STATE_NONE = 0;
    private final static int STATE_MOVE = 1;
    private final static int STATE_MOVE_OVER = 2;
    private final static int STATE_ROTATE = 3;
    private final static int STATE_ROTATE_OVER = 4;

    private final static int DEFAULT_DURATION = 250;
    private final static String DEFAULT_SHOPPING_TEXT = "加入购物车";

    private Paint mPaintBg, mPaintText, mPaintNum;
    private Paint mPaintMinus;

    //是�?�是�?��?状�?（= = �??字�?好�?�，�?�?就是区分�?��?和回退状�?）
    private boolean mIsForward = true;
    //动画时长
    private int mDuration;
    //购买数�?
    private int mNum = 0;
    //展示文案
    private String mShoppingText;
    //当�?状�?
    private int mState = STATE_NONE;

    //属性值
    private int mWidth = 0;
    private int mAngle = 0;
    private int mTextPosition = 0;
    private int mMinusBtnPosition = 0;
    private int mAlpha = 0;

    private int MAX_WIDTH;
    private int MAX_HEIGHT;

    private ShoppingClickListener mShoppingClickListener;

    public RxShoppingView(Context context) {
        this(context, null);
    }

    public RxShoppingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RxShoppingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray typeArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.ShoppingView);
        mDuration = typeArray.getInt(R.styleable.ShoppingView_sv_duration, DEFAULT_DURATION);
        mShoppingText = TextUtils.isEmpty(typeArray.getString(R.styleable.ShoppingView_sv_text)) ? DEFAULT_SHOPPING_TEXT : typeArray.getString(R.styleable.ShoppingView_sv_text);
        //展示文案大�?
        int textSize = (int) typeArray.getDimension(R.styleable.ShoppingView_sv_text_size, sp2px(16));
        //背景色
        int bgColor = typeArray.getColor(R.styleable.ShoppingView_sv_bg_color, ContextCompat.getColor(getContext(), R.color.slateblue));
        typeArray.recycle();

        mPaintBg = new Paint();
        mPaintBg.setColor(bgColor);
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintBg.setAntiAlias(true);
        mPaintMinus = new Paint();
        mPaintMinus.setColor(bgColor);
        mPaintMinus.setStyle(Paint.Style.STROKE);
        mPaintMinus.setAntiAlias(true);
        mPaintMinus.setStrokeWidth(textSize / 6);
        mPaintText = new Paint();
        mPaintText.setColor(Color.WHITE);
        mPaintText.setStrokeWidth(textSize / 6);
        mPaintText.setTextSize(textSize);
        mPaintText.setAntiAlias(true);
        mPaintNum = new Paint();
        mPaintNum.setColor(Color.BLACK);
        mPaintNum.setTextSize(textSize / 3 * 4);
        mPaintNum.setStrokeWidth(textSize / 6);
        mPaintNum.setAntiAlias(true);

        MAX_WIDTH = getTextWidth(mPaintText, mShoppingText) / 5 * 8;
        MAX_HEIGHT = textSize * 2;

        if (MAX_WIDTH / (float) MAX_HEIGHT < 3.5) {
            MAX_WIDTH = (int) (MAX_HEIGHT * 3.5);
        }

        mTextPosition = MAX_WIDTH / 2;
        mMinusBtnPosition = MAX_HEIGHT / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MAX_WIDTH, MAX_HEIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mState == STATE_NONE) {
            drawBgMove(canvas);
            drawShoppingText(canvas);
        } else if (mState == STATE_MOVE) {
            drawBgMove(canvas);
        } else if (mState == STATE_MOVE_OVER) {
            mState = STATE_ROTATE;
            if (mIsForward) {
                drawAddBtn(canvas);
                startRotateAnim();
            } else {
                drawBgMove(canvas);
                drawShoppingText(canvas);
                mState = STATE_NONE;
                mIsForward = true;
                mNum = 0;
            }
        } else if (mState == STATE_ROTATE) {
            mPaintMinus.setAlpha(mAlpha);
            mPaintNum.setAlpha(mAlpha);
            drawMinusBtn(canvas, mAngle);
            drawNumText(canvas);
            drawAddBtn(canvas);
        } else if (mState == STATE_ROTATE_OVER) {
            drawMinusBtn(canvas, mAngle);
            drawNumText(canvas);
            drawAddBtn(canvas);
            if (!mIsForward) {
                startMoveAnim();
            }
        }

    }

    /**
     * 绘制移动的背景
     *
     * @param canvas 画�?�
     */
    private void drawBgMove(Canvas canvas) {
        canvas.drawArc(new RectF(mWidth, 0, mWidth + MAX_HEIGHT, MAX_HEIGHT), 90, 180, false, mPaintBg);
        canvas.drawRect(new RectF(mWidth + MAX_HEIGHT / 2, 0, MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT), mPaintBg);
        canvas.drawArc(new RectF(MAX_WIDTH - MAX_HEIGHT, 0, MAX_WIDTH, MAX_HEIGHT), 180, 270, false, mPaintBg);
    }

    /**
     * 绘制购物车文案
     *
     * @param canvas 画�?�
     */
    private void drawShoppingText(Canvas canvas) {
        canvas.drawText(mShoppingText, MAX_WIDTH / 2 - getTextWidth(mPaintText, mShoppingText) / 2f, MAX_HEIGHT / 2 + getTextHeight(mShoppingText, mPaintText) / 2f, mPaintText);
    }

    /**
     * 绘制加�?�按钮
     *
     * @param canvas 画�?�
     */
    private void drawAddBtn(Canvas canvas) {
        canvas.drawCircle(MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 2, MAX_HEIGHT / 2, mPaintBg);
        canvas.drawLine(MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 4, MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 4 * 3, mPaintText);
        canvas.drawLine(MAX_WIDTH - MAX_HEIGHT / 2 - MAX_HEIGHT / 4, MAX_HEIGHT / 2, MAX_WIDTH - MAX_HEIGHT / 4, MAX_HEIGHT / 2, mPaintText);
    }

    /**
     * 绘制�?�?�按钮
     *
     * @param canvas 画�?�
     * @param angle  旋转角度
     */
    private void drawMinusBtn(Canvas canvas, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, mMinusBtnPosition, MAX_HEIGHT / 2);
        }
        canvas.drawCircle(mMinusBtnPosition, MAX_HEIGHT / 2, MAX_HEIGHT / 2 - MAX_HEIGHT / 20, mPaintMinus);
        canvas.drawLine(mMinusBtnPosition - MAX_HEIGHT / 4, MAX_HEIGHT / 2, mMinusBtnPosition + MAX_HEIGHT / 4, MAX_HEIGHT / 2, mPaintMinus);
        if (angle != 0) {
            canvas.rotate(-angle, mMinusBtnPosition, MAX_HEIGHT / 2);
        }
    }

    /**
     * 绘制购买数�?
     *
     * @param canvas 画�?�
     */
    private void drawNumText(Canvas canvas) {
        drawText(canvas, String.valueOf(mNum), mTextPosition - getTextWidth(mPaintNum, String.valueOf(mNum)) / 2f, MAX_HEIGHT / 2 + getTextHeight(String.valueOf(mNum), mPaintNum) / 2f, mPaintNum, mAngle);
    }

    /**
     * 绘制Text带角度
     *
     * @param canvas 画�?�
     * @param text   文案
     * @param x      x�??标
     * @param y      y�??标
     * @param paint  画笔
     * @param angle  旋转角度
     */
    private void drawText(Canvas canvas, String text, float x, float y, Paint paint, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle, x, y);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (mState == STATE_NONE) {
                    mNum++;
                    startMoveAnim();
                    if (mShoppingClickListener != null) {
                        mShoppingClickListener.onAddClick(mNum);
                    }
                } else if (mState == STATE_ROTATE_OVER) {
                    if (isPointInCircle(new PointF(event.getX(), event.getY()), new PointF(MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 2), MAX_HEIGHT / 2)) {
                        if (mNum > 0) {
                            mNum++;
                            mIsForward = true;
                            if (mShoppingClickListener != null) {
                                mShoppingClickListener.onAddClick(mNum);
                            }
                        }
                        invalidate();
                    } else if (isPointInCircle(new PointF(event.getX(), event.getY()), new PointF(MAX_HEIGHT / 2, MAX_HEIGHT / 2), MAX_HEIGHT / 2)) {
                        if (mNum > 1) {
                            mNum--;
                            if (mShoppingClickListener != null) {
                                mShoppingClickListener.onMinusClick(mNum);
                            }
                            invalidate();
                        } else {
                            if (mShoppingClickListener != null) {
                                mShoppingClickListener.onMinusClick(0);
                            }
                            mState = STATE_ROTATE;
                            mIsForward = false;
                            startRotateAnim();
                        }
                    }
                }

                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 开始移动动画
     */
    private void startMoveAnim() {
        mState = STATE_MOVE;
        ValueAnimator valueAnimator;
        if (mIsForward) {
            valueAnimator = ValueAnimator.ofInt(0, MAX_WIDTH - MAX_HEIGHT);
        } else {
            valueAnimator = ValueAnimator.ofInt(MAX_WIDTH - MAX_HEIGHT, 0);
        }
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mWidth = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mWidth == MAX_WIDTH - MAX_HEIGHT) {
                        mState = STATE_MOVE_OVER;
                    }
                } else {
                    if (mWidth == 0) {
                        mState = STATE_MOVE_OVER;
                    }
                }

                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 开始旋转动画
     */
    private void startRotateAnim() {

        Collection<Animator> animatorList = new ArrayList<>();

        ValueAnimator animatorTextRotate;
        if (mIsForward) {
            animatorTextRotate = ValueAnimator.ofInt(0, 360);
        } else {
            animatorTextRotate = ValueAnimator.ofInt(360, 0);
        }
        animatorTextRotate.setDuration(mDuration);
        animatorTextRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAngle = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mAngle == 360) {
                        mState = STATE_ROTATE_OVER;
                    }
                } else {
                    if (mAngle == 0) {
                        mState = STATE_ROTATE_OVER;
                    }
                }

            }
        });

        animatorList.add(animatorTextRotate);

        ValueAnimator animatorAlpha;
        if (mIsForward) {
            animatorAlpha = ValueAnimator.ofInt(0, 255);
        } else {
            animatorAlpha = ValueAnimator.ofInt(255, 0);
        }
        animatorAlpha.setDuration(mDuration);
        animatorAlpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAlpha = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mAlpha == 255) {
                        mState = STATE_ROTATE_OVER;
                    }
                } else {
                    if (mAlpha == 0) {
                        mState = STATE_ROTATE_OVER;
                    }
                }

            }
        });

        animatorList.add(animatorAlpha);

        ValueAnimator animatorTextMove;
        if (mIsForward) {
            animatorTextMove = ValueAnimator.ofInt(MAX_WIDTH - MAX_HEIGHT / 2, MAX_WIDTH / 2);
        } else {
            animatorTextMove = ValueAnimator.ofInt(MAX_WIDTH / 2, MAX_WIDTH - MAX_HEIGHT / 2);
        }
        animatorTextMove.setDuration(mDuration);
        animatorTextMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mTextPosition = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mTextPosition == MAX_WIDTH / 2) {
                        mState = STATE_ROTATE_OVER;
                    }
                } else {
                    if (mTextPosition == MAX_WIDTH - MAX_HEIGHT / 2) {
                        mState = STATE_ROTATE_OVER;
                    }
                }

            }
        });

        animatorList.add(animatorTextMove);

        ValueAnimator animatorBtnMove;
        if (mIsForward) {
            animatorBtnMove = ValueAnimator.ofInt(MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 2);
        } else {
            animatorBtnMove = ValueAnimator.ofInt(MAX_HEIGHT / 2, MAX_WIDTH - MAX_HEIGHT / 2);
        }
        animatorBtnMove.setDuration(mDuration);
        animatorBtnMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mMinusBtnPosition = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mMinusBtnPosition == MAX_HEIGHT / 2) {
                        mState = STATE_ROTATE_OVER;
                    }
                } else {
                    if (mMinusBtnPosition == MAX_WIDTH - MAX_HEIGHT / 2) {
                        mState = STATE_ROTATE_OVER;
                    }
                }

                invalidate();
            }
        });

        animatorList.add(animatorBtnMove);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.playTogether(animatorList);
        animatorSet.start();
    }

    /**
     * 设置购买数�?
     *
     * @param num 购买数�?
     */
    public void setTextNum(int num) {
        mNum = num;
        mState = STATE_ROTATE_OVER;
        invalidate();
    }

    public void setOnShoppingClickListener(ShoppingClickListener shoppingClickListener) {
        this.mShoppingClickListener = shoppingClickListener;
    }

    public interface ShoppingClickListener {
        void onAddClick(int num);

        void onMinusClick(int num);
    }

    /**
     * 判断点是�?�在圆内
     *
     * @param pointF 待确定点
     * @param circle 圆心
     * @param radius �?�径
     * @return true在圆内
     */
    private boolean isPointInCircle(PointF pointF, PointF circle, float radius) {
        return Math.pow((pointF.x - circle.x), 2) + Math.pow((pointF.y - circle.y), 2) <= Math.pow(radius, 2);
    }

    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //获�?�Text高度
    private int getTextHeight(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (int) (rect.height() / 33f * 29);
    }

    //获�?�Text宽度
    private int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

}
