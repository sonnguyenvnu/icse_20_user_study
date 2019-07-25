package com.xuexiang.xuidemo.fragment.expands.floatview.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.fragment.expands.floatview.AppSwitchView;

import java.util.Objects;

/**
 * �?�是简�?�演示�?务，没�?��?活
 *
 * @author xuexiang
 * @since 2019/1/21 下�?�12:03
 */
public class AppMonitorService extends Service {

    private AppSwitchView mAppSwitchView;

    private AppMonitor mAppMonitor;

    public final static String KEY_APP_UID = "com.xuexiang.xuidemo.fragment.expands.floatview.service.key_app_uid";
    public final static String KEY_APP_NAME = "com.xuexiang.xuidemo.fragment.expands.floatview.service.key_app_name";
    public final static String KEY_APP_PACKAGE_NAME = "com.xuexiang.xuidemo.fragment.expands.floatview.service.key_app_package_name";

    public static final String CHANNEL_ID = "AppMonitorService";

    /**
     * 开�?�监测
     *
     * @param info
     */
    public static void start(Context context, ApplicationInfo info) {
        Intent intent = new Intent(context, AppMonitorService.class);
        if (info != null) {
            intent.putExtra(KEY_APP_UID, info.uid);
            intent.putExtra(KEY_APP_NAME, context.getPackageManager().getApplicationLabel(info));
            intent.putExtra(KEY_APP_PACKAGE_NAME, info.packageName);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /**
     * �?�止监测
     *
     * @param context
     */
    public static void stop(Context context) {
        context.stopService(new Intent(context, AppMonitorService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int uid;
        String appName, appPackageName;
        if (intent != null) {
            uid = intent.getIntExtra(KEY_APP_UID, 0);
            appName = intent.getStringExtra(KEY_APP_NAME);
            appPackageName = intent.getStringExtra(KEY_APP_PACKAGE_NAME);

            init(uid, appName, appPackageName);
        } else {
            uid = getApplicationInfo().uid;
            appName = getApplicationInfo().loadLabel(getPackageManager()).toString();
            appPackageName = getPackageName();

            init(uid, appName, appPackageName);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "应用监测", NotificationManager.IMPORTANCE_HIGH);
            Objects.requireNonNull(manager).createNotificationChannel(channel);
            builder = new Notification.Builder(this, CHANNEL_ID);
        } else {
            builder = new Notification.Builder(this);
        }
        startForeground(1000, builder.setContentTitle("应用监测")
                .setContentText("正在监测手机的应用使用情况")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .setOngoing(true)
                .build());
    }

    private void init(int uid, String appName, String appPackageName) {
        if (mAppSwitchView == null) {
            mAppSwitchView = new AppSwitchView(this);
            mAppSwitchView.show();
        }
        mAppSwitchView.updateAppInfo(appName, appPackageName);

        if (mAppMonitor == null) {
            mAppMonitor = new AppMonitor(this, new AppMonitor.OnAppListener() {
                @Override
                public void onAppChanged(String appName, String packageName) {
                    if (mAppSwitchView != null) {
                        mAppSwitchView.updateAppInfo(appName, packageName);
                    }
                }
            });
            mAppMonitor.updateUid(uid).start();
        } else {
            mAppMonitor.updateUid(uid);
        }

    }

    @Override
    public void onDestroy() {
        if (mAppMonitor != null) {
            mAppMonitor.close();
            mAppMonitor = null;
        }
        if (mAppSwitchView != null) {
            mAppSwitchView.clear();
            mAppSwitchView = null;
        }
        super.onDestroy();
    }
}
