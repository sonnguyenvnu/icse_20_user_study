/*
 * Copyright (C) 2017-2018 Manbang Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wlqq.phantom.library.pool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * 用于存�?�系统处�?�的 Intent 相关的 Activity。一般是通知点击�?�动 Activity，由于在程�?退出以�?�，点击通知也�?�能�?�动
 * Intent 相关的 Activity，所以这类�?�件 Activity 对应的�?��? Activity 就�?能分�?给其他�?�件 Activity。将这类 Activity
 * 进行�?久化存储，使�?�件 Activity 和�?��? Activity 的对应关系固定下�?�。
 */
class FixedActivityCache {
    private Set<String> mSingleTopFixedActivities;
    private Set<String> mSingleInstanceFixedActivities;
    private Set<String> mSingleTaskFixedActivities;

    public static final String PREFS_FIXED_ACTIVITY_CACHE = "fixed_activity_cache";

    private static final String TAG_SINGLE_TOP = "singleTop";
    private static final String TAG_SINGLE_INSTANCE = "singleInstance";
    private static final String TAG_SINGLE_TASK = "singleTask";

    private final SharedPreferences mSharedPreferences;

    FixedActivityCache(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_FIXED_ACTIVITY_CACHE, Context.MODE_PRIVATE);
    }

    void init() {
        mSingleTopFixedActivities = mSharedPreferences.getStringSet(TAG_SINGLE_TOP, new HashSet<String>());
        mSingleInstanceFixedActivities = mSharedPreferences.getStringSet(TAG_SINGLE_INSTANCE, new HashSet<String>());
        mSingleTaskFixedActivities = mSharedPreferences.getStringSet(TAG_SINGLE_TASK, new HashSet<String>());
    }

    void clean() {
        mSharedPreferences.edit()
                .remove(TAG_SINGLE_TOP)
                .remove(TAG_SINGLE_INSTANCE)
                .remove(TAG_SINGLE_TASK)
                .apply();
    }

    Set<String> getSingleTopActivities() {
        return this.mSingleTopFixedActivities;
    }

    Set<String> getSingleInstanceActivities() {
        return this.mSingleInstanceFixedActivities;
    }

    Set<String> getSingleTaskActivities() {
        return this.mSingleTaskFixedActivities;
    }

    void save(FixedActivity fixedActivity, int launchMode) {
        Set<String> fixedActivities = null;
        String tag = null;

        if (launchMode == ActivityInfo.LAUNCH_SINGLE_TOP) {
            fixedActivities = mSingleTopFixedActivities;
            tag = TAG_SINGLE_TOP;
        } else if (launchMode == ActivityInfo.LAUNCH_SINGLE_INSTANCE) {
            fixedActivities = mSingleInstanceFixedActivities;
            tag = TAG_SINGLE_INSTANCE;
        } else if (launchMode == ActivityInfo.LAUNCH_SINGLE_TASK) {
            fixedActivities = mSingleTaskFixedActivities;
            tag = TAG_SINGLE_TASK;
        }

        if (null != fixedActivities) {
            int size = fixedActivities.size();
            fixedActivities.add(fixedActivity.toString());
            if (fixedActivities.size() > size) {
                mSharedPreferences.edit()
                        .putStringSet(tag, fixedActivities)
                        .apply();
            }
        }
    }
}
