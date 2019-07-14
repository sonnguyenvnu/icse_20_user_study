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
package com.roncoo.pay.app.reconciliation.biz;

import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import com.roncoo.pay.trade.service.RpTradePaymentQueryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * å¹³å?°æ•°æ?®èŽ·å?–bizä¸šåŠ¡ç±».
 * 
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Component("reconciliationDataGetBiz")
public class ReconciliationDataGetBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationDataGetBiz.class);

	@Autowired
	private RpTradePaymentQueryService rpTradePaymentQueryService;

	/**
	 * èŽ·å?–å¹³å?°æŒ‡å®šæ”¯ä»˜æ¸ é?“ã€?æŒ‡å®šè®¢å?•æ—¥ä¸‹[æ‰€æœ‰æˆ?åŠŸ]çš„æ•°æ?®
	 * 
	 * @param billDate
	 *            è´¦å?•æ—¥
	 * @param interfaceCode
	 *            æ”¯ä»˜æ¸ é?“
	 * @return
	 */
	public List<RpTradePaymentRecord> getSuccessPlatformDateByBillDate(Date billDate, String interfaceCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("interfaceCode", interfaceCode);
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());

		LOG.info("å¼€å§‹æŸ¥è¯¢å¹³å?°æ”¯ä»˜æˆ?åŠŸçš„æ•°æ?®ï¼šbillDate[" + billDateStr + "],æ”¯ä»˜æ–¹å¼?ä¸º[" + interfaceCode + "]");
		List<RpTradePaymentRecord> recordList = rpTradePaymentQueryService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<RpTradePaymentRecord>();
		}
		LOG.info("æŸ¥è¯¢å¾—åˆ°çš„æ•°æ?®count[" + recordList.size() + "]");
		return recordList;

	}

	/**
	 * èŽ·å?–å¹³å?°æŒ‡å®šæ”¯ä»˜æ¸ é?“ã€?æŒ‡å®šè®¢å?•æ—¥ä¸‹[æ‰€æœ‰]çš„æ•°æ?®
	 * 
	 * @param billDate
	 *            è´¦å?•æ—¥
	 * @param interfaceCode
	 *            æ”¯ä»˜æ¸ é?“
	 * @return
	 */
	public List<RpTradePaymentRecord> getAllPlatformDateByBillDate(Date billDate, String interfaceCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("interfaceCode", interfaceCode);

		LOG.info("å¼€å§‹æŸ¥è¯¢å¹³å?°æ”¯ä»˜æ‰€æœ‰çš„æ•°æ?®ï¼šbillDate[" + billDateStr + "],æ”¯ä»˜æ–¹å¼?ä¸º[" + interfaceCode + "]");
		List<RpTradePaymentRecord> recordList = rpTradePaymentQueryService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<RpTradePaymentRecord>();
		}
		LOG.info("æŸ¥è¯¢å¾—åˆ°çš„æ•°æ?®count[" + recordList.size() + "]");

		return recordList;

	}
}
