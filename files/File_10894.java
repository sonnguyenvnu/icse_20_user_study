package com.vondear.rxtool;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author vondear
 * @date 2016/12/21
 */

public class RxProcessTool {
    /**
     * èŽ·å?–å‰?å?°çº¿ç¨‹åŒ…å??
     * <p>å½“ä¸?æ˜¯æŸ¥çœ‹å½“å‰?Appï¼Œä¸”SDKå¤§äºŽ21æ—¶ï¼Œ
     * éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.PACKAGE_USAGE_STATS"/>}</p>
     *
     * @return å‰?å?°åº”ç”¨åŒ…å??
     */
    public static String getForegroundProcessName(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        if (infos != null && infos.size() != 0) {
            for (ActivityManager.RunningAppProcessInfo info : infos) {
                if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return info.processName;
                }
            }
        }
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            System.out.println(list);
            if (list.size() > 0) {// æœ‰"æœ‰æ?ƒæŸ¥çœ‹ä½¿ç”¨æ?ƒé™?çš„åº”ç”¨"é€‰é¡¹
                try {
                    ApplicationInfo info = packageManager.getApplicationInfo(context.getPackageName(), 0);
                    AppOpsManager aom = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                    if (aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName) != AppOpsManager.MODE_ALLOWED) {
                        context.startActivity(intent);
                    }
                    if (aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName) != AppOpsManager.MODE_ALLOWED) {
                        Log.d("getForegroundApp", "æ²¡æœ‰æ‰“å¼€\"æœ‰æ?ƒæŸ¥çœ‹ä½¿ç”¨æ?ƒé™?çš„åº”ç”¨\"é€‰é¡¹");
                        return null;
                    }
                    UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
                    long endTime = System.currentTimeMillis();
                    long beginTime = endTime - 86400000 * 7;
                    List<UsageStats> usageStatses = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, beginTime, endTime);
                    if (usageStatses == null || usageStatses.isEmpty()) return null;
                    UsageStats recentStats = null;
                    for (UsageStats usageStats : usageStatses) {
                        if (recentStats == null || usageStats.getLastTimeUsed() > recentStats.getLastTimeUsed()) {
                            recentStats = usageStats;
                        }
                    }
                    return recentStats == null ? null : recentStats.getPackageName();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("getForegroundApp", "æ— \"æœ‰æ?ƒæŸ¥çœ‹ä½¿ç”¨æ?ƒé™?çš„åº”ç”¨\"é€‰é¡¹");
            }
        }
        return null;
    }

    /**
     * èŽ·å?–å?Žå?°æœ?åŠ¡è¿›ç¨‹
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
     *
     * @return å?Žå?°æœ?åŠ¡è¿›ç¨‹
     */
    public static Set<String> getAllBackgroundProcesses(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        Set<String> set = new HashSet<>();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            Collections.addAll(set, info.pkgList);
        }
        return set;
    }

    /**
     * æ?€æ­»å?Žå?°æœ?åŠ¡è¿›ç¨‹
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
     *
     * @return è¢«æš‚æ—¶æ?€æ­»çš„æœ?åŠ¡é›†å?ˆ
     */
    public static Set<String> killAllBackgroundProcesses(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        Set<String> set = new HashSet<>();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            for (String pkg : info.pkgList) {
                am.killBackgroundProcesses(pkg);
                set.add(pkg);
            }
        }
        infos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            for (String pkg : info.pkgList) {
                set.remove(pkg);
            }
        }
        return set;
    }

    /**
     * æ?€æ­»å?Žå?°æœ?åŠ¡è¿›ç¨‹
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
     *
     * @param packageName åŒ…å??
     * @return {@code true}: æ?€æ­»æˆ?åŠŸ<br>{@code false}: æ?€æ­»å¤±è´¥
     */
    public static boolean killBackgroundProcesses(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        if (infos == null || infos.size() == 0) return true;
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (Arrays.asList(info.pkgList).contains(packageName)) {
                am.killBackgroundProcesses(packageName);
            }
        }
        infos = am.getRunningAppProcesses();
        if (infos == null || infos.size() == 0) return true;
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (Arrays.asList(info.pkgList).contains(packageName)) {
                return false;
            }
        }
        return true;
    }
}
