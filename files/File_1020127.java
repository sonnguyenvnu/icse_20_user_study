package com.myimooc.small.access.rest;

import java.util.Objects;

import com.myimooc.small.access.domain.EventMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myimooc.small.access.domain.TextMessage;
import com.myimooc.small.access.util.MessageUtils;

/**
 * å¤„ç?†æ¶ˆæ?¯è¯·æ±‚ä¸Žå“?åº”
 * @author ZhangCheng on 2017-08-11
 *
 */
@RestController
public class MessageRest {

	private static final String KEY_1 = "1";
	private static final String KEY_2 = "2";
	private static final String KEY_MENU_CN = "ï¼Ÿ";
	private static final String KEY_MENU_EN = "?";

	/**
	 * æŽ¥æ”¶å¾®ä¿¡æœ?åŠ¡å™¨å?‘é€?çš„POSTè¯·æ±‚
	 */
	@PostMapping("textmessage")
	public Object textmessage(TextMessage msg){
		// æ–‡æœ¬æ¶ˆæ?¯
		if(Objects.equals(MessageUtils.MESSAGE_TEXT, msg.getMsgType())){
			TextMessage textMessage = new TextMessage();
			// å…³é”®å­— 1
			if(Objects.equals(KEY_1, msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.firstMenu());
				return textMessage;
			}
			// å…³é”®å­— 2
			if(Objects.equals(KEY_2, msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.secondMenu());
				return textMessage;
			}
			// å…³é”®å­— ï¼Ÿ? è°ƒå‡ºè?œå?•
			if(Objects.equals(KEY_MENU_CN, msg.getContent()) || Objects.equals(KEY_MENU_EN, msg.getContent())){
				textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
				return textMessage;
			}
			
			// é?žå…³é”®å­—
			textMessage.setFromUserName(msg.getToUserName());
			textMessage.setToUserName(msg.getFromUserName());
			textMessage.setMsgType(MessageUtils.MESSAGE_TEXT);
			textMessage.setCreateTime(System.currentTimeMillis());
			textMessage.setContent("æ‚¨å?‘é€?çš„æ¶ˆæ?¯æ˜¯ï¼š" + msg.getContent());
			return textMessage;
		}
		return null;
	}
	
	/**
	 * æŽ¥æ”¶å¾®ä¿¡æœ?åŠ¡å™¨å?‘é€?çš„POSTè¯·æ±‚
	 */
	@PostMapping("eventmessage")
	public Object eventmessage(EventMessage msg){
		// äº‹ä»¶æŽ¨é€?
		if(Objects.equals(MessageUtils.MESSAGE_EVENT, msg.getMsgType())){
			// å…³æ³¨
			if(Objects.equals(MessageUtils.MESSAGE_SUBSCRIBE, msg.getEvent())){
				TextMessage text = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
				return text;
			}
		}
		return null;
	}

}
