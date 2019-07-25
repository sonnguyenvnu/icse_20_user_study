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
package org.qiyi.pluginlibrary;

import android.annotation.SuppressLint;
import android.app.ActivityThread;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;

import org.qiyi.pluginlibrary.component.wraper.NeptuneInstrument;
import org.qiyi.pluginlibrary.component.wraper.PluginInstrument;
import org.qiyi.pluginlibrary.install.IInstallCallBack;
import org.qiyi.pluginlibrary.install.IUninstallCallBack;
import org.qiyi.pluginlibrary.pm.PluginLiteInfo;
import org.qiyi.pluginlibrary.pm.PluginPackageManagerNative;
import org.qiyi.pluginlibrary.runtime.PluginManager;
import org.qiyi.pluginlibrary.utils.PluginDebugLog;
import org.qiyi.pluginlibrary.utils.ReflectionUtils;
import org.qiyi.pluginlibrary.utils.VersionUtils;

import java.io.File;

/**
 * Neptuneå¯¹å¤–æš´éœ²çš„ç»Ÿä¸€è°ƒç”¨ç±»
 */
public class Neptune {
    private static final String TAG = "Neptune";

    public static final boolean SEPARATED_CLASSLOADER = true;
    public static final boolean NEW_COMPONENT_PARSER = true;

    @SuppressLint("StaticFieldLeak")
    private static Context sHostContext;

    private static NeptuneConfig sGlobalConfig;

    private static Instrumentation mHostInstr;

    private Neptune() {
    }

    /**
     * åˆ?å§‹åŒ–Neptuneæ?’ä»¶çŽ¯å¢ƒ
     *
     * @param application  å®¿ä¸»çš„Appliction
     * @param config  é…?ç½®ä¿¡æ?¯
     */
    public static void init(Application application, NeptuneConfig config) {

        sHostContext = application;
        sGlobalConfig = config != null ? config
                : new NeptuneConfig.NeptuneConfigBuilder().build();

        PluginDebugLog.setIsDebug(sGlobalConfig.isDebug());

        boolean hookInstr = VersionUtils.hasPie() || sGlobalConfig.getSdkMode() != NeptuneConfig.LEGACY_MODE;
        if (hookInstr) {
            hookInstrumentation();
        }

        // è°ƒç”¨getInstance()æ–¹æ³•ä¼šåˆ?å§‹åŒ–bindService
        PluginPackageManagerNative.getInstance(sHostContext).setPackageInfoManager(sGlobalConfig.getPluginInfoProvider());
        // æ³¨å†Œå?¸è½½ç›‘å?¬å¹¿æ’­
        //PluginManager.registerUninstallReceiver(sHostContext);
    }

    public static Context getHostContext() {
        return sHostContext;
    }

    public static NeptuneConfig getConfig() {
        if (sGlobalConfig == null) {
            sGlobalConfig = new NeptuneConfig.NeptuneConfigBuilder().build();
        }
        return sGlobalConfig;
    }


    /**
     * å??å°„æ›¿æ?¢ActivityThreadçš„mInstrumentation
     */
    private static void hookInstrumentation() {

        PluginDebugLog.runtimeLog(TAG, "need to hook Instrumentation for plugin framework");
        ActivityThread activityThread = ActivityThread.currentActivityThread();
        Instrumentation hostInstr = getHostInstrumentation();

        if (hostInstr != null) {
            String hostInstrName = hostInstr.getClass().getName();
            PluginDebugLog.runtimeLog(TAG, "host Instrument name: " + hostInstrName);

            if (hostInstrName.startsWith("com.chaozhuo.superme")
                    || hostInstrName.startsWith("com.lody.virtual")) {
                // warning: ç‰¹æ®Šcaseï¼ŒVirtualAppçŽ¯å¢ƒï¼Œæš‚ä¸?Hook
                PluginDebugLog.runtimeLog(TAG, "reject hook instrument, run in VirtualApp Environment");
            } else if (hostInstr instanceof NeptuneInstrument) {
                // already hooked
                PluginDebugLog.runtimeLog(TAG, "ActivityThread Instrumentation already hooked");
            } else {
                PluginInstrument pluginInstrument = new NeptuneInstrument(hostInstr);
                ReflectionUtils.on(activityThread).set("mInstrumentation", pluginInstrument);
                PluginDebugLog.runtimeLog(TAG, "init hook ActivityThread Instrumentation success");
            }
        } else {
            PluginDebugLog.runtimeLog(TAG, "init hook ActivityThread Instrumentation failed, hostInstr==null");
        }
    }

    /**
     * èŽ·å?–ActivityThreadçš„Instrumentationå¯¹è±¡
     */
    public static Instrumentation getHostInstrumentation() {

        if (mHostInstr == null) {
            ActivityThread activityThread = ActivityThread.currentActivityThread();
            Instrumentation hostInstr = activityThread.getInstrumentation();
            mHostInstr = PluginInstrument.unwrap(hostInstr);
        }

        return mHostInstr;
    }

    /**
     * å®‰è£…sdå?¡ä¸Šçš„æ?’ä»¶
     *
     * @param context å®¿ä¸»çš„Context
     * @param apkPath æ?’ä»¶apkè·¯å¾„
     */
    public static void install(Context context, String apkPath) {
        install(context, apkPath, null);
    }

    /**
     * å®‰è£…sdä¸Šçš„æ?’ä»¶
     *
     * @param context  å®¿ä¸»çš„Context
     * @param apkPath  æ?’ä»¶apkè·¯å¾„
     * @param callBack å®‰è£…å›žè°ƒ
     */
    public static void install(Context context, String apkPath, IInstallCallBack callBack) {

        File apkFile = new File(apkPath);
        if (!apkFile.exists()) {
            return;
        }

        PluginLiteInfo liteInfo = new PluginLiteInfo();
        Context mContext = ensureContext(context);
        PackageInfo packageInfo = mContext.getPackageManager()
                .getPackageArchiveInfo(apkPath, 0);
        if (packageInfo != null) {
            liteInfo.mPath = apkPath;
            liteInfo.packageName = packageInfo.packageName;
            liteInfo.pluginVersion = packageInfo.versionName;
            install(mContext, liteInfo, callBack);
        }
    }

    /**
     * å®‰è£…ä¸€ä¸ªæ?’ä»¶
     *
     * @param context  å®¿ä¸»çš„Context
     * @param info     æ?’ä»¶çš„ä¿¡æ?¯ï¼ŒåŒ…æ‹¬åŒ…å??ï¼Œè·¯å¾„ç­‰
     * @param callBack å®‰è£…å›žè°ƒ
     */
    public static void install(Context context, PluginLiteInfo info, IInstallCallBack callBack) {
        // install
        Context mContext = ensureContext(context);
        PluginPackageManagerNative.getInstance(mContext).install(info, callBack);
    }


    /**
     * æ ¹æ?®åŒ…å??åˆ é™¤æ?’ä»¶apkï¼Œsoï¼Œdexç­‰æ•°æ?®
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  å¾…åˆ é™¤æ?’ä»¶çš„åŒ…å??
     */
    public static void deletePackage(Context context, String pkgName) {
        deletePackage(context, pkgName, null);
    }


    /**
     * æ ¹æ?®åŒ…å??åˆ é™¤æ?’ä»¶apkï¼Œsoï¼Œdexç­‰æ•°æ?®
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  å¾…åˆ é™¤æ?’ä»¶çš„åŒ…å??
     * @param callBack  å?¸è½½å›žè°ƒ
     */
    public static void deletePackage(Context context, String pkgName, IUninstallCallBack callBack) {
        Context mContext = ensureContext(context);
        PluginLiteInfo info = PluginPackageManagerNative.getInstance(mContext).getPackageInfo(pkgName);
        if (info != null) {
            deletePackage(mContext, info, callBack);
        }
    }

    /**
     * åˆ é™¤æ?’ä»¶apkï¼Œdexï¼Œsoç­‰æ•°æ?®
     *
     * @param context å®¿ä¸»çš„Context
     * @param info    å¾…åˆ é™¤æ?’ä»¶çš„ä¿¡æ?¯ï¼ŒåŒ…æ‹¬åŒ…å??ï¼Œè·¯å¾„ç­‰
     * @param callBack å?¸è½½å›žè°ƒ
     */
    public static void deletePackage(Context context, PluginLiteInfo info, IUninstallCallBack callBack) {
        // uninstall
        Context mContext = ensureContext(context);
        PluginPackageManagerNative.getInstance(mContext).deletePackage(info, callBack);
    }

    /**
     * æ ¹æ?®åŒ…å??å?¸è½½ä¸€ä¸ªæ?’ä»¶
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  å¾…å?¸è½½æ?’ä»¶çš„åŒ…å??
     */
    public static void uninstall(Context context, String pkgName) {
        uninstall(context, pkgName, null);
    }


    /**
     * æ ¹æ?®åŒ…å??å?¸è½½ä¸€ä¸ªæ?’ä»¶
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  å¾…å?¸è½½æ?’ä»¶çš„åŒ…å??
     * @param callBack  å?¸è½½å›žè°ƒ
     */
    public static void uninstall(Context context, String pkgName, IUninstallCallBack callBack) {
        Context mContext = ensureContext(context);
        PluginLiteInfo info = PluginPackageManagerNative.getInstance(mContext).getPackageInfo(pkgName);
        if (info != null) {
            uninstall(mContext, info, callBack);
        }
    }

    /**
     * å?¸è½½ä¸€ä¸ªæ?’ä»¶
     *
     * @param context å®¿ä¸»çš„Context
     * @param info    å¾…å?¸è½½æ?’ä»¶çš„ä¿¡æ?¯ï¼ŒåŒ…æ‹¬åŒ…å??ï¼Œè·¯å¾„ç­‰
     * @param callBack å?¸è½½å›žè°ƒ
     */
    public static void uninstall(Context context, PluginLiteInfo info, IUninstallCallBack callBack) {
        // uninstall
        Context mContext = ensureContext(context);
        PluginPackageManagerNative.getInstance(mContext).uninstall(info, callBack);
    }

    /**
     * å?¯åŠ¨ä¸€ä¸ªæ?’ä»¶çš„å…¥å?£ç±»
     *
     * @param mHostContext  å®¿ä¸»çš„Context
     * @param pkgName  å¾…å?¯åŠ¨æ?’ä»¶çš„åŒ…å??
     */
    public static void launchPlugin(Context mHostContext, String pkgName) {
        // start plugin
        PluginManager.launchPlugin(mHostContext, pkgName);
    }

    /**
     * æ ¹æ?®Intentå?¯åŠ¨ä¸€ä¸ªæ?’ä»¶
     *
     * @param mHostContext  å®¿ä¸»çš„Context
     * @param intent  éœ€è¦?å?¯åŠ¨æ?’ä»¶çš„Intent
     */
    public static void launchPlugin(Context mHostContext, Intent intent) {
        // start plugin, é»˜è®¤é€‰æ‹©è¿›ç¨‹
        PluginManager.launchPlugin(mHostContext, intent, null);
    }

    /**
     * æ ¹æ?®Intentå?¯åŠ¨ä¸€ä¸ªæ?’ä»¶ï¼ŒæŒ‡å®šè¿?è¡Œè¿›ç¨‹çš„å??ç§°
     *
     * @param mHostContext  å®¿ä¸»çš„Context
     * @param intent  éœ€è¦?å?¯åŠ¨æ?’ä»¶çš„Intent
     * @param processName  æŒ‡å®šå?¯åŠ¨æ?’ä»¶è¿?è¡Œçš„è¿›ç¨‹
     */
    public static void launchPlugin(Context mHostContext, Intent intent, String processName) {
        // start plugin, æŒ‡å®šè¿›ç¨‹
        PluginManager.launchPlugin(mHostContext, intent, processName);
    }

    /**
     * æ ¹æ?®Intentå?¯åŠ¨ä¸€ä¸ªæ?’ä»¶ï¼Œæ”¯æŒ?ServiceConnection
     *
     * @param mHostContext  å®¿ä¸»çš„Context
     * @param intent éœ€è¦?å?¯åŠ¨çš„æ?’ä»¶Intent
     * @param sc  ServiceConnection
     * @param processName è¿?è¡Œçš„è¿›ç¨‹å??
     */
    public static void launchPlugin(Context mHostContext, Intent intent, ServiceConnection sc, String processName) {
        // start plugin, æŒ‡å®šServiceConnectionå’Œè¿›ç¨‹
        PluginManager.launchPlugin(mHostContext, intent, sc, processName);
    }

    /**
     * åˆ¤æ–­æ?’ä»¶æ˜¯å?¦å®‰è£…
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  æ?’ä»¶çš„åŒ…å??
     * @return  æ?’ä»¶å·²å®‰è£…ï¼Œ è¿”å›žtrue; æ?’ä»¶æœªå®‰è£…ï¼Œè¿”å›žfalse
     */
    public static boolean isPackageInstalled(Context context, String pkgName) {

        Context mContext = ensureContext(context);
        return PluginPackageManagerNative.getInstance(mContext).isPackageInstalled(pkgName);
    }

    /**
     * åˆ¤æ–­æ?’ä»¶æ˜¯å?¦å?¯ç”¨
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  æ?’ä»¶çš„åŒ…å??
     * @return  æ?’ä»¶æ˜¯å?¯ç”¨çš„ï¼Œè¿”å›žtrue; æ?’ä»¶ä¸?å?¯ç”¨ï¼Œè¿”å›žfalse
     */
    public static boolean isPackageAvailable(Context context, String pkgName) {

        Context mContext = ensureContext(context);
        return PluginPackageManagerNative.getInstance(mContext).isPackageAvailable(pkgName);
    }

    /**
     * èŽ·å?–æ?’ä»¶PluginLiteInfo
     *
     * @param context  å®¿ä¸»çš„Context
     * @param pkgName  æ?’ä»¶çš„åŒ…å??
     * @return  æ?’ä»¶çš„ä¿¡æ?¯
     */
    public static PluginLiteInfo getPluginInfo(Context context, String pkgName) {

        Context mContext = ensureContext(context);
        return PluginPackageManagerNative.getInstance(mContext).getPackageInfo(pkgName);
    }

    private static Context ensureContext(Context originContext) {
        if (originContext != null) {
            return originContext;
        }
        return sHostContext;
    }
}
