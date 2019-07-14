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

import com.roncoo.pay.common.core.exception.BizException;
import com.roncoo.pay.notify.entity.RpOrderResultQueryVo;
import com.roncoo.pay.trade.service.RpTradePaymentManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <b>功能说明:
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
@Service("pollingPersist")
public class PollingPersist {

    private static final Logger LOG = LoggerFactory.getLogger(PollingPersist.class);

    @Autowired
    private PollingQueue pollingQueue;

    @Autowired
    private RpTradePaymentManagerService rpTradePaymentManagerService;
    
    /**
     * 获�?�订�?�结果
     * @param rpOrderResultQueryVo
     */
    public void getOrderResult(RpOrderResultQueryVo rpOrderResultQueryVo){
    	Integer notifyTimes = rpOrderResultQueryVo.getNotifyTimes(); // 得到当�?通知对象的通知次数
        Integer maxNotifyTimes = rpOrderResultQueryVo.getLimitNotifyTimes(); // 最大通知次数
        Date notifyTime = new Date(); // 本次通知的时间
        rpOrderResultQueryVo.setEditTime(notifyTime); // �?�本次通知时间作为最�?�修改时间
        rpOrderResultQueryVo.setNotifyTimes(notifyTimes + 1); // 通知次数+1

        LOG.info("notifyTimes:{}  , maxNotifyTimes:{} " ,notifyTimes , maxNotifyTimes);
        try{
            boolean processingResult = rpTradePaymentManagerService.processingTradeRecord(rpOrderResultQueryVo.getBankOrderNo());

            LOG.info("order processing result:{}" ,processingResult);
            if (!processingResult){//返回失败,说明还未支付
                // 通知�?�?功（返回的结果�?是success）
                if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
                    // 判断是�?�超过�?�?�次数，未超�?�?�次数的，�?次进入延迟�?��?队列
                    pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
                    LOG.info("===>bank order {} need processing again ", rpOrderResultQueryVo.getBankOrderNo());
                } else {
                    LOG.info("bank order No {} not pay" , rpOrderResultQueryVo.getBankOrderNo());
                }
            }

        }catch (BizException e){
            LOG.error("订�?�处�?�业务异常:", e);
            if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
                // 判断是�?�超过�?�?�次数，未超�?�?�次数的，�?次进入延迟�?��?队列
                pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
                LOG.info("===>bank order {} need processing again ", rpOrderResultQueryVo.getBankOrderNo());
            } else {
                LOG.info("bank order No {} not pay" , rpOrderResultQueryVo.getBankOrderNo());
            }
        }catch (Exception e){
            LOG.error("订�?�处�?�系统异常:", e);
            if (rpOrderResultQueryVo.getNotifyTimes() < maxNotifyTimes) {
                // 判断是�?�超过�?�?�次数，未超�?�?�次数的，�?次进入延迟�?��?队列
                pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo);
                LOG.info("===>bank order {} need processing again ", rpOrderResultQueryVo.getBankOrderNo());
            } else {
                LOG.info("bank order No {} not pay" , rpOrderResultQueryVo.getBankOrderNo());
            }
        }
    }



}
