package com.example.jingbin.cloudreader.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 用于判断是�?是�?�网状�?
 *
 * @author Dzy
 */
public class CheckNetwork {

    /**
     * 判断网络是�?�连通
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            if(context!=null){
                @SuppressWarnings("static-access")
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                return info != null && info.isConnected();
            }else{
                /**如果context为空，就返回false，表示网络未连接*/
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info != null && (info.getType() == ConnectivityManager.TYPE_WIFI);
        } else {
            /**如果context为null就表示为未连接*/
            return false;
        }

    }

}
