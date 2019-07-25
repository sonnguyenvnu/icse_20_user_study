package com.fly.tour.common.util;

/**
 * Description: <åŠ¨æ€?èŽ·å?–èµ„æº?id><br>
 * Author: mxdl<br>
 * Date: 2018/6/19<br>
 * Version: V1.0.0<br>
 * Update: <br>
 */
import android.content.Context;

public class ResIdUtil {


    /**
     * èŽ·å?–id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int id(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "id", context.getPackageName());
    }

    /**
     * èŽ·å?–animç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int anim(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "anim", context.getPackageName());
    }

    /**
     * èŽ·å?–layoutç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int layout(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "layout", context.getPackageName());
    }

    /**
     * èŽ·å?–drawableç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int drawable(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
    }

    /**
     * èŽ·å?–stringç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int string(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "string", context.getPackageName());
    }

    /**
     * èŽ·å?–rawç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int raw(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "raw", context.getPackageName());
    }

    /**
     * èŽ·å?–styleç±»åž‹èµ„æº?id
     *
     * @param resName èµ„æº?å??ç§°
     * @return èµ„æº?id
     */
    public static int style(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "style", context.getPackageName());
    }
}
