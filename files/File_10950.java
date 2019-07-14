package com.vondear.rxtool;

import android.content.Context;
import android.os.Vibrator;

/**
 *
 * @author Vondear
 * @date 2017/7/25
 * 震动帮助类
 * androidManifest.xml中加入 以下�?��?
 * <uses-permission android:name="android.permission.VIBRATE" />
 */
public class RxVibrateTool {
    private static Vibrator vibrator;

    /**
     * 简�?�震动
     * @param context     调用震动的Context
     * @param millisecond 震动的时间，毫秒
     */
    @SuppressWarnings("static-access")
    public static void vibrateOnce(Context context, int millisecond) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(millisecond);
    }

    /**
     * �?�?�的震动
     * @param context 调用震动的Context
     * @param pattern 震动形�?
     *                数组�?�数�?义：
     *                      第一个�?�数为等待指定时间�?�开始震动，
     *                      震动时间为第二个�?�数。
     *                      �?�边的�?�数�?次为等待震动和震动的时间
     * @param repeate 震动的次数，-1�?�?�?，�?�-1为从pattern的指定下标开始�?�? 0为一直震动
     *
     *
     */
    @SuppressWarnings("static-access")
    public static void vibrateComplicated(Context context, long[] pattern, int repeate) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeate);
    }

    /**
     * �?�止震动
     */
    public static void vibrateStop() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}
