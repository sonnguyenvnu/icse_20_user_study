package com.zlm.hp.manager;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

/**
 * activity的管�?�:退出时，�??历所有的activity，并finish,最�?�退出系统。
 *
 * @author Administrator 最近修改时间2013年12月10日
 */
public class ActivityManage {

    /**
     * activity列表
     */
    private List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManage instance = null;

    private ActivityManage() {

    }

    public static ActivityManage getInstance() {
        if (instance == null) {
            instance = new ActivityManage();
        }
        return instance;
    }

    /**
     * 添加
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 退出
     */
    public void exit() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing() && activity != null) {
                activity.finish();
            }
        }
    }
}
