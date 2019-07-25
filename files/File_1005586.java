package com.limpoxe.fairy.util;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * Copy From FreeReflection
 * https://github.com/tiann/FreeReflection
 */
public class FreeReflection {
    private static final String TAG = "FreeReflection";

    private static Object sVmRuntime;
    private static Method setHiddenApiExemptions;

    static {
        try {
            Method forName = Class.class.getDeclaredMethod("forName", String.class);
            Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);

            Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
            Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
            sVmRuntime = getRuntime.invoke(null);
            // 到此处为止其实已�?获得了绕开�?制的方法，�?�续所有的�?��?API都�?�以通过上�?�获�?�到的forName�?getDeclaredMethod这两个对象�?�获�?�目标类和函数(如果需�?的�?还�?�以增加getField)

            // 下�?�这个并�?是必需的。�?��?过赶巧系统本身已�?�??供了一个�?�?开关。打开开关以�?�，�?�续所有的�?��?API都�?�以直接调用了，就连上�?�准备的几个跳�?�函数都�?需�?了
            setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
        } catch (Throwable e) {
            Log.e(TAG, "reflect bootstrap failed:", e);
        }
    }

    public static boolean exempt(String method) {
        return exempt(new String[]{method});
    }

    public static boolean exempt(String... methods) {
        if (sVmRuntime == null || setHiddenApiExemptions == null) {
            return false;
        }

        try {
            setHiddenApiExemptions.invoke(sVmRuntime, new Object[]{methods});
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean exemptAll() {
        //指定被�?�?的方法签�??字符串。所有方法签�??字符串都是L开头，因此L�?�以�?�?所有接�?�
        return exempt(new String[]{"L"});
    }
}
