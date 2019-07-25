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

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.util.ArrayMap;

import com.wlqq.phantom.library.utils.VLog;

import java.util.ArrayList;
import java.util.Set;

/**
 * é?žæ ‡å‡†å?¯åŠ¨æ¨¡å¼? {@link ActivityInfo#LAUNCH_MULTIPLE} å?‘ä½? {@link Activity} å¤?ç”¨æ± 
 * <p/>
 * è‹¥ç³»ç»Ÿå›žæ”¶ {@link Activity}ï¼Œåˆ™ {@link Activity#isFinishing()} ä¸º falseï¼›
 * è‹¥æ˜¯ {@link Activity} é€€å‡ºï¼Œåˆ™ {@link Activity#isFinishing()} ä¸º
 * true
 * <p/>
 * é€šçŸ¥æ ?å?¯åŠ¨çš„ {@link Activity} éœ€è¦?å?¦å¤–å¤„ç?†ï¼Œ
 */

class ActivityPool {
    private final ArrayList<String> mUnusedActivities;
    private final ArrayMap<String, ActivityRecord> mUsedActivities;

    /**
     * @param size            å? ä½?activityä¸ªæ•°
     * @param modePrefix      å? ä½?activityå‰?ç¼€ï¼Œæ¯”å¦‚ActivityProxySingleInstance0çš„å‰?ç¼€æ˜¯ActivityProxySingleInstanceã€‚
     *                        å? ä½?activityç¼–å?·ä»Ž0å¼€å§‹
     * @param fixedActivities è¢«åˆ†é…?ç»™PendingIntentçš„Activity
     */
    ActivityPool(int size, String modePrefix, Set<String> fixedActivities) {
        this.mUnusedActivities = new ArrayList<>(size);
        this.mUsedActivities = new ArrayMap<>(size);
        for (int i = 0; i < size; i++) {
            this.mUnusedActivities.add(modePrefix + i);
        }

        for (String fixedActivityStr : fixedActivities) {
            FixedActivity fixedActivity = FixedActivity.parseFormString(fixedActivityStr);
            this.mUsedActivities.put(fixedActivity.pluginActivity,
                    new ActivityRecord(fixedActivity.proxyActivity, fixedActivity.pluginActivity, true, 0));
            this.mUnusedActivities.remove(fixedActivity.proxyActivity);
        }

    }

    String resolveActivity(String targetActivity) {
        return resolveActivity(targetActivity, false);
    }

    String resolveFixedActivity(String targetActivity) {
        return resolveActivity(targetActivity, true);
    }

    private String resolveActivity(String targetActivity, boolean isFixed) {
        //è¯¥activityå·²ç»?å?¯åŠ¨è¿‡
        ActivityRecord record = this.mUsedActivities.get(targetActivity);
        if (null != record) {
            VLog.d("resolveActivity %s has record, record isFixed=%s", targetActivity, record.isFixed());
            record.addRef();
            if (isFixed) {
                record.setFixed();
            }
            return record.mProxyActivity;
        }

        if (this.mUnusedActivities.size() > 0) {
            String activity = this.mUnusedActivities.remove(0);
            this.mUsedActivities.put(targetActivity, new ActivityRecord(activity, targetActivity, isFixed, 0));
            return activity;
        }

        return null;
    }


    void unrefActivity(String targetActivity) {
        ActivityRecord record = this.mUsedActivities.get(targetActivity);
        if (null == record) {
            return;
        }

        int ref = record.reduceRef();
        VLog.d("unrefActivity %s ref is %d isFixed %s", targetActivity, ref, record.isFixed());
        if (ref < 0 && !record.isFixed()) {
            //å›žæ”¶å? ä½?activity
            this.mUsedActivities.remove(targetActivity);
            this.mUnusedActivities.add(record.mProxyActivity);
            VLog.d("recycle proxy activity %s for %s", record.mProxyActivity, targetActivity);
        }
    }

    /**
     * æŸ¥æ‰¾æ?’ä»¶Activityå¯¹åº”çš„å? ä½?Activity
     *
     * @param pluginActivity æ?’ä»¶Activityå…¨å??ï¼Œæ ¼å¼?ä¸ºï¼špackageName/activityName
     * @return å¦‚æžœæ?’ä»¶Activityè¿˜æ²¡å?¯åŠ¨æˆ–æ˜¯æ ‡å‡†å?¯åŠ¨æ¨¡å¼?è¿”å›žnull
     */
    String findProxyActivity(String pluginActivity) {
        ActivityRecord record = mUsedActivities.get(pluginActivity);
        return null == record ? null : record.mProxyActivity;
    }

    /**
     * æ‰“å?°Activityæ˜ å°„å…³ç³»
     */
    void dump() {
        for (ActivityRecord record : mUsedActivities.values()) {
            VLog.w("%s  -->  %s", record.mProxyActivity, record.mPluginActivity);
        }

        for (String unusedActivity : mUnusedActivities) {
            VLog.w("%s  -->", unusedActivity);
        }
    }

    private static class ActivityRecord {
        String mProxyActivity;
        String mPluginActivity;
        int mActivityRef;
        //PendingIntentæ˜¯ç”±ç³»ç»Ÿå¤„ç?†çš„ï¼Œå¯¹è¿™ç±»Intentä½¿ç”¨Activityåˆ†é…?å›ºå®šçš„ä»£ç?†activity
        boolean mIsFixed;

        ActivityRecord(String proxyActivity, String pluginActivity, boolean isFixed, int ref) {
            this.mProxyActivity = proxyActivity;
            this.mActivityRef = ref;
            this.mIsFixed = isFixed;
            this.mPluginActivity = pluginActivity;
        }

        void setFixed() {
            this.mIsFixed = true;
        }

        boolean isFixed() {
            return this.mIsFixed;
        }

        /**
         * å¢žåŠ å¼•ç”¨è®¡æ•°
         *
         * @return å½“å‰?å¼•ç”¨æ•°
         */
        int addRef() {
            return ++this.mActivityRef;
        }

        /**
         * å‡?å°‘å¼•ç”¨è®¡æ•°
         *
         * @return å½“å‰?å¼•ç”¨æ•°
         */
        int reduceRef() {
            return --this.mActivityRef;
        }

        @Override
        public String toString() {
            return String.format("%s, use host %s activity", mPluginActivity, mProxyActivity);
        }
    }
}
