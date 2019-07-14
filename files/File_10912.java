package com.vondear.rxtool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 *
 * @author vondear
 * @date 2016/1/24
 */

public class RxSPTool {

    private final static String JSON_CACHE = "JSON_CACHE";

    /**
     * 存入自定义的标识的数�?� �?�以近似的看作网络下载数�?�的缓存
     * �?��?�方�?存入
     *
     * @param context 使用的上下文
     * @param tag     存入内容的标记，约定俗�?的tag用当�?的类�??命�??�?�区分�?�?�的sp
     * @param content 存入的内
     */
    public static void putContent(Context context, String tag, String content) {
        putString(context, tag, content);
    }

    /**
     * 获�?�以tag命�??的存储内
     *
     * @param context 当�?调用的上下文
     * @param tag     命�??的tag
     * @return 返回以tag区分的内容，默认为空
     */
    public static String getContent(Context context, String tag) {
        return getString(context, tag);
    }

    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * SP中读�?�String
     *
     * @param key 键
     * @return 存在返回对应值，�?存在返回默认值{@code defaultValue}
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        String value;
        value = sp.getString(key, "");
        return value;
    }

    /**
     * SP中写入int类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * SP中读�?�int
     *
     * @param key 键
     * @return 存在返回对应值，�?存在返回默认值-1
     */
    public static int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        int value;
        value = sp.getInt(key, -1);
        return value;
    }

    /**
     * SP中写入long类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * SP中读�?�long
     *
     * @param key 键
     * @return 存在返回对应值，�?存在返回默认值-1
     */
    public static long getLong(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        long value;
        value = sp.getLong(key, -1L);
        return value;
    }

    /**
     * SP中写入float类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * SP中读�?�float
     *
     * @param key 键
     * @return 存在返回对应值，�?存在返回默认值-1
     */
    public static float getFloat(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        float value;
        value = sp.getFloat(key, -1F);
        return value;
    }

    /**
     * SP中写入boolean类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * SP中读�?�boolean
     *
     * @param key 键
     * @return 存在返回对应值，�?存在返回默认值{@code defaultValue}
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        boolean value;
        value = sp.getBoolean(key, false);
        return value;
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }


    /**
     * 存放JSON缓存数�?�
     *
     * @param context 上下文
     * @param key 键�??
     * @param content 内容
     * @return
     */
    public static void putJSONCache(Context context, String key, String content) {
        SharedPreferences sp = context.getSharedPreferences(JSON_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, content);
        editor.apply();

    }

    /**
     * 读�?�JSON缓存数�?�
     *
     * @param context 上下文
     * @param key 键�??
     * @return
     */
    public static String readJSONCache(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(JSON_CACHE, Context.MODE_PRIVATE);
        String jsoncache = sp.getString(key, null);
        return jsoncache;
    }


    /**
     * 清除指定的信�?�
     *
     * @param context 上下文
     * @param name 键�??
     * @param key     若为null 则删除name下所有的键值
     */
    public static void clearPreference(Context context, String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (key != null) {
            editor.remove(key);
        } else {
            editor.clear();
        }
        editor.apply();
    }
}
