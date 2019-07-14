package com.example.jingbin.cloudreader.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jingbin.cloudreader.app.CloudReaderApplication;
import com.example.jingbin.cloudreader.app.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingbin on 2015/2/26.
 */
public class SPUtils {

    private static final String CONFIG = "config";

    /**
     * 获�?�SharedPreferences实例对象
     *
     * @param fileName
     */
    private static SharedPreferences getSharedPreference(String fileName) {
        return CloudReaderApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * �?存一个String类型的值�?
     */
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putString(key, value).apply();
    }

    /**
     * 获�?�String的value
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getString(key, defValue);
    }

    /**
     * �?存一个Boolean类型的值�?
     */
    public static void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * 获�?�boolean的value
     */
    public static boolean getBoolean(String key, Boolean defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getBoolean(key, defValue);
    }

    /**
     * �?存一个int类型的值�?
     */
    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putInt(key, value).apply();
    }

    /**
     * 获�?�int的value
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getInt(key, defValue);
    }

    /**
     * �?存一个float类型的值�?
     */
    public static void putFloat(String fileName, String key, float value) {
        SharedPreferences.Editor editor = getSharedPreference(fileName).edit();
        editor.putFloat(key, value).apply();
    }

    /**
     * 获�?�float的value
     */
    public static float getFloat(String key, Float defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getFloat(key, defValue);
    }

    /**
     * �?存一个long类型的值�?
     */
    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putLong(key, value).apply();
    }

    /**
     * 获�?�long的value
     */
    public static long getLong(String key, long defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getLong(key, defValue);
    }

    /**
     * �?�出List<String>
     *
     * @param key List<String> 对应的key
     * @return List<String>
     */
    public static List<String> getStrListValue(String key) {
        List<String> strList = new ArrayList<String>();
        int size = getInt(key + "size", 0);
        //Log.d("sp", "" + size);
        for (int i = 0; i < size; i++) {
            strList.add(getString(key + i, null));
        }
        return strList;
    }

    /**
     * 存储List<String>
     *
     * @param context
     * @param key     List<String>对应的key
     * @param strList 对应需�?存储的List<String>
     */
    public static void putStrListValue(String key, List<String> strList) {
        if (null == strList) {
            return;
        }
        // �?存之�?先清�?�已�?存在的数�?�，�?�?数�?�的唯一性
        removeStrList(key);
        int size = strList.size();
        putInt(key + "size", size);
        for (int i = 0; i < size; i++) {
            putString(key + i, strList.get(i));
        }
    }

    /**
     * 清空List<String>所有数�?�
     *
     * @param key List<String>对应的key
     */
    public static void removeStrList(String key) {
        int size = getInt(key + "size", 0);
        if (0 == size) {
            return;
        }
        remove(key + "size");
        for (int i = 0; i < size; i++) {
            remove(key + i);
        }
    }

    /**
     * 清空对应key数�?�
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.remove(key).apply();
    }

    public static boolean getNightMode() {
        return SPUtils.getBoolean(Constants.KEY_MODE_NIGHT, false);
    }

    public static void setNightMode(boolean nightMode) {
        SPUtils.putBoolean(Constants.KEY_MODE_NIGHT, nightMode);
    }

    public static boolean isRead() {
        return SPUtils.getBoolean(Constants.MESSAGE_READ_TIP, false);
    }

    public static void setRead(boolean isRead) {
        SPUtils.putBoolean(Constants.MESSAGE_READ_TIP, isRead);
    }

}
