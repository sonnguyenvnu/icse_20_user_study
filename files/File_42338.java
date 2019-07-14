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

import com.roncoo.pay.reconciliation.entity.RpAccountCheckBatch;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistake;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistakeScratchPool;
import com.roncoo.pay.reconciliation.enums.MistakeHandleStatusEnum;
import com.roncoo.pay.reconciliation.enums.ReconciliationMistakeTypeEnum;
import com.roncoo.pay.reconciliation.service.RpAccountCheckMistakeScratchPoolService;
import com.roncoo.pay.reconciliation.service.RpAccountCheckTransactionService;
import com.roncoo.pay.reconciliation.vo.ReconciliationEntityVo;
import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对账的核心业务biz.
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Component("reconciliationCheckBiz")
public class ReconciliationCheckBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationCheckBiz.class);

	@Autowired
	private RpAccountCheckMistakeScratchPoolService rpAccountCheckMistakeScratchPoolService;
	@Autowired
	private RpAccountCheckTransactionService rpAccountCheckTransactionService;

	@Autowired
	private ReconciliationDataGetBiz reconciliationDataGetBiz;

	/**
	 * 对账核心方法
	 * 
	 * @param bankList
	 *            对账文件解�?出�?�的数�?�
	 * @param interfaceCode
	 *            支付渠�?�
	 * @param batch
	 *            对账批次记录
	 */
	public void check(List<ReconciliationEntityVo> bankList, String interfaceCode, RpAccountCheckBatch batch) {
		// 判断bankList是�?�为空
		if (bankList == null) {
			bankList = new ArrayList<ReconciliationEntityVo>();
		}
		// 查询平�?�bill_date,interfaceCode�?功的交易
		List<RpTradePaymentRecord> platSucessDateList = reconciliationDataGetBiz.getSuccessPlatformDateByBillDate(batch.getBillDate(), interfaceCode);

		// 查询平�?�bill_date,interfaceCode所有的交易
		List<RpTradePaymentRecord> platAllDateList = reconciliationDataGetBiz.getAllPlatformDateByBillDate(batch.getBillDate(), interfaceCode);

		// 查询平�?�缓冲池中所有的数�?�
		List<RpAccountCheckMistakeScratchPool> platScreatchRecordList = rpAccountCheckMistakeScratchPoolService.listScratchPoolRecord(null);

		// 差错list
		List<RpAccountCheckMistake> mistakeList = new ArrayList<RpAccountCheckMistake>();

		// 需�?放入缓冲池中平�?�长款list
		List<RpAccountCheckMistakeScratchPool> insertScreatchRecordList = new ArrayList<RpAccountCheckMistakeScratchPool>();

		// 需�?从缓冲池中移除的数�?�
		List<RpAccountCheckMistakeScratchPool> removeScreatchRecordList = new ArrayList<RpAccountCheckMistakeScratchPool>();

		LOG.info("  开始以平�?�的数�?�为准对账,平�?�长款记入缓冲池");
		baseOnPaltForm(platSucessDateList, bankList, mistakeList, insertScreatchRecordList, batch);
		LOG.info("结�?�以平�?�的数�?�为准对账");

		LOG.info("  开始以银行通�?�的数�?�为准对账");
		baseOnBank(platAllDateList, bankList, platScreatchRecordList, mistakeList, batch, removeScreatchRecordList);
		LOG.info(" 结�?�以银行通�?�的数�?�为准对账");

		// �?存数�?�
		rpAccountCheckTransactionService.saveDatasaveDate(batch, mistakeList, insertScreatchRecordList, removeScreatchRecordList);

	}

	/**
	 * 以平�?�的数�?�为准对账
	 * 
	 * @param platformDateList
	 *            平�?�dilldate的�?功数�?�
	 * @param bankList
	 *            银行�?功对账�?�数�?�
	 * 
	 * @param misTakeList
	 *            差错list
	 * @param screatchRecordList
	 *            需�?放入缓冲池中平�?�长款list
	 * 
	 * @param batch
	 *            对账批次
	 */
	private void baseOnPaltForm(List<RpTradePaymentRecord> platformDateList, List<ReconciliationEntityVo> bankList, List<RpAccountCheckMistake> misTakeList, List<RpAccountCheckMistakeScratchPool> screatchRecordList, RpAccountCheckBatch batch) {
		BigDecimal platTradeAmount = BigDecimal.ZERO;// 平�?�交易总金�?
		BigDecimal platFee = BigDecimal.ZERO;// 平�?�总手续费
		Integer tradeCount = 0;// 平�?�订�?�总数
		Integer mistakeCount = 0;

		for (RpTradePaymentRecord record : platformDateList) {
			Boolean flag = false;// 用于标记是�?�有匹�?
			// 累计平�?�交易总金�?和总手续费
			platTradeAmount = platTradeAmount.add(record.getOrderAmount());
			platFee = platFee.add(record.getPlatCost() == null ? BigDecimal.ZERO : record.getPlatCost());
			tradeCount++;
			for (ReconciliationEntityVo bankRecord : bankList) {
				// 如果银行账�?�中有匹�?数�?�：进行金�?，手续费校验
				if (record.getBankOrderNo().equalsIgnoreCase(bankRecord.getBankOrderNo())) {
					flag = true;// 标记已�?找到匹�?

					/** step1:匹�?订�?�金�? **/
					// 平�?�金�?多
					if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
						// 金�?�?匹�?，创建差错记录
						RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}
					// 平�?�金�?少
					else if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
						// 金�?�?匹�?，创建差错记录
						RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}

					/** step2:匹�?订�?�手续费 **/
					if (record.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
						// 金�?�?匹�?，创建差错记录
						RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}

				}
			}
			// 没有找到匹�?的记录，把这个订�?�记录到缓冲池中
			if (!flag) {
				RpAccountCheckMistakeScratchPool screatchRecord = getScratchRecord(record, batch);
				screatchRecordList.add(screatchRecord);
			}
		}

		// 统计数�?��?存
		batch.setTradeAmount(platTradeAmount);
		batch.setTradeCount(tradeCount);
		batch.setFee(platFee);
		batch.setMistakeCount(mistakeCount);
	}

	/**
	 * 以银行的数�?�为准对账
	 * 
	 * @param bankList
	 *            银行对账�?�数�?�
	 * 
	 * @param misTakeList
	 *            差错list
	 * 
	 * @param platScreatchRecordList
	 *            平�?�缓冲池中的数�?�
	 * 
	 * @param batch
	 *            对账批次
	 */
	private void baseOnBank(List<RpTradePaymentRecord> platAllDateList, List<ReconciliationEntityVo> bankList, List<RpAccountCheckMistakeScratchPool> platScreatchRecordList, List<RpAccountCheckMistake> misTakeList, RpAccountCheckBatch batch, List<RpAccountCheckMistakeScratchPool> removeScreatchRecordList) {
		BigDecimal platTradeAmount = BigDecimal.ZERO;// 平�?�交易总金�?
		BigDecimal platFee = BigDecimal.ZERO;// 平�?�总手续费
		Integer tradeCount = 0;// 平�?�订�?�总数
		Integer mistakeCount = 0;
		// 拿银行数�?�去对账
		for (ReconciliationEntityVo bankRecord : bankList) {

			boolean flag = false;// 用于标记是�?�有匹�?
			for (RpTradePaymentRecord record : platAllDateList) {
				/** step1 检查有匹�?的数�?� **/
				if (bankRecord.getBankOrderNo().equals(record.getBankOrderNo())) {
					flag = true;
					/** step2： 判断平�?�状�?是�?�匹�? **/
					/** 注�?：状�?匹�?�?需�?�?�金�?和手续费验�?，以平�?�数�?�为基准对账已�?�?�了验�? **/
					// �?匹�?记录差错。
					if (!TradeStatusEnum.SUCCESS.name().equals(record.getStatus())) {
						RpAccountCheckMistake misktake1 = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_STATUS_MISMATCH, batch);
						misTakeList.add(misktake1);
						mistakeCount++;
						// break;

						/** 订�?�状�?�?匹�?验�?完之�?�，在验�?金�?和手续费，差错处�?�必须先处�?�状�?�?符的情况 **/
						// 验�?金�?和手续费
						/** step1:匹�?订�?�金�? **/
						// 平�?�金�?多
						if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
							// 金�?�?匹�?，创建差错记录
							RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}
						// 平�?�金�?少
						else if (record.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
							// 金�?�?匹�?，创建差错记录
							RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}

						/** step2:匹�?订�?�手续费 **/
						if (record.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
							// 金�?�?匹�?，创建差错记录
							RpAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}

					}
				}
			}

			/** step3： 如果没有匹�?的数�?�，去缓冲池中查找对账，如果没有记录差错 **/
			if (!flag) {
				// 去缓冲池中查找对账(�?�??是缓冲池里�?�有数�?�)
				if (platScreatchRecordList != null)
					for (RpAccountCheckMistakeScratchPool scratchRecord : platScreatchRecordList) {

						// 找到匹�?的
						if (scratchRecord.getBankOrderNo().equals(bankRecord.getBankOrderNo())) {
							// 累计平�?�交易总金�?和总手续费
							platTradeAmount = platTradeAmount.add(scratchRecord.getOrderAmount());
							platFee = platFee.add(scratchRecord.getPlatCost() == null ? BigDecimal.ZERO : scratchRecord.getPlatCost());
							tradeCount++;
							flag = true;

							// 验�?金�?和手续费
							/** step1:匹�?订�?�金�? **/
							// 平�?�金�?多
							if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
								// 金�?�?匹�?，创建差错记录
								RpAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}
							// 平�?�金�?少
							else if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
								// 金�?�?匹�?，创建差错记录
								RpAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}

							/** step2:匹�?订�?�手续费 **/
							if (scratchRecord.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
								// 金�?�?匹�?，创建差错记录
								RpAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}

							/** step3:把缓存池中匹�?的记录删除掉 **/
							removeScreatchRecordList.add(scratchRecord);
						}
					}
			}

			// 缓冲池中还是没有这�?�记录,直接记录差错，差错类型为 PLATFORM_MISS("平�?��?�?�")
			if (!flag) {
				RpAccountCheckMistake misktake = createMisktake(null, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_MISS, batch);
				misTakeList.add(misktake);
				mistakeCount++;
			}
		}

		// 统计数�?��?存
		batch.setTradeAmount(batch.getTradeAmount().add(platTradeAmount));
		batch.setTradeCount(batch.getTradeCount() + tradeCount);
		batch.setFee(batch.getFee().add(platFee));
		batch.setMistakeCount(batch.getMistakeCount() + mistakeCount);
	}

	/**
	 * 创建差错记录
	 * 
	 * @param scratchRecord
	 *            平�?�缓冲池中的订�?�记录
	 * @param record
	 *            平�?�订�?�记录
	 * @param bankRecord
	 *            银行账�?�记录
	 * @param mistakeType
	 *            差错类型
	 * @return 注�?：scratchRecord和record 至少有一个为空
	 */
	private RpAccountCheckMistake createMisktake(RpAccountCheckMistakeScratchPool scratchRecord, RpTradePaymentRecord record, ReconciliationEntityVo bankRecord, ReconciliationMistakeTypeEnum mistakeType, RpAccountCheckBatch batch) {

		RpAccountCheckMistake mistake = new RpAccountCheckMistake();
		mistake.setAccountCheckBatchNo(batch.getBatchNo());
		mistake.setBillDate(batch.getBillDate());
		mistake.setErrType(mistakeType.name());
		mistake.setHandleStatus(MistakeHandleStatusEnum.NOHANDLE.name());
		mistake.setBankType(batch.getBankType());
		if (record != null) {
			mistake.setMerchantName(record.getMerchantName());
			mistake.setMerchantNo(record.getMerchantNo());
			mistake.setOrderNo(record.getMerchantOrderNo());
			mistake.setTradeTime(record.getPaySuccessTime());
			mistake.setTrxNo(record.getTrxNo());
			mistake.setOrderAmount(record.getOrderAmount());
			mistake.setRefundAmount(record.getSuccessRefundAmount());
			mistake.setTradeStatus(record.getStatus());
			mistake.setFee(record.getPlatCost());
		}

		if (scratchRecord != null) {
			mistake.setOrderNo(scratchRecord.getMerchantOrderNo());
			mistake.setTradeTime(scratchRecord.getPaySuccessTime());
			mistake.setTrxNo(scratchRecord.getTrxNo());
			mistake.setOrderAmount(scratchRecord.getOrderAmount());
			mistake.setRefundAmount(scratchRecord.getSuccessRefundAmount());
			mistake.setTradeStatus(scratchRecord.getStatus());
			mistake.setFee(scratchRecord.getPlatCost());
		}

		if (bankRecord != null) {
			mistake.setBankAmount(bankRecord.getBankAmount());
			mistake.setBankFee(bankRecord.getBankFee());
			mistake.setBankOrderNo(bankRecord.getBankOrderNo());
			mistake.setBankRefundAmount(bankRecord.getBankRefundAmount());
			mistake.setBankTradeStatus(bankRecord.getBankTradeStatus());
			mistake.setBankTradeTime(bankRecord.getBankTradeTime());
			mistake.setBankTrxNo(bankRecord.getBankTrxNo());
		}
		return mistake;

	}

	/**
	 * 得到缓存记录：用于放入缓冲池
	 * 
	 * @param record
	 *            支付记录
	 * @param batch
	 *            对账批次记录
	 * @return
	 */
	private RpAccountCheckMistakeScratchPool getScratchRecord(RpTradePaymentRecord record, RpAccountCheckBatch batch) {

		RpAccountCheckMistakeScratchPool scratchRecord = new RpAccountCheckMistakeScratchPool();
		scratchRecord.setBankOrderNo(record.getBankOrderNo());
		scratchRecord.setBankTrxNo(record.getBankTrxNo());
		scratchRecord.setCompleteTime(record.getCompleteTime());
		scratchRecord.setPaySuccessTime(record.getPaySuccessTime());
		scratchRecord.setMerchantOrderNo(record.getMerchantOrderNo());
		scratchRecord.setOrderAmount(record.getOrderAmount());
		scratchRecord.setPlatCost(record.getPlatCost());
		scratchRecord.setPayWayCode(record.getPayWayCode());
		scratchRecord.setTrxNo(record.getTrxNo());
		scratchRecord.setStatus(TradeStatusEnum.SUCCESS.name());
		scratchRecord.setBatchNo(batch.getBatchNo());
		scratchRecord.setBillDate(batch.getBillDate());
		return scratchRecord;
	}
}
