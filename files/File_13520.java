package org.springframework.cloud.alibaba.cloud.example;

import com.aliyun.mns.model.Message;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author å¦‚æžœéœ€è¦?ç›‘å?¬çŸ­ä¿¡æ˜¯å?¦è¢«å¯¹æ–¹æˆ?åŠŸæŽ¥æ”¶ï¼Œå?ªéœ€å®žçŽ°è¿™ä¸ªæŽ¥å?£å¹¶åˆ?å§‹åŒ–ä¸€ä¸ª Spring Bean å?³å?¯ã€‚
 */
@Component
public class SmsReportMessageListener
		implements org.springframework.cloud.alicloud.sms.SmsReportMessageListener {
	private List<Message> smsReportMessageSet = new LinkedList<>();

	@Override
	public boolean dealMessage(Message message) {
		smsReportMessageSet.add(message);
		System.err.println(this.getClass().getName() + "; " + message.toString());
		return true;
	}

	public List<Message> getSmsReportMessageSet() {

		return smsReportMessageSet;
	}
}
