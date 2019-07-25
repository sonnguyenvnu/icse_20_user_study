package com.xuexiang.xui.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.core.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * 状�?�?工具
 *
 * @author XUE
 * @since 2019/3/22 10:50
 */
public class StatusBarUtils {

    private final static int STATUSBAR_TYPE_DEFAULT = 0;
    private final static int STATUSBAR_TYPE_MIUI = 1;
    private final static int STATUSBAR_TYPE_FLYME = 2;
    private final static int STATUSBAR_TYPE_ANDROID6 = 3; // Android 6.0
    private final static int STATUS_BAR_DEFAULT_HEIGHT_DP = 25; // 大部分状�?�?都是25dp
    // 在�?些机�?上存在�?�?�的density值，所以增加两个虚拟值
    public static float sVirtualDensity = -1;
    public static float sVirtualDensityDpi = -1;
    private static int sStatusbarHeight = -1;
    private static @StatusBarType
    int mStatuBarType = STATUSBAR_TYPE_DEFAULT;
    private static Integer sTransparentValue;

    public static void translucent(Activity activity) {
        translucent(activity.getWindow());
    }

    public static void translucent(Window window) {
        translucent(window, 0x40000000);
    }

    private static boolean supportTranslucent() {
        return Build.VERSION.SDK_INT >= KITKAT
                // Essential Phone 在 Android 8 之�?沉浸�?�?�得�?全，系统�?从状�?�?顶部开始布局�?�会下�?� WindowInsets
                && !(DeviceUtils.isEssentialPhone() && Build.VERSION.SDK_INT < 26);
    }

    private StatusBarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 沉浸�?状�?�?。
     * 支�? 4.4 以上版本的 MIUI 和 Flyme，以�?� 5.0 以上版本的其他 Android。
     *
     * @param activity 需�?被设置沉浸�?状�?�?的 Activity。
     */
    public static void translucent(Activity activity, @ColorInt int colorOn5x) {
        Window window = activity.getWindow();
        translucent(window, colorOn5x);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void translucent(Window window, @ColorInt int colorOn5x) {
        if (!supportTranslucent()) {
            // 版本�?于4.4，�?对�?考虑沉浸�?
            return;
        }

        if (isNotchOfficialSupport()) {
            handleDisplayCutoutMode(window);
        }

        // �?米和魅�?4.4 以上版本支�?沉浸�?
        // �?米 Android 6.0 ，开�?�版 7.7.13 �?�以�?�版本设置黑色字体�?�需�? clear FLAG_TRANSLUCENT_STATUS, 因此还原为官方模�?
        if (DeviceUtils.isMeizu() || (DeviceUtils.isMIUI() && Build.VERSION.SDK_INT < Build.VERSION_CODES.M)) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && supportTransclentStatusBar6()) {
                // android 6以�?��?�以改状�?�?字体颜色，因此�?�以自行设置为�?明
                // ZUK Z1是个�?�类，自家应用�?�以实现字体颜色�?�色，但没开放接�?�
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                // android 5�?能修改状�?�?字体颜色，因此直接用FLAG_TRANSLUCENT_STATUS，nexus表现为�?��?明
                // 魅�?和�?米的表现如何？
                // update: 部分手机�?用FLAG_TRANSLUCENT_STATUS时背景�?是�?��?明而是没有背景了。。。。。
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                // 采�?�setStatusBarColor的方�?，部分机型�?支�?，那就纯黑了，�?�?状�?�?图标�?��?
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorOn5x);
            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // android4.4的默认是从上到下黑到�?明，我们的背景是白色，很难看，因此�?��?�魅�?和�?米的
//        } else if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1){
//            // 如果app 为白色，需�?更改状�?�?颜色，因此�?能让19一下支�?�?明状�?�?
//            Window window = activity.getWindow();
//            Integer transparentValue = getStatusBarAPITransparentValue(activity);
//            if(transparentValue != null) {
//                window.getDecorView().setSystemUiVisibility(transparentValue);
//            }
        }
    }

    public static boolean isNotchOfficialSupport() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    @TargetApi(Build.VERSION_CODES.P)
    private static void handleDisplayCutoutMode(final Window window) {
        View decorView = window.getDecorView();
        if (decorView != null) {
            if (ViewCompat.isAttachedToWindow(decorView)) {
                realHandleDisplayCutoutMode(window, decorView);
            } else {
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        v.removeOnAttachStateChangeListener(this);
                        realHandleDisplayCutoutMode(window, v);
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {

                    }
                });
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private static void realHandleDisplayCutoutMode(Window window, View decorView) {
        if (decorView.getRootWindowInsets() != null &&
                decorView.getRootWindowInsets().getDisplayCutout() != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams
                    .LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
        }
    }

    /**
     * 设置状�?�?黑色字体图标，
     * 支�? 4.4 以上版本 MIUI 和 Flyme，以�?� 6.0 以上版本的其他 Android
     *
     * @param activity 需�?被处�?�的 Activity
     */
    public static boolean setStatusBarLightMode(Activity activity) {
        if (activity == null) return false;
        // 无语系列：ZTK C2016�?�能时间和电池图标�?�色。。。。
        if (DeviceUtils.isZTKC2016()) {
            return false;
        }

        if (mStatuBarType != STATUSBAR_TYPE_DEFAULT) {
            return setStatusBarLightMode(activity, mStatuBarType);
        }
        if (Build.VERSION.SDK_INT >= KITKAT) {
            if (isMIUICustomStatusBarLightModeImpl() && MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = STATUSBAR_TYPE_MIUI;
                return true;
            } else if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                mStatuBarType = STATUSBAR_TYPE_FLYME;
                return true;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Android6SetStatusBarLightMode(activity.getWindow(), true);
                mStatuBarType = STATUSBAR_TYPE_ANDROID6;
                return true;
            }
        }
        return false;
    }

    /**
     * 已知系统类型时，设置状�?�?黑色字体图标。
     * 支�? 4.4 以上版本 MIUI 和 Flyme，以�?� 6.0 以上版本的其他 Android
     *
     * @param activity 需�?被处�?�的 Activity
     * @param type     StatusBar 类型，对应�?�?�的系统
     */
    private static boolean setStatusBarLightMode(Activity activity, @StatusBarType int type) {
        if (type == STATUSBAR_TYPE_MIUI) {
            return MIUISetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == STATUSBAR_TYPE_FLYME) {
            return FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if (type == STATUSBAR_TYPE_ANDROID6) {
            return Android6SetStatusBarLightMode(activity.getWindow(), true);
        }
        return false;
    }


    /**
     * 设置状�?�?白色字体图标
     * 支�? 4.4 以上版本 MIUI 和 Flyme，以�?� 6.0 以上版本的其他 Android
     */
    public static boolean setStatusBarDarkMode(Activity activity) {
        if (activity == null) return false;
        if (mStatuBarType == STATUSBAR_TYPE_DEFAULT) {
            // 默认状�?，�?需�?处�?�
            return true;
        }

        if (mStatuBarType == STATUSBAR_TYPE_MIUI) {
            return MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if (mStatuBarType == STATUSBAR_TYPE_FLYME) {
            return FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (mStatuBarType == STATUSBAR_TYPE_ANDROID6) {
            return Android6SetStatusBarLightMode(activity.getWindow(), false);
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static int changeStatusBarModeRetainFlag(Window window, int out) {
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        return out;
    }

    public static int retainSystemUiFlag(Window window, int out, int type) {
        int now = window.getDecorView().getSystemUiVisibility();
        if ((now & type) == type) {
            out |= type;
        }
        return out;
    }


    /**
     * 设置状�?�?字体图标为深色，Android 6
     *
     * @param window 需�?设置的窗�?�
     * @param light  是�?�把状�?�?字体�?�图标颜色设置为深色
     * @return boolean �?功执行返回true
     */
    @TargetApi(Build.VERSION_CODES.M)
    private static boolean Android6SetStatusBarLightMode(Window window, boolean light) {
        View decorView = window.getDecorView();
        int systemUi = light ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        systemUi = changeStatusBarModeRetainFlag(window, systemUi);
        decorView.setSystemUiVisibility(systemUi);
        if (DeviceUtils.isMIUIV9()) {
            // MIUI 9 低于 6.0 版本�?旧�?�能回退到以�?的方案
            // https://github.com/Tencent/QMUI_Android/issues/160
            MIUISetStatusBarLightMode(window, light);
        }
        return true;
    }

    /**
     * 设置状�?�?字体图标为深色，需�? MIUIV6 以上
     *
     * @param window 需�?设置的窗�?�
     * @param light  是�?�把状�?�?字体�?�图标颜色设置为深色
     * @return boolean �?功执行返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean MIUISetStatusBarLightMode(Window window, boolean light) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (light) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状�?�?�?明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception ignored) {

            }
        }
        return result;
    }

    /**
     * 更改状�?�?图标�?文字颜色的方案是�?�是MIUI自家的， MIUI9 && Android 6 之�?�用回Android原生实现
     * �?�?米开�?�文档说明：https://dev.mi.com/console/doc/detail?pId=1159
     */
    private static boolean isMIUICustomStatusBarLightModeImpl() {
        if (DeviceUtils.isMIUIV9() && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return DeviceUtils.isMIUIV5() || DeviceUtils.isMIUIV6() ||
                DeviceUtils.isMIUIV7() || DeviceUtils.isMIUIV8();
    }

    /**
     * 设置状�?�?图标为深色和魅�?特定的文字风格
     * �?�以用�?�判断是�?�为 Flyme 用户
     *
     * @param window 需�?设置的窗�?�
     * @param light  是�?�把状�?�?字体�?�图标颜色设置为深色
     * @return boolean �?功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean light) {
        boolean result = false;
        if (window != null) {
            // flyme 在 6.2.0.0A 支�?了 Android 官方的实现方案，旧的方案失效
            Android6SetStatusBarLightMode(window, light);

            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (light) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception ignored) {

            }
        }
        return result;
    }

    /**
     * 获�?�是�?�全�?
     *
     * @return 是�?�全�?
     */
    public static boolean isFullScreen(Activity activity) {
        boolean ret = false;
        try {
            WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
            ret = (attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * API19之�?�?明状�?�?：获�?�设置�?明状�?�?的system ui visibility的值，这是部分有�??供接�?�的rom使用的
     * http://stackoverflow.com/questions/21865621/transparent-status-bar-before-4-4-kitkat
     */
    public static Integer getStatusBarAPITransparentValue(Context context) {
        if (sTransparentValue != null) {
            return sTransparentValue;
        }
        String[] systemSharedLibraryNames = context.getPackageManager()
                .getSystemSharedLibraryNames();
        String fieldName = null;
        for (String lib : systemSharedLibraryNames) {
            if ("touchwiz".equals(lib)) {
                fieldName = "SYSTEM_UI_FLAG_TRANSPARENT_BACKGROUND";
            } else if (lib.startsWith("com.sonyericsson.navigationbar")) {
                fieldName = "SYSTEM_UI_FLAG_TRANSPARENT";
            }
        }

        if (fieldName != null) {
            try {
                Field field = View.class.getField(fieldName);
                if (field != null) {
                    Class<?> type = field.getType();
                    if (type == int.class) {
                        sTransparentValue = field.getInt(null);
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return sTransparentValue;
    }

    /**
     * 检测 Android 6.0 是�?��?�以�?�用 window.setStatusBarColor(Color.TRANSPARENT)。
     */
    public static boolean supportTransclentStatusBar6() {
        return !(DeviceUtils.isZUKZ1() || DeviceUtils.isZTKC2016());
    }

    /**
     * 获�?�状�?�?的高度。
     */
    public static int getStatusBarHeight(Context context) {
        if (sStatusbarHeight == -1) {
            initStatusBarHeight(context);
        }
        return sStatusbarHeight;
    }

    private static void initStatusBarHeight(Context context) {
        Class<?> clazz;
        Object obj = null;
        Field field = null;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            obj = clazz.newInstance();
            if (DeviceUtils.isMeizu()) {
                try {
                    field = clazz.getField("status_bar_height_large");
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            if (field == null) {
                field = clazz.getField("status_bar_height");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        if (field != null && obj != null) {
            try {
                int id = Integer.parseInt(field.get(obj).toString());
                sStatusbarHeight = context.getResources().getDimensionPixelSize(id);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        if (DeviceUtils.isTablet(context)
                && sStatusbarHeight > DensityUtils.dp2px(context, STATUS_BAR_DEFAULT_HEIGHT_DP)) {
            //状�?�?高度大于25dp的平�?�，状�?�?通常在下方
            sStatusbarHeight = 0;
        } else {
            if (sStatusbarHeight <= 0) {
                if (sVirtualDensity == -1) {
                    sStatusbarHeight = DensityUtils.dp2px(context, STATUS_BAR_DEFAULT_HEIGHT_DP);
                } else {
                    sStatusbarHeight = (int) (STATUS_BAR_DEFAULT_HEIGHT_DP * sVirtualDensity + 0.5f);
                }
            }
        }
    }

    public static void setVirtualDensity(float density) {
        sVirtualDensity = density;
    }

    public static void setVirtualDensityDpi(float densityDpi) {
        sVirtualDensityDpi = densityDpi;
    }

    @IntDef({STATUSBAR_TYPE_DEFAULT, STATUSBAR_TYPE_MIUI, STATUSBAR_TYPE_FLYME, STATUSBAR_TYPE_ANDROID6})
    @Retention(RetentionPolicy.SOURCE)
    private @interface StatusBarType {
    }


    /**
     * 全�?
     *
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        fullScreen(activity.getWindow());
    }

    /**
     * 全�?
     *
     * @param window
     */
    public static void fullScreen(Window window) {
        if (Build.VERSION.SDK_INT >= KITKAT) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    /**
     * �?�消全�?
     *
     * @param activity
     * @param statusBarColor     状�?�?的颜色
     * @param navigationBarColor 导航�?的颜色
     */
    public static void cancelFullScreen(Activity activity, @ColorInt int statusBarColor, @ColorInt int navigationBarColor) {
        cancelFullScreen(activity, statusBarColor, navigationBarColor);
    }

    /**
     * �?�消全�?
     *
     * @param window
     * @param statusBarColor     状�?�?的颜色
     * @param navigationBarColor 导航�?的颜色
     */
    public static void cancelFullScreen(Window window, @ColorInt int statusBarColor, @ColorInt int navigationBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (statusBarColor != -1) {
                window.setStatusBarColor(statusBarColor);
            }
            if (navigationBarColor != -1) {
                window.setNavigationBarColor(navigationBarColor);
            }
        }
    }

    /**
     * �?�消全�?
     *
     * @param activity
     */
    public static void cancelFullScreen(Activity activity) {
        cancelFullScreen(activity.getWindow());
    }

    /**
     * �?�消全�?
     *
     * @param window
     */
    public static void cancelFullScreen(Window window) {
        cancelFullScreen(window, -1, -1);
    }


    public static void setNavigationBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上�?�以直接设置 navigation颜色
            activity.getWindow().setNavigationBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View navigationBar = new View(activity);
            FrameLayout.LayoutParams params;
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationBarHeight(activity));
            params.gravity = Gravity.BOTTOM;
            navigationBar.setLayoutParams(params);
            navigationBar.setBackgroundColor(color);
            decorView.addView(navigationBar);
        } else {
            //4.4以下无法设置NavigationBar颜色
        }

    }

    public static int getNavigationBarHeight(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }

}
