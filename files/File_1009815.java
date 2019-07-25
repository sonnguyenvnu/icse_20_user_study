package com.starrtc.demo.utils;

/**
 * Created by zhangjt on 2018/3/16.
 */

import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.OrientationEventListener;

/**
 * 该类�?�以对Activity旋转和方�?�进行更加�?�活的控制。
 * 注�?，使用该类进行方�?�控制的Activity�?�?在清�?�文件中添加：
 * android:configChanges="orientation"
 *
 * 典型的应用场景：
 * 视频播放器的�?幕方�?��?功能。
 * 当�?�?�?幕方�?��?�，Activity就�?会�?�?�手机方�?�的旋转而改�?�方�?�。一旦打开�?，Activity将会立�?��?�?��?幕的方�?�而改�?�。
 *
 * 一般调用代�?：
 *
 * 默认打开�?
 * ActivityRotationController controller=new ActivityRotationController(this);
 *
 * 打开�?
 * controller.openActivityRotation();
 *
 * 关闭�?
 * controller.closeActivityRotation();
 *
 * 关闭监�?�，�?��?到系统之�?旋转设定
 * controller.disable()
 *
 * �?求的�?��?
 * @permission android.permission.WRITE_SETTINGS
 */

public class ActivityRotationController extends OrientationEventListener {
    private int systemRotation;
    private boolean activityRotation;
    private int activityOrientation;
    private Activity activity;

    public ActivityRotationController(Activity activity) {
        super(activity);
        this.activity = activity;
        activityOrientation = activity.getResources().getConfiguration().orientation;
        try {
            systemRotation = getScreenRotation(activity.getContentResolver());
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
            systemRotation = -1;
        }

        openActivityRotation();
        enable();
    }

    /**
     * 打开Activity旋转。
     * 如果打开了�?幕旋转，Activity将接收�?幕旋转事件并执行onConfigurationChanged方法。
     */
    public void openActivityRotation() {
        activityRotation = true;
    }

    /**
     * 关闭Activity旋转。
     * 无论是�?�打开�?幕旋转，Activity都�?能接收到�?幕旋转事件。
     */
    public void closeActivityRotation() {
        activityRotation = false;
    }

    /**
     * 检查Activity能�?�旋转
     */
    public boolean isActivityRotationEnabled() {
        return activityRotation;
    }

    /**
     * 获�?�Activity当�?方�?�。
     * 注�?，Activity方�?��?是�?幕方�?�。�?�有打开Activity旋转，Activity方�?��?和�?幕方�?��?�?一致。
     */
    public int getActivityOrientation() {
        return activityOrientation;
    }

    /**
     * 打开对�?幕旋转的监�?�，并设置�?幕为�?�旋转。
     */
    @Override
    public void enable() {
        super.enable();
        setScreenRotation(activity.getContentResolver(), 0);
    }

    /**
     * 关闭对�?幕旋转的监�?�，并�?��?到系统之�?旋转设定。
     */
    @Override
    public void disable() {
        super.disable();
        if (systemRotation == -1) {
            return;
        }
        setScreenRotation(activity.getContentResolver(), systemRotation);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        if (orientation < 0) {
            return;
        }

        int newOrientation= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        if (orientation >= 0 && orientation <= 60) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }else if (orientation >60 && orientation <120) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        }else if (orientation >=120 && orientation <=240) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
        }else if (orientation >240 && orientation <300) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }else if (orientation >=300 && orientation <=360) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }else{
            return;
        }

        if ((newOrientation != orientation) && activityRotation) {
            activity.setRequestedOrientation(newOrientation);
            activityOrientation = newOrientation;
        }
    }

    private void setScreenRotation(ContentResolver cr, int rotation) {
        Settings.System.putInt(cr, Settings.System.ACCELEROMETER_ROTATION,
                rotation);
    }

    private int getScreenRotation(ContentResolver cr)
            throws SettingNotFoundException {
        return Settings.System.getInt(cr,
                Settings.System.ACCELEROMETER_ROTATION);
    }

}
