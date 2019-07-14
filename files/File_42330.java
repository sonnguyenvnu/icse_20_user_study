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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>功能说明:
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
@Component
public class PollingQueue implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Log LOG = LogFactory.getLog(PollingQueue.class);

    /**
     * 将传过�?�的对象进行通知次数判断，决定是�?�放在任务队列中.<br/>
     * @param rpOrderResultQueryVo
     * @throws Exception
     */
    public void addToNotifyTaskDelayQueue(RpOrderResultQueryVo rpOrderResultQueryVo) {
        if (rpOrderResultQueryVo == null) {
            return;
        }
        LOG.info("===>addToOrderQueryTaskDelayQueue bank order no:" + rpOrderResultQueryVo.getBankOrderNo());
        Integer notifyTimes = rpOrderResultQueryVo.getNotifyTimes(); // 通知次数
        Integer maxNotifyTimes = rpOrderResultQueryVo.getLimitNotifyTimes(); // 最大通知次数

        if (rpOrderResultQueryVo.getNotifyTimes().intValue() == 0) {
            rpOrderResultQueryVo.setLastNotifyTime(new Date()); // 第一次�?��?(�?�当�?时间)
        }else{
            rpOrderResultQueryVo.setLastNotifyTime(rpOrderResultQueryVo.getEditTime()); // �?�第一次�?��?（�?�上一次修改时间，也是上一次�?��?时间）
        }

        if (notifyTimes < maxNotifyTimes) {
            // 未超过最大通知次数，继续下一次通知
            LOG.info("===>bank order No  " + rpOrderResultQueryVo.getBankOrderNo() + ", 上次通知时间lastNotifyTime:" + DateUtils.formatDate(rpOrderResultQueryVo.getLastNotifyTime(), "yyyy-MM-dd HH:mm:ss SSS"));
            AppOrderPollingApplication.tasks.put(new PollingTask(rpOrderResultQueryVo));
        }

    }
}
