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
package com.roncoo.pay.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.roncoo.pay.common.core.exception.BizException;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.user.dao.BuildNoDao;
import com.roncoo.pay.user.entity.SeqBuild;
import com.roncoo.pay.user.service.BuildNoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ç”Ÿæˆ?ç¼–å?·serviceå®žçŽ°ç±»,æ¯?ä¸ªç¼–å?·å‰?é?¢éƒ½ä¼šæœ‰ä¸€ä¸ªå‰?ç¼€ç”¨æ?¥æ–¹ä¾¿åŒºåˆ†æ˜¯é‚£ç§?ç¼–å?·
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * @authorï¼šzenghao
 */
@Service("buildNoService")
public class BuildNoServiceImpl implements BuildNoService {

	private static final Log LOG = LogFactory.getLog(BuildNoServiceImpl.class);

	/** å¯¹è´¦æ‰¹æ¬¡å?·å‰?ç¼€ **/
	private static final String RECONCILIATION_BATCH_NO = "5555";

	/** é“¶è¡Œè®¢å?•å?· **/
	private static final String BANK_ORDER_NO_PREFIX = "6666";
	/** æ”¯ä»˜æµ?æ°´å?·å‰?ç¼€ **/
	private static final String TRX_NO_PREFIX = "7777";

	/** ç”¨æˆ·ç¼–å?·å‰?ç¼€ **/
	private static final String USER_NO_PREFIX = "8888";

	/** è´¦æˆ·ç¼–å?·å‰?ç¼€ **/
	private static final String ACCOUNT_NO_PREFIX = "9999";

	@Autowired
	private BuildNoDao buildNoDao;

	/** èŽ·å?–ç”¨æˆ·ç¼–å?· **/
	@Transactional(rollbackFor = Exception.class)
	public String buildUserNo() {
		// èŽ·å?–ç”¨æˆ·ç¼–å?·åº?åˆ—
		String userNoSeq = this.getSeqNextValue("USER_NO_SEQ");

		// 20ä½?çš„ç”¨æˆ·ç¼–å?·è§„èŒƒï¼š'8888' + yyyyMMdd(æ—¶é—´) + åº?åˆ—çš„å?Ž8ä½?
		String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
		String userNo = USER_NO_PREFIX + dateString + userNoSeq.substring(userNoSeq.length() - 8, userNoSeq.length());
		return userNo;
	}

	/** èŽ·å?–è´¦æˆ·ç¼–å?· **/
	@Transactional(rollbackFor = Exception.class)
	public String buildAccountNo() {
		// èŽ·å?–è´¦æˆ·ç¼–å?·åº?åˆ—å€¼ï¼Œç”¨äºŽç”Ÿæˆ?20ä½?çš„è´¦æˆ·ç¼–å?·
		String accountNoSeq = this.getSeqNextValue("ACCOUNT_NO_SEQ");
		// 20ä½?çš„è´¦æˆ·ç¼–å?·è§„èŒƒï¼š'9999' + yyyyMMdd(æ—¶é—´) + åº?åˆ—çš„å?Ž8ä½?
		String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
		String accountNo = ACCOUNT_NO_PREFIX + dateString + accountNoSeq.substring(accountNoSeq.length() - 8, accountNoSeq.length());

		return accountNo;
	}

	/**
	 * èŽ·å?–æ”¯ä»˜æµ?æ°´å?·
	 **/
	@Override
	public String buildTrxNo() {

		String trxNoSeq = this.getSeqNextValue("TRX_NO_SEQ");
		// 20ä½?çš„æ”¯ä»˜æµ?æ°´å?·è§„èŒƒï¼š'8888' + yyyyMMdd(æ—¶é—´) + åº?åˆ—çš„å?Ž8ä½?
		String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
		String trxNo = TRX_NO_PREFIX + dateString + trxNoSeq.substring(trxNoSeq.length() - 8, trxNoSeq.length());
		return trxNo;
	}

	/**
	 * èŽ·å?–é“¶è¡Œè®¢å?•å?·
	 **/
	@Override
	public String buildBankOrderNo() {

		String bankOrderNoSeq = this.getSeqNextValue("BANK_ORDER_NO_SEQ");
		// 20ä½?çš„ç”¨æˆ·ç¼–å?·è§„èŒƒï¼š'8888' + yyyyMMdd(æ—¶é—´) + åº?åˆ—çš„å?Ž8ä½?
		String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
		String bankOrderNo = BANK_ORDER_NO_PREFIX + dateString + bankOrderNoSeq.substring(bankOrderNoSeq.length() - 8, bankOrderNoSeq.length());
		return bankOrderNo;
	}

	/** èŽ·å?–å¯¹è´¦æ‰¹æ¬¡å?· **/
	public String buildReconciliationNo() {
		String batchNoSeq = this.getSeqNextValue("RECONCILIATION_BATCH_NO_SEQ");
		String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
		String batchNo = RECONCILIATION_BATCH_NO + dateString + batchNoSeq.substring(batchNoSeq.length() - 8, batchNoSeq.length());
		return batchNo;
	}

	/**
	 * æ ¹æ?®åº?åˆ—å??ç§°,èŽ·å?–åº?åˆ—å€¼
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getSeqNextValue(String seqName) {
		String seqNextValue = null;
		try {
			SeqBuild seqBuild = new SeqBuild();
			seqBuild.setSeqName(seqName);
			seqNextValue = buildNoDao.getSeqNextValue(seqBuild);
		} catch (Exception e) {
			LOG.error("ç”Ÿæˆ?åº?å?·å¼‚å¸¸ï¼š" + "seqName=" + seqName, e);
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		if (StringUtils.isEmpty(seqNextValue)) {
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		return seqNextValue;
	}

}
