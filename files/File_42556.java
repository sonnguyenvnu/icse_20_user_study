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
package com.roncoo.pay.reconciliation.service.impl;

import com.roncoo.pay.reconciliation.entity.RpAccountCheckBatch;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistake;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistakeScratchPool;
import com.roncoo.pay.reconciliation.enums.BatchStatusEnum;
import com.roncoo.pay.reconciliation.enums.MistakeHandleStatusEnum;
import com.roncoo.pay.reconciliation.service.RpAccountCheckBatchService;
import com.roncoo.pay.reconciliation.service.RpAccountCheckMistakeScratchPoolService;
import com.roncoo.pay.reconciliation.service.RpAccountCheckMistakeService;
import com.roncoo.pay.reconciliation.service.RpAccountCheckTransactionService;
import com.roncoo.pay.trade.service.RpTradeReconciliationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 对账数�?�事务一致性service.
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("rpAccountCheckTransactionService")
public class RpAccountCheckTransactionServiceImpl implements RpAccountCheckTransactionService {

	private static final Log LOG = LogFactory.getLog(RpAccountCheckTransactionServiceImpl.class);

	@Autowired
	private RpAccountCheckBatchService rpAccountCheckBatchService;
	@Autowired
	private RpAccountCheckMistakeService rpAccountCheckMistakeService;
	@Autowired
	private RpAccountCheckMistakeScratchPoolService rpAccountCheckMistakeScratchPoolService;
	@Autowired
	private RpTradeReconciliationService rpTradeReconciliationService;

	/**
	 * 为了�?�?事务一致性，把对数�?�库的�?作放入一个事务中
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveDatasaveDate(RpAccountCheckBatch batch, List<RpAccountCheckMistake> mistakeList, List<RpAccountCheckMistakeScratchPool> insertScreatchRecordList, List<RpAccountCheckMistakeScratchPool> removeScreatchRecordList) {
		LOG.info("========>  对完数�?��?�业务数�?�处�?�开始=========>");

		LOG.info("===> step1:�?存批次记录====");
		if (batch.getStatus() == null) {
			batch.setStatus(BatchStatusEnum.SUCCESS.name());
		}
		rpAccountCheckBatchService.saveData(batch);

		LOG.info("===> step2:�?存差错记录====总共[" + mistakeList.size() + "]�?�");
		rpAccountCheckMistakeService.saveListDate(mistakeList);

		LOG.info("===> step3:�?存记录到缓存池中====总共[" + insertScreatchRecordList.size() + "]�?�");
		rpAccountCheckMistakeScratchPoolService.savaListDate(insertScreatchRecordList);

		LOG.info("===> step4:从缓存池中删除已匹�?记录====总共[" + removeScreatchRecordList.size() + "]�?�");
		rpAccountCheckMistakeScratchPoolService.deleteFromPool(removeScreatchRecordList);

		LOG.info("<========  对完数�?��?�业务数�?�处�?�结�?�<=========");
	}

	/**
	 * 
	 * @param list
	 * @param mistakeList
	 */
	@Transactional(rollbackFor = Exception.class)
	public void removeDateFromPool(List<RpAccountCheckMistakeScratchPool> list, List<RpAccountCheckMistake> mistakeList) {
		LOG.info("========>  清�?�缓冲池中没有对账的数�?�，记录差错=========>");

		LOG.info("===> step1:�?存差错记录====总共[" + mistakeList.size() + "]�?�");
		rpAccountCheckMistakeService.saveListDate(mistakeList);

		LOG.info("===> step2:从缓存池中删除已匹�?记录====总共[" + list.size() + "]�?�");
		rpAccountCheckMistakeScratchPoolService.deleteFromPool(list);

		LOG.info("<========  清�?�缓冲池中没有对账的数�?�，记录差错结�?�<=========");
	}

	/**
	 * 差错处�?�
	 * 
	 * @param mistake
	 *            差错信�?�
	 */

	@Transactional(rollbackFor = Exception.class)
	public void handle(String id, String handleType, String handleRemark) {
		// 根�?�id查询
		RpAccountCheckMistake mistake = rpAccountCheckMistakeService.getDataById(id);
		mistake.setHandleStatus(MistakeHandleStatusEnum.HANDLED.name());
		mistake.setHandleRemark(handleRemark);
		// 修改差错记录
		rpAccountCheckMistakeService.updateData(mistake);

		Boolean bank = false;
		if ("bank".equals(handleType.trim())) {
			mistake.setHandleValue("以银行为准");
			bank = true;
		}
		// 以平�?�数�?�为准：�?�需修改差错记录
		if (!bank) {
			return;
		}

		switch (mistake.getErrType()) {

		case "BANK_MISS":// 银行�?存在该订�?�
			// 以银行为准
			if (bank) {
				// 把订�?�改为失败，�?款
				String trxNo = mistake.getTrxNo();
				rpTradeReconciliationService.bankMissOrBankFailBaseBank(trxNo);
			}

			break;

		case "PLATFORM_SHORT_STATUS_MISMATCH":// 银行支付�?功，平�?�支付�?�?功,默认以银行为准
			// 以银行为准
			if (bank) {
				String trxNo = mistake.getTrxNo();
				String bankTrxNo = mistake.getBankTrxNo();
				rpTradeReconciliationService.platFailBankSuccess(trxNo, bankTrxNo);
			}
			break;

		case "PLATFORM_SHORT_CASH_MISMATCH":// 平�?�需支付金�?比银行实际支付金�?少
			// 以银行为准
			if (bank) {
				// 累加金�?
				rpTradeReconciliationService.handleAmountMistake(mistake, true);
			}
			break;

		case "PLATFORM_OVER_CASH_MISMATCH":// 银行实际支付金�?比平�?�需支付金�?少
			// 以银行为准
			if (bank) {
				// 支付记录�?款
				rpTradeReconciliationService.handleAmountMistake(mistake, false);
			}
			break;

		case "PLATFORM_OVER_STATUS_MISMATCH":// 平�?�支付�?功，银行支付�?�?功(和银行�?�?�一致)
			// 以银行为准
			if (bank) {
				// 把订�?�改为失败，�?款
				String trxNo = mistake.getTrxNo();
				rpTradeReconciliationService.bankMissOrBankFailBaseBank(trxNo);
			}
			break;

		case "FEE_MISMATCH":// 手续费�?匹�?
			// 以银行为准
			if (bank) {
				rpTradeReconciliationService.handleFeeMistake(mistake);
			}
			break;

		case "PLATFORM_MISS":// 平�?��?存在该订�?�(暂时�?考虑这�?情况)
			break;

		default:
			break;
		}

	}
}
