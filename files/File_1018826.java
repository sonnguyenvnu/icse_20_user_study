/*
 *
 * Copyright 2018 iQIYI.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.qiyi.pluginlibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import org.qiyi.pluginlibrary.component.InstrActivityProxy1;
import org.qiyi.pluginlibrary.constant.IntentConstant;
import org.qiyi.pluginlibrary.loader.PluginClassLoader;
import org.qiyi.pluginlibrary.runtime.PluginLoadedApk;
import org.qiyi.pluginlibrary.runtime.PluginManager;

/**
 * 设置和解�?代�?�组件的包�??和组件�??<br/>
 * 这里将这些信�?�使用Action�?存而�?是Intent的extra�?存是因为
 * 如果�?�件在Intent中放入了自定义Bean,会出现ClassNotFoundException,
 * 除�?��?�件的ClassLoader注入到基线的ClassLoader
 * <p>
 */
public class IntentUtils {
    private static final String TAG = "IntentUtils";
    private static final String TOKEN = "@#@#";

    private IntentUtils() {

    }

    /**
     * 将�?�件的包�??信�?�放入Intent#action字段
     *
     * @param mIntent  原始跳转的Intent
     * @param pkgName  �?�件包�??
     */
    public static void setProxyInfo(Intent mIntent, String pkgName) {
        String mPkgName = TextUtils.isEmpty(pkgName) ? "" : pkgName;
        String oriAction = mIntent.getAction();

        StringBuilder mBuilder = new StringBuilder(mPkgName);
        mBuilder.append(TOKEN)
                .append(oriAction);
        if (PluginDebugLog.isDebug()) {
            PluginDebugLog.log(TAG, "setProxyInfo mLast Action is:" + mBuilder.toString());
        }
        mIntent.setAction(mBuilder.toString());
    }

    /**
     * 从Activity中解�?�?�件的包�??
     */
    public static String parsePkgNameFromActivity(Activity activity) {
        String pkgName = "";
        if (activity instanceof InstrActivityProxy1) {
            pkgName = ((InstrActivityProxy1) activity).getPluginPackageName();
        }
        if (TextUtils.isEmpty(pkgName) && activity.getIntent() != null) {
            String[] result = parsePkgAndClsFromIntent(activity.getIntent());
            if (result != null && result.length >= 1) {
                pkgName = result[0];
            }
        }
        // fallback, 通过ClassLoader识别
        if (TextUtils.isEmpty(pkgName)) {
            ClassLoader cl = activity.getClass().getClassLoader();
            if (cl instanceof PluginClassLoader) {
                pkgName = ((PluginClassLoader) cl).getPackageName();
            }
        }

        return pkgName;
    }

    /**
     * 从intent中解�?出�?�件包�??和跳转组件的�??
     */
    public static String[] parsePkgAndClsFromIntent(Intent intent) {
        String[] result = new String[2];
        if (intent == null) {
            result[0] = "";
            result[1] = "";
            return result;
        }

        String pkgName = getPluginPackage(intent);
        if (!TextUtils.isEmpty(pkgName)) {
            PluginLoadedApk loadedApk = PluginManager.getPluginLoadedApkByPkgName(pkgName);
            if (loadedApk != null) {
                //解决�?�件中跳转自定义Bean对象失败的问题
                intent.setExtrasClassLoader(loadedApk.getPluginClassLoader());
            }
        }
        try {
            result[0] = getTargetPackage(intent);
            result[1] = getTargetClass(intent);
        } catch (RuntimeException e) {
            // Intent里放置了�?�件自定义的�?列化数�?�
            // 进程�?�?�时，android.os.BadParcelableException: ClassNotFoundException when unmarshalling:
            // �?�能从 action 里�?�读�?� packageName
            //
            // Caused by: java.io.InvalidClassException: org.iqiyi.video.mode.lpt3; Incompatible class (SUID):
            result[0] = pkgName;
        }
        PluginDebugLog.runtimeFormatLog(TAG, "pluginPkg:%s, pluginCls:%s", result[0], result[1]);
        return result;
    }

    /**
     * 从Action里获�?��?�件包�??
     */
    public static String getPluginPackage(Intent intent) {
        if (intent == null) {
            return "";
        }

        String action = intent.getAction();
        String pkgName = "";
        if (!TextUtils.isEmpty(action) && action.contains(TOKEN)) {
            PluginDebugLog.log(TAG, "getPluginPackage action is " + action);
            String[] info = action.split(TOKEN);
            if (info != null && info.length == 2) {
                pkgName = info[0];
            }
        }

        return pkgName;
    }

    public static boolean isIntentForPlugin(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra(IntentConstant.EXTRA_TARGET_IS_PLUGIN_KEY, false);
    }

    public static String getTargetPackage(Intent intent) {
        if (intent == null) {
            return "";
        }
        return intent.getStringExtra(IntentConstant.EXTRA_TARGET_PACKAGE_KEY);
    }

    public static String getTargetClass(Intent intent) {
        if (intent == null) {
            return "";
        }
        return intent.getStringExtra(IntentConstant.EXTRA_TARGET_CLASS_KEY);
    }

    /**
     * �?置�?��?Intent中的Action
     */
    public static void resetAction(Intent intent) {
        if (intent == null) {
            return;
        }

        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.contains(TOKEN)) {
            String[] info = action.split(TOKEN);
            if (info != null && info.length == 2) {
                action = info[1];
            }
        }

        PluginDebugLog.log(TAG, "resetAction: " + action);
        if (TextUtils.isEmpty(action) || action.equalsIgnoreCase("null")) {
            action = null;
        }
        intent.setAction(action);
    }

    /**
     * 从Activity中dump优先的�?�件信�?�
     */
    public static String dump(Activity activity) {
        String info = "";
        if (activity instanceof InstrActivityProxy1) {
            info = ((InstrActivityProxy1) activity).dump();
        } else {
            Intent intent = activity.getIntent();
            String[] pkgCls = parsePkgAndClsFromIntent(intent);
            if (pkgCls != null && pkgCls.length == 2) {
                info = "Package&Cls is: " + activity + " " + (pkgCls[0] + " " + pkgCls[1]) + " flg=0x"
                        + Integer.toHexString(intent.getFlags());
            } else {
                info = "Package&Cls is: " + activity + " flg=0x" + Integer.toHexString(intent.getFlags());
            }
        }
        return info;
    }
}
