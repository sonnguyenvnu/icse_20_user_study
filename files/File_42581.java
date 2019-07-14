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
package com.roncoo.pay.trade.dao.impl;

import com.roncoo.pay.common.core.dao.impl.BaseDaoImpl;
import com.roncoo.pay.trade.dao.RpTradePaymentRecordDao;
import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>åŠŸèƒ½è¯´æ˜Ž:å•†æˆ·æ”¯ä»˜è®°å½•,daoå±‚å®žçŽ°ç±»</b>
 * @author  Peter
 * <a href="http://www.roncoo.com">é¾™æžœå­¦é™¢(www.roncoo.com)</a>
 */
@Repository("rpTradePaymentRecordDao")
public class RpTradePaymentRecordDaoImpl extends BaseDaoImpl<RpTradePaymentRecord> implements RpTradePaymentRecordDao {

	/**
	 * æ ¹æ?®é“¶è¡Œè®¢å?•å?·èŽ·å?–æ”¯ä»˜ä¿¡æ?¯
	 *
	 * @param bankOrderNo
	 * @return
	 */
	@Override
	public RpTradePaymentRecord getByBankOrderNo(String bankOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bankOrderNo", bankOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * æ ¹æ?®å•†æˆ·ç¼–å?·å?Šå•†æˆ·è®¢å?•å?·èŽ·å?–æ”¯ä»˜ç»“æžœ
	 *
	 * @param merchantNo
	 * @param merchantOrderNo
	 * @return
	 */
	@Override
	public RpTradePaymentRecord getSuccessRecordByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		paramMap.put("merchantOrderNo", merchantOrderNo);
		return super.getBy(paramMap);
	}

	/**
	 * æ ¹æ?®æ”¯ä»˜æµ?æ°´å?·æŸ¥è¯¢æ”¯ä»˜è®°å½•
	 * 
	 * @param trxNo
	 * @return
	 */
	public RpTradePaymentRecord getByTrxNo(String trxNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trxNo", trxNo);
		return super.getBy(paramMap);
	}
	
	public List<Map<String, String>> getPaymentReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSessionTemplate().selectList(getStatement("getPaymentReport"),paramMap);
	}

	public List<Map<String, String>> getPayWayReport(String merchantNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", TradeStatusEnum.SUCCESS.name());
		paramMap.put("merchantNo", merchantNo);
		return super.getSessionTemplate().selectList(getStatement("getPayWayReport"),paramMap);
	}

}
