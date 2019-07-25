package com.zlm.hp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.zlm.hp.libs.utils.ColorUtil;
import com.zlm.hp.utils.FontUtil;


/**
 * @Description: 字体图标指示器文本
 * @Author: zhangliangming
 * @Date: 2017/7/16 15:55
 * @Version:
 */
public class IconfontIndicatorTextView extends AppCompatTextView {

    private float oldTextSize = -1f;
    /**
     * 是�?�倒置
     */
    private boolean convert = false;

    /**
     * 是�?�是已选中状�?
     */
    private boolean isSelected = false;

    public IconfontIndicatorTextView(Context context) {
        super(context);
        init(context);
    }

    public IconfontIndicatorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IconfontIndicatorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // 设置字体图片
        Typeface iconfont = FontUtil.getInstance(context).getTypeFace();
        setTypeface(iconfont);
        setClickable(true);
    }


    public void setConvert(boolean convert) {
        this.convert = convert;
        setPressed(false);
    }

    @Override
    public void setSelected(boolean selected) {

        int color = getCurrentTextColor();
        TextPaint paint = getPaint();

        //
        if (oldTextSize == -1) {
            oldTextSize = getTextSize();
        }

        boolean isSelected = selected;
        //如果倒置为true
        if (convert) {
            isSelected = !selected;
        }

        if (isSelected) {
            int pressedColor = ColorUtil.parserColor(color, 240);
            setTextColor(pressedColor);
            paint.setFakeBoldText(true);
            paint.setTextSize(oldTextSize + 5);
        } else {
            int defColor = ColorUtil.parserColor(color, 150);
            setTextColor(defColor);
            paint.setFakeBoldText(false);
            paint.setTextSize(oldTextSize);
        }

        this.isSelected = selected;

    }

    @Override
    public void setPressed(boolean pressed) {

        int color = getCurrentTextColor();
        TextPaint paint = getPaint();

        //
        if (oldTextSize == -1) {
            oldTextSize = getTextSize();
        }

        boolean isPressed = pressed;
        //如果倒置为true
        if (convert) {
            isPressed = !pressed;
        }

        if (isPressed) {
            int pressedColor = ColorUtil.parserColor(color, 255);
            setTextColor(pressedColor);
            paint.setFakeBoldText(true);
            paint.setTextSize(oldTextSize + 5);
        } else {
            int defColor = ColorUtil.parserColor(color, 150);
            setTextColor(defColor);
            paint.setFakeBoldText(false);
            paint.setTextSize(oldTextSize);
        }
        //
        if(!pressed){
            setSelected(isSelected);
        }
        //
        super.setPressed(pressed);

    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }
}
