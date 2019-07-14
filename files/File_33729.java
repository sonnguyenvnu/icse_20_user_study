package com.example.jingbin.cloudreader.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * @author jingbin
 * @data 2018/7/18
 * @Description æ?ƒé™?å¤„ç?†
 * // å­˜å‚¨æ?ƒé™?
 * Manifest.permission.WRITE_EXTERNAL_STORAGE
 * // ç›¸æœº
 * Manifest.permission.CAMERA
 * // èŽ·å¾—ç»?çº¬åº¦1
 * Manifest.permission.ACCESS_FINE_LOCATION
 * // èŽ·å¾—ç»?çº¬åº¦2
 * Manifest.permission.ACCESS_COARSE_LOCATION
 * // èŽ·å?–æ‰‹æœºæ?ƒé™? ä¸ºäº†èŽ·å¾—uuidï¼š
 * Manifest.permission.READ_PHONE_STATE
 * // åº”ç”¨åˆ—è¡¨
 * Manifest.permission.GET_PACKAGE_SIZE
 */

public class PermissionHandler {

    private static final int PERMISSION_CODE = 10010;
    private static final int PERMISSION_CODE_ONE = 10011;

    /**
     * æ£€æŸ¥æ˜¯å?¦æœ‰æ?ƒé™?ï¼Œæ²¡æœ‰è¯·æ±‚æ?ƒé™?
     */
    public static boolean isHandlePermission(Activity activity, String permission, String permission2) {
        if (isPermission(activity, permission, permission2)) {
            // æœ‰æ?ƒé™?
            return true;
        } else {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);
            int permissionCheck2 = ContextCompat.checkSelfPermission(activity, permission2);
            // æ²¡æœ‰æ?ƒé™?
            ArrayList<String> list = new ArrayList<>();
            // æ²¡æœ‰æŽˆæ?ƒ
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                list.add(permission);
            }
            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                list.add(permission2);
            }
            if (list.size() > 0) {
                String[] strings = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    strings[i] = list.get(i);
                }
                ActivityCompat.requestPermissions(activity, strings, PERMISSION_CODE);
            }
            return false;
        }
    }

    /**
     * æ£€æŸ¥å?ªæœ‰ä¸€ä¸ªæ?ƒé™?ï¼ŒçŽ°å?ªç”¨äºŽç›¸å†Œæ?ƒé™?
     */
    public static boolean isHandlePermission(Activity activity, String permission) {
        if (isPermission(activity, permission)) {
            // æœ‰æ?ƒé™?
            return true;
        } else {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);
            // æ²¡æœ‰æŽˆæ?ƒ
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSION_CODE_ONE);
            }
            return false;
        }
    }

    /**
     * æ£€æµ‹æ˜¯å?¦æœ‰å?•ä¸ªæ?ƒé™?
     */
    private static boolean isPermission(Activity activity, String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);
        // æœ‰ä¸€ä¸ªæ²¡æœ‰æŽˆæ?ƒå°±ä½œä¸º æ²¡æœ‰æ?ƒé™?å¤„ç?†
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * æ£€æµ‹æ˜¯å?¦å…¨éƒ¨æœ‰æ?ƒé™?
     */
    private static boolean isPermission(Activity activity, String permission, String permission2) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);
        int permissionCheck2 = ContextCompat.checkSelfPermission(activity, permission2);
        // æœ‰ä¸€ä¸ªæ²¡æœ‰æŽˆæ?ƒå°±ä½œä¸º æ²¡æœ‰æ?ƒé™?å¤„ç?†
        return permissionCheck == PackageManager.PERMISSION_GRANTED && permissionCheck2 == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * vivoä¸?èµ°å›žè°ƒ
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, OnHandlePermissionListener listener) {
        onRequestPermissionsResult(null, null, requestCode, permissions, grantResults, listener);
    }

    /**
     * ä¸¤ä¸ªæ?ƒé™?æ—¶çš„æ??ç¤º
     */
    public static void onRequestPermissionsResult(String toastTextTwo, int requestCode, String[] permissions, int[] grantResults, OnHandlePermissionListener listener) {
        onRequestPermissionsResult(toastTextTwo, null, requestCode, permissions, grantResults, listener);
    }

    /**
     * @param toastTextTwo ä¸¤ä¸ªæ?ƒé™?æ—¶çš„æ??ç¤º
     * @param toastTextOne ä¸€ä¸ªæ?ƒé™?æ—¶çš„æ??ç¤º
     */
    public static void onRequestPermissionsResult(String toastTextTwo, String toastTextOne, int requestCode, String[] permissions, int[] grantResults, OnHandlePermissionListener listener) {
        if (grantResults != null) {
            DebugUtil.error("-----requestCode:" + requestCode);
            for (int i : grantResults) {
                DebugUtil.error("-----grantResults:" + i);
            }
            for (String p : permissions) {
                DebugUtil.error("-----permissions:" + p);
            }

            switch (requestCode) {
                case PERMISSION_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (listener != null) {
                            listener.granted();
                        }
                    } else {
                        if (TextUtils.isEmpty(toastTextTwo)) {
                            ToastUtil.showToastLong("æ?ƒé™?è¢«æ‹’ç»?");
                        } else {
                            ToastUtil.showToastLong(toastTextTwo);
                        }
                    }
                    break;
                case PERMISSION_CODE_ONE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (listener != null) {
                            listener.granted();
                        }
                    } else {
                        if (TextUtils.isEmpty(toastTextOne)) {
                            ToastUtil.showToastLong("æ?ƒé™?è¢«æ‹’ç»?");
                        } else {
                            ToastUtil.showToastLong(toastTextOne);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    interface OnHandlePermissionListener {
        /**
         * å?Œæ„?
         */
        void granted();
    }
}
