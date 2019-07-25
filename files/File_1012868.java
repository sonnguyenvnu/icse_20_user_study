/*-
 * <<
 * task
 * ==
 * Copyright (C) 2019 sia
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */

package com.sia.scheduler.quartz.trigger;


import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**
 * CronTriggerImpl
 *
 * Trigger implementation of cron type
 *
 * @description
 * @see
 * @author maozhengwei
 * @date 2018-08-19 19:49
 * @version V1.0.0
 **/
public class CronTriggerImpl extends AbstractTrigger {

    TriggerKey triggerKey;

    String cronExpression;

    public CronTriggerImpl() {
    }

    public CronTriggerImpl(TriggerKey triggerKey, String cronExpression) {

        this.triggerKey = triggerKey;
        this.cronExpression = cronExpression;
    }

    private Trigger getTrigger() {
        /**
         * quartz-misfire 错失�?补�?�执行
         * withMisfireHandlingInstructionFireAndProceed（默认） : 以当�?时间为触�?�频率立刻触�?�一次执行,然�?�按照Cron频率�?次执行.
         * withMisfireHandlingInstructionDoNothing : �?触�?�立�?�执行;等待下次Cron触�?�频率到达时刻开始按照Cron频率�?次执行;
         * withMisfireHandlingInstructionIgnoreMisfires : 以错过的第一个频率时间立刻开始执行,�?�?�错过的所有频率周期�?�,当下一次触�?�频率�?�生时间大于当�?时间�?�，�?按照正常的Cron频率�?次执行;
         * <code>{@Link https://www.w3cschool.cn/quartz_doc/}</code>
         */
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        Trigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        return cronTrigger;
    }

    /**
     * @param jobKey
     * @param jobGroup
     * @param trigerValue
     * @return
     */
    @Override
    Trigger build(String jobKey, String jobGroup, String trigerType, String trigerValue) {
        return new CronTriggerImpl(TriggerKey.triggerKey(jobKey, jobGroup), trigerValue).getTrigger();
    }
}
