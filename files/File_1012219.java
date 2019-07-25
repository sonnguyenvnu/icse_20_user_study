package com.xuexiang.xui.logs;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * 框架日志记录
 *
 * @author xuexiang
 * @since 2018/11/14 上�?�11:59
 */
public final class UILog {

    //==============常�?================//
    /**
     * 默认tag
     */
    private final static String DEFAULT_LOG_TAG = "[XUI]";
    /**
     * 最大日志优先级�?日志优先级为最大等级，所有日志都�?打�?�】
     */
    private final static int MAX_LOG_PRIORITY = 10;
    /**
     * 最�?日志优先级�?日志优先级为最�?等级，所有日志都打�?�】
     */
    private final static int MIN_LOG_PRIORITY = 0;

    //==============属性================//
    /**
     * 默认的日志记录为Logcat
     */
    private static ILogger sILogger = new LogcatLogger();

    private static String sTag = DEFAULT_LOG_TAG;
    /**
     * 是�?�是调试模�?
     */
    private static boolean sIsDebug = false;
    /**
     * 日志打�?�优先级
     */
    private static int sLogPriority = MAX_LOG_PRIORITY;

    //==============属性设置================//

    /**
     * 设置日志记录者的接�?�
     *
     * @param logger
     */
    public static void setLogger(@NonNull ILogger logger) {
        sILogger = logger;
    }

    /**
     * 设置日志的tag
     *
     * @param tag
     */
    public static void setTag(String tag) {
        sTag = tag;
    }

    /**
     * 设置是�?�是调试模�?
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    /**
     * 设置打�?�日志的等级（�?�打�?�改等级以上的日志）
     *
     * @param priority
     */
    public static void setPriority(int priority) {
        sLogPriority = priority;
    }

    //===================对外接�?�=======================//

    /**
     * 设置是�?�打开调试
     * @param isDebug
     */
    public static void debug(boolean isDebug) {
        if (isDebug) {
            debug(DEFAULT_LOG_TAG);
        } else {
            debug("");
        }
    }

    /**
     * 设置调试模�?
     *
     * @param tag
     */
    public static void debug(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            setDebug(true);
            setPriority(MIN_LOG_PRIORITY);
            setTag(tag);
        } else {
            setDebug(false);
            setPriority(MAX_LOG_PRIORITY);
            setTag("");
        }
    }

    //=============打�?�方法===============//
    /**
     * 打�?�任何（所有）信�?�
     *
     * @param msg
     */
    public static void v(String msg) {
        if (enableLog(Log.VERBOSE)) {
            sILogger.log(Log.VERBOSE, sTag, msg, null);
        }
    }

    /**
     * 打�?�任何（所有）信�?�
     *
     * @param tag
     * @param msg
     */
    public static void vTag(String tag, String msg) {
        if (enableLog(Log.VERBOSE)) {
            sILogger.log(Log.VERBOSE, tag, msg, null);
        }
    }

    /**
     * 打�?�调试信�?�
     *
     * @param msg
     */
    public static void d(String msg) {
        if (enableLog(Log.DEBUG)) {
            sILogger.log(Log.DEBUG, sTag, msg, null);
        }
    }

    /**
     * 打�?�调试信�?�
     *
     * @param tag
     * @param msg
     */
    public static void dTag(String tag, String msg) {
        if (enableLog(Log.DEBUG)) {
            sILogger.log(Log.DEBUG, tag, msg, null);
        }
    }

    /**
     * 打�?��??示性的信�?�
     *
     * @param msg
     */
    public static void i(String msg) {
        if (enableLog(Log.INFO)) {
            sILogger.log(Log.INFO, sTag, msg, null);
        }
    }

    /**
     * 打�?��??示性的信�?�
     *
     * @param tag
     * @param msg
     */
    public static void iTag(String tag, String msg) {
        if (enableLog(Log.INFO)) {
            sILogger.log(Log.INFO, tag, msg, null);
        }
    }

    /**
     * 打�?�warning警告信�?�
     *
     * @param msg
     */
    public static void w(String msg) {
        if (enableLog(Log.WARN)) {
            sILogger.log(Log.WARN, sTag, msg, null);
        }
    }

    /**
     * 打�?�warning警告信�?�
     *
     * @param tag
     * @param msg
     */
    public static void wTag(String tag, String msg) {
        if (enableLog(Log.WARN)) {
            sILogger.log(Log.WARN, tag, msg, null);
        }
    }

    /**
     * 打�?�出错信�?�
     *
     * @param msg
     */
    public static void e(String msg) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, sTag, msg, null);
        }
    }

    /**
     * 打�?�出错信�?�
     *
     * @param tag
     * @param msg
     */
    public static void eTag(String tag, String msg) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, tag, msg, null);
        }
    }

    /**
     * 打�?�出错堆栈信�?�
     *
     * @param t
     */
    public static void e(Throwable t) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, sTag, null, t);
        }
    }

    /**
     * 打�?�出错堆栈信�?�
     *
     * @param tag
     * @param t
     */
    public static void eTag(String tag, Throwable t) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, tag, null, t);
        }
    }


    /**
     * 打�?�出错堆栈信�?�
     *
     * @param msg
     * @param t
     */
    public static void e(String msg, Throwable t) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, sTag, msg, t);
        }
    }

    /**
     * 打�?�出错堆栈信�?�
     *
     * @param tag
     * @param msg
     * @param t
     */
    public static void eTag(String tag, String msg, Throwable t) {
        if (enableLog(Log.ERROR)) {
            sILogger.log(Log.ERROR, tag, msg, t);
        }
    }

    /**
     * 打�?�严�?的错误信�?�
     *
     * @param msg
     */
    public static void wtf(String msg) {
        if (enableLog(Log.ASSERT)) {
            sILogger.log(Log.ASSERT, sTag, msg, null);
        }
    }

    /**
     * 打�?�严�?的错误信�?�
     *
     * @param tag
     * @param msg
     */
    public static void wtfTag(String tag, String msg) {
        if (enableLog(Log.ASSERT)) {
            sILogger.log(Log.ASSERT, tag, msg, null);
        }
    }

    /**
     * 能�?�打�?�
     *
     * @param logPriority
     * @return
     */
    private static boolean enableLog(int logPriority) {
        return sILogger != null && sIsDebug && logPriority >= sLogPriority;
    }
}
