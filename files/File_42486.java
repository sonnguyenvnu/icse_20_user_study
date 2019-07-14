package com.roncoo.pay.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.pay.common.core.config.MqConfig;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.notify.dao.RpNotifyRecordDao;
import com.roncoo.pay.notify.dao.RpNotifyRecordLogDao;
import com.roncoo.pay.notify.entity.RpNotifyRecord;
import com.roncoo.pay.notify.entity.RpNotifyRecordLog;
import com.roncoo.pay.notify.enums.NotifyStatusEnum;
import com.roncoo.pay.notify.enums.NotifyTypeEnum;
import com.roncoo.pay.notify.service.RpNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Map;

/**
 * @åŠŸèƒ½è¯´æ˜Ž:
 * @åˆ›å»ºè€…: Peter
 * @åˆ›å»ºæ—¶é—´: 16/6/2 ä¸Šå?ˆ10:42
 * @å…¬å?¸å??ç§°:å¹¿å·žå¸‚é¢†è¯¾ç½‘ç»œç§‘æŠ€æœ‰é™?å…¬å?¸ é¾™æžœå­¦é™¢(www.roncoo.com)
 * @ç‰ˆæœ¬:V1.0
 */
@Service("rpNotifyService")
public class RpNotifyServiceImpl implements RpNotifyService {

	@Autowired
	private JmsTemplate notifyJmsTemplate;

	@Autowired
	private RpNotifyRecordDao rpNotifyRecordDao;

	@Autowired
	private RpNotifyRecordLogDao rpNotifyRecordLogDao;

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * å?‘é€?æ¶ˆæ?¯é€šçŸ¥
	 *
	 * @param notifyUrl
	 *            é€šçŸ¥åœ°å?€
	 * @param merchantOrderNo
	 *            å•†æˆ·è®¢å?•å?·
	 * @param merchantNo
	 *            å•†æˆ·ç¼–å?·
	 */
	@Override
	public void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo) {

		RpNotifyRecord record = new RpNotifyRecord();
		record.setNotifyTimes(0);
		record.setLimitNotifyTimes(5);
		record.setStatus(NotifyStatusEnum.CREATED.name());
		record.setUrl(notifyUrl);
		record.setMerchantOrderNo(merchantOrderNo);
		record.setMerchantNo(merchantNo);
		record.setNotifyType(NotifyTypeEnum.MERCHANT.name());

		Object toJSON = JSONObject.toJSON(record);
		final String str = toJSON.toString();
		
		notifyJmsTemplate.setDefaultDestinationName(MqConfig.MERCHANT_NOTIFY_QUEUE);
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(str);
			}
		});
	}

	/**
	 * è®¢å?•é€šçŸ¥
	 * 
	 * @param merchantOrderNo
	 */
	@Override
	public void orderSend(String bankOrderNo) {
		final String orderNo = bankOrderNo;
		
		jmsTemplate.setDefaultDestinationName(MqConfig.ORDER_NOTIFY_QUEUE);
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(orderNo);
			}
		});
	}

	/**
	 * é€šè¿‡IDèŽ·å?–é€šçŸ¥è®°å½•
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RpNotifyRecord getNotifyRecordById(String id) {
		return rpNotifyRecordDao.getById(id);
	}

	/**
	 * æ ¹æ?®å•†æˆ·ç¼–å?·,å•†æˆ·è®¢å?•å?·,é€šçŸ¥ç±»åž‹èŽ·å?–é€šçŸ¥è®°å½•
	 *
	 * @param merchantNo
	 *            å•†æˆ·ç¼–å?·
	 * @param merchantOrderNo
	 *            å•†æˆ·è®¢å?•å?·
	 * @param notifyType
	 *            æ¶ˆæ?¯ç±»åž‹
	 * @return
	 */
	@Override
	public RpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo,
                                                                               String merchantOrderNo, String notifyType) {
		return rpNotifyRecordDao.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(merchantNo, merchantOrderNo,
				notifyType);
	}

	@Override
	public PageBean<RpNotifyRecord> queryNotifyRecordListPage(PageParam pageParam, Map<String, Object> paramMap) {
		return rpNotifyRecordDao.listPage(pageParam, paramMap);
	}

	/**
	 * åˆ›å»ºæ¶ˆæ?¯é€šçŸ¥
	 *
	 * @param rpNotifyRecord
	 */
	@Override
	public long createNotifyRecord(RpNotifyRecord rpNotifyRecord) {
		return rpNotifyRecordDao.insert(rpNotifyRecord);
	}

	/**
	 * ä¿®æ”¹æ¶ˆæ?¯é€šçŸ¥
	 *
	 * @param rpNotifyRecord
	 */
	@Override
	public void updateNotifyRecord(RpNotifyRecord rpNotifyRecord) {
		rpNotifyRecordDao.update(rpNotifyRecord);
	}

	/**
	 * åˆ›å»ºæ¶ˆæ?¯é€šçŸ¥è®°å½•
	 *
	 * @param rpNotifyRecordLog
	 * @return
	 */
	@Override
	public long createNotifyRecordLog(RpNotifyRecordLog rpNotifyRecordLog) {
		return rpNotifyRecordLogDao.insert(rpNotifyRecordLog);
	}

}
