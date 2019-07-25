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
package org.qiyi.pluginlibrary.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.assist.AssistContent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;

import org.qiyi.pluginlibrary.Neptune;
import org.qiyi.pluginlibrary.component.stackmgr.PServiceSupervisor;
import org.qiyi.pluginlibrary.component.stackmgr.PluginActivityControl;
import org.qiyi.pluginlibrary.component.stackmgr.PluginServiceWrapper;
import org.qiyi.pluginlibrary.constant.IntentConstant;
import org.qiyi.pluginlibrary.context.PluginContextWrapper;
import org.qiyi.pluginlibrary.error.ErrorType;
import org.qiyi.pluginlibrary.plugin.InterfaceToGetHost;
import org.qiyi.pluginlibrary.pm.PluginLiteInfo;
import org.qiyi.pluginlibrary.pm.PluginPackageInfo;
import org.qiyi.pluginlibrary.pm.PluginPackageManagerNative;
import org.qiyi.pluginlibrary.runtime.NotifyCenter;
import org.qiyi.pluginlibrary.runtime.PluginLoadedApk;
import org.qiyi.pluginlibrary.runtime.PluginManager;
import org.qiyi.pluginlibrary.utils.ComponentFinder;
import org.qiyi.pluginlibrary.utils.ErrorUtil;
import org.qiyi.pluginlibrary.utils.FileUtils;
import org.qiyi.pluginlibrary.utils.IRecoveryCallback;
import org.qiyi.pluginlibrary.utils.IntentUtils;
import org.qiyi.pluginlibrary.utils.PluginDebugLog;
import org.qiyi.pluginlibrary.utils.ReflectionUtils;
import org.qiyi.pluginlibrary.utils.ResourcesToolForPlugin;
import org.qiyi.pluginlibrary.utils.VersionUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * :plugin1 Activity　代�?�
 */
public class InstrActivityProxy1 extends Activity implements InterfaceToGetHost {
    private static final String TAG = InstrActivityProxy1.class.getSimpleName();
    /**
     * �?留 savedInstanceState，在真正�?��?的时候模拟�?��?
     * <p>
     * 已知问题：如果 savedInstanceState 包�?�自定义 class 会出错，考虑到代�?�方案�?�能会下线，暂�?修�?
     */
    private static ArrayMap<Intent, Bundle> sPendingSavedInstanceStateMap = new ArrayMap<>();
    /**
     * �?�动�?�件 Receiver 的优先级
     * <p>
     * 在�?��? Activity 堆栈时，如果栈顶 Activity 是�?明主题，会连续�?��?多个 Activity 直到�?��?明主题 Activity，
     * 这个优先级递增，�?�?�?��?��?的 Activity �?�以先打开被压到栈底。
     * <p>
     * �?�有进程�?��?时�?需�?，进程�?��?时会自动�?置。
     */
    private static int sLaunchPluginReceiverPriority;
    private PluginLoadedApk mLoadedApk;
    private PluginActivityControl mPluginControl;
    private PluginContextWrapper mPluginContextWrapper;
    private String mPluginPackage = "";
    private volatile boolean mRestartCalled = false;
    private BroadcastReceiver mFinishSelfReceiver;
    /**
     * 等待 PluginPackageManagerService 连接�?功�?��?�动�?�件
     */
    private BroadcastReceiver mLaunchPluginReceiver;
    private IRecoveryCallback mRecoveryCallback;
    private Handler mHandler = new Handler();
    private Runnable mMockServiceReady = new Runnable() {
        @Override
        public void run() {
            PluginDebugLog.runtimeLog(TAG, "mock ServiceConnected event.");
            NotifyCenter.notifyServiceConnected(InstrActivityProxy1.this, "");
        }
    };
    private boolean mNeedUpdateConfiguration = true;

    private void initRecoveryCallback() {
        mRecoveryCallback = Neptune.getConfig().getRecoveryCallback();
        if (mRecoveryCallback == null) {
            mRecoveryCallback = new IRecoveryCallback.DefaultRecoveryCallback();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecoveryCallback();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onCreate....");
        String pluginActivityName = null;
        String pluginPkgName = null;
        final String[] pkgAndCls = parsePkgAndClsFromIntent();
        if (pkgAndCls != null && pkgAndCls.length == 2) {
            pluginPkgName = pkgAndCls[0];
            pluginActivityName = pkgAndCls[1];
        } else {
            PluginManager.deliver(this, false, "",
                    ErrorType.ERROR_PLUGIN_GET_PKG_AND_CLS_FAIL);
            PluginDebugLog.log(TAG, "Pkg or activity is null in LActivityProxy, just return!");
            this.finish();
            return;
        }

        if (!tryToInitPluginLoadApk(pluginPkgName)) {
            tryRecoverPluginActivity(pluginPkgName, pluginActivityName, savedInstanceState);
            return;
        }
        if (!PluginManager.isPluginLoadedAndInit(pluginPkgName)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(pluginPkgName,
                    IntentConstant.EXTRA_VALUE_LOADTARGET_STUB));
            PluginManager.readyToStartSpecifyPlugin(this, null, intent, true);
        }

        NotifyCenter.notifyPluginStarted(this, getIntent());
        Activity mPluginActivity = loadPluginActivity(mLoadedApk, pluginActivityName);
        if (null == mPluginActivity) {
            PluginManager.deliver(this, false, pluginPkgName,
                    ErrorType.ERROR_PLUGIN_LOAD_TARGET_ACTIVITY);
            PluginDebugLog.log(TAG, "Cannot get pluginActivityName class finish!, pkgName: " + pluginPkgName);
            this.finish();
            return;
        }

        mPluginControl = new PluginActivityControl(InstrActivityProxy1.this, mPluginActivity,
                mLoadedApk.getPluginApplication(), mLoadedApk.getPluginInstrument());
        mPluginContextWrapper = new PluginContextWrapper(InstrActivityProxy1.this.getBaseContext(), mLoadedApk);
        // 需�?先修改InstrActivity的ActivityInfo，这样�?��?�attach的信�?��?是正确的
        ActivityInfo actInfo = mLoadedApk.getActivityInfoByClassName(pluginActivityName);
        if (actInfo != null) {
            changeActivityInfo(this, pluginPkgName, actInfo);
        }

        if (!mPluginControl.dispatchProxyToPlugin(mLoadedApk.getPluginInstrument(), mPluginContextWrapper, pluginPkgName)) {
            PluginDebugLog.runtimeLog(TAG, "dispatchProxyToPlugin failed, call attach failed");
            this.finish();
            return;
        }

        int resTheme = mLoadedApk.getActivityThemeResourceByClassName(pluginActivityName);
        setTheme(resTheme);
        // Set plugin's default theme.
        mPluginActivity.setTheme(resTheme);

        try {
            callProxyOnCreate(savedInstanceState);
        } catch (Exception e) {
            ErrorUtil.throwErrorIfNeed(e);
            PluginManager.deliver(this, false, pluginPkgName,
                    ErrorType.ERROR_PLUGIN_CALL_ACTIVITY_ONCREATE);
            this.finish();
            return;
        }
        mRestartCalled = false;  // onCreate()-->onStart()
    }

    /**
     * 调用被代�?�的Activity的onCreate方法
     */
    private void callProxyOnCreate(Bundle savedInstanceState) {
        boolean mockRestoreInstanceState = false;
        // 使用上一次的 savedInstance 进行�?��?
        if (savedInstanceState == null) {
            savedInstanceState = sPendingSavedInstanceStateMap.remove(getIntent());
            if (savedInstanceState != null) {
                savedInstanceState.setClassLoader(mLoadedApk.getPluginClassLoader());
                mockRestoreInstanceState = true;
            }
        }
        if (getParent() == null) {
            mLoadedApk.getActivityStackSupervisor().pushActivityToStack(this);
        }
        mPluginControl.callOnCreate(savedInstanceState);

        // 模拟 onRestoreInstanceState
        if (mockRestoreInstanceState) {
            onRestoreInstanceState(savedInstanceState);
        }

        mPluginControl.getPluginRef().set("mDecor", this.getWindow().getDecorView());
        NotifyCenter.notifyPluginActivityLoaded(this.getBaseContext());
    }

    /**
     * 装载被代�?�的Activity
     *
     * @param mLoadedApk   �?�件的实例对象
     * @param activityName 需�?被代�?�的Activity 类�??
     * @return �?功则返回�?�件中被代�?�的Activity对象
     */
    private Activity loadPluginActivity(PluginLoadedApk mLoadedApk, String activityName) {
        try {
            Activity mActivity = (Activity) mLoadedApk.getPluginClassLoader()
                    .loadClass(activityName).newInstance();
            return mActivity;
        } catch (Exception e) {
            ErrorUtil.throwErrorIfNeed(e);
        }
        return null;
    }

    /**
     * 从Intent里�?�解�?被代�?�的Activity的包�??和组件�??
     *
     * @return �?功则返回一个长度为2的String[], String[0]表示包�??，String[1]表示类�??
     * 失败则返回null
     */
    private String[] parsePkgAndClsFromIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }

        //从action里�?�拿到pkg,并全局�?存，然�?�还原action
        if (TextUtils.isEmpty(mPluginPackage)) {
            mPluginPackage = IntentUtils.getPluginPackage(intent);
        }
        IntentUtils.resetAction(intent);

        if (!TextUtils.isEmpty(mPluginPackage)) {
            if (mLoadedApk == null) {
                mLoadedApk = PluginManager.getPluginLoadedApkByPkgName(mPluginPackage);
            }
            if (mLoadedApk != null) {
                //解决�?�件中跳转自定义Bean对象失败的问题
                intent.setExtrasClassLoader(mLoadedApk.getPluginClassLoader());
            }
        }

        String[] result = new String[2];
        try {
            result[0] = IntentUtils.getTargetPackage(intent);
            result[1] = IntentUtils.getTargetClass(intent);
        } catch (RuntimeException e) {
            // Intent里放置了�?�件自定义的�?列化数�?�
            // 进程�?��?时，android.os.BadParcelableException: ClassNotFoundException when unmarshalling:
            // 使用 action 里�?�的 pluginPackageName
            result[0] = mPluginPackage;
        }
        if (!TextUtils.isEmpty(result[0]) && !TextUtils.isEmpty(result[1])) {
            PluginDebugLog.runtimeFormatLog(TAG, "pluginPkg:%s, pluginCls:%s", result[0], result[1]);
            return result;
        }
        return null;
    }

    /**
     * �?试�?始化PluginLoadedApk
     */
    private boolean tryToInitPluginLoadApk(String mPluginPackage) {
        if (!TextUtils.isEmpty(mPluginPackage) && null == mLoadedApk) {
            mLoadedApk = PluginManager.getPluginLoadedApkByPkgName(mPluginPackage);
        }

        return mLoadedApk != null;
    }

    /**
     * �?试�?��?�?�件Activity
     *
     * @param pkgName  �?�件包�??
     * @param activityName  �?�件Activity类�??
     * @param savedInstanceState 状�?数�?�
     */
    private void tryRecoverPluginActivity(final String pkgName, final String activityName,
                                          Bundle savedInstanceState) {
        PluginLiteInfo packageInfo = PluginPackageManagerNative.getInstance(this).getPackageInfo(pkgName);
        boolean enableRecovery = packageInfo != null && packageInfo.enableRecovery;
        // 如果�?�件�?支�? recovery，则直接 finish
        if (!enableRecovery) {
            this.finish();
            PluginDebugLog.log(TAG, "PluginLoadedApk not loaded in InstrActivityProxy, pkgName: " + pkgName);
        } else {
            mRecoveryCallback.beforeRecovery(this, pkgName, activityName);
            mRecoveryCallback.onSetContentView(this, pkgName, activityName);

            final Intent pluginIntent = new Intent(getIntent());
            pluginIntent.setComponent(new ComponentName(pkgName, activityName));
            sPendingSavedInstanceStateMap.put(pluginIntent, savedInstanceState);

            mLaunchPluginReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String serviceCls = intent.getStringExtra(IntentConstant.EXTRA_SERVICE_CLASS);
                    PluginDebugLog.formatLog(TAG, "LaunchPluginReceiver#onReceive %s %s", pkgName, serviceCls);
                    boolean ppmsReady = PluginPackageManagerNative.getInstance(context).isConnected();
                    boolean readyLaunch = mRecoveryCallback.beforeLaunch(context, pkgName, activityName);
                    if (ppmsReady && readyLaunch) {
                        PluginDebugLog.formatLog(TAG, "LaunchPluginReceiver#launch %s", activityName);
                        PluginManager.launchPlugin(context, pluginIntent, FileUtils.getCurrentProcessName(context));
                        unregisterReceiver(mLaunchPluginReceiver);
                        mLaunchPluginReceiver = null;
                    }
                }
            };
            IntentFilter serviceConnectedFilter = new IntentFilter(IntentConstant.ACTION_SERVICE_CONNECTED);
            // 设置 priority �?�?�?��?�?明主题 Activity 时的顺�?，详�? sLaunchPluginReceiverPriority 注释
            serviceConnectedFilter.setPriority(sLaunchPluginReceiverPriority++);
            registerReceiver(mLaunchPluginReceiver, serviceConnectedFilter);

            mFinishSelfReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    InstrActivityProxy1.this.finish();
                    mRecoveryCallback.afterRecovery(context, pkgName, activityName);
                }
            };
            IntentFilter filter = new IntentFilter();
            filter.addAction(IntentConstant.ACTION_START_PLUGIN_ERROR);
            filter.addAction(IntentConstant.ACTION_PLUGIN_LOADED);
            registerReceiver(mFinishSelfReceiver, filter);
        }
    }

    public PluginActivityControl getController() {
        return mPluginControl;
    }

    @Override
    public Resources getResources() {
        if (mLoadedApk == null) {
            return super.getResources();
        }
        Resources mPluginResource = mLoadedApk.getPluginResource();
        return mPluginResource == null ? super.getResources()
                : mPluginResource;
    }

    /**
     * Override Oppo method in Context Resolve cann't start plugin on oppo
     * devices, true or false both OK, false as the temporary result
     * [warning] �?�?删除该方法，在oppo机型的Context类中存在
     *
     * @Override
     */
    public boolean isOppoStyle() {
        return false;
    }

    @Override
    public Resources.Theme getTheme() {
        if (mLoadedApk == null) {
            String[] temp = parsePkgAndClsFromIntent();
            if (null != temp && temp.length == 2) {
                tryToInitPluginLoadApk(temp[0]);
            }
        }
        return super.getTheme();
    }

    @Override
    public void setTheme(int resId) {
        if (VersionUtils.hasNougat()) {
            String[] temp = parsePkgAndClsFromIntent();
            if (mNeedUpdateConfiguration && (temp != null || mLoadedApk != null)) {
                if (null != temp && temp.length == 2) {
                    tryToInitPluginLoadApk(temp[0]);
                }
                if (mLoadedApk != null && temp != null) {
                    ActivityInfo actInfo = mLoadedApk.getActivityInfoByClassName(temp[1]);
                    if (actInfo != null) {
                        int resTheme = actInfo.getThemeResource();
                        if (mNeedUpdateConfiguration) {
                            changeActivityInfo(InstrActivityProxy1.this, temp[0], actInfo);
                            super.setTheme(resTheme);
                            mNeedUpdateConfiguration = false;
                            return;
                        }
                    }
                }
            }
            super.setTheme(resId);
        } else {
            getTheme().applyStyle(resId, true);
        }
    }

    @Override
    public AssetManager getAssets() {
        if (mLoadedApk == null) {
            return super.getAssets();
        }
        AssetManager mPluginAssetManager = mLoadedApk.getPluginAssetManager();
        return mPluginAssetManager == null ? super.getAssets()
                : mPluginAssetManager;
    }

    @Override
    public File getFilesDir() {
        return mPluginContextWrapper.getFilesDir();
    }

    @Override
    public File getCacheDir() {
        return mPluginContextWrapper.getCacheDir();
    }

    @Override
    public File getExternalFilesDir(String type) {
        return mPluginContextWrapper.getExternalFilesDir(type);
    }

    @Override
    public File getExternalCacheDir() {
        return mPluginContextWrapper.getExternalCacheDir();
    }

    @Override
    public File getFileStreamPath(String name) {
        return mPluginContextWrapper.getFileStreamPath(name);
    }

    @Override
    public File getDir(String name, int mode) {
        return mPluginContextWrapper.getDir(name, mode);

    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        return mPluginContextWrapper.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        return mPluginContextWrapper.openFileOutput(name, mode);
    }

    @Override
    public File getDatabasePath(String name) {
        return mPluginContextWrapper.getDatabasePath(name);
    }

    @Override
    public boolean deleteFile(String name) {
        return mPluginContextWrapper.deleteFile(name);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {

        return mPluginContextWrapper.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        return mPluginContextWrapper.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    public boolean deleteDatabase(String name) {

        return mPluginContextWrapper.deleteDatabase(name);
    }

    @Override
    public String[] databaseList() {
        return mPluginContextWrapper.databaseList();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mLoadedApk == null) {
            return super.getClassLoader();
        }
        return mLoadedApk.getPluginClassLoader();
    }

    @Override
    public Context getApplicationContext() {
        if (null != mLoadedApk && null != mLoadedApk.getPluginApplication()) {
            return mLoadedApk.getPluginApplication();
        }
        return super.getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onResume....");
        if (getController() != null) {
            try {
                getController().callOnResume();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
        // 模拟触�?� service 连接�?功，防止部分手机由于 plugin1 进程自�?�动 service �??�?准备好�?�，无法�?次触�?� service 连接事件
        mHandler.postDelayed(mMockServiceReady, 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onStart...., mRestartCalled: " + mRestartCalled);
        if (mRestartCalled) {
            // onStop()-->onRestart()-->onStart()，�?��?回调�?�件Activity#onStart方法两次
            mRestartCalled = false;
            return;
        }

        if (getController() != null) {
            try {
                getController().callOnStart();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onPostCreate....");
        if (getController() != null) {
            try {
                getController().callOnPostCreate(savedInstanceState);
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onDestroy....");
        if (null == this.getParent() && mLoadedApk != null) {
            mLoadedApk.getActivityStackSupervisor().popActivityFromStack(this);
        }
        if (getController() != null) {

            try {
                getController().callOnDestroy();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
        if (mLaunchPluginReceiver != null) {
            unregisterReceiver(mLaunchPluginReceiver);
        }
        if (mFinishSelfReceiver != null) {
            unregisterReceiver(mFinishSelfReceiver);
        }
        mHandler.removeCallbacks(mMockServiceReady);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onPause....");
        if (getController() != null) {

            try {
                getController().callOnPause();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onBackPressed....");
        if (getController() != null) {
            try {
                getController().callOnBackPressed();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onStop....");
        if (getController() != null) {
            try {
                getController().callOnStop();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onRestart....");
        if (getController() != null) {
            try {
                getController().callOnRestart();
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
        mRestartCalled = true;  //标记onRestart()被回调
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onKeyDown....keyCode=" + keyCode);
        if (getController() != null) {
            try {
                return getController().callOnKeyDown(keyCode, event);
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onPictureInPictureModeChanged....");
        if (getController() != null) {
            try {
                getController().callOnPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onPictureInPictureModeChanged....");
        if (getController() != null) {
            try {
                getController().callOnPictureInPictureModeChanged(isInPictureInPictureMode);
            } catch (Exception e) {
                ErrorUtil.throwErrorIfNeed(e);
            }
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy startActivityForResult one....");
        if (mLoadedApk != null) {
            super.startActivityForResult(
                    ComponentFinder.switchToActivityProxy(mLoadedApk.getPluginPackageName(),
                            intent, requestCode, this),
                    requestCode);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }


    @Override
    @SuppressLint("NewApi")
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy startActivityForResult two....");
        if (mLoadedApk != null) {
            super.startActivityForResult(
                    ComponentFinder.switchToActivityProxy(mLoadedApk.getPluginPackageName(),
                            intent, requestCode, this),
                    requestCode, options);
        } else {
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    @Override
    public ComponentName startService(Intent mIntent) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 startService....");
        if (mLoadedApk != null) {
            ComponentFinder.switchToServiceProxy(mLoadedApk, mIntent);
        }
        return super.startService(mIntent);
    }

    @Override
    public boolean stopService(Intent name) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 stopService....");
        if (mLoadedApk != null) {
            String mTargetServiceName = null;
            if (name.getComponent() != null) {
                mTargetServiceName = name.getComponent().getClassName();

            } else {
                PluginPackageInfo mInfo = mLoadedApk.getPluginPackageInfo();
                ServiceInfo mServiceInfo = mInfo.resolveService(name);
                if (mServiceInfo != null) {
                    mTargetServiceName = mServiceInfo.name;
                }
            }
            if (!TextUtils.isEmpty(mTargetServiceName)) {
                PluginServiceWrapper plugin = PServiceSupervisor.getServiceByIdentifer(
                        PluginServiceWrapper.getIdentify(mLoadedApk.getPluginPackageName(),
                                mTargetServiceName));
                if (plugin != null) {
                    plugin.updateServiceState(PluginServiceWrapper.PLUGIN_SERVICE_STOPED);
                    plugin.tryToDestroyService();
                    return true;
                }
            }

        }
        return super.stopService(name);
    }

    @Override
    public boolean bindService(Intent mIntent, ServiceConnection conn, int flags) {
        if (mLoadedApk != null) {
            ComponentFinder.switchToServiceProxy(mLoadedApk, mIntent);
        }
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 bindService...." + mIntent);
        return super.bindService(mIntent, conn, flags);
    }


    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return mPluginContextWrapper.getSharedPreferences(name, mode);
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        if (getController() != null) {
            getController().callDump(prefix, fd, writer, args);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNeedUpdateConfiguration = true;
        if (getController() != null) {
            getController().callOnConfigurationChanged(newConfig);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (getController() != null) {
            getController().callOnPostResume();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        if (getController() != null) {
            getController().callOnDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onCreateView1:" + name);
        if (getController() != null) {
            return getController().callOnCreateView(name, context, attrs);
        }
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onCreateView2:" + name);
        if (getController() != null) {
            return getController().callOnCreateView(parent, name, context, attrs);
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onNewIntent");
        if (getController() != null) {
            getController().callOnNewIntent(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onActivityResult");
        if (getController() != null) {
            Class<?>[] paramTypes = new Class[]{int.class, int.class, Intent.class};
            getController().getPluginRef().call("onActivityResult", PluginActivityControl.sMethods, paramTypes, requestCode, resultCode, data);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onAttachFragment");
        if (getController() != null && getController().getPlugin() != null) {
            getController().getPlugin().onAttachFragment(fragment);
        }
    }

    @Override
    public View onCreatePanelView(int featureId) {

        if (getController() != null && getController().getPlugin() != null) {
            return getController().getPlugin().onCreatePanelView(featureId);
        } else {
            return super.onCreatePanelView(featureId);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        if (getController() != null && getController().getPlugin() != null) {
            getController().getPlugin().onOptionsMenuClosed(menu);
        }
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
        if (getController() != null && getController().getPlugin() != null) {
            getController().getPlugin().onPanelClosed(featureId, menu);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (getController() != null && getController().getPlugin() != null) {
            return getController().getPlugin().onKeyUp(keyCode, event);
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    public void onAttachedToWindow() {

        super.onAttachedToWindow();
        if (getController() != null && getController().getPlugin() != null) {
            getController().getPlugin().onAttachedToWindow();
        }
    }

    @Override
    public CharSequence onCreateDescription() {
        if (getController() != null && getController().getPlugin() != null) {
            return getController().getPlugin().onCreateDescription();
        } else {
            return super.onCreateDescription();
        }
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (getController() != null && getController().getPlugin() != null) {
            return getController().getPlugin().onGenericMotionEvent(event);
        } else {
            return super.onGenericMotionEvent(event);
        }
    }

    @Override
    public void onContentChanged() {

        super.onContentChanged();
        if (getController() != null && getController().getPlugin() != null) {
            getController().getPlugin().onContentChanged();
        }
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        if (getController() != null && getController().getPlugin() != null) {
            return getController().getPlugin().onCreateThumbnail(outBitmap, canvas);
        } else {
            return super.onCreateThumbnail(outBitmap, canvas);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onRestoreInstanceState");
        if (getController() != null) {
            getController().callOnRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PluginDebugLog.runtimeLog(TAG, "InstrActivityProxy1 onSaveInstanceState");
        if (getController() != null) {
            getController().callOnSaveInstanceState(outState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (getController() != null) {
            ReflectionUtils pluginRef = getController().getPluginRef();
            if (pluginRef != null) {
                // 6.0.1用mHasCurrentPermissionsRequest�?�?分�?�permission
                // result完�?，根�?�现有
                // 的逻辑，第一次�?��?请求onRequestPermissionsResult一直是true，会影�?之�?�的申请�?��?的
                // dialog弹出
                try {
                    pluginRef.set("mHasCurrentPermissionsRequest", false);
                } catch (Exception e) {
                    // ignore
                }
                Class<?>[] paramTyps = new Class[]{int.class, String[].class, int[].class};
                pluginRef.call("onRequestPermissionsResult", PluginActivityControl.sMethods, paramTyps, requestCode, permissions, grantResults);
            }
        }
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        if (getController() != null) {
            getController().getPluginRef().call("onStateNotSaved", PluginActivityControl.sMethods, null);
        }
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getController() != null) {
                return getController().getPlugin().onSearchRequested(searchEvent);
            }
            return super.onSearchRequested(searchEvent);
        } else {
            return false;
        }
    }

    @Override
    public boolean onSearchRequested() {
        if (getController() != null) {
            getController().getPlugin().onSearchRequested();
        }
        return super.onSearchRequested();
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.onProvideAssistContent(outContent);
            if (getController() != null) {
                getController().getPlugin().onProvideAssistContent(outContent);
            }
        }
    }

    /**
     * Get the context which start this plugin
     */
    @Override
    public Context getOriginalContext() {
        if (null != mLoadedApk) {
            return mLoadedApk.getHostContext();
        }
        return null;
    }

    /**
     * 返回宿主的ResourceTools
     * @deprecated 使用资�?分区
     *
     * @return host resource tool
     */
    @Override
    public ResourcesToolForPlugin getHostResourceTool() {
        if (null != mLoadedApk) {
            return mLoadedApk.getHostResourceTool();
        }
        return null;
    }

    @Override
    public void exitApp() {
        if (null != mLoadedApk) {
            mLoadedApk.quitApp(true);
        }
    }

    @Override
    public String getPluginPackageName() {
        if (null != mLoadedApk) {
            return mLoadedApk.getPluginPackageName();
        }
        return this.getPackageName();
    }

    public String dump() {
        String[] pkgCls = parsePkgAndClsFromIntent();
        if (null != pkgCls && pkgCls.length == 2) {
            return "Package&Cls is: " + this + " " + (pkgCls[0] + " " + pkgCls[1]) + " flg=0x"
                    + Integer.toHexString(getIntent().getFlags());
        } else {
            return "Package&Cls is: " + this + " flg=0x" + Integer.toHexString(getIntent().getFlags());
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (mPluginContextWrapper != null) {
            return mPluginContextWrapper.getApplicationInfo();
        }
        return super.getApplicationInfo();
    }

    @Override
    public String getPackageCodePath() {
        if (mPluginContextWrapper != null) {
            return mPluginContextWrapper.getPackageCodePath();
        }
        return super.getPackageCodePath();
    }

    /**
     * 修改ActivityInfo信�?�
     *
     * @param activity  代�?�Activity
     * @param pkgName   �?�件包�??
     * @param mActivityInfo  �?�件ActivityInfo的信�?�
     */
    private void changeActivityInfo(Activity activity, String pkgName, ActivityInfo mActivityInfo) {

        ActivityInfo origActInfo = ReflectionUtils.on(activity).get("mActivityInfo");
        PluginLoadedApk mPlugin = PluginManager.getPluginLoadedApkByPkgName(pkgName);
        if (null != mActivityInfo) {
            if (null != mPlugin && null != mPlugin.getPluginPackageInfo()) {
                mActivityInfo.applicationInfo = mPlugin.getPluginPackageInfo().getPackageInfo().applicationInfo;
            }
            if (origActInfo != null) {
                origActInfo.applicationInfo = mActivityInfo.applicationInfo;
                origActInfo.configChanges = mActivityInfo.configChanges;
                origActInfo.descriptionRes = mActivityInfo.descriptionRes;
                origActInfo.enabled = mActivityInfo.enabled;
                origActInfo.exported = mActivityInfo.exported;
                origActInfo.flags = mActivityInfo.flags;
                origActInfo.icon = mActivityInfo.icon;
                origActInfo.labelRes = mActivityInfo.labelRes;
                origActInfo.logo = mActivityInfo.logo;
                origActInfo.metaData = mActivityInfo.metaData;
                origActInfo.name = mActivityInfo.name;
                origActInfo.nonLocalizedLabel = mActivityInfo.nonLocalizedLabel;
                origActInfo.packageName = mActivityInfo.packageName;
                origActInfo.permission = mActivityInfo.permission;
                origActInfo.screenOrientation = mActivityInfo.screenOrientation;
                origActInfo.softInputMode = mActivityInfo.softInputMode;
                origActInfo.targetActivity = mActivityInfo.targetActivity;
                origActInfo.taskAffinity = mActivityInfo.taskAffinity;
                origActInfo.theme = mActivityInfo.theme;
            }
        }
        // Handle ActionBar title
        if (null != origActInfo) {
            if (origActInfo.nonLocalizedLabel != null) {
                activity.setTitle(origActInfo.nonLocalizedLabel);
            } else if (origActInfo.labelRes != 0) {
                activity.setTitle(origActInfo.labelRes);
            } else {
                if (origActInfo.applicationInfo != null) {
                    if (origActInfo.applicationInfo.nonLocalizedLabel != null) {
                        activity.setTitle(origActInfo.applicationInfo.nonLocalizedLabel);
                    } else if (origActInfo.applicationInfo.labelRes != 0) {
                        activity.setTitle(origActInfo.applicationInfo.labelRes);
                    } else {
                        activity.setTitle(origActInfo.applicationInfo.packageName);
                    }
                }
            }
        }
        if (null != mActivityInfo) {
            getWindow().setSoftInputMode(mActivityInfo.softInputMode);

            PluginDebugLog.log(TAG, "changeActivityInfo->changeTheme: " + " theme = " +
                    mActivityInfo.getThemeResource() + ", icon = " + mActivityInfo.getIconResource()
                    + ", logo = " + mActivityInfo.logo + ", labelRes" + mActivityInfo.labelRes);
        }
    }
}
