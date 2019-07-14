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
package com.roncoo.pay.app.polling.listener;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.pay.app.polling.core.PollingQueue;
import com.roncoo.pay.app.polling.entity.PollingParam;
import com.roncoo.pay.common.core.exception.BizException;
import com.roncoo.pay.notify.entity.RpOrderResultQueryVo;
import com.roncoo.pay.notify.enums.NotifyStatusEnum;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author wujing
 */
@Component("pollingMessageListener")
public class PollingMessageListener implements MessageListener {
	private static final Log log = LogFactory.getLog(PollingMessageListener.class);

	@Autowired
	private PollingQueue pollingQueue;

	@Autowired
	private PollingParam pollingParam;

	@Override
	public void onMessage(Message message) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String msgText = msg.getText();
			log.info("== receive bankOrderNo :" + msgText);

			RpOrderResultQueryVo rpOrderResultQueryVo = new RpOrderResultQueryVo();

			rpOrderResultQueryVo.setBankOrderNo(msgText);
			rpOrderResultQueryVo.setStatus(NotifyStatusEnum.CREATED.name());
			rpOrderResultQueryVo.setCreateTime(new Date());
			rpOrderResultQueryVo.setEditTime(new Date());
			rpOrderResultQueryVo.setLastNotifyTime(new Date());
			rpOrderResultQueryVo.setNotifyTimes(0); // �?始化通知0次
			rpOrderResultQueryVo.setLimitNotifyTimes(pollingParam.getMaxNotifyTimes()); // 最大通知次数
			Map<Integer, Integer> notifyParams = pollingParam.getNotifyParams();
			rpOrderResultQueryVo.setNotifyRule(JSONObject.toJSONString(notifyParams)); // �?存JSON

			try {

				pollingQueue.addToNotifyTaskDelayQueue(rpOrderResultQueryVo); // 添加到通知队列(第一次通知)

			}  catch (BizException e) {
				log.error("BizException :", e);
			} catch (Exception e) {
				log.error(e);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

}
