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
package com.roncoo.pay.trade.service.impl;

import com.roncoo.pay.account.service.RpAccountTransactionService;
import com.roncoo.pay.notify.service.RpNotifyService;
import com.roncoo.pay.reconciliation.entity.RpAccountCheckMistake;
import com.roncoo.pay.trade.dao.RpTradePaymentOrderDao;
import com.roncoo.pay.trade.dao.RpTradePaymentRecordDao;
import com.roncoo.pay.trade.entity.RpTradePaymentOrder;
import com.roncoo.pay.trade.entity.RpTradePaymentRecord;
import com.roncoo.pay.trade.enums.TradeStatusEnum;
import com.roncoo.pay.trade.enums.TrxTypeEnum;
import com.roncoo.pay.trade.exception.TradeBizException;
import com.roncoo.pay.trade.service.RpTradeReconciliationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <b>功能说明:交易模�?�对账差错实现</b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
@Service("rpTradeReconciliationService")
public class RpTradeReconciliationServiceImpl implements RpTradeReconciliationService {

	private static final Logger LOG = LoggerFactory.getLogger(RpTradeReconciliationServiceImpl.class);

	@Autowired
	private RpTradePaymentOrderDao rpTradePaymentOrderDao;
	@Autowired
	private RpTradePaymentRecordDao rpTradePaymentRecordDao;
	@Autowired
	private RpNotifyService rpNotifyService;
	@Autowired
	private RpAccountTransactionService rpAccountTransactionService;

	/**
	 * 平�?��?功，银行记录�?存在，或者银行失败，以银行为准
	 * 
	 * @param trxNo
	 *            平�?�交易�?水
	 */

	// @Transactional(rollbackFor = Exception.class)
	public void bankMissOrBankFailBaseBank(String trxNo) {
		LOG.info("===== 把订�?�改为失败，并�?款开始========");
		RpTradePaymentRecord record = rpTradePaymentRecordDao.getByTrxNo(trxNo);
		if (record == null) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "trxNo[" + trxNo + "]的支付记录�?存在");
		}

		if (!record.getStatus().equals(TradeStatusEnum.SUCCESS.name())) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_STATUS_NOT_SUCCESS, "trxNo[" + trxNo + "]的支付记录状�?�?是success");
		}

		// 改支付记录状�?
		record.setStatus(TradeStatusEnum.FAILED.name());
		record.setRemark("对账差错处�?�,订�?�改为失败，并�?款.");
		rpTradePaymentRecordDao.update(record);

		// 改支付订�?�状�?
		RpTradePaymentOrder order = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(), record.getMerchantOrderNo());
		order.setStatus(TradeStatusEnum.FAILED.name());
		order.setRemark("对账差错处�?�,订�?�改为失败，并�?款.");
		rpTradePaymentOrderDao.update(order);

		// �?款
		rpAccountTransactionService.debitToAccount(record.getMerchantNo(), record.getOrderAmount().subtract(record.getPlatIncome()), record.getBankOrderNo(), TrxTypeEnum.ERRORHANKLE.name(), "对账差错处�?�,订�?�改为失败，并�?款.");
		LOG.info("===== 把订�?�改为失败，并�?款�?功========");
	}

	/**
	 * 银行支付�?功，平�?�失败.
	 * 
	 * @param trxNo
	 *            平�?�交易�?水
	 * @param bankTrxNo
	 *            银行返回�?水
	 */
	@Transactional(rollbackFor = Exception.class)
	public void platFailBankSuccess(String trxNo, String bankTrxNo) {

		LOG.info("===== 银行支付�?功，平�?�失败.========");

		RpTradePaymentRecord record = rpTradePaymentRecordDao.getByTrxNo(trxNo);
		if (record == null) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "trxNo[" + trxNo + "]的支付记录�?存在");
		}

		record.setBankTrxNo(bankTrxNo);
		record.setBankReturnMsg("SUCCESS");
		record.setStatus(TradeStatusEnum.SUCCESS.name());
		rpTradePaymentRecordDao.update(record);

		RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(), record.getMerchantOrderNo());
		rpTradePaymentOrder.setStatus(TradeStatusEnum.SUCCESS.name());
		rpTradePaymentOrderDao.update(rpTradePaymentOrder);

		rpAccountTransactionService.creditToAccount(record.getMerchantNo(), record.getOrderAmount().subtract(record.getPlatIncome()), record.getBankOrderNo(), record.getBankTrxNo(), record.getTrxType(), record.getRemark());

		rpNotifyService.notifySend(record.getNotifyUrl(), record.getMerchantOrderNo(), record.getMerchantNo());

	}

	/**
	 * 处�?�金�?�?匹�?异常
	 * 
	 * @param mistake
	 *            差错记录
	 * @param isBankMore
	 *            是�?�是银行金�?多
	 * @param baseOnBank
	 *            是�?�以银行为准
	 */

	@Transactional(rollbackFor = Exception.class)
	public void handleAmountMistake(RpAccountCheckMistake mistake, boolean isBankMore) {

		LOG.info("=====开始处�?�金�?差错,是�?�是银行金�?多[" + isBankMore + "],且都是以银行数�?�为准========");
		String trxNo = mistake.getTrxNo();
		RpTradePaymentRecord record = rpTradePaymentRecordDao.getByTrxNo(trxNo);
		if (record == null) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "trxNo[" + trxNo + "]的支付记录�?存在");
		}

		if (!record.getStatus().equals(TradeStatusEnum.SUCCESS.name())) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_STATUS_NOT_SUCCESS, "请先处�?�该订�?�状�?�?符的差错");
		}
		// 银行支付金�?
		BigDecimal bankAmount = mistake.getBankAmount();
		// 银行�?本
		BigDecimal bankFee = mistake.getBankFee();
		// 平�?�订�?�支付金�?
		BigDecimal orderAmount = record.getOrderAmount();
		// 平�?�已收商户的手续费
		BigDecimal fee = record.getPlatIncome();
		// 实际需�?手续费
		BigDecimal needFee = bankAmount.multiply(record.getFeeRate()).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		// 订�?�金�?差
		BigDecimal subOrderAmount = bankAmount.subtract(orderAmount).abs();
		// 手续费差
		BigDecimal subFee = needFee.subtract(fee).abs();

		/** 如果是银行金�?多 ----加 **/
		if (isBankMore) {
			/** 以银行数�?�为准 **/

			record.setOrderAmount(bankAmount);
			record.setPlatCost(bankFee);
			record.setPlatIncome(needFee);
			record.setRemark("差错调整：订�?�金�?加[" + subOrderAmount + "],手续费加[" + subFee + "],�?本�?��?[" + bankFee + "]");
			rpTradePaymentRecordDao.update(record);

			RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(), record.getMerchantOrderNo());
			rpTradePaymentOrder.setOrderAmount(bankAmount);
			rpTradePaymentOrder.setRemark("差错处�?�:订�?�金�?由[" + orderAmount + "]改为[" + bankAmount + "]");
			rpTradePaymentOrderDao.update(rpTradePaymentOrder);

			// 加款
			rpAccountTransactionService.creditToAccount(record.getMerchantNo(), subOrderAmount.subtract(subFee), record.getBankOrderNo(), record.getBankTrxNo(), TrxTypeEnum.ERRORHANKLE.name(), "差错处�?�加款。");
		}
		/** 平�?�金�?多 -----�? **/
		else {
			/** 以银行数�?�为准 **/

			record.setOrderAmount(bankAmount);
			record.setPlatCost(bankFee);
			record.setPlatIncome(needFee);
			record.setRemark("差错调整：订�?�金�?�?[" + subOrderAmount + "],手续费�?[" + subFee + "],�?本�?��?[" + bankFee + "]");
			rpTradePaymentRecordDao.update(record);

			RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(record.getMerchantNo(), record.getMerchantOrderNo());
			rpTradePaymentOrder.setOrderAmount(bankAmount);
			rpTradePaymentOrder.setRemark("差错处�?�:订�?�金�?由[" + orderAmount + "]改为[" + bankAmount + "]");
			rpTradePaymentOrderDao.update(rpTradePaymentOrder);

			// �?款
			rpAccountTransactionService.debitToAccount(record.getMerchantNo(), subOrderAmount.subtract(subFee), record.getBankOrderNo(), record.getBankTrxNo(), TrxTypeEnum.ERRORHANKLE.name(), "差错处�?��?款。");
		}
	}

	/**
	 * 处�?�手续费�?匹�?差错（默认以银行为准）
	 * 
	 * @param mistake
	 */

	@Transactional(rollbackFor = Exception.class)
	public void handleFeeMistake(RpAccountCheckMistake mistake) {

		String trxNo = mistake.getTrxNo();
		RpTradePaymentRecord record = rpTradePaymentRecordDao.getByTrxNo(trxNo);
		if (record == null) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "trxNo[" + trxNo + "]的支付记录�?存在");
		}

		if (!record.getStatus().equals(TradeStatusEnum.SUCCESS.name())) {
			throw new TradeBizException(TradeBizException.TRADE_ORDER_STATUS_NOT_SUCCESS, "请先处�?�该订�?�状�?�?符的差错");
		}

		BigDecimal oldBankFee = record.getPlatCost();
		BigDecimal bankFee = mistake.getBankFee();

		record.setPlatCost(bankFee);
		record.setRemark("差错处�?�:银行�?本由[" + oldBankFee + "]改为[" + bankFee + "]");
		rpTradePaymentRecordDao.update(record);
	}
}
