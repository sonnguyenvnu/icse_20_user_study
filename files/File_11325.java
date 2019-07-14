package com.vondear.rxui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxKeyboardTool;
import com.vondear.rxui.R;

/**
 * @author vondear
 * @date 2017/1/2
 */

public class RxTitle extends FrameLayout {
    //*******************************************控件start******************************************
    //根布局
    private RelativeLayout mRootLayout;

    //Title的TextView控件
    private RxTextAutoZoom mTvTitle;

    //左边布局
    private LinearLayout mLlLeft;

    //左边ImageView控件
    private ImageView mIvLeft;

    //左边TextView控件
    private TextView mTvLeft;

    //�?�边布局
    private LinearLayout mLlRight;

    //�?�边ImageView控件
    private ImageView mIvRight;

    //�?�边TextView控件
    private TextView mTvRight;
    //===========================================控件end=============================================

    //*******************************************属性start*******************************************
    //Title文字
    private String mTitle;

    //Title字体颜色
    private int mTitleColor;

    //Title字体大�?
    private int mTitleSize;

    //Title是�?�显示
    private boolean mTitleVisibility;

    //左边 ICON 引用的资�?ID
    private int mLeftIcon;

    //�?�边 ICON 引用的资�?ID
    private int mRightIcon;

    //左边 ICON 是�?�显示
    private boolean mLeftIconVisibility;

    //�?�边 ICON 是�?�显示
    private boolean mRightIconVisibility;

    //左边文字
    private String mLeftText;

    //左边字体颜色
    private int mLeftTextColor;

    //左边字体大�?
    private int mLeftTextSize;

    //左边文字是�?�显示
    private boolean mLeftTextVisibility;

    //�?�边文字
    private String mRightText;

    //�?�边字体颜色
    private int mRightTextColor;

    //�?�边字体大�?
    private int mRightTextSize;

    //�?�边文字是�?�显示
    private boolean mRightTextVisibility;
    //===========================================属性end=============================================

    public RxTitle(Context context) {
        super(context);
    }

    public RxTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        //导入布局
        initView(context, attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.include_rx_title, this);

        mRootLayout = findViewById(R.id.root_layout);
        mTvTitle = findViewById(R.id.tv_rx_title);
        mLlLeft = findViewById(R.id.ll_left);
        mIvLeft = findViewById(R.id.iv_left);
        mIvRight = findViewById(R.id.iv_right);
        mLlRight = findViewById(R.id.ll_right);
        mTvLeft = findViewById(R.id.tv_left);
        mTvRight = findViewById(R.id.tv_right);

        //获得这个控件对应的属性。
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RxTitle);

        try {
            //获得属性值
            //getColor(R.styleable.RxTitle_RxBackground, getResources().getColor(R.color.transparent))
            //标题
            mTitle = a.getString(R.styleable.RxTitle_title);
            //标题颜色
            mTitleColor = a.getColor(R.styleable.RxTitle_titleColor, getResources().getColor(R.color.white));
            //标题字体大�?
            mTitleSize = a.getDimensionPixelSize(R.styleable.RxTitle_titleSize, RxImageTool.dip2px(20));
            //TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics())
            mTitleVisibility = a.getBoolean(R.styleable.RxTitle_titleVisibility, true);

            //左边图标
            mLeftIcon = a.getResourceId(R.styleable.RxTitle_leftIcon, R.drawable.previous_icon);
            //�?�边图标
            mRightIcon = a.getResourceId(R.styleable.RxTitle_rightIcon, R.drawable.set);
            //左边图标是�?�显示
            mLeftIconVisibility = a.getBoolean(R.styleable.RxTitle_leftIconVisibility, true);
            //�?�边图标是�?�显示
            mRightIconVisibility = a.getBoolean(R.styleable.RxTitle_rightIconVisibility, false);

            mLeftText = a.getString(R.styleable.RxTitle_leftText);
            //左边字体颜色
            mLeftTextColor = a.getColor(R.styleable.RxTitle_leftTextColor, getResources().getColor(R.color.white));
            //标题字体大�?
            mLeftTextSize = a.getDimensionPixelSize(R.styleable.RxTitle_leftTextSize, RxImageTool.dip2px(8));
            mLeftTextVisibility = a.getBoolean(R.styleable.RxTitle_leftTextVisibility, false);

            mRightText = a.getString(R.styleable.RxTitle_rightText);
            //�?�边字体颜色
            mRightTextColor = a.getColor(R.styleable.RxTitle_rightTextColor, getResources().getColor(R.color.white));
            //标题字体大�?
            mRightTextSize = a.getDimensionPixelSize(R.styleable.RxTitle_rightTextSize, RxImageTool.dip2px(8));
            mRightTextVisibility = a.getBoolean(R.styleable.RxTitle_rightTextVisibility, false);

        } finally {
            //回收这个对象
            a.recycle();
        }

        //******************************************************************************************以下属性�?始化
        if (!RxDataTool.isNullString(mTitle)) {
            setTitle(mTitle);
        }

        if (mTitleColor != 0) {
            setTitleColor(mTitleColor);
        }

        if (mTitleSize != 0) {
            setTitleSize(mTitleSize);
        }

        if (mLeftIcon != 0) {
            setLeftIcon(mLeftIcon);
        }

        if (mRightIcon != 0) {
            setRightIcon(mRightIcon);
        }

        setTitleVisibility(mTitleVisibility);

        setLeftText(mLeftText);

        setLeftTextColor(mLeftTextColor);

        setLeftTextSize(mLeftTextSize);

        setLeftTextVisibility(mLeftTextVisibility);

        setRightText(mRightText);

        setRightTextColor(mRightTextColor);

        setRightTextSize(mRightTextSize);

        setRightTextVisibility(mRightTextVisibility);

        setLeftIconVisibility(mLeftIconVisibility);

        setRightIconVisibility(mRightIconVisibility);

        initAutoFitEditText();
        //==========================================================================================以上为属性�?始化
    }

    private void initAutoFitEditText() {
        mTvTitle.clearFocus();
        mTvTitle.setEnabled(false);
        mTvTitle.setFocusableInTouchMode(false);
        mTvTitle.setFocusable(false);
        mTvTitle.setEnableSizeCache(false);
        //might cause crash on some devices
        mTvTitle.setMovementMethod(null);
        // can be added after layout inflation;
        mTvTitle.setMaxHeight(RxImageTool.dip2px(55f));
        //don't forget to add min text size programmatically
        mTvTitle.setMinTextSize(37f);
        try {
            RxTextAutoZoom.setNormalization((Activity) getContext(), mRootLayout, mTvTitle);
            RxKeyboardTool.hideSoftInput((Activity) getContext());
        } catch (Exception e) {

        }
    }

    //**********************************************************************************************以下为get方法

    public RelativeLayout getRootLayout() {
        return mRootLayout;
    }

    public RxTextAutoZoom getTvTitle() {
        return mTvTitle;
    }

    public LinearLayout getLlLeft() {
        return mLlLeft;
    }

    public ImageView getIvLeft() {
        return mIvLeft;
    }

    public TextView getTvLeft() {
        return mTvLeft;
    }

    public LinearLayout getLlRight() {
        return mLlRight;
    }

    public ImageView getIvRight() {
        return mIvRight;
    }

    public TextView getTvRight() {
        return mTvRight;
    }

    public boolean isTitleVisibility() {
        return mTitleVisibility;
    }

    public void setTitleVisibility(boolean titleVisibility) {
        mTitleVisibility = titleVisibility;
        if (mTitleVisibility) {
            mTvTitle.setVisibility(VISIBLE);
        } else {
            mTvTitle.setVisibility(GONE);
        }
    }

    public String getLeftText() {
        return mLeftText;
    }

    //**********************************************************************************************以下为  左边文字  相关方法
    public void setLeftText(String leftText) {
        mLeftText = leftText;
        mTvLeft.setText(mLeftText);

    }

    public int getLeftTextColor() {
        return mLeftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        mLeftTextColor = leftTextColor;
        mTvLeft.setTextColor(mLeftTextColor);
    }

    public int getLeftTextSize() {
        return mLeftTextSize;
    }

    public void setLeftTextSize(int leftTextSize) {
        mLeftTextSize = leftTextSize;
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
    }

    public boolean isLeftTextVisibility() {
        return mLeftTextVisibility;
    }

    public void setLeftTextVisibility(boolean leftTextVisibility) {
        mLeftTextVisibility = leftTextVisibility;
        if (mLeftTextVisibility) {
            mTvLeft.setVisibility(VISIBLE);
        } else {
            mTvLeft.setVisibility(GONE);
        }
    }

    public String getRightText() {
        return mRightText;
    }

    //**********************************************************************************************以下为  �?�边文字  相关方法
    public void setRightText(String rightText) {
        mRightText = rightText;
        mTvRight.setText(mRightText);

    }

    public int getRightTextColor() {
        return mRightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        mRightTextColor = rightTextColor;
        mTvRight.setTextColor(mRightTextColor);
    }

    public int getRightTextSize() {
        return mRightTextSize;
    }

    public void setRightTextSize(int rightTextSize) {
        mRightTextSize = rightTextSize;
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
    }

    //==============================================================================================以上为get方法

    //**********************************************************************************************以下为set方法

    public boolean isRightTextVisibility() {
        return mRightTextVisibility;
    }

    public void setRightTextVisibility(boolean rightTextVisibility) {
        mRightTextVisibility = rightTextVisibility;
        if (mRightTextVisibility) {
            mTvRight.setVisibility(VISIBLE);
            if (isRightIconVisibility()) {
                mTvRight.setPadding(0, 0, 0, 0);
            }
        } else {
            mTvRight.setVisibility(GONE);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    //**********************************************************************************************以下为Title相关方法
    public void setTitle(String title) {
        mTitle = title;
        mTvTitle.setText(mTitle);
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        mTvTitle.setTextColor(mTitleColor);
    }

    public int getTitleSize() {
        return mTitleSize;
    }

    public void setTitleSize(int titleSize) {
        mTitleSize = titleSize;
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSize);
    }

    public int getLeftIcon() {
        return mLeftIcon;
    }

    public void setLeftIcon(int leftIcon) {
        mLeftIcon = leftIcon;
        mIvLeft.setImageResource(mLeftIcon);
    }

    public int getRightIcon() {
        return mRightIcon;
    }
    //==============================================================================================以上为  Title  相关方法

    public void setRightIcon(int rightIcon) {
        mRightIcon = rightIcon;
        mIvRight.setImageResource(mRightIcon);
    }

    public boolean isLeftIconVisibility() {
        return mLeftIconVisibility;
    }

    public void setLeftIconVisibility(boolean leftIconVisibility) {
        mLeftIconVisibility = leftIconVisibility;
        if (mLeftIconVisibility) {
            mIvLeft.setVisibility(VISIBLE);
        } else {
            mIvLeft.setVisibility(GONE);
        }
    }

    public boolean isRightIconVisibility() {
        return mRightIconVisibility;
    }
    //==============================================================================================以上为  左边文字  相关方法

    public void setRightIconVisibility(boolean rightIconVisibility) {
        mRightIconVisibility = rightIconVisibility;
        if (mRightIconVisibility) {
            mIvRight.setVisibility(VISIBLE);
        } else {
            mIvRight.setVisibility(GONE);
        }
    }

    public void setLeftFinish(final Activity activity) {
        mLlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setLeftOnClickListener(OnClickListener onClickListener) {
        mLlLeft.setOnClickListener(onClickListener);
    }

    public void setRightOnClickListener(OnClickListener onClickListener) {
        mLlRight.setOnClickListener(onClickListener);
    }
    //==============================================================================================以上为  �?�边文字  相关方法

    public void setLeftTextOnClickListener(OnClickListener onClickListener) {
        mTvLeft.setOnClickListener(onClickListener);
    }

    public void setRightTextOnClickListener(OnClickListener onClickListener) {
        mTvRight.setOnClickListener(onClickListener);
    }

    public void setLeftIconOnClickListener(OnClickListener onClickListener) {
        mIvLeft.setOnClickListener(onClickListener);
    }

    public void setRightIconOnClickListener(OnClickListener onClickListener) {
        mIvRight.setOnClickListener(onClickListener);
    }
    //==============================================================================================以上为set方法

}
