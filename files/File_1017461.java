package com.foxinmy.weixin4j.dispatcher;

import java.util.HashMap;
import java.util.Map;

import com.foxinmy.weixin4j.message.ImageMessage;
import com.foxinmy.weixin4j.message.LinkMessage;
import com.foxinmy.weixin4j.message.LocationMessage;
import com.foxinmy.weixin4j.message.TextMessage;
import com.foxinmy.weixin4j.message.VideoMessage;
import com.foxinmy.weixin4j.message.VoiceMessage;
import com.foxinmy.weixin4j.message.event.LocationEventMessage;
import com.foxinmy.weixin4j.message.event.MenuEventMessage;
import com.foxinmy.weixin4j.message.event.MenuLocationEventMessage;
import com.foxinmy.weixin4j.message.event.MenuPhotoEventMessage;
import com.foxinmy.weixin4j.message.event.MenuScanEventMessage;
import com.foxinmy.weixin4j.mp.event.KfCloseEventMessage;
import com.foxinmy.weixin4j.mp.event.KfCreateEventMessage;
import com.foxinmy.weixin4j.mp.event.KfSwitchEventMessage;
import com.foxinmy.weixin4j.mp.event.MassEventMessage;
import com.foxinmy.weixin4j.mp.event.TemplatesendjobfinishMessage;
import com.foxinmy.weixin4j.mp.event.VerifyExpireEventMessage;
import com.foxinmy.weixin4j.mp.event.VerifyFailEventMessage;
import com.foxinmy.weixin4j.qy.event.BatchjobresultMessage;
import com.foxinmy.weixin4j.qy.event.EnterAgentEventMessage;
import com.foxinmy.weixin4j.request.WeixinMessage;
import com.foxinmy.weixin4j.type.AccountType;
import com.foxinmy.weixin4j.type.EventType;
import com.foxinmy.weixin4j.type.MessageType;

/**
 * é»˜è®¤MessageMatcherå®žçŽ°(å?¯ä»¥æ”¹è¿›)
 * 
 * @className DefaultMessageMatcher
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015å¹´6æœˆ10æ—¥
 * @since JDK 1.6
 * @see
 */
public class DefaultMessageMatcher implements WeixinMessageMatcher {

	private final Map<WeixinMessageKey, Class<? extends WeixinMessage>> messageClassMap;

	public DefaultMessageMatcher() {
		messageClassMap = new HashMap<WeixinMessageKey, Class<? extends WeixinMessage>>();
		initMessageClass();
	}

	private void initMessageClass() {
		// /////////////////////////////////////////////////
		/******************** æ™®é€šæ¶ˆæ?¯ ********************/
		// /////////////////////////////////////////////////
		initGeneralMessageClass();
		// /////////////////////////////////////////////////
		/******************** äº‹ä»¶æ¶ˆæ?¯ ********************/
		// /////////////////////////////////////////////////
		initEventMessageClass();
		// /////////////////////////////////////////////////
		/***************** å…¬ä¼—å¹³å?°äº‹ä»¶æ¶ˆæ?¯ *****************/
		// /////////////////////////////////////////////////
		initMpEventMessageClass();
		// /////////////////////////////////////////////////
		/****************** ä¼?ä¸šå?·äº‹ä»¶æ¶ˆæ?¯ ******************/
		// /////////////////////////////////////////////////
		initQyEventMessageClass();
	}

	private void initGeneralMessageClass() {
		for (AccountType accountType : AccountType.values()) {
			messageClassMap.put(new WeixinMessageKey(MessageType.text.name(),
					null, accountType), TextMessage.class);
			messageClassMap.put(new WeixinMessageKey(MessageType.image.name(),
					null, accountType), ImageMessage.class);
			messageClassMap.put(new WeixinMessageKey(MessageType.voice.name(),
					null, accountType), VoiceMessage.class);
			messageClassMap.put(new WeixinMessageKey(MessageType.video.name(),
					null, accountType), VideoMessage.class);
			messageClassMap.put(
					new WeixinMessageKey(MessageType.shortvideo.name(), null,
							accountType), VideoMessage.class);
			messageClassMap.put(
					new WeixinMessageKey(MessageType.location.name(), null,
							accountType), LocationMessage.class);
			messageClassMap.put(new WeixinMessageKey(MessageType.link.name(),
					null, accountType), LinkMessage.class);
		}
	}

	private void initEventMessageClass() {
		String messageType = MessageType.event.name();
		EventType[] eventTypes = new EventType[] { EventType.subscribe,
				EventType.unsubscribe };
		for (EventType eventType : eventTypes) {
			messageClassMap.put(
					new WeixinMessageKey(messageType, eventType.name(),
							AccountType.MP),
					com.foxinmy.weixin4j.mp.event.ScribeEventMessage.class);
		}
		for (EventType eventType : eventTypes) {
			messageClassMap.put(
					new WeixinMessageKey(messageType, eventType.name(),
							AccountType.QY),
					com.foxinmy.weixin4j.qy.event.ScribeEventMessage.class);
		}
		for (AccountType accountType : AccountType.values()) {
			messageClassMap.put(new WeixinMessageKey(messageType,
					EventType.location.name(), accountType),
					LocationEventMessage.class);
			messageClassMap.put(new WeixinMessageKey(messageType,
					EventType.location_select.name(), accountType),
					MenuLocationEventMessage.class);
			for (EventType eventType : new EventType[] { EventType.click,
					EventType.view }) {
				messageClassMap.put(
						new WeixinMessageKey(messageType, eventType.name(),
								accountType), MenuEventMessage.class);
			}
			for (EventType eventType : new EventType[] {
					EventType.scancode_push, EventType.scancode_waitmsg }) {
				messageClassMap.put(
						new WeixinMessageKey(messageType, eventType.name(),
								accountType), MenuScanEventMessage.class);
			}
			for (EventType eventType : new EventType[] {
					EventType.pic_sysphoto, EventType.pic_photo_or_album,
					EventType.pic_weixin }) {
				messageClassMap.put(
						new WeixinMessageKey(messageType, eventType.name(),
								accountType), MenuPhotoEventMessage.class);
			}
		}
	}

	private void initMpEventMessageClass() {
		String messageType = MessageType.event.name();
		AccountType accountType = AccountType.MP;
		messageClassMap.put(
				new WeixinMessageKey(messageType, EventType.scan.name(),
						accountType),
				com.foxinmy.weixin4j.mp.event.ScanEventMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.masssendjobfinish.name(), accountType),
				MassEventMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.templatesendjobfinish.name(), accountType),
				TemplatesendjobfinishMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.kf_create_session.name(), accountType),
				KfCreateEventMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.kf_close_session.name(), accountType),
				KfCloseEventMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.kf_switch_session.name(), accountType),
				KfSwitchEventMessage.class);
		EventType[] eventTypes = new EventType[] {
				EventType.qualification_verify_success,
				EventType.naming_verify_success, EventType.annual_renew,
				EventType.verify_expired };
		for (EventType eventType : eventTypes) {
			messageClassMap.put(
					new WeixinMessageKey(messageType, eventType.name(),
							accountType), VerifyExpireEventMessage.class);
		}
		eventTypes = new EventType[] { EventType.qualification_verify_success,
				EventType.naming_verify_fail };
		for (EventType eventType : eventTypes) {
			messageClassMap.put(
					new WeixinMessageKey(messageType, eventType.name(),
							accountType), VerifyFailEventMessage.class);
		}
	}

	private void initQyEventMessageClass() {
		String messageType = MessageType.event.name();
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.batch_job_result.name(), AccountType.QY),
				BatchjobresultMessage.class);
		messageClassMap.put(new WeixinMessageKey(messageType,
				EventType.enter_agent.name(), AccountType.QY),
				EnterAgentEventMessage.class);
		//messageClassMap.put(new WeixinMessageKey(messageType,
			//	EventType.suite.name(), AccountType.QY),
				//SuiteMessage.class);
	}

	@Override
	public Class<? extends WeixinMessage> match(WeixinMessageKey messageKey) {
		return messageClassMap.get(messageKey);
	}

	@Override
	public void regist(WeixinMessageKey messageKey,
			Class<? extends WeixinMessage> messageClass) {
		Class<?> clazz = match(messageKey);
		if (clazz != null) {
			throw new IllegalArgumentException("duplicate messagekey '"
					+ messageKey + "' define for " + clazz);
		}
		messageClassMap.put(messageKey, messageClass);
	}
}
