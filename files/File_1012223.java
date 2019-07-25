package com.xuexiang.xui.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.AnimRes;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;

import com.xuexiang.xui.XUI;

/**
 * 获�?�res中的资�?
 *
 * @author xuexiang
 * @since 2018/12/18 上�?�12:14
 */
public final class ResUtils {

    private ResUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获�?�resources对象
     *
     * @return
     */
    public static Resources getResources() {
        return XUI.getContext().getResources();
    }

    /**
     * 获�?�字符串
     *
     * @param resId
     * @return
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获�?�资�?图片
     *
     * @param resId
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return XUI.getContext().getDrawable(resId);
        }
        return getResources().getDrawable(resId);
    }

    /**
     * 获�?�资�?图片�?和主体有关】
     *
     * @param resId
     * @return
     */
    public static Drawable getDrawable(Context context, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resId);
        }
        return context.getResources().getDrawable(resId);
    }

    /**
     * 获�?�svg资�?图片
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getVectorDrawable(Context context, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resId);
        }
        return AppCompatResources.getDrawable(context, resId);
    }

    /**
     * 获�?�dimes值，返回的是精确的值
     *
     * @param resId
     * @return
     */
    public static float getDimens(@DimenRes int resId) {
        return getResources().getDimension(resId);
    }

    /**
     * 获�?�Color值
     *
     * @param resId
     * @return
     */
    public static int getColor(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获�?�ColorStateList值
     *
     * @param resId
     * @return
     */
    public static ColorStateList getColors(@ColorRes int resId) {
        return getResources().getColorStateList(resId);
    }

    /**
     * 获�?�dimes值，返回的是�?去余�?�整】的值
     *
     * @param resId
     * @return
     */
    public static int getDimensionPixelOffset(@DimenRes int resId) {
        return getResources().getDimensionPixelOffset(resId);
    }

    /**
     * 获�?�dimes值，返回的是�?4�?5入】的值
     *
     * @param resId
     * @return
     */
    public static int getDimensionPixelSize(@DimenRes int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获�?�字符串的数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获�?�数字的数组
     *
     * @param resId
     * @return
     */
    public static int[] getIntArray(@ArrayRes int resId) {
        return getResources().getIntArray(resId);
    }

    /**
     * 获�?�动画
     *
     * @param resId
     * @return
     */
    public static Animation getAnim(@AnimRes int resId) {
        return AnimationUtils.loadAnimation(XUI.getContext(), resId);
    }

    /**
     * Check if layout direction is RTL
     *
     * @return {@code true} if the layout direction is right-to-left
     */
    public static boolean isRtl() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    /**
     * Darkens a color by a given factor.
     *
     * @param color
     *     the color to darken
     * @param factor
     *     The factor to darken the color.
     * @return darker version of specified color.
     */
    public static int darker(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                Math.max((int) (Color.green(color) * factor), 0),
                Math.max((int) (Color.blue(color) * factor), 0));
    }

    /**
     * Lightens a color by a given factor.
     *
     * @param color
     *     The color to lighten
     * @param factor
     *     The factor to lighten the color. 0 will make the color unchanged. 1 will make the
     *     color white.
     * @return lighter version of the specified color.
     */
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

}
