package com.example.jingbin.cloudreader.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * @author jingbin
 * @Description 在代�?中�?打�?�log, 就直接DebugUtil.debug(....).
 * 然�?�如果�?�布的时候,就直接把这个类的DEBUG 改�?false,这样所有的log就�?会�?打�?�在控制�?�.
 */
public class DebugUtil {

    public static final String TAG = "jingbin";
    public static final boolean DEBUG = true;

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void error(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void error(String error) {

        if (DEBUG) {

            Log.e(TAG, error);
        }
    }

    public static void isMainThread() {
        if (DEBUG) {
            Log.e(TAG, "---是�?�在主线程：" + (Thread.currentThread() == Looper.getMainLooper().getThread()));
        }
    }
}
