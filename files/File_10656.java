package com.vondear.rxtool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vondear
 * @date 2016/1/24
 */
public class RxAppTool {

    /**
     * 安装App(支�?7.0)
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void installApp(Context context, String filePath) {
        context.startActivity(RxIntentTool.getInstallAppIntent(context, filePath));
    }

    /**
     * 安装App（支�?7.0）
     *
     * @param context 上下文
     * @param file    文件
     */
    public static void installApp(Context context, File file) {
        if (!RxFileTool.isFileExists(file)) return;
        installApp(context, file.getAbsolutePath());
    }

    /**
     * 安装App（支�?7.0）
     *
     * @param activity    activity
     * @param filePath    文件路径
     * @param requestCode 请求值
     */
    public static void installApp(Activity activity, String filePath, int requestCode) {
        activity.startActivityForResult(RxIntentTool.getInstallAppIntent(activity, filePath), requestCode);
    }

    /**
     * 安装App(支�?7.0)
     *
     * @param activity    activity
     * @param file        文件
     * @param requestCode 请求值
     */
    public static void installApp(Activity activity, File file, int requestCode) {
        if (!RxFileTool.isFileExists(file)) return;
        installApp(activity, file.getAbsolutePath(), requestCode);
    }

    /**
     * �?�默安装App
     * <p>�?�root需添加�?��? {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}</p>
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return {@code true}: 安装�?功<br>{@code false}: 安装失败
     */
    public static boolean installAppSilent(Context context, String filePath) {
        File file = RxFileTool.getFileByPath(filePath);
        if (!RxFileTool.isFileExists(file)) return false;
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
        RxShellTool.CommandResult commandResult = RxShellTool.execCmd(command, !isSystemApp(context), true);
        return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
    }

    /**
     * 判断App是�?�是系统应用
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isSystemApp(Context context) {
        return isSystemApp(context, context.getPackageName());
    }

    /**
     * 判断App是�?�是系统应用
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isSystemApp(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return false;
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �?�载App
     *
     * @param context     上下文
     * @param packageName 包�??
     */
    public static void uninstallApp(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return;
        context.startActivity(RxIntentTool.getUninstallAppIntent(packageName));
    }

    /**
     * �?�载App
     *
     * @param activity    activity
     * @param packageName 包�??
     * @param requestCode 请求值
     */
    public static void uninstallApp(Activity activity, String packageName, int requestCode) {
        if (RxDataTool.isNullString(packageName)) return;
        activity.startActivityForResult(RxIntentTool.getUninstallAppIntent(packageName), requestCode);
    }

    /**
     * �?�默�?�载App
     * <p>�?�root需添加�?��? {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}</p>
     *
     * @param context     上下文
     * @param packageName 包�??
     * @param isKeepData  是�?��?留数�?�
     * @return {@code true}: �?�载�?功<br>{@code false}: �?�载�?功
     */
    public static boolean uninstallAppSilent(Context context, String packageName, boolean isKeepData) {
        if (RxDataTool.isNullString(packageName)) return false;
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
        RxShellTool.CommandResult commandResult = RxShellTool.execCmd(command, !isSystemApp(context), true);
        return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
    }

    /**
     * 判断App是�?�有root�?��?
     *
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isAppRoot() {
        RxShellTool.CommandResult result = RxShellTool.execCmd("echo root", true);
        if (result.result == 0) {
            return true;
        }
        if (result.errorMsg != null) {
            Log.d("isAppRoot", result.errorMsg);
        }
        return false;
    }

    /**
     * 打开App
     *
     * @param context     上下文
     * @param packageName 包�??
     */
    public static void launchApp(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return;
        context.startActivity(RxIntentTool.getLaunchAppIntent(context, packageName));
    }

    /**
     * 打开App
     *
     * @param activity    activity
     * @param packageName 包�??
     * @param requestCode 请求值
     */
    public static void launchApp(Activity activity, String packageName, int requestCode) {
        if (RxDataTool.isNullString(packageName)) return;
        activity.startActivityForResult(RxIntentTool.getLaunchAppIntent(activity, packageName), requestCode);
    }

    /**
     * 获�?�App包�??
     *
     * @param context 上下文
     * @return App包�??
     */
    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获�?�App具体设置
     *
     * @param context 上下文
     */
    public static void getAppDetailsSettings(Context context) {
        getAppDetailsSettings(context, context.getPackageName());
    }

    /**
     * 获�?�App具体设置
     *
     * @param context     上下文
     * @param packageName 包�??
     */
    public static void getAppDetailsSettings(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return;
        context.startActivity(RxIntentTool.getAppDetailsSettingsIntent(packageName));
    }

    /**
     * 获�?�App�??称
     *
     * @param context 上下文
     * @return App�??称
     */
    public static String getAppName(Context context) {
        return getAppName(context, context.getPackageName());
    }

    /**
     * 获�?�App�??称
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App�??称
     */
    public static String getAppName(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获�?�App图标
     *
     * @param context 上下文
     * @return App图标
     */
    public static Drawable getAppIcon(Context context) {
        return getAppIcon(context, context.getPackageName());
    }

    /**
     * 获�?�App图标
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App图标
     */
    public static Drawable getAppIcon(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获�?�App路径
     *
     * @param context 上下文
     * @return App路径
     */
    public static String getAppPath(Context context) {
        return getAppPath(context, context.getPackageName());
    }

    /**
     * 获�?�App路径
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App路径
     */
    public static String getAppPath(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获�?�App版本�?�
     *
     * @param context 上下文
     * @return App版本�?�
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    /**
     * 获�?�App版本�?�
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App版本�?�
     */
    public static String getAppVersionName(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获�?�App版本�?
     *
     * @param context 上下文
     * @return App版本�?
     */
    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context.getPackageName());
    }

    /**
     * 获�?�App版本�?
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App版本�?
     */
    public static int getAppVersionCode(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return -1;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 判断App是�?�是Debug版本
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isAppDebug(Context context) {
        return isAppDebug(context, context.getPackageName());
    }

    /**
     * 判断App是�?�是Debug版本
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isAppDebug(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return false;
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获�?�App签�??
     *
     * @param context 上下文
     * @return App签�??
     */
    public static Signature[] getAppSignature(Context context) {
        return getAppSignature(context, context.getPackageName());
    }

    /**
     * 获�?�App签�??
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return App签�??
     */
    @SuppressLint("PackageManagerGetSignatures")
    public static Signature[] getAppSignature(Context context, String packageName) {
        if (RxDataTool.isNullString(packageName)) return null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获�?�应用签�??的的SHA1值
     * <p>�?��?�此判断高德，百度地图key是�?�正确</p>
     *
     * @param context 上下文
     * @return 应用签�??的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
     */
    public static String getAppSignatureSHA1(Context context) {
        return getAppSignatureSHA1(context, context.getPackageName());
    }

    /**
     * 获�?�应用签�??的的SHA1值
     * <p>�?��?�此判断高德，百度地图key是�?�正确</p>
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return 应用签�??的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
     */
    public static String getAppSignatureSHA1(Context context, String packageName) {
        Signature[] signature = getAppSignature(context, packageName);
        if (signature == null) return null;
        return RxEncryptTool.encryptSHA1ToString(signature[0].toByteArray()).
                replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    /**
     * 判断App是�?�处于�?�?�
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isAppForeground(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        if (infos == null || infos.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return info.processName.equals(context.getPackageName());
            }
        }
        return false;
    }

    /**
     * 判断App是�?�处于�?�?�
     * <p>当�?是查看当�?App，且SDK大于21时，
     * 需添加�?��? {@code <uses-permission android:name="android.permission.PACKAGE_USAGE_STATS"/>}</p>
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isAppForeground(Context context, String packageName) {
        return !RxDataTool.isNullString(packageName) && packageName.equals(RxProcessTool.getForegroundProcessName(context));
    }
    //----------------------------------------------------------------------------------------------------------------

    /**
     * 判断App是�?�安装
     *
     * @param context     上下文
     * @param packageName 包�??
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(Context context, String packageName) {
        return !RxDataTool.isNullString(packageName) && RxIntentTool.getLaunchAppIntent(context, packageName) != null;
    }

    /**
     * 安装APK
     *
     * @param context
     * @param APK_PATH
     */
    public static void InstallAPK(Context context, String APK_PATH) {//�??示安装APK
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + APK_PATH), "application/vnd.android.package-archive");
        context.startActivity(i);
    }

    /**
     * 获�?�当�?App信�?�
     * <p>AppInfo（�??称，图标，包�??，版本�?�，版本Code，是�?�安装在SD�?�，是�?�是用户程�?）</p>
     *
     * @param context 上下文
     * @return 当�?应用的AppInfo
     */
    public static AppInfo getAppInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi != null ? getBean(pm, pi) : null;
    }

    /**
     * 得到AppInfo的Bean
     *
     * @param pm 包的管�?�
     * @param pi 包的信�?�
     * @return AppInfo类
     */
    private static AppInfo getBean(PackageManager pm, PackageInfo pi) {
        ApplicationInfo ai = pi.applicationInfo;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packageName = pi.packageName;
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSD = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        boolean isUser = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
        return new AppInfo(name, icon, packageName, packagePath, versionName, versionCode, isSD, isUser);
    }

    /**
     * 获�?�所有已安装App信�?�
     * <p>{@link #getBean(PackageManager, PackageInfo)}（�??称，图标，包�??，包路径，版本�?�，版本Code，是�?�安装在SD�?�，是�?�是用户程�?）</p>
     * <p>�?赖上�?�的getBean方法</p>
     *
     * @param context 上下文
     * @return 所有已安装的AppInfo列表
     */
    public static List<AppInfo> getAllAppsInfo(Context context) {
        List<AppInfo> list = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        // 获�?�系统中安装的所有软件信�?�
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo pi : installedPackages) {
            if (pi != null) {
                list.add(getBean(pm, pi));
            }
        }
        return list;
    }

    /**
     * 判断当�?App处于�?�?�还是�?��?�
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p>
     * <p>并且必须是系统应用该方法�?有效</p>
     *
     * @param context 上下文
     * @return {@code true}: �?��?�<br>{@code false}: �?�?�
     */
    public static boolean isAppBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            return !topActivity.getPackageName().equals(context.getPackageName());
        }
        return false;
    }

    /**
     * 清除App所有数�?�
     *
     * @param context  上下文
     * @param dirPaths 目录路径
     * @return {@code true}: �?功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(Context context, String... dirPaths) {
        File[] dirs = new File[dirPaths.length];
        int i = 0;
        for (String dirPath : dirPaths) {
            dirs[i++] = new File(dirPath);
        }
        return cleanAppData(context, dirs);
    }

    /**
     * 清除App所有数�?�
     *
     * @param dirs 目录
     * @return {@code true}: �?功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(Context context, File... dirs) {
        boolean isSuccess = RxFileTool.cleanInternalCache(context);
        isSuccess &= RxFileTool.cleanInternalDbs(context);
        isSuccess &= RxFileTool.cleanInternalSP(context);
        isSuccess &= RxFileTool.cleanInternalFiles(context);
        isSuccess &= RxFileTool.cleanExternalCache(context);
        for (File dir : dirs) {
            isSuccess &= RxFileTool.cleanCustomCache(dir);
        }
        return isSuccess;
    }

    /**
     * �?装App信�?�的Bean类
     */
    public static class AppInfo {

        private String name;
        private Drawable icon;
        private String packageName;
        private String packagePath;
        private String versionName;
        private int versionCode;
        private boolean isSD;
        private boolean isUser;

        /**
         * @param name        �??称
         * @param icon        图标
         * @param packageName 包�??
         * @param packagePath 包路径
         * @param versionName 版本�?�
         * @param versionCode 版本Code
         * @param isSD        是�?�安装在SD�?�
         * @param isUser      是�?�是用户程�?
         */
        public AppInfo(String name, Drawable icon, String packageName, String packagePath,
                       String versionName, int versionCode, boolean isSD, boolean isUser) {
            this.setName(name);
            this.setIcon(icon);
            this.setPackageName(packageName);
            this.setPackagePath(packagePath);
            this.setVersionName(versionName);
            this.setVersionCode(versionCode);
            this.setSD(isSD);
            this.setUser(isUser);
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public boolean isSD() {
            return isSD;
        }

        public void setSD(boolean SD) {
            isSD = SD;
        }

        public boolean isUser() {
            return isUser;
        }

        public void setUser(boolean user) {
            isUser = user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packagName) {
            this.packageName = packagName;
        }

        public String getPackagePath() {
            return packagePath;
        }

        public void setPackagePath(String packagePath) {
            this.packagePath = packagePath;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

//        @Override
//        public String toString() {
//            return getName() + "\n"
//                    + getIcon() + "\n"
//                    + getPackageName() + "\n"
//                    + getPackagePath() + "\n"
//                    + getVersionName() + "\n"
//                    + getVersionCode() + "\n"
//                    + isSD() + "\n"
//                    + isUser() + "\n";
//        }
    }
}
