/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
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
 */
package com.roncoo.pay.app.polling.core;

import com.roncoo.pay.AppOrderPollingApplication;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.notify.entity.RpOrderResultQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <b>功能说明:
 * </b>
 *
 * @author Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
public class PollingTask implements Runnable, Delayed {

    private static final Logger LOG = LoggerFactory.getLogger(PollingTask.class);

    private long executeTime;

    private PollingPersist pollingPersist = AppOrderPollingApplication.cachePollingPersist;

    private RpOrderResultQueryVo rpOrderResultQueryVo;

    public PollingTask() {
    }

    public PollingTask(RpOrderResultQueryVo rpOrderResultQueryVo) {
        super();
        this.rpOrderResultQueryVo = rpOrderResultQueryVo;
        this.executeTime = getExecuteTime(rpOrderResultQueryVo);
    }

    /**
     * 计算任务�?许执行的开始时间(executeTime).<br/>
     *
     * @param rpOrderResultQueryVo
     * @return
     */
    private long getExecuteTime(RpOrderResultQueryVo rpOrderResultQueryVo) {
        long lastNotifyTime = rpOrderResultQueryVo.getLastNotifyTime().getTime(); // 最�?�通知时间（上次通知时间）
        Integer notifyTimes = rpOrderResultQueryVo.getNotifyTimes(); // 已通知次数
        LOG.info("===>pollingTimes:{}", notifyTimes);
        //Integer nextNotifyTimeInterval = pollingParam.getNotifyParams().get(notifyTimes + 1); // 当�?�?��?次数对应的时间间隔数（分钟数）
        Integer nextNotifyTimeInterval = rpOrderResultQueryVo.getNotifyRuleMap().get(notifyTimes + 1); // 当�?�?��?次数对应的时间间隔数（分钟数）
        long nextNotifyTime = (nextNotifyTimeInterval == null ? 0 : nextNotifyTimeInterval * 1000) + lastNotifyTime;
        LOG.info("===>notify id:{}, nextNotifyTime:{}", rpOrderResultQueryVo.getId(), DateUtils.formatDate(new Date(nextNotifyTime), "yyyy-MM-dd HH:mm:ss SSS"));
        return nextNotifyTime;
    }

    /**
     * 比较当�?时间(task.executeTime)与任务�?许执行的开始时间(executeTime).<br/>
     * 如果当�?时间到了或超过任务�?许执行的开始时间，那么就返回-1，�?�以执行。
     */
    public int compareTo(Delayed o) {
        PollingTask task = (PollingTask) o;
        return executeTime > task.executeTime ? 1 : (executeTime < task.executeTime ? -1 : 0);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 执行通知处�?�.
     */
    public void run() {
        pollingPersist.getOrderResult(rpOrderResultQueryVo);
    }
}
