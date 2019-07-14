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
 * 平�?�数�?�获�?�biz业务类.
 * 
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Component("reconciliationDataGetBiz")
public class ReconciliationDataGetBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationDataGetBiz.class);

	@Autowired
	private RpTradePaymentQueryService rpTradePaymentQueryService;

	/**
	 * 获�?�平�?�指定支付渠�?��?指定订�?�日下[所有�?功]的数�?�
	 * 
	 * @param billDate
	 *            账�?�日
	 * @param interfaceCode
	 *            支付渠�?�
	 * @return
	 */
	public List<RpTradePaymentRecord> getSuccessPlatformDateByBillDate(Date billDate, String interfaceCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("interfaceCode", interfaceCode);
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());

		LOG.info("开始查询平�?�支付�?功的数�?�：billDate[" + billDateStr + "],支付方�?为[" + interfaceCode + "]");
		List<RpTradePaymentRecord> recordList = rpTradePaymentQueryService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<RpTradePaymentRecord>();
		}
		LOG.info("查询得到的数�?�count[" + recordList.size() + "]");
		return recordList;

	}

	/**
	 * 获�?�平�?�指定支付渠�?��?指定订�?�日下[所有]的数�?�
	 * 
	 * @param billDate
	 *            账�?�日
	 * @param interfaceCode
	 *            支付渠�?�
	 * @return
	 */
	public List<RpTradePaymentRecord> getAllPlatformDateByBillDate(Date billDate, String interfaceCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("interfaceCode", interfaceCode);

		LOG.info("开始查询平�?�支付所有的数�?�：billDate[" + billDateStr + "],支付方�?为[" + interfaceCode + "]");
		List<RpTradePaymentRecord> recordList = rpTradePaymentQueryService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<RpTradePaymentRecord>();
		}
		LOG.info("查询得到的数�?�count[" + recordList.size() + "]");

		return recordList;

	}
}