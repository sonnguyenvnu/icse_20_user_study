package com.vondear.rxtool;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.vondear.rxtool.view.RxToast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @author vondear
 * @date 2016/1/29
 */
public class RxNetTool {

    /**
     * no network
     */
    public static final int NETWORK_NO = -1;
    /**
     * wifi network
     */
    public static final int NETWORK_WIFI = 1;
    /**
     * "2G" networks
     */
    public static final int NETWORK_2G = 2;
    /**
     * "3G" networks
     */
    public static final int NETWORK_3G = 3;
    /**
     * "4G" networks
     */
    public static final int NETWORK_4G = 4;
    /**
     * unknown network
     */
    public static final int NETWORK_UNKNOWN = 5;

    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;

    /**
     * 需添加�?��?
     *
     * @param context 上下文
     * @return 网络类型
     * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     * <p>
     * 它主�?负责的是
     * 1 监视网络连接状�? 包括（Wi-Fi, 2G, 3G, 4G）
     * 2 当网络状�?改�?�时�?��?广播通知
     * 3 网络连接失败�?试连接其他网络
     * 4 �??供API，�?许应用程�?获�?��?�用的网络状�?
     * <p>
     * netTyped 的结果
     * @link #NETWORK_NO      = -1; 当�?无网络连接
     * @link #NETWORK_WIFI    =  1; wifi的情况下
     * @link #NETWORK_2G      =  2; 切�?�到2G环境下
     * @link #NETWORK_3G      =  3; 切�?�到3G环境下
     * @link #NETWORK_4G      =  4; 切�?�到4G环境下
     * @link #NETWORK_UNKNOWN =  5; 未知网络
     */
    public static int getNetWorkType(Context context) {
        // 获�?�ConnectivityManager
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();// 获�?�当�?网络状�?

        int netType = NETWORK_NO;

        if (ni != null && ni.isConnectedOrConnecting()) {

            switch (ni.getType()) {//获�?�当�?网络的状�?
                case ConnectivityManager.TYPE_WIFI:// wifi的情况下
                    netType = NETWORK_WIFI;
                    RxToast.success("切�?�到wifi环境下");
                    break;
                case ConnectivityManager.TYPE_MOBILE:

                    switch (ni.getSubtype()) {
                        case NETWORK_TYPE_GSM:
                        case TelephonyManager.NETWORK_TYPE_GPRS: // �?�通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            netType = NETWORK_2G;
                            RxToast.info("切�?�到2G环境下");
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                        case NETWORK_TYPE_TD_SCDMA:
                            netType = NETWORK_3G;
                            RxToast.info("切�?�到3G环境下");
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:

                        case NETWORK_TYPE_IWLAN:
                            netType = NETWORK_4G;
                            RxToast.info("切�?�到4G环境下");
                            break;
                        default:

                            String subtypeName = ni.getSubtypeName();
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                    || subtypeName.equalsIgnoreCase("WCDMA")
                                    || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                netType = NETWORK_3G;
                            } else {
                                netType = NETWORK_UNKNOWN;
                            }
                            RxToast.normal("未知网络");
                    }
                    break;
                default:
                    netType = 5;
                    RxToast.normal("未知网络");
            }

        } else {
            netType = NETWORK_NO;
            RxToast.error(context, "当�?无网络连接").show();
        }
        return netType;
    }

    /**
     * 获�?�当�?的网络类型(WIFI,2G,3G,4G)
     * <p>�?赖上�?�的方法</p>
     *
     * @param context 上下文
     * @return 网络类型�??称
     * <ul>
     * <li>NETWORK_WIFI   </li>
     * <li>NETWORK_4G     </li>
     * <li>NETWORK_3G     </li>
     * <li>NETWORK_2G     </li>
     * <li>NETWORK_UNKNOWN</li>
     * <li>NETWORK_NO     </li>
     * </ul>
     */
    public static String getNetWorkTypeName(Context context) {
        switch (getNetWorkType(context)) {
            case NETWORK_WIFI:
                return "NETWORK_WIFI";
            case NETWORK_4G:
                return "NETWORK_4G";
            case NETWORK_3G:
                return "NETWORK_3G";
            case NETWORK_2G:
                return "NETWORK_2G";
            case NETWORK_NO:
                return "NETWORK_NO";
            default:
                return "NETWORK_UNKNOWN";
        }
    }

    /**
     * 判断网络连接是�?��?�用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用�?�判断网络连接
            //则�?�以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是�?��?�用
     * 需添加�?��?
     *
     * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     */
    public static boolean isAvailable(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 判断网络是�?�连接
     * 需添加�?��?
     *
     * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 判断是�?�有外网连接（普通方法�?能判断外网的网络是�?�连接，比如连接上局域网）
     * �?�?在主线程使用，会阻塞线程
     */
    public static final boolean ping() {

        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地�?�，�?�以�?��?任何一�?�?��?�的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网�?�3次
            // 读�?�ping的内容，�?�以�?加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
//            Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状�?
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
//            Log.d("----result---", "result = " + result);
        }
        return false;
    }

    /**
     * ping IP
     * �?�?在主线程使用，会阻塞线程
     */
    public static final boolean ping(String ip) {

        String result = null;
        try {
            // ping网�?�3次
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
            // 读�?�ping的内容，�?�以�?加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            //Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状�?
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            //Log.d("----result---", "result = " + result);
        }
        return false;
    }

    /**
     * 判断WIFI是�?�打开
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null
                && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)
                || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断网络连接方�?是�?�为WIFI
     */
    public static boolean isWifi(Context context) {
        NetworkInfo networkINfo = getActiveNetworkInfo(context);
        return networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断wifi是�?�连接状�?
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断是�?�为3G网络
     */
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 判断网络是�?�是4G
     * 需添加�?��?
     *
     * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     */
    public static boolean is4G(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * GPS是�?�打开
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    /*
     * 下�?�列举几个�?�直接跳到�?�网设置的�?图,供大家学习
     *
     *      startActivity(new Intent(android.provider.Settings.ACTION_APN_SETTINGS));
     *      startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
     *
     *  用下�?�两�?方�?设置网络
     *
     *      startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
     *      startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
     */

    /**
     * 打开网络设置界�?�
     * <p>3.0以下打开设置界�?�</p>
     *
     * @param context 上下文
     */
    public static void openWirelessSettings(Context context) {
        if (Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 获�?�活动网络信�?�
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 获�?�移动网络�?�?�商�??称
     * <p>如中国�?�通�?中国移动�?中国电信</p>
     *
     * @param context 上下文
     * @return 移动网络�?�?�商�??称
     */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getNetworkOperatorName() : null;
    }

    /**
     * 获�?�移动终端类型
     *
     * @param context 上下文
     * @return 手机制�?
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE } : 0 手机制�?未知</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM  } : 1 手机制�?为GSM，移动和�?�通</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA } : 2 手机制�?为CDMA，电信</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP  } : 3</li>
     * </ul>
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getPhoneType() : -1;
    }
}
