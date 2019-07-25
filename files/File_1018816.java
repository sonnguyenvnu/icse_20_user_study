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
package org.qiyi.pluginlibrary.component.stackmgr;

import android.app.Activity;

import org.qiyi.pluginlibrary.utils.ContextUtils;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * �?�件的Activity栈抽象, 和系统的{@link com.android.server.am.ActivityStack}类似
 */
public class PActivityStack {
    private final LinkedList<Activity> mActivities;
    // taskAffinity
    private String taskName;

    PActivityStack(String taskName) {
        this.taskName = taskName;
        mActivities = new LinkedList<>();
    }

    /**
     * 获�?�当�?任务栈的�??称
     */
    public String getTaskName() {
        return taskName;
    }

    public LinkedList<Activity> getActivities() {
        return mActivities;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized boolean isEmpty() {
        return mActivities.isEmpty();
    }

    // 放入链表的�?�?�
    public synchronized void push(Activity activity) {
        mActivities.addFirst(activity);
    }

    public synchronized void insertFirst(Activity activity) {
        mActivities.addLast(activity);
    }

    public synchronized boolean pop(Activity activity) {
        return mActivities.remove(activity);
    }

    public synchronized Activity getTop() {
        return mActivities.getFirst();
    }

    /**
     * 清空当�?任务栈里的Activity
     */
    public void clear(boolean needFinish) {
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null && needFinish
                    && !ContextUtils.isFinished(activity)) {
                activity.finish();
            }
            iterator.remove();
        }
    }
}
