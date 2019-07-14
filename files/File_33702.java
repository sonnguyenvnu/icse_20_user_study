package com.example.jingbin.cloudreader.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.jingbin.cloudreader.app.CloudReaderApplication;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by jingbin on 2017/2/13.
 */

public class BaseTools {

    //获�?�图片所在文件夹�??称
    public static String getDir(String path) {
        String subString = path.substring(0, path.lastIndexOf('/'));
        return subString.substring(subString.lastIndexOf('/') + 1, subString.length());
    }

    public static int getWindowWidth(Context context) {
        // 获�?��?幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        return mScreenWidth;
    }

    public static int getWindowHeigh(Context context) {
        // 获�?��?幕分辨率
        WindowManager wm = (WindowManager) (context
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int mScreenHeigh = dm.heightPixels;
        return mScreenHeigh;
    }

    //获得状�?�?/通知�?的高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 使用默认方�?显示货�?：
     * 例如:￥12,345.46 默认�?留2�?�?数，四�?五入
     *
     * @param d double
     * @return String
     */
    public static String formatCurrency(double d) {
        String s = "";
        try {
            DecimalFormat nf = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CHINA);
            s = nf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "" + d;
        }
        return s;
    }


    /**
     * 去掉无效�?数点 ".00"
     */

    public static String formatMoney(double d) {
        String tmp = formatCurrency(d);

        if (tmp.endsWith(".00")) {
            return tmp.substring(0, tmp.length() - 3);
        } else {
            return tmp;
        }
    }


    /**
     * 处于栈顶的Activity�??
     */
    public String getTopActivityName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List var2 = am.getRunningTasks(1);
        return ((ActivityManager.RunningTaskInfo) var2.get(0)).topActivity.getClassName();
    }

    public static void setText(String text, TextView textView) {
        if (textView != null) {
            if (TextUtils.isEmpty(text)) {
                textView.setText("");
            } else {
                textView.setText(text);
            }
        }
    }

    /**
     * 获�?�当�?应用的版本�?�
     */
    public static String getVersionName() {
        // 获�?�packagemanager的实例
        PackageManager packageManager = CloudReaderApplication.getInstance().getPackageManager();
        // getPackageName()是你当�?类的包�??，0代表是获�?�版本信�?�
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(CloudReaderApplication.getInstance().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /**
     * 实现文本�?制功能
     *
     * @param content �?制的文本
     */
    public static void copy(String content) {
        if (!TextUtils.isEmpty(content)) {
            // 得到剪贴�?�管�?�器
            ClipboardManager cmb = (ClipboardManager) CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(content.trim());
            // 创建一个剪贴数�?�集，包�?�一个普通文本数�?��?�目（需�?�?制的数�?�）
            ClipData clipData = ClipData.newPlainText(null, content);
            // 把数�?�集设置（�?制）到剪贴�?�
            cmb.setPrimaryClip(clipData);
        }
    }

    /**
     * 获�?�系统剪切�?�内容
     */
    public static String getClipContent() {
        ClipboardManager manager = (ClipboardManager) CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(addedText);
                if (!TextUtils.isEmpty(addedTextString)) {
                    return StringFormatUtil.formatUrl(String.valueOf(addedText));
                }
            }
        }
        return "";
    }

    /**
     * 清空剪切�?�内容
     * 加上  manager.setText(null);  �?然�?米3Android6.0 清空无效
     * 因为api过期使用最新注�?使用 manager.getPrimaryClip()，�?然�?米3Android6.0 清空无效
     */
    public static void clearClipboard() {
        ClipboardManager manager = (ClipboardManager) CloudReaderApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setText(null);
            } catch (Exception e) {
                DebugUtil.error(e.getMessage());
            }
        }
    }


    /**
     * 使用�?览器打开链接
     */
    public static void openLink(Context context, String content) {
        Uri issuesUrl = Uri.parse(content);
        Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
        context.startActivity(intent);
    }

    /**
     * 判断手机是�?�安装�?个应用
     *
     * @param context
     * @param appPackageName 应用包�??
     * @return true：安装，false：未安装
     */
    public static boolean isApplicationAvilible(Context context, String appPackageName) {
        try {
            // 获�?�packagemanager
            PackageManager packageManager = context.getPackageManager();
            // 获�?�所有已安装程�?的包信�?�
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (appPackageName.equals(pn)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * �?�?软键盘
     *
     * @param activity �?�?�?软键盘的activity
     */
    public static void hideSoftKeyBoard(Activity activity) {

        final View v = activity.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            try {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                Log.w("TAG", e.toString());
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showSoftKeyBoard(Activity activity, View view) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    /**
     * �?�起添加群�?程。群�?�：Android 云阅交�?群(727379132) 的 key 为： jSdY9xxzZ7xXG55_V8OUb8ds_YT6JjAn
     * 调用 joinQQGroup(jSdY9xxzZ7xXG55_V8OUb8ds_YT6JjAn) �?��?��?�起手Q客户端申请加群 Android 云阅交�?群(727379132)
     *
     * @param key 由官网生�?的key
     */
    public static void joinQQGroup(Context context, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag�?�根�?�具体产�?需�?自定义，如设置，则在加群界�?�按返回，返回手Q主界�?�，�?设置，按返回会返回到呼起产�?界�?�    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            // 未安装手Q或安装的版本�?支�?
            ToastUtil.showToastLong("未安装手Q或安装的版本�?支�?~");
        }
    }

    public static void joinQQChat(Context context, String qqNumber) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            // 未安装手Q或安装的版本�?支�?
            ToastUtil.showToastLong("未安装手Q或安装的版本�?支�?~");
        }
    }

}
