/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.xui.utils;

import android.graphics.Color;
import androidx.annotation.ColorInt;

import java.util.Random;

/**
 * 颜色辅助工具
 *
 * @author xuexiang
 * @since 2018/12/27 下�?�3:00
 */
public final class ColorUtils {

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int setColorAlpha(@ColorInt int color, float alpha){
        return setColorAlpha(color, alpha, true);
    }

    /**
     * 设置颜色的alpha值
     *
     * @param color 需�?被设置的颜色值
     * @param alpha �?�值为[0,1]，0表示全�?明，1表示�?�?明
     * @param override 覆盖原本的 alpha
     * @return 返回改�?�了 alpha 值的颜色值
     */
    public static int setColorAlpha(@ColorInt int color, float alpha, boolean override) {
        int origin = override ? 0xff : (color >> 24) & 0xff;
        return color & 0x00ffffff | (int) (alpha * origin) << 24;
    }

    /**
     * 根�?�比例，在两个color值之间计算出一个color值
     * <b>注�?该方法是ARGB通�?�分开计算比例的</b>
     *
     * @param fromColor 开始的color值
     * @param toColor   最终的color值
     * @param fraction  比例，�?�值为[0,1]，为0时返回 fromColor， 为1时返回 toColor
     * @return 计算出的color值
     */
    public static int computeColor(@ColorInt int fromColor, @ColorInt int toColor, float fraction) {
        fraction = Math.max(Math.min(fraction, 1), 0);

        int minColorA = Color.alpha(fromColor);
        int maxColorA = Color.alpha(toColor);
        int resultA = (int) ((maxColorA - minColorA) * fraction) + minColorA;

        int minColorR = Color.red(fromColor);
        int maxColorR = Color.red(toColor);
        int resultR = (int) ((maxColorR - minColorR) * fraction) + minColorR;

        int minColorG = Color.green(fromColor);
        int maxColorG = Color.green(toColor);
        int resultG = (int) ((maxColorG - minColorG) * fraction) + minColorG;

        int minColorB = Color.blue(fromColor);
        int maxColorB = Color.blue(toColor);
        int resultB = (int) ((maxColorB - minColorB) * fraction) + minColorB;

        return Color.argb(resultA, resultR, resultG, resultB);
    }

    /**
     * 将 color 颜色值转�?�为�??六进制字符串
     *
     * @param color 颜色值
     * @return 转�?��?�的字符串
     */
    public static String colorToString(@ColorInt int color) {
        return String.format("#%08X", color);
    }

    /**
     * 加深颜色
     *
     * @param color  需�?加深的颜色
     */
    public static int darker(int color) {
        return darker(color, 0.8F);
    }

    /**
     * 加深颜色
     *
     * @param color  需�?加深的颜色
     * @param factor The factor to darken the color.
     * @return darker version of specified color.
     */
    public static int darker(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                Math.max((int) (Color.green(color) * factor), 0),
                Math.max((int) (Color.blue(color) * factor), 0));
    }

    /**
     * �?�浅颜色
     *
     * @param color  需�?�?�浅的颜色
     */
    public static int lighter(int color) {
        return lighter(color, 0.8F);
    }

    /**
     * �?�浅颜色
     *
     * @param color  需�?�?�浅的颜色
     * @param factor The factor to lighten the color. 0 will make the color unchanged. 1 will make the
     *               color white.
     * @return lighter version of the specified color.
     */
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    /**
     * 是�?�是深色的颜色
     *
     * @param color
     * @return
     */
    public static boolean isColorDark(@ColorInt int color) {
        double darkness =
                1
                        - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color))
                        / 255;
        return darkness >= 0.5;
    }

    /**
     * @return 获�?��?机色
     */
    public static int getRandomColor() {
        return new RandomColor(255, 0, 255).getColor();
    }

    /**
     * �?机颜色
     */
    public static class RandomColor {
        int alpha;
        int lower;
        int upper;

        RandomColor(int alpha, int lower, int upper) {
            if (upper <= lower) {
                throw new IllegalArgumentException("must be lower < upper");
            }
            setAlpha(alpha);
            setLower(lower);
            setUpper(upper);
        }

        public int getColor() {
            //�?机数是�?闭  �?�开
            int red = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            int green = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            int blue = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            return Color.argb(getAlpha(), red, green, blue);
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            if (alpha > 255) alpha = 255;
            if (alpha < 0) alpha = 0;
            this.alpha = alpha;
        }

        int getLower() {
            return lower;
        }

        void setLower(int lower) {
            if (lower < 0) lower = 0;
            this.lower = lower;
        }

        int getUpper() {
            return upper;
        }

        void setUpper(int upper) {
            if (upper > 255) upper = 255;
            this.upper = upper;
        }
    }




}
