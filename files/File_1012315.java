package com.xuexiang.xui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.xuexiang.xui.logs.UILog;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * UI全局设置
 *
 * @author xuexiang
 * @since 2018/11/14 上�?�11:40
 */
public class XUI {

    private static volatile XUI sInstance = null;

    private static Application sContext;

    private static boolean sIsTabletChecked;

    private static int sScreenType;

    private XUI() {

    }

    /**
     * 获�?��?�例
     *
     * @return
     */
    public static XUI getInstance() {
        if (sInstance == null) {
            synchronized (XUI.class) {
                if (sInstance == null) {
                    sInstance = new XUI();
                }
            }
        }
        return sInstance;
    }

    //=======================�?始化设置===========================//
    /**
     * �?始化
     *
     * @param context
     */
    public static void init(Application context) {
        sContext = context;
    }

    /**
     * 设置默认字体
     */
    public XUI initFontStyle(String defaultFontAssetPath) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultFontAssetPath)
                .setFontAttrId(R.attr.fontPath)
                .build());
        return this;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 XUI.init() �?始化�?");
        }
    }

    //=======================日志调试===========================//
    /**
     * 设置调试模�?
     *
     * @param tag
     * @return
     */
    public static void debug(String tag) {
        UILog.debug(tag);
    }

    /**
     * 设置调试模�?
     *
     * @param isDebug
     * @return
     */
    public static void debug(boolean isDebug) {
        UILog.debug(isDebug);
    }

    //=======================字体===========================//
    /**
     * @return 获�?�默认字体
     */
    @Nullable
    public static Typeface getDefaultTypeface() {
        String fontPath = CalligraphyConfig.get().getFontPath();
        if (!TextUtils.isEmpty(fontPath)) {
            return TypefaceUtils.load(getContext().getAssets(), fontPath);
        }
        return null;
    }

    /**
     * @param fontPath 字体路径
     * @return 获�?�默认字体
     */
    @Nullable
    public static Typeface getDefaultTypeface(String fontPath) {
        if (TextUtils.isEmpty(fontPath)) {
            fontPath = CalligraphyConfig.get().getFontPath();
        }
        if (!TextUtils.isEmpty(fontPath)) {
            return TypefaceUtils.load(getContext().getAssets(), fontPath);
        }
        return null;
    }

    //=======================�?幕尺寸===========================//

    /**
     * 检验设备�?幕的尺寸
     * @param context
     * @return
     */
    private static int checkScreenSize(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            //�?明是平�?�
            if (screenSize >= Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                return UIConsts.ScreenType.BIG_TABLET;
            } else {
                return UIConsts.ScreenType.SMALL_TABLET;
            }
        } else {
            return UIConsts.ScreenType.PHONE;
        }
    }

    /**
     * 判断是�?�平�?�设备
     * @return true:平�?�,false:手机
     */
    public static int getScreenType() {
        if (sIsTabletChecked) {
            return sScreenType;
        }
        sScreenType = checkScreenSize(XUI.getContext());
        sIsTabletChecked = true;
        return sScreenType;
    }

    /**
     * 是�?�是平�?�
     * @return
     */
    public static boolean isTablet() {
        return getScreenType() == UIConsts.ScreenType.SMALL_TABLET || getScreenType() == UIConsts.ScreenType.BIG_TABLET;
    }

    /**
     * �?始化主题
     * @param activity
     */
    public static void initTheme(Activity activity) {
        int screenType = getScreenType();
        if (screenType == UIConsts.ScreenType.PHONE) {
            activity.setTheme(R.style.XUITheme_Phone);
        } else if (screenType == UIConsts.ScreenType.SMALL_TABLET){
            activity.setTheme(R.style.XUITheme_Tablet_Small);
        } else {
            activity.setTheme(R.style.XUITheme_Tablet_Big);
        }
    }

}
