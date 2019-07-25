package com.cheikh.lazywaimai.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

@SuppressLint("SimpleDateFormat")
public class SystemUtil {

    public static final String LOG_TAG = SystemUtil.class.getSimpleName();

    /**
     * deviceID的组�?为：渠�?�标志+识别符�?��?标志+hash�?�的终端识别符
     *
     * 渠�?�标志为：
     * 1，andriod（a）
     *
     * 识别符�?��?标志：
     * 1， wifi mac地�?�（wifi）；
     * 2， IMEI（imei）；
     * 3， �?列�?�（sn）；
     * 4， id：�?机�?。若�?�?�的都�?��?到时，则�?机生�?一个�?机�?，需�?缓存。
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠�?�标志
        deviceId.append("a");

        try {
            // wifi mac地�?�
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!TextUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                return deviceId.toString();
            }

            // IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                return deviceId.toString();
            }

            // �?列�?�（sn）
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                return deviceId.toString();
            }

            // 如果上�?�都没有， 则生�?一个id：�?机�?
            String uuid = getUUID(context);
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID(context));
        }

        return deviceId.toString();
    }


    /**
     * 得到全局唯一UUID
     */
    private static String getUUID(Context context) {
        String uuid = null;
        SharedPreferences sp = context.getSharedPreferences("sysCacheMap", Context.MODE_PRIVATE);
        if (sp != null) {
            uuid = sp.getString("uuid", "");
        }

        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            sp.edit().putString("uuid", uuid).apply();
        }

        return uuid;
    }

    /**
     * 获�?�手机系统SDK版本
     *
     * @return 如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获�?�系统版本
     *
     * @return 形如2.3.3
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获�?�当�?应用程�?的版本�??
     */
    public static String getAppVersionName(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            throw new RuntimeException(SystemUtil.class.getName()
                    + "the application not found");
        }
        return version;
    }

    /**
     * 获�?�当�?应用程�?的版本�?�
     */
    public static int getAppVersionCode(Context context) {
        int version = 0;
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException(SystemUtil.class.getName()
                    + "the application not found");
        }
        return version;
    }

    /**
     * �?�?系统键盘
     *
     * <br>
     * <b>警告</b> 必须是确定键盘显示时�?能调用
     */
    public static void hideKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断当�?应用程�?是�?��?��?��?行
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    // �?��?��?行
                    return true;
                } else {
                    // �?�?��?行
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断手机是�?�处�?��?�眠
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        return isSleeping;
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 回到home，�?��?��?行
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * 获�?�应用签�??
     *
     * @param context
     * @param pkgName
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo pis = context.getPackageManager().getPackageInfo(
                    pkgName, PackageManager.GET_SIGNATURES);
            return hexdigest(pis.signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            throw new RuntimeException(SystemUtil.class.getName() + "the "
                    + pkgName + "'s application not found");
        }
    }

    /**
     * 将签�??字符串转�?��?需�?的32�?签�??
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
                98, 99, 100, 101, 102 };
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0;; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获�?�设备的�?�用内存大�?
     *
     * @param cxt
     *            应用上下文对象context
     * @return 当�?内存大�?
     */
    public static int getDeviceUsableMemory(Context cxt) {
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // 返回当�?系统的�?�用内存
        return (int) (mi.availMem / (1024 * 1024));
    }

    /**
     * 清�?��?��?�进程与�?务
     *
     * @param cxt
     *            应用上下文对象context
     * @return 被清�?�的数�?
     */
    public static int gc(Context cxt) {
        long i = getDeviceUsableMemory(cxt);
        int count = 0; // 清�?�掉的进程数
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        // 获�?�正在�?行的service列表
        List<RunningServiceInfo> serviceList = am.getRunningServices(100);
        if (serviceList != null)
            for (RunningServiceInfo service : serviceList) {
                if (service.pid == android.os.Process.myPid())
                    continue;
                try {
                    android.os.Process.killProcess(service.pid);
                    count++;
                } catch (Exception e) {
                    e.getStackTrace();
                    continue;
                }
            }

        // 获�?�正在�?行的进程列表
        List<RunningAppProcessInfo> processList = am.getRunningAppProcesses();
        if (processList != null)
            for (RunningAppProcessInfo process : processList) {
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的进程都是�?��?��?进程，也就是在�?��?��?行�?�
                if (process.importance > RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    // pkgList 得到该进程下�?行的包�??
                    String[] pkgList = process.pkgList;
                    for (String pkgName : pkgList) {
                        Log.d(LOG_TAG, "======正在�?�死包�??：" + pkgName);
                        try {
                            am.killBackgroundProcesses(pkgName);
                            count++;
                        } catch (Exception e) { // 防止�?外�?�生
                            e.getStackTrace();
                            continue;
                        }
                    }
                }
            }
        Log.d(LOG_TAG, "清�?�了" + (getDeviceUsableMemory(cxt) - i) + "M内存");
        return count;
    }
}
