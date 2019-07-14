package org.springframework.cloud.alibaba.cloud.example;

import org.springframework.stereotype.Component;

import com.aliyun.mns.model.Message;

/**
 * @author å¦‚æžœå?‘é€?çš„çŸ­ä¿¡éœ€è¦?æŽ¥æ”¶å¯¹æ–¹å›žå¤?çš„çŠ¶æ€?æ¶ˆæ?¯ï¼Œå?ªéœ€å®žçŽ°è¯¥æŽ¥å?£å¹¶åˆ?å§‹åŒ–ä¸€ä¸ª Spring Bean å?³å?¯ã€‚
 */
@Component
public class SmsUpMessageListener
		implements org.springframework.cloud.alicloud.sms.SmsUpMessageListener {

	@Override
	public boolean dealMessage(Message message) {
		System.err.println(this.getClass().getName() + "; " + message.toString());
		return true;
	}
}
