package com.vondear.rxtool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.io.File;

/**
 *
 * @author vondear
 * @date 2016/1/24
 */
public class RxIntentTool {

    /**
     * èŽ·å?–å®‰è£…App(æ”¯æŒ?7.0)çš„æ„?å›¾
     *
     * @param context
     * @param filePath
     * @return
     */
    public static Intent getInstallAppIntent(Context context, String filePath) {
        //apkæ–‡ä»¶çš„æœ¬åœ°è·¯å¾„
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri contentUri = RxFileTool.getUriForFile(context, apkfile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * èŽ·å?–å?¸è½½Appçš„æ„?å›¾
     *
     * @param packageName åŒ…å??
     * @return æ„?å›¾
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * èŽ·å?–æ‰“å¼€Appçš„æ„?å›¾
     *
     * @param context     ä¸Šä¸‹æ–‡
     * @param packageName åŒ…å??
     * @return æ„?å›¾
     */
    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return getIntentByPackageName(context, packageName);
    }

    /**
     * æ ¹æ?®åŒ…å??èŽ·å?–æ„?å›¾
     *
     * @param context     ä¸Šä¸‹æ–‡
     * @param packageName åŒ…å??
     * @return æ„?å›¾
     */
    private static Intent getIntentByPackageName(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * èŽ·å?–Appä¿¡æ?¯çš„æ„?å›¾
     *
     * @param packageName åŒ…å??
     * @return æ„?å›¾
     */
    public static Intent getAppInfoIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        return intent.setData(Uri.parse("package:" + packageName));
    }

    /**
     * èŽ·å?–Appä¿¡æ?¯åˆ†äº«çš„æ„?å›¾
     *
     * @param info åˆ†äº«ä¿¡æ?¯
     * @return æ„?å›¾
     */
    public static Intent getShareInfoIntent(String info) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        return intent.putExtra(Intent.EXTRA_TEXT, info);
    }

    /**
     * èŽ·å?–å…¶ä»–åº”ç”¨çš„Intent
     *
     * @param packageName åŒ…å??
     * @param className   å…¨ç±»å??
     * @return æ„?å›¾
     */
    public static Intent getComponentNameIntent(String packageName, String className) {
        return getComponentNameIntent(packageName, className, null);
    }

    /**
     * èŽ·å?–å…¶ä»–åº”ç”¨çš„Intent
     *
     * @param packageName åŒ…å??
     * @param className   å…¨ç±»å??
     * @return æ„?å›¾
     */
    public static Intent getComponentNameIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * èŽ·å?–åº”ç”¨è¯¦æƒ…é¡µé?¢å…·ä½“è®¾ç½® intent
     *
     * @return
     */
    public static Intent getAppDetailsSettingsIntent(Context mContext) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }
        return localIntent;
    }

    /**
     * èŽ·å?–åº”ç”¨è¯¦æƒ…é¡µé?¢å…·ä½“è®¾ç½® intent
     *
     * @param packageName åŒ…å??
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", packageName, null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", packageName);
        }
        return localIntent;
    }
}
