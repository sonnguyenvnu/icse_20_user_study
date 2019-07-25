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
package org.qiyi.pluginlibrary.runtime;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import org.qiyi.pluginlibrary.component.AbstractFragmentProxy;
import org.qiyi.pluginlibrary.component.FragmentProxyFactory;
import org.qiyi.pluginlibrary.component.processmgr.ProcessManager;
import org.qiyi.pluginlibrary.component.stackmgr.PActivityStackSupervisor;
import org.qiyi.pluginlibrary.component.stackmgr.PServiceSupervisor;
import org.qiyi.pluginlibrary.component.wraper.ActivityWrapper;
import org.qiyi.pluginlibrary.constant.IntentConstant;
import org.qiyi.pluginlibrary.context.PluginContextWrapper;
import org.qiyi.pluginlibrary.error.ErrorType;
import org.qiyi.pluginlibrary.install.IInstallCallBack;
import org.qiyi.pluginlibrary.listenter.IPluginElementLoadListener;
import org.qiyi.pluginlibrary.listenter.IPluginLoadListener;
import org.qiyi.pluginlibrary.listenter.IPluginStatusListener;
import org.qiyi.pluginlibrary.pm.PluginLiteInfo;
import org.qiyi.pluginlibrary.pm.PluginPackageInfo;
import org.qiyi.pluginlibrary.pm.PluginPackageManager;
import org.qiyi.pluginlibrary.pm.PluginPackageManagerNative;
import org.qiyi.pluginlibrary.utils.ComponentFinder;
import org.qiyi.pluginlibrary.utils.ContextUtils;
import org.qiyi.pluginlibrary.utils.ErrorUtil;
import org.qiyi.pluginlibrary.utils.FileUtils;
import org.qiyi.pluginlibrary.utils.IntentUtils;
import org.qiyi.pluginlibrary.utils.PluginDebugLog;
import org.qiyi.pluginlibrary.utils.RunUtil;
import org.qiyi.pluginlibrary.utils.ViewPluginHelper;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 管�?�所有�?�件的�?行状�?
 */
public class PluginManager {
    public static final String TAG = "PluginManager";
    /**
     * 宿主注册到�?�件里的ActivityLifeCycle监�?�器
     * �?�件�?写了Application，需�?注册到�?�件的Application类里去
     */
    final static ArrayList<Application.ActivityLifecycleCallbacks> sActivityLifecycleCallbacks =
            new ArrayList<Application.ActivityLifecycleCallbacks>();
    /* 已�?加载到内存了的�?�件集�?� */
    private static ConcurrentHashMap<String, PluginLoadedApk> sPluginsMap =
            new ConcurrentHashMap<>();
    /* 异步加载�?�件线程池 */
    private static Executor sExecutor = Executors.newCachedThreadPool();
    /* �?�件加载线程和主线程通信 */
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    /* �?�件状�?投递 */
    private static IDeliverInterface mDeliver;
    /* �?�件状�?监�?�器 */
    private static IPluginStatusListener sPluginStatusListener;
    /* 处�?��?�件退出时的善�?�逻辑 */
    private static IAppExitStuff sExitStuff;

    /**
     * 通过包�??获�?�{@link PluginLoadedApk}对象
     * 注�?:此方法仅仅到{@link #sPluginsMap}中查找，并�?会去加载�?�件
     *
     * @param pkgName �?�件的包�??
     * @return 返回包�??为mPluginPackage 指定的�?�件的PluginLoadedApk对象，如果�?�件没有加载，则返回Null
     */
    public static PluginLoadedApk getPluginLoadedApkByPkgName(String pkgName) {
        if (TextUtils.isEmpty(pkgName)) {
            return null;
        }
        return sPluginsMap.get(pkgName);
    }

    /**
     * 通过ClassLoader查找{@link PluginLoadedApk}对象
     *
     * @param classLoader �?�件的ClassLoader
     * @return �?�件LoadedApk内存实例
     */
    public static PluginLoadedApk findPluginLoadedApkByClassLoader(ClassLoader classLoader) {
        for (PluginLoadedApk loadedApk : sPluginsMap.values()) {
            if (loadedApk != null && loadedApk.getPluginClassLoader() == classLoader) {
                return loadedApk;
            }
        }
        return null;
    }

    /**
     * 判断�?�件是�?�已�?加载
     *
     * @param mPluginPackage �?�件的包�??
     * @return true :�?�件已�?加载，false:�?�件没有加载
     */
    public static boolean isPluginLoaded(String mPluginPackage) {
        return getPluginLoadedApkByPkgName(mPluginPackage) != null;
    }

    /**
     * �?存已�?加载�?功的{@link PluginLoadedApk}
     *
     * @param mPluginPackage   �?�件包�??
     * @param mPluginLoadedApk �?�件的内存实例对象
     */
    private static void addPluginLoadedApk(String mPluginPackage, PluginLoadedApk mPluginLoadedApk) {
        if (TextUtils.isEmpty(mPluginPackage)
                || mPluginLoadedApk == null) {
            return;
        }

        sPluginsMap.put(mPluginPackage, mPluginLoadedApk);
    }

    /**
     * 移除已�?加载�?功的{@link PluginLoadedApk}
     *
     * @param mPluginPackage 需�?被移除的�?�件包�??
     * @return 被移除的�?�件内存对象
     */
    public static PluginLoadedApk removePluginLoadedApk(String mPluginPackage) {
        if (TextUtils.isEmpty(mPluginPackage)) {
            return null;
        }
        return sPluginsMap.remove(mPluginPackage);
    }

    /**
     * 获�?�所有已�?加载的�?�件
     *
     * @return 以Map的形�?返回加载�?功的�?�件，此Map是�?�?��?�的
     */
    public static Map<String, PluginLoadedApk> getAllPluginLoadedApk() {
        return Collections.unmodifiableMap(sPluginsMap);
    }

    /**
     * 创建�?�件中的 Fragment 代�?�实例，代�?�会负责加载具体�?�件 Fragment
     * <br/>
     * 如果�?�件未安装，则返回 null
     *
     * @param context       host context
     * @param proxyClass    FragmentProxy 具体类型，�?�以为空使用 SDK 默认 FragmentProxy
     * @param pkgName       plugin package name
     * @param fragmentClass plugin fragment class name
     * @return FragmentProxy or null if plugin is not installed
     */
    @Nullable
    public static AbstractFragmentProxy createFragmentProxy(@NonNull Context context,
                                                            @Nullable Class<? extends AbstractFragmentProxy> proxyClass,
                                                            @NonNull String pkgName, @NonNull String fragmentClass) {
        if (PluginPackageManagerNative.getInstance(context).isPackageInstalled(pkgName)) {
            return FragmentProxyFactory.create(proxyClass, pkgName, fragmentClass);
        } else {
            return null;
        }
    }

    /**
     * 创建�?�件中的 Fragment 实例
     *
     * @param hostContext   host context
     * @param packageName   plugin package name
     * @param fragmentClass plugin fragment class name
     * @param listener      listener for result
     */
    public static void createFragment(@NonNull Context hostContext,
                                      @NonNull String packageName,
                                      @NonNull String fragmentClass,
                                      @NonNull IPluginElementLoadListener<Fragment> listener) {
        createFragment(hostContext, packageName, fragmentClass, null, listener);
    }

    /**
     * 创建�?�件中的 Fragment 实例
     *
     * @param hostContext   host context
     * @param packageName   plugin package name
     * @param fragmentClass plugin fragment class name
     * @param arguments     arguments for fragment
     * @param listener      listener for result
     */
    public static void createFragment(@NonNull final Context hostContext,
                                      @NonNull final String packageName,
                                      @NonNull final String fragmentClass,
                                      @Nullable final Bundle arguments,
                                      @NonNull final IPluginElementLoadListener<Fragment> listener) {
        if (!PluginPackageManagerNative.getInstance(hostContext).isPackageInstalled(packageName)) {
            listener.onFail(ErrorType.ERROR_PLUGIN_LOAD_NOT_INSTALLED, packageName);
            return;
        }
        loadClass(hostContext.getApplicationContext(), packageName, fragmentClass, new IPluginElementLoadListener<Class<?>>() {
            @Override
            public void onSuccess(Class<?> element, String packageName) {
                try {
                    Fragment fragment = (Fragment) element.newInstance();
                    Bundle args = new Bundle();
                    if (arguments != null) {
                        args.putAll(arguments);
                    }
                    //  告诉 Fragment 当�?所在包�?? �?�以处�?�资�?访问问题
                    args.putString(IntentConstant.EXTRA_TARGET_PACKAGE_KEY, packageName);
                    fragment.setArguments(args);
                    listener.onSuccess(fragment, packageName);
                } catch (Throwable e) {
                    ErrorUtil.throwErrorIfNeed(e);
                    listener.onFail(ErrorType.ERROR_PLUGIN_CREATE_CLASS_INSTANCE, packageName);
                }
            }

            @Override
            public void onFail(int errorType, String packageName) {
                listener.onFail(errorType, packageName);

            }
        });
    }

    /**
     * 创建�?�件 View 实例，�?�以被添加到宿主 ViewGroup 中
     *
     * @param hostActivity  host activity
     * @param packageName   package name
     * @param viewClassName view class name
     * @param listener      listener
     */
    public static void createView(@NonNull final Activity hostActivity,
                                  @NonNull String packageName,
                                  @NonNull final String viewClassName,
                                  @NonNull final IPluginElementLoadListener<View> listener) {
        loadClass(hostActivity.getApplicationContext(), packageName, viewClassName, new IPluginElementLoadListener<Class<?>>() {
            @Override
            public void onSuccess(Class<?> element, String packageName) {
                try {
                    PluginLoadedApk loadedApk = getPluginLoadedApkByPkgName(packageName);
                    if (loadedApk != null) {
                        ActivityWrapper context = new ActivityWrapper(hostActivity, loadedApk);
                        View view = (View) element.getConstructor(Context.class).newInstance(context);
                        ViewPluginHelper.disableViewSaveInstanceRecursively(view);
                        listener.onSuccess(view, packageName);
                    } else {
                        listener.onFail(ErrorType.ERROR_PLUGIN_NOT_LOADED, packageName);
                    }
                } catch (Throwable e) {
                    ErrorUtil.throwErrorIfNeed(e);
                    listener.onFail(ErrorType.ERROR_PLUGIN_CREATE_CLASS_INSTANCE, packageName);
                }
            }

            @Override
            public void onFail(int errorType, String packageName) {
                listener.onFail(errorType, packageName);
            }
        });
    }

    /**
     * 加载指定包�??的指定 class，处�?��?�件之间的�?赖关系
     *
     * @param hostContext host context
     * @param packageName packageName
     * @param className   className
     * @param listener    load listener
     */
    private static void loadClass(@NonNull final Context hostContext,
                                  @NonNull final String packageName,
                                  @NonNull final String className,
                                  @NonNull final IPluginElementLoadListener<Class<?>> listener) {
        if (hostContext == null || TextUtils.isEmpty(packageName) || TextUtils.isEmpty(className)) {
            PluginDebugLog.runtimeLog(TAG, "loadClass hostContext or packageName or className is null!");
            listener.onFail(ErrorType.ERROR_PLUGIN_GET_PKG_AND_CLS_FAIL, packageName);
            return;
        }
        PluginLoadedApk loadedApk = getPluginLoadedApkByPkgName(packageName);
        // 1. �?�件已�?在内存，直接加载�?功
        if (loadedApk != null) {
            try {
                Class<?> pluginClass = loadedApk.getPluginClassLoader().loadClass(className);
                listener.onSuccess(pluginClass, packageName);
            } catch (ClassNotFoundException e) {
                ErrorUtil.throwErrorIfNeed(e);
                listener.onFail(ErrorType.ERROR_PLUGIN_LOAD_TARGET_CLASS, packageName);
            }
            return;
        }
        PluginLiteInfo packageInfo = PluginPackageManagerNative.getInstance(hostContext).getPackageInfo(packageName);
        List<String> pluginRefs = PluginPackageManagerNative.getInstance(hostContext).getPluginRefs(packageName);
        // 2. 如果没有�?赖，异步加载�?�件获�?�
        if (packageInfo != null && (pluginRefs == null || pluginRefs.isEmpty())) {
            PluginDebugLog.runtimeLog(TAG, "start Check installation without dependence packageName: " + packageName);
            doLoadClassAsync(hostContext, packageName, className, listener);
            return;
        }
        // 3. 包�?��?赖的情况下，先�?�动�?赖
        if (packageInfo != null) {
            PluginDebugLog.runtimeLog(TAG, "start to check dependence installation size: " + pluginRefs.size());
            final AtomicInteger count = new AtomicInteger(pluginRefs.size());
            for (String pkgName : pluginRefs) {
                PluginDebugLog.runtimeLog(TAG, "start to check installation pkgName: " + pkgName);
                final PluginLiteInfo refInfo = PluginPackageManagerNative.getInstance(hostContext).getPackageInfo(pkgName);
                PluginPackageManagerNative.getInstance(hostContext).packageAction(refInfo,
                        new IInstallCallBack.Stub() {
                            @Override
                            public void onPackageInstalled(PluginLiteInfo packageInfo) {
                                count.getAndDecrement();
                                PluginDebugLog.runtimeLog(TAG, "check installation success pkgName: " + refInfo.packageName);
                                if (count.get() == 0) {
                                    PluginDebugLog.runtimeLog(TAG, "start Check installation after check dependence packageName: " + packageName);
                                    doLoadClassAsync(hostContext, packageName, className, listener);
                                }
                            }

                            @Override
                            public void onPackageInstallFail(PluginLiteInfo info, int failReason) throws RemoteException {
                                PluginDebugLog.runtimeLog(TAG, "check installation failed pkgName: " + info.packageName + " failReason: " + failReason);
                                count.set(-1);
                                listener.onFail(failReason, packageName);
                            }
                        });
            }
            return;
        }
        // 4. packageInfo 为空的情况，记录异常，用户未安装
        PluginDebugLog.runtimeLog(TAG, "pluginLiteInfo is null packageName: " + packageName);
        listener.onFail(ErrorType.ERROR_PLUGIN_LITEINFO_NOT_FOUND, packageName);
    }

    private static void doLoadClassAsync(@NonNull final Context hostContext,
                                         @NonNull String packageName,
                                         @NonNull final String fragmentClass,
                                         @NonNull final IPluginElementLoadListener<Class<?>> listener) {
        loadPluginAsync(hostContext, packageName, new IPluginLoadListener() {
            @Override
            public void onLoadSuccess(String packageName) {
                if (getPluginLoadedApkByPkgName(packageName) != null) {
                    loadClass(hostContext, packageName, fragmentClass, listener);
                }
            }

            @Override
            public void onLoadFailed(String packageName) {
                listener.onFail(ErrorType.ERROR_PLUGIN_CREATE_LOADEDAPK, packageName);
            }
        }, FileUtils.getCurrentProcessName(hostContext));
    }

    /**
     * �?�动�?�件的默认入�?�Activity
     *
     * @param mHostContext 宿主的Context
     * @param packageName  �?�件包�??
     */
    public static void launchPlugin(Context mHostContext, String packageName) {
        if (mHostContext == null || TextUtils.isEmpty(packageName)) {
            PluginDebugLog.runtimeLog(TAG, "launchPlugin mHostContext is null or packageName is null!");
            return;
        }

        ComponentName mComponentName = new ComponentName(packageName, "");
        Intent mIntent = new Intent();
        mIntent.setComponent(mComponentName);
        launchPlugin(mHostContext, mIntent, ProcessManager.chooseDefaultProcess(mHostContext, packageName));
    }

    /**
     * �?�动�?�件
     *
     * @param mHostContext 主工程的上下文
     * @param mIntent      需�?�?�动的组件的Intent
     * @param mProcessName 需�?�?�动的�?�件�?行的进程�??称,�?�件方�?�以在Application的android:process指定
     *                     如果没有指定，则有�?�件中心分�?
     */
    public static void launchPlugin(final Context mHostContext,
                                    Intent mIntent,
                                    String mProcessName) {
        launchPlugin(mHostContext, mIntent, null, mProcessName);
    }

    /**
     * �?�动�?�件
     *
     * @param mHostContext       主工程的上下文
     * @param mIntent            需�?�?�动的组件的Intent
     * @param mServiceConnection bindService时需�?的ServiceConnection,如果�?是bindService的方�?�?�动组件，传入Null
     * @param mProcessName       需�?�?�动的�?�件�?行的进程�??称,�?�件方�?�以在Application的android:process指定
     *                           如果没有指定，则有�?�件中心分�?
     */
    public static void launchPlugin(final Context mHostContext,
                                    final Intent mIntent,
                                    final ServiceConnection mServiceConnection,
                                    final String mProcessName) {
        final String packageName = tryParsePkgName(mHostContext, mIntent);
        if (TextUtils.isEmpty(packageName)) {
            if (null != mHostContext) {
                deliver(mHostContext, false, mHostContext.getPackageName(), ErrorType.ERROR_PLUGIN_LOAD_NO_PKGNAME_INTENT);
            }
            PluginDebugLog.runtimeLog(TAG, "enterProxy packageName is null return! packageName: " + packageName);
            return;
        }
        // 处�?��?�?�进程跳转
        final String targetProcessName = TextUtils.isEmpty(mProcessName) ?
                ProcessManager.chooseDefaultProcess(mHostContext, packageName) : mProcessName;
        String currentProcess = FileUtils.getCurrentProcessName(mHostContext);
        if (!TextUtils.equals(currentProcess, targetProcessName)) {
            // �?�动进程和目标进程�?一致，需�?先�?�动目标进程，�?始化PluginLoadedApk
            PluginDebugLog.runtimeFormatLog(TAG, "enterProxy caller process %s not match with target process %s for pkgName %s",
                    currentProcess, targetProcessName, packageName);
            Intent transIntent = new Intent();
            transIntent.setAction(IntentConstant.ACTION_START_PLUGIN);
            transIntent.putExtra(IntentConstant.EXTRA_START_INTENT_KEY, mIntent);
            try {
                String proxyServiceName = ComponentFinder.matchServiceProxyByFeature(targetProcessName);
                String fixedProcess = ComponentFinder.fixProcessNameByService(mHostContext, proxyServiceName);
                transIntent.putExtra(IntentConstant.EXTRA_TARGET_PROCESS, fixedProcess);

                transIntent.setClass(mHostContext, Class.forName(proxyServiceName));
                mHostContext.startService(transIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        final IntentRequest request = new IntentRequest(mIntent, mServiceConnection);
        LinkedBlockingQueue<IntentRequest> cacheIntents = PActivityStackSupervisor.getCachedIntent(packageName);
        if (cacheIntents != null && cacheIntents.size() > 0) {
            PluginDebugLog.runtimeLog(TAG, "LoadingMap is not empty, Cache current intent and execute it later, intent: "
                    + mIntent + ", packageName: " + packageName);
            cacheIntents.add(request);
            return;
        }

        boolean isLoadAndInit = isPluginLoadedAndInit(packageName);
        if (isLoadAndInit) {
            PluginDebugLog.runtimeLog(TAG, "Environment is already ready, launch current intent directly: " + mIntent);
            // �?�以直接�?�动组件
            readyToStartSpecifyPlugin(mHostContext, mServiceConnection, mIntent, true);
            return;
        }

        if (null == cacheIntents) {
            cacheIntents = new LinkedBlockingQueue<IntentRequest>();
            PActivityStackSupervisor.addCachedIntent(packageName, cacheIntents);
        }
        // 缓存这个intent，等待PluginLoadedApk加载到内存之�?��?�?�动这个Intent
        PluginDebugLog.runtimeLog(TAG, "Environment is initializing and loading, cache current intent first, intent: " + mIntent);
        cacheIntents.add(request);
        // 处�?��?�件的�?赖关系
        final PluginLiteInfo info = PluginPackageManagerNative.getInstance(mHostContext.getApplicationContext())
                .getPackageInfo(packageName);
        final List<String> mPluginRefs = PluginPackageManagerNative.getInstance(mHostContext)
                .getPluginRefs(packageName);
        if (info != null && mPluginRefs != null
                && mPluginRefs.size() > 0) {
            PluginDebugLog.runtimeLog(TAG,
                    "start to check dependence installation size: " + mPluginRefs.size());
            final AtomicInteger count = new AtomicInteger(mPluginRefs.size());
            for (String pkgName : mPluginRefs) {
                PluginDebugLog.runtimeLog(TAG, "start to check installation pkgName: " + pkgName);
                final PluginLiteInfo refInfo = PluginPackageManagerNative.getInstance(mHostContext.getApplicationContext())
                        .getPackageInfo(pkgName);

                PluginPackageManagerNative.getInstance(mHostContext.getApplicationContext()).packageAction(refInfo,
                        new IInstallCallBack.Stub() {
                            @Override
                            public void onPackageInstalled(PluginLiteInfo packageInfo) {
                                count.getAndDecrement();
                                PluginDebugLog.runtimeLog(TAG, "check installation success pkgName: " + refInfo.packageName);
                                if (count.get() == 0) {
                                    PluginDebugLog.runtimeLog(TAG,
                                            "start check installation after check dependence packageName: "
                                                    + packageName);
                                    checkPkgInstallationAndLaunch(mHostContext, info, mServiceConnection, mIntent, targetProcessName);
                                }
                            }

                            @Override
                            public void onPackageInstallFail(PluginLiteInfo info, int failReason) throws RemoteException {
                                PluginDebugLog.runtimeLog(TAG,
                                        "check installation failed pkgName: " + info.packageName + " failReason: " + failReason);
                                count.set(-1);
                            }
                        });
            }
        } else if (info != null) {
            PluginDebugLog.runtimeLog(TAG, "start check installation without dependence packageName: " + packageName);
            checkPkgInstallationAndLaunch(mHostContext, info, mServiceConnection, mIntent, targetProcessName);
        } else {
            PluginDebugLog.runtimeLog(TAG, "pluginLiteInfo is null packageName: " + packageName);
            PActivityStackSupervisor.clearLoadingIntent(packageName);
            if (PluginDebugLog.isDebug()) {
                throw new IllegalStateException("pluginLiteInfo is null when launchPlugin " + packageName);
            }
        }
    }

    /**
     * 异步�?始化�?�件，宿主�?�默加载�?�件
     *
     * @deprecated �?建议使用
     */
    @Deprecated
    public static void initPluginAsync(final Context mHostContext,
                                       final String packageName,
                                       final String processName,
                                       final org.qiyi.pluginlibrary.listenter.IPluginStatusListener mListener) {
        // �?�件已�?加载
        if (PluginManager.isPluginLoadedAndInit(packageName)) {
            if (mListener != null) {
                mListener.onInitFinished(packageName);
            }
            return;
        }

        BroadcastReceiver recv = new BroadcastReceiver() {
            public void onReceive(Context ctx, Intent intent) {
                String curPkg = IntentUtils.getTargetPackage(intent);
                if (IntentConstant.ACTION_PLUGIN_INIT.equals(intent.getAction()) && TextUtils.equals(packageName, curPkg)) {
                    PluginDebugLog.runtimeLog(TAG, "收到自定义的广播org.qiyi.pluginapp.action.TARGET_LOADED");
                    if (mListener != null) {
                        mListener.onInitFinished(packageName);
                    }
                    mHostContext.getApplicationContext().unregisterReceiver(this);
                }
            }
        };
        PluginDebugLog.runtimeLog(TAG, "注册自定义广播org.qiyi.pluginapp.action.TARGET_LOADED");
        IntentFilter filter = new IntentFilter();
        filter.addAction(IntentConstant.ACTION_PLUGIN_INIT);
        mHostContext.getApplicationContext().registerReceiver(recv, filter);

        Intent intent = new Intent();
        intent.setAction(IntentConstant.ACTION_PLUGIN_INIT);
        intent.setComponent(new ComponentName(packageName, recv.getClass().getName()));
        launchPlugin(mHostContext, intent, processName);
    }

    /**
     * 准备�?�动指定�?�件组件
     *
     * @param mContext     主工程Context
     * @param mConnection  bindService时需�?的ServiceConnection,如果�?是bindService的方�?�?�动组件，传入Null
     * @param mIntent      需�?�?�动组件的Intent
     * @param needAddCache 是�?�需�?缓存Intnet,true:如果�?�件没有�?始化，那么会缓存起�?�，等�?�件加载完毕�?执行此Intent
     *                     false:如果�?�件没�?始化，则直接抛弃此Intent
     */
    public static boolean readyToStartSpecifyPlugin(Context mContext,
                                                    ServiceConnection mConnection,
                                                    Intent mIntent,
                                                    boolean needAddCache) {
        PluginDebugLog.runtimeLog(TAG, "readyToStartSpecifyPlugin launchIntent: " + mIntent);
        String packageName = tryParsePkgName(mContext, mIntent);
        PluginLoadedApk mLoadedApk = getPluginLoadedApkByPkgName(packageName);
        if (mLoadedApk == null) {
            deliver(mContext, false, packageName, ErrorType.ERROR_PLUGIN_NOT_LOADED);
            PluginDebugLog.runtimeLog(TAG, packageName + "readyToStartSpecifyPlugin launchIntent exception, plugin loaded apk not exist");
            PActivityStackSupervisor.clearLoadingIntent(packageName);
            return false;
        }

        LinkedBlockingQueue<IntentRequest> cacheIntents =
                PActivityStackSupervisor.getCachedIntent(packageName);
        if (cacheIntents == null) {
            cacheIntents = new LinkedBlockingQueue<IntentRequest>();
            PActivityStackSupervisor.addCachedIntent(packageName, cacheIntents);
        }
        // �?��?�?�?添加Intent请求到队列中，尤其是第一次�?始化时在enterProxy中已�?添加了一次
        IntentRequest request = new IntentRequest(mIntent, mConnection);
        if (!cacheIntents.contains(request) && needAddCache) {
            PluginDebugLog.runtimeLog(TAG, "readyToStartSpecifyPlugin launchIntent add to cacheIntent....");
            cacheIntents.offer(request);  // 添加到队列
        } else {
            PluginDebugLog.runtimeLog(TAG, "readyToStartSpecifyPlugin launchIntent no need add to cacheIntent....needAddCache:" + needAddCache);
        }

        PluginDebugLog.runtimeLog(TAG, "readyToStartSpecifyPlugin launchIntent_cacheIntents: " + cacheIntents);
        if (!mLoadedApk.hasLaunchIngIntent()) {
            IntentRequest firstRequest = cacheIntents.poll(); //处�?�队首的Intent
            if (firstRequest != null && firstRequest.getIntent() != null) {
                PluginDebugLog.runtimeFormatLog(TAG, "readyToStartSpecifyPlugin, no launching intent for pkgName: %s, " +
                        "ready to process first intent in queue!", packageName);
                doRealLaunch(mContext, mLoadedApk, firstRequest.getIntent(), firstRequest.getServiceConnection());
            }
        } else {
            PluginDebugLog.runtimeFormatLog(TAG, "readyToStartSpecifyPlugin, has launching intent for pkgName %s " +
                    "waiting other intent process over", packageName);
        }
        return true;
    }

    /**
     * 更新所有�?�件的资�?�?置
     * 使用Application的callback实现
     */
    @Deprecated
    public static void updateConfiguration(Configuration config) {
        for (Map.Entry<String, PluginLoadedApk> entry : sPluginsMap.entrySet()) {
            PluginLoadedApk mLoadedApk = entry.getValue();
            mLoadedApk.updateConfiguration(config);
        }
    }

    /**
     * 真正�?�动一个组件
     *
     * @param mHostContext 主工程Context
     * @param mLoadedApk   需�?�?�动的�?�件的PluginLoadedApk
     * @param mIntent      需�?�?�动组件的Intent
     * @param mConnection  bindService时需�?的ServiceConnection,如果�?是bindService的方�?�?�动组件，传入Null
     */
    private static boolean doRealLaunch(Context mHostContext,
                                     PluginLoadedApk mLoadedApk,
                                     Intent mIntent,
                                     ServiceConnection mConnection) {
        String targetClassName = "";
        ComponentName mComponent = mIntent.getComponent();
        if (mComponent != null) {
            //显�?�?�动
            targetClassName = mComponent.getClassName();
            PluginDebugLog.runtimeLog(TAG, "doRealLaunch launchIntent_targetClassName:" + targetClassName);
            if (TextUtils.isEmpty(targetClassName)) {
                targetClassName = mLoadedApk.getPluginPackageInfo().getDefaultActivityName();
            }
        }

        String pkgName = mLoadedApk.getPluginPackageName();
        Class<?> targetClass = null;
        if (!TextUtils.isEmpty(targetClassName)
                && !TextUtils.equals(targetClassName, IntentConstant.EXTRA_VALUE_LOADTARGET_STUB)) {
            try {
                targetClass = mLoadedApk.getPluginClassLoader().loadClass(targetClassName);
            } catch (Exception e) {
                deliver(mHostContext, false,
                        pkgName, ErrorType.ERROR_PLUGIN_LOAD_COMP_CLASS);
                PluginDebugLog.runtimeLog(TAG, "doRealLaunch loadClass failed for targetClassName: "
                        + targetClassName);
                executeNext(mHostContext, mLoadedApk);
                return false;
            }
        }

        String action = mIntent.getAction();
        if (TextUtils.equals(action, IntentConstant.ACTION_PLUGIN_INIT)
                || TextUtils.equals(targetClassName, IntentConstant.EXTRA_VALUE_LOADTARGET_STUB)) {
            PluginDebugLog.runtimeLog(TAG, "doRealLaunch load target stub for pkgName: " + pkgName);
            //通知�?�件�?始化完毕
            if (targetClass != null && BroadcastReceiver.class.isAssignableFrom(targetClass)) {
                Intent newIntent = new Intent(mIntent);
                newIntent.setComponent(null);
                newIntent.putExtra(IntentConstant.EXTRA_TARGET_PACKAGE_KEY, pkgName);
                newIntent.setPackage(mHostContext.getPackageName());
                mHostContext.sendBroadcast(newIntent);
            }
            // 表示�?��?�加载Application，�?需�?�?�动组件
            executeNext(mHostContext, mLoadedApk);
            return false;
        }

        mLoadedApk.changeLaunchingIntentStatus(true);
        PluginDebugLog.runtimeLog(TAG, "doRealLaunch launchIntent_targetClass: " + targetClassName);
        if (targetClass != null && Service.class.isAssignableFrom(targetClass)) {
            //处�?�的是Service, 宿主�?�动�?�件Service�?�能通过显�?�?�动
            ComponentFinder.switchToServiceProxy(mLoadedApk, mIntent, targetClassName);
            if (mConnection == null) {
                PluginDebugLog.runtimeLog(TAG, "doRealLaunch serviceConnection is null, startService: "
                        + targetClassName);
                mHostContext.startService(mIntent);
            } else {
                PluginDebugLog.runtimeLog(TAG, "doRealLaunch serviceConnection is " + mConnection.getClass().getName()
                        + ", bindService: " + targetClassName);
                mHostContext.bindService(mIntent, mConnection,
                        mIntent.getIntExtra(IntentConstant.BIND_SERVICE_FLAGS, Context.BIND_AUTO_CREATE));
            }
        } else {
            //处�?�的是Activity
            ComponentFinder.switchToActivityProxy(pkgName,
                    mIntent, -1, mHostContext);
            PActivityStackSupervisor.addLoadingIntent(pkgName, new IntentRequest(mIntent, mConnection));
            Context lastActivity = null;
            PActivityStackSupervisor mActivityStackSupervisor =
                    mLoadedApk.getActivityStackSupervisor();
            lastActivity = mActivityStackSupervisor.getAvailableActivity();
            PluginDebugLog.runtimeLog(TAG, "doRealLaunch startActivity: " + targetClassName);
            if (mHostContext instanceof Activity) {
                mHostContext.startActivity(mIntent);
            } else if (lastActivity != null) {
                ActivityInfo lastInfo = mLoadedApk.getActivityInfoByClassName(lastActivity.getClass().getName());
                ActivityInfo currentInfo = mLoadedApk.getActivityInfoByClassName(targetClassName);
                if (lastInfo != null && currentInfo != null && TextUtils.equals(lastInfo.taskAffinity, currentInfo.taskAffinity)) {
                    // Clear the Intent.FLAG_ACTIVITY_NEW_TASK
                    int flag = mIntent.getFlags();
                    flag = flag ^ Intent.FLAG_ACTIVITY_NEW_TASK;
                    mIntent.setFlags(flag);
                }
                lastActivity.startActivity(mIntent);
            } else {
                // Add the Intent.FLAG_ACTIVITY_NEW_TASK
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mHostContext.startActivity(mIntent);
            }
        }
        if (sPluginStatusListener != null) {
            sPluginStatusListener.onLaunchSuccess(pkgName, mIntent);
        }

        // 执行下一个Intent
        PluginDebugLog.runtimeFormatLog(TAG, "doRealLaunch process intent %s end, ready to executeNext intent", mIntent.toString());
        executeNext(mHostContext, mLoadedApk);
        return true;
    }

    /**
     * 处�?�队列中下一个IntentRequest的请求
     *
     * @param mContext   主进程的Context
     * @param mLoadedApk 当�?�?�?�动的组件的�?�件实例
     */
    private static void executeNext(final Context mContext,
                                    final PluginLoadedApk mLoadedApk) {
        Message msg = Message.obtain(sHandler, new Runnable() {

            @Override
            public void run() {
                LinkedBlockingQueue<IntentRequest> cacheIntents =
                        PActivityStackSupervisor.getCachedIntent(mLoadedApk.getPluginPackageName());
                PluginDebugLog.runtimeLog(TAG, "executeNext cacheIntents: " + cacheIntents);
                if (null != cacheIntents && !cacheIntents.isEmpty()) {
                    IntentRequest request = cacheIntents.poll();
                    if (request != null && request.getIntent() != null) {
                        PluginDebugLog.runtimeLog(TAG, "executeNext process intent: " + request.getIntent());
                        doRealLaunch(mContext, mLoadedApk, request.getIntent(), request.getServiceConnection());
                        return;
                    }
                }
                mLoadedApk.changeLaunchingIntentStatus(false);
            }
        });
        sHandler.sendMessage(msg);
    }

    /**
     * 判断�?�件是�?�已�?�?始化
     *
     * @param mPluginPackage 需�?判断的�?�件的包�??
     * @return true:已�?�?始化，false:没有�?始化
     */
    public static boolean isPluginLoadedAndInit(String mPluginPackage) {

        PluginLoadedApk mPlugin = getPluginLoadedApkByPkgName(mPluginPackage);
        return mPlugin != null && mPlugin.hasPluginInit();
    }

    /**
     * 检查�?�件是�?�安装，如果安装则�?�动�?�件
     *
     * @param mHostContext       主进程Context
     * @param packageInfo        �?�件的详细信�?�
     * @param mServiceConnection bindService时需�?的ServiceConnection,如果�?是bindService的方�?�?�动组件，传入Null
     * @param mIntent            �?�动组件的Intent
     */
    private static void checkPkgInstallationAndLaunch(final Context mHostContext,
                                                      final PluginLiteInfo packageInfo,
                                                      final ServiceConnection mServiceConnection,
                                                      final Intent mIntent,
                                                      final String mProcessName) {
        final Context appContext = mHostContext.getApplicationContext();
        PluginPackageManagerNative.getInstance(appContext).packageAction(packageInfo,
                new IInstallCallBack.Stub() {
                    @Override
                    public void onPackageInstalled(PluginLiteInfo info) {
                        // install done ,load plugin async
                        PluginDebugLog.runtimeLog(TAG,
                                "checkPkgInstallationAndLaunch installed packageName: " + info.packageName);
                        startLoadPlugin(appContext, packageInfo, mServiceConnection, mIntent, mProcessName);
                    }

                    @Override
                    public void onPackageInstallFail(PluginLiteInfo info, int failReason) throws RemoteException {
                        String packageName = info.packageName;
                        PluginDebugLog.runtimeLog(TAG, "checkPkgInstallationAndLaunch failed packageName: " + packageName
                                + " failReason: " + failReason);
                        PActivityStackSupervisor.clearLoadingIntent(packageName);
                        deliver(mHostContext, false, packageName, failReason);
                    }
                });
    }

    /**
     * 开始准备加载�?�件LoadedApk实例
     *
     * @param mHostContext       主进程Context
     * @param packageInfo        �?�件的详细信�?�
     * @param mServiceConnection bindService时需�?的ServiceConnection,如果�?是bindService的方�?�?�动组件，传入Null
     * @param mIntent            �?�动组件的Intent
     */
    private static void startLoadPlugin(final Context mHostContext,
                                        final PluginLiteInfo packageInfo,
                                        final ServiceConnection mServiceConnection,
                                        final Intent mIntent,
                                        final String mProcessName) {
        loadPluginAsync(mHostContext, packageInfo.packageName,
                new IPluginLoadListener() {

                    @Override
                    public void onLoadSuccess(String packageName) {
                        PluginDebugLog.runtimeLog(TAG,
                                "checkPkgInstallationAndLaunch loadPluginAsync callback onLoadSuccess pkgName: " + packageName);
                        // load done, start plugin
                        readyToStartSpecifyPlugin(mHostContext, mServiceConnection, mIntent, false);
                        if (sPluginStatusListener != null) {
                            sPluginStatusListener.onPluginReady(packageName);
                        }
                    }

                    @Override
                    public void onLoadFailed(String packageName) {
                        PluginDebugLog.runtimeLog(TAG,
                                "checkPkgInstallationAndLaunch loadPluginAsync callback onLoadFailed pkgName: " + packageName);
                        // load failed, clear launching intent
                        PActivityStackSupervisor.clearLoadingIntent(packageName);
                        PluginLoadedApk mPlugin = sPluginsMap.get(packageName);
                        if (null != mPlugin) {
                            mPlugin.changeLaunchingIntentStatus(false);
                        }
                    }
                }, mProcessName);
    }

    /**
     * 异步加载�?�件
     *
     * @param context     主进程的Context
     * @param packageName 需�?加载的�?�件包�??
     * @param listener    加载结果回调
     * @param processName 进程�??称
     */
    private static void loadPluginAsync(Context context, String packageName,
                                        IPluginLoadListener listener, String processName) {
        sExecutor.execute(new LoadPluginTask(context, packageName, listener, processName));
    }

    /**
     * �?�步加载�?�件
     *
     * @param context     主进程的Context
     * @param packageName 需�?加载的�?�件包�??
     * @param processName 进程�??称
     */
    public static void loadPluginSync(final Context context, String packageName,
                                      String processName) {
        LoadPluginTask task = new LoadPluginTask(context, packageName, new IPluginLoadListener() {
            @Override
            public void onLoadSuccess(String packageName) {
                PluginDebugLog.runtimeFormatLog(TAG, "loadPluginSync success for plugin %s", packageName);
            }

            @Override
            public void onLoadFailed(String packageName) {
                PluginDebugLog.runtimeFormatLog(TAG, "loadPluginSync failed for plugin %s", packageName);
            }
        }, processName);
        task.run();
    }


    /**
     * 从mIntent里�?�解�?�?�件包�??
     * 1. 从Intent的package获�?�
     * 2. 从Intent的ComponentName获�?�
     * 3. �?�?Intent，从已安装�?�件列表中查找�?�以�?应的�?�件
     *
     * @param mHostContext 主工程Context
     * @param mIntent      需�?�?�动的组件
     * @return 返回需�?�?�动�?�件的包�??
     */
    private static String tryParsePkgName(Context mHostContext, Intent mIntent) {
        if (mIntent == null || mHostContext == null) {
            return "";
        }

        String pkgName = mIntent.getPackage();
        if (!TextUtils.isEmpty(pkgName) && !TextUtils.equals(pkgName, mHostContext.getPackageName())) {
            // 与宿主pkgName�?�?�
            return pkgName;
        }

        ComponentName cpn = mIntent.getComponent();
        if (cpn != null && !TextUtils.isEmpty(cpn.getPackageName())) {
            // 显�?�?�动�?�件
            return cpn.getPackageName();
        } else {
            // �?�?�?�动�?�件
            List<PluginLiteInfo> packageList =
                    PluginPackageManagerNative.getInstance(mHostContext).getInstalledApps();
            if (packageList != null) {
                // Here, loop all installed packages to get pkgName for this intent
                String packageName = "";
                ActivityInfo activityInfo = null;
                ServiceInfo serviceInfo = null;
                for (PluginLiteInfo info : packageList) {
                    if (info != null) {
                        PluginPackageInfo target = PluginPackageManagerNative.getInstance(mHostContext)
                                .getPluginPackageInfo(mHostContext, info);
                        if (target != null && (activityInfo = target.resolveActivity(mIntent)) != null) {
                            // 优先查找Activity, 这里转�?显�?Intent，�?��?��?用二次resolve了
                            mIntent.setComponent(new ComponentName(info.packageName, activityInfo.name));
                            return info.packageName;
                        }
                        // resolve�?�?Service
                        if (!TextUtils.isEmpty(packageName) && serviceInfo != null) {
                            continue;
                        }
                        if (target != null && (serviceInfo = target.resolveService(mIntent)) != null) {
                            packageName = info.packageName;
                        }
                    }
                }
                // Here, No Activity can handle this intent, we check service fallback
                if (!TextUtils.isEmpty(packageName)) {
                    if (serviceInfo != null) {
                        // �?�件框架�?��?�的逻辑�?�支�?显�?Service处�?�，这里需�?更新Intent的信�?�
                        mIntent.setComponent(new ComponentName(packageName, serviceInfo.name));
                    }
                    return packageName;
                }
            }
        }

        return "";
    }

    /**
     * �?�件状�?投递
     *
     * @param mContext  �?�件进程Context
     * @param success   结果是�?��?功
     * @param pakName   �?�件包�??
     * @param errorCode 错误�?
     */
    public static void deliver(final Context mContext, final boolean success, final String pakName,
                               final int errorCode) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    deliverPlugInner(mContext, success, pakName, errorCode);
                    return null;
                }

            }.execute();
        } else {
            deliverPlugInner(mContext, success, pakName, errorCode);
        }
    }

    /**
     * �?�件状�?投递
     *
     * @param mContext  �?�件进程Context
     * @param success   结果是�?��?功
     * @param pakName   �?�件包�??
     * @param errorCode 错误�?
     */
    private static void deliverPlugInner(Context mContext, boolean success, String pakName, int errorCode) {
        if (null != mContext && mDeliver != null && !TextUtils.isEmpty(pakName)) {
            PluginLiteInfo info = PluginPackageManagerNative.getInstance(ContextUtils.getOriginalContext(mContext))
                    .getPackageInfo(pakName);
            if (info != null) {
                mDeliver.deliver(success, info, errorCode);
            }
        }
    }

    /**
     * 退出�?�件,将�?�件中的类从PathClassLoader中剔除
     *
     * @param mPackageName 需�?退出的�?�件的包�??
     */
    public static void exitPlugin(String mPackageName) {
        if (!TextUtils.isEmpty(mPackageName)) {
            PluginLoadedApk mLoadedApk = removePluginLoadedApk(mPackageName);
            if (mLoadedApk == null || mLoadedApk.getPluginApplication() == null) {
                return;
            }
            mLoadedApk.ejectClassLoader();
        }
    }

    /**
     * 注册�?�载广播，清�?�PluginLoadedApk内存引用
     */
    public static void registerUninstallReceiver(Context context) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(PluginPackageManager.ACTION_PACKAGE_UNINSTALL);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (PluginPackageManager.ACTION_PACKAGE_UNINSTALL.equals(intent.getAction())) {
                    // �?�载广播
                    String pkgName = intent.getStringExtra(IntentConstant.EXTRA_PKG_NAME);
                    exitPlugin(pkgName);
                }
            }
        };
        context.registerReceiver(receiver, filter);
    }

    /**
     * �?�件进程的Activity栈是�?�空
     *
     * @return true: Activity栈是空，false：Activity栈�?是空
     */
    public static boolean isActivityStackEmpty() {
        for (Map.Entry<String, PluginLoadedApk> entry : PluginManager.getAllPluginLoadedApk().entrySet()) {
            PluginLoadedApk mLoadedApk = entry.getValue();
            if (mLoadedApk != null && !mLoadedApk.getActivityStackSupervisor().isStackEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 处�?��?�件退出时的善�?��?作
     *
     * @param mPackageName 退出�?�件的包�??
     * @param force        是�?�强制退出
     */
    public static void doExitStuff(String mPackageName, boolean force) {
        if (TextUtils.isEmpty(mPackageName)) {
            return;
        }

        if (force || (isActivityStackEmpty() && PServiceSupervisor.getAliveServices().isEmpty())) {
            if (null != sExitStuff) {
                PluginDebugLog.runtimeLog(TAG, "do release stuff with " + mPackageName);
                sExitStuff.doExitStuff(mPackageName);
            }
        }
    }

    /**
     * 设置投递逻辑的实现(宿主工程调用)
     */
    public static void setDeliverImpl(IDeliverInterface mDeliverImpl) {
        mDeliver = mDeliverImpl;
    }

    /**
     * 设置�?�件状�?监�?�器(宿主工程调用)
     */
    public static void setPluginStatusListener(IPluginStatusListener mListener) {
        sPluginStatusListener = mListener;
    }

    /**
     * 设置�?�件退出监�?�回调(宿主工程调用)
     */
    public static void setExitStuff(IAppExitStuff mExitStuff) {
        sExitStuff = mExitStuff;
    }

    /**
     * �?�止指定的Service
     */
    public static void stopService(Intent intent) {
        if (intent == null || intent.getComponent() == null
                || TextUtils.isEmpty(intent.getComponent().getPackageName())) {
            return;
        }
        final String packageName = intent.getComponent().getPackageName();
        PluginLoadedApk mLoadedApk = sPluginsMap.get(packageName);
        if (mLoadedApk == null) {
            return;
        }
        PluginContextWrapper appWrapper = mLoadedApk.getAppWrapper();
        if (appWrapper != null) {
            appWrapper.stopService(intent);
        }
    }

    /**
     * 退出�?�件进程
     *
     * @param mContext     主进程Context
     * @param mProcessName �?退出进程
     */
    public static void quit(Context mContext, String mProcessName) {

        PluginPackageManagerNative.getInstance(mContext).release();

        for (Map.Entry<String, PluginLoadedApk> entry : getAllPluginLoadedApk().entrySet()) {
            PluginLoadedApk plugin = entry.getValue();
            if (plugin != null) {
                plugin.quitApp(true, false);
            }
        }
        PServiceSupervisor.clearConnections();
        // sAliveServices will be cleared, when on ServiceProxy1 destroy.
        Intent intent = new Intent();
        String proxyServiceName = ComponentFinder.matchServiceProxyByFeature(mProcessName);
        try {
            PluginDebugLog.runtimeLog(TAG, "try to stop service " + proxyServiceName);
            intent.setClass(mContext, Class.forName(proxyServiceName));
            intent.setAction(IntentConstant.ACTION_QUIT_SERVICE);
            mContext.startService(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * dump 当�?的�?�件栈信�?�
     */
    public static void dump(PrintWriter printWriter) {
        try {
            printWriter.print("================start dump plugin activity stack====================");
            Iterator<Map.Entry<String, PluginLoadedApk>> mIterator = sPluginsMap.entrySet().iterator();
            while (mIterator.hasNext()) {
                Map.Entry<String, PluginLoadedApk> tmp = mIterator.next();
                printWriter.print("packageName:" + tmp.getKey());
                printWriter.print("\n");
                tmp.getValue().getActivityStackSupervisor().dump(printWriter);
            }
            printWriter.print("================end dump plugin activity stack====================");
        } catch (Exception e) {
            e.printStackTrace();
            printWriter.print("error:" + e.getMessage());
        }

    }

    /**
     * 注册ActivityLifeCycle到�?�件的Application
     */
    public static void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        synchronized (sActivityLifecycleCallbacks) {
            sActivityLifecycleCallbacks.add(callback);
        }
        // 对于已�?�?行的�?�件，需�?注册到其Application类中
        for (Map.Entry<String, PluginLoadedApk> entry : sPluginsMap.entrySet()) {
            PluginLoadedApk loadedApk = entry.getValue();
            if (loadedApk != null && loadedApk.getPluginApplication() != null) {
                Application application = loadedApk.getPluginApplication();
                application.registerActivityLifecycleCallbacks(callback);
            }
        }
    }

    /**
     * �?�消�?�件Application里的ActivityLifeCycle监�?�
     */
    public static void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        synchronized (sActivityLifecycleCallbacks) {
            sActivityLifecycleCallbacks.remove(callback);
        }
        // 对于已�?�?行的�?�件，需�?从其Application类中�??注册
        for (Map.Entry<String, PluginLoadedApk> entry : sPluginsMap.entrySet()) {
            PluginLoadedApk loadedApk = entry.getValue();
            if (loadedApk != null && loadedApk.getPluginApplication() != null) {
                Application application = loadedApk.getPluginApplication();
                application.unregisterActivityLifecycleCallbacks(callback);
            }
        }
    }


    /**
     * �?�件状�?投递逻辑接�?�，由外部实现并设置进�?�
     */
    public interface IDeliverInterface {
        void deliver(boolean success, PluginLiteInfo pkgInfo, int errorCode);
    }

    public interface IAppExitStuff {
        void doExitStuff(String pkgName);
    }

    /**
     * 加载�?�件的异步任务
     */
    private static class LoadPluginTask implements Runnable {

        private String mPackageName;
        private Context mHostContext;
        private String mProcessName;
        private PluginLoadedApk mLoadedApk;
        private PluginLoadedApkHandler mHandler;

        LoadPluginTask(Context mHostContext,
                       String mPackageName,
                       IPluginLoadListener mListener,
                       String mProcessName) {
            this.mHostContext = mHostContext.getApplicationContext();
            this.mPackageName = mPackageName;
            this.mProcessName = mProcessName;
            this.mHandler = new PluginLoadedApkHandler(mListener, mPackageName, Looper.getMainLooper());
        }

        @Override
        public void run() {
            boolean loaded = false;
            try {
                PluginLiteInfo packageInfo = PluginPackageManagerNative.getInstance(mHostContext)
                        .getPackageInfo(mPackageName);
                if (packageInfo != null) {
                    PluginDebugLog.runtimeLog(TAG,
                            "doInBackground:" + mPackageName);
                    loaded = createPluginLoadedApkInstance(mHostContext, packageInfo, mProcessName);
                    if (loaded && mLoadedApk != null) {
                        invokeApplication();
                    }
                } else {
                    PluginDebugLog.runtimeLog(TAG, "packageInfo is null before initProxyEnvironment");
                }
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
                PActivityStackSupervisor.clearLoadingIntent(mPackageName);
                deliver(mHostContext, false, mPackageName,
                        ErrorType.ERROR_PLUGIN_CREATE_LOADEDAPK);
                loaded = false;
            }
            int what = loaded ? PluginLoadedApkHandler.PLUGIN_LOADED_APK_CREATE_SUCCESS : PluginLoadedApkHandler.PLUGIN_LOADED_APK_CREATE_FAILED;
            mHandler.sendEmptyMessage(what);
        }

        /**
         * �?始化�?�件的Application对象
         */
        private void invokeApplication() throws Exception {
            final Exception[] temp = new Exception[1];
            RunUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mLoadedApk != null
                            && mLoadedApk.makeApplication()) {
                        PluginDebugLog.runtimeFormatLog(TAG, "plugin %s makeApplication success", mPackageName);
                    } else {
                        temp[0] = new RuntimeException("init Application failed");
                    }
                }
            }, true);
            if (temp[0] != null) {
                throw temp[0];
            }
        }

        /**
         * 创建�?�件的{@link PluginLoadedApk}实例
         */
        private boolean createPluginLoadedApkInstance(Context context,
                                                      PluginLiteInfo packageInfo,
                                                      String mProcessName) {
            String packageName = packageInfo.packageName;
            if (!TextUtils.isEmpty(packageName)) {
                mLoadedApk = getPluginLoadedApkByPkgName(packageName);
                if (mLoadedApk != null) {
                    return true;
                }
                PluginPackageManager.updateSrcApkPath(context, packageInfo);
                if (!TextUtils.isEmpty(packageInfo.srcApkPath)) {
                    File apkFile = new File(packageInfo.srcApkPath);
                    if (!apkFile.exists()) {
                        PluginDebugLog.runtimeLog(TAG,
                                "Special case apkFile not exist, notify client! packageName: " + packageName);
                        PluginPackageManager.notifyClientPluginException(context, packageName, "Apk file not exist when load plugin");
                        return false;
                    }

                    mLoadedApk = new PluginLoadedApk(context, packageInfo.srcApkPath, packageName, mProcessName);
                    addPluginLoadedApk(packageName, mLoadedApk);
                    PluginDebugLog.runtimeLog(TAG, "plugin loaded success! packageName: " + packageName);
                    return true;
                }
            }
            PluginDebugLog.runtimeLog(TAG, "plugin loaded failed! packageName: " + packageName);
            return false;
        }
    }

    /**
     * 加载�?�件线程和主线程通信Handler
     */
    static class PluginLoadedApkHandler extends Handler {
        public static final int PLUGIN_LOADED_APK_CREATE_SUCCESS = 0x10;
        public static final int PLUGIN_LOADED_APK_CREATE_FAILED = 0x20;

        IPluginLoadListener mListener;
        String mPackageName;

        public PluginLoadedApkHandler(IPluginLoadListener listener, String pakName, Looper looper) {
            super(looper);
            this.mListener = listener;
            this.mPackageName = pakName;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PLUGIN_LOADED_APK_CREATE_SUCCESS:
                    if (mListener != null) {
                        mListener.onLoadSuccess(mPackageName);
                    }
                    break;
                case PLUGIN_LOADED_APK_CREATE_FAILED:
                    if (mListener != null) {
                        mListener.onLoadFailed(mPackageName);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 查看�?�件是�?�创建了 ClassLoader
     *
     * @param packageName 包�??
     * @return true or false
     */
    public static boolean isPluginClassLoaderLoaded(String packageName) {
        return PluginLoadedApk.isPluginClassLoaderLoaded(packageName);
    }
}
