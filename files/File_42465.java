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
package com.roncoo.pay.account.service.impl;

import com.roncoo.pay.account.dao.RpAccountDao;
import com.roncoo.pay.account.dao.RpAccountHistoryDao;
import com.roncoo.pay.account.entity.RpAccount;
import com.roncoo.pay.account.entity.RpAccountHistory;
import com.roncoo.pay.account.enums.AccountFundDirectionEnum;
import com.roncoo.pay.account.exception.AccountBizException;
import com.roncoo.pay.account.service.RpAccountTransactionService;
import com.roncoo.pay.common.core.enums.PublicEnum;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.common.core.utils.StringUtil;
import com.roncoo.pay.trade.enums.TrxTypeEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户�?作service实现类
 * 龙果学院：www.roncoo.com
 * @author：zenghao
 */
@Service("rpAccountTransactionService")
public class RpAccountTransactionServiceImpl implements RpAccountTransactionService {
	
	private static final Log LOG = LogFactory.getLog(RpAccountTransactionServiceImpl.class);
	
	@Autowired
	private RpAccountDao rpAccountDao;
	@Autowired
	private RpAccountHistoryDao rpAccountHistoryDao;
	
	

	/**
	 * 根�?�用户编�?�编�?�获�?�账户信�?�
	 * 
	 * @param userNO
	 *            用户编�?�
	 * @param isPessimist
	 *            是�?�加行�?
	 * @return
	 */
	private RpAccount getByUserNo_IsPessimist(String userNo, boolean isPessimist) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("isPessimist", isPessimist);
		return rpAccountDao.getByUserNo(map);
	}

	/**
	 * 加款
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            加款金�?
	 * @param requestNo
	 *            请求�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark){

		return this.creditToAccount(userNo, amount, requestNo, null, trxType, remark);
	}

	/**
	 * 加款:有银行�?水
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            加款金�?
	 * @param requestNo
	 *            请求�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) {
		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// �?是�?�一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
		}

		// 总收益累加和今日收益
		if (TrxTypeEnum.EXPENSE.name().equals(trxType)) {// 业务类型是交易
			account.setTotalIncome(account.getTotalIncome().add(amount));

			/***** 根�?�上次修改时间，统计今日收益 *******/
			if (DateUtils.isSameDayWithToday(lastModifyDate)) {
				// 如果是�?�一天
				account.setTodayIncome(account.getTodayIncome().add(amount));
			} else {
				// �?是�?�一天
				account.setTodayIncome(amount);
			}
			/************************************/
		}

		String completeSett = PublicEnum.NO.name();
		String isAllowSett = PublicEnum.YES.name();

		/** 设置余�?的值 **/
		account.setBalance(account.getBalance().add(amount));
		account.setEditTime(new Date());

		// 记录账户历�?�
		RpAccountHistory accountHistoryEntity = new RpAccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setBankTrxNo(bankTrxNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.ADD.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setId(StringUtil.get32UUID());
		accountHistoryEntity.setUserNo(userNo);

		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		LOG.info("账户加款�?功，并记录了账户历�?�");
		return account;
	}

	/**
	 * �?款
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            �?款金�?
	 * @param requestNo
	 *            请求�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark){
		return this.debitToAccount(userNo, amount, requestNo, null, trxType, remark);
	}

	/**
	 * �?款:有银行�?水
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            �?款金�?
	 * @param requestNo
	 *            请求�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */
	@Transactional(rollbackFor = Exception.class)
	public RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) {
		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		// 获�?��?�用余�?
		BigDecimal availableBalance = account.getAvailableBalance();

		String isAllowSett = PublicEnum.YES.name();
		String completeSett = PublicEnum.NO.name();

		if (availableBalance.compareTo(amount) == -1) {
			throw AccountBizException.ACCOUNT_SUB_AMOUNT_OUTLIMIT;
		}

		/** �?少总余�? **/
		account.setBalance(account.getBalance().subtract(amount));

		Date lastModifyDate = account.getEditTime();
		// �?是�?�一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setTodayExpend(amount);
		}else{
			account.setTodayExpend(account.getTodayExpend().add(amount));
		}
		account.setTotalExpend(account.getTodayExpend().add(amount));
		account.setEditTime(new Date());

		// 记录账户历�?�
		RpAccountHistory accountHistoryEntity = new RpAccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setBankTrxNo(bankTrxNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setId(StringUtil.get32UUID());
		accountHistoryEntity.setUserNo(userNo);
		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 冻结账户资金
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param freezeAmount
	 *            冻结金�?
	 **/

	@Transactional(rollbackFor = Exception.class)
	public RpAccount freezeAmount(String userNo, BigDecimal freezeAmount) {
		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		account.setEditTime(new Date());
		// 比较�?�用余�?和冻结金�?
		if (!account.availableBalanceIsEnough(freezeAmount)) {
			// �?�用余�?�?足
			throw AccountBizException.ACCOUNT_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setUnbalance(account.getUnbalance().add(freezeAmount));
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 结算�?功 解冻金�?+�?款
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            解冻和�?款金�?
	 * @param requestNo
	 *            �?水�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */

	@Transactional(rollbackFor = Exception.class)
	public RpAccount unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) {
		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// �?是�?�一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setTodayExpend(amount);
		}else{
			account.setTodayExpend(account.getTodayExpend().add(amount));
		}
		account.setTotalExpend(account.getTodayExpend().add(amount));

		// 判断解冻金�?是�?�充足
		if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
			// 解冻金�?超�?
			throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setEditTime(new Date());
		account.setBalance(account.getBalance().subtract(amount));// �?款
		account.setUnbalance(account.getUnbalance().subtract(amount));// 解冻
		account.setSettAmount(account.getSettAmount().subtract(amount));// �?少�?�结算金�?

		String isAllowSett = PublicEnum.NO.name();
		String completeSett = PublicEnum.NO.name();
		// 记录账户历�?�
		RpAccountHistory accountHistoryEntity = new RpAccountHistory();
		accountHistoryEntity.setCreateTime(new Date());
		accountHistoryEntity.setEditTime(new Date());
		accountHistoryEntity.setIsAllowSett(isAllowSett);
		accountHistoryEntity.setAmount(amount);
		accountHistoryEntity.setBalance(account.getBalance());
		accountHistoryEntity.setRequestNo(requestNo);
		accountHistoryEntity.setIsCompleteSett(completeSett);
		accountHistoryEntity.setRemark(remark);
		accountHistoryEntity.setFundDirection(AccountFundDirectionEnum.SUB.name());
		accountHistoryEntity.setAccountNo(account.getAccountNo());
		accountHistoryEntity.setTrxType(trxType);
		accountHistoryEntity.setUserNo(userNo);
		this.rpAccountHistoryDao.insert(accountHistoryEntity);
		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 结算失败 解冻金�?
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param amount
	 *            解冻和�?款金�?
	 * @param requestNo
	 *            �?水�?�
	 * @param trxType
	 *            业务类型
	 * @param remark
	 *            备注
	 */

	@Transactional(rollbackFor = Exception.class)
	public RpAccount unFreezeSettAmount(String userNo, BigDecimal amount) {
		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}

		Date lastModifyDate = account.getEditTime();
		// �?是�?�一天直接清0
		if (!DateUtils.isSameDayWithToday(lastModifyDate)) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
		}

		// 判断解冻金�?是�?�充足
		if (account.getUnbalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
			// 解冻金�?超�?
			throw AccountBizException.ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT;
		}
		account.setEditTime(new Date());
		account.setUnbalance(account.getUnbalance().subtract(amount));// 解冻

		this.rpAccountDao.update(account);
		return account;
	}

	/**
	 * 更新账户历�?�中的结算状�?，并且累加�?�结算金�?
	 * 
	 * @param userNo
	 *            用户编�?�
	 * @param collectDate
	 *            汇总日期
	 * @param riskDay
	 *            风险预存期
	 * @param totalAmount
	 *            �?�结算金�?累计
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void settCollectSuccess(String userNo, String collectDate, int riskDay, BigDecimal totalAmount) {

		LOG.info("==>settCollectSuccess");
		LOG.info(String.format("==>userNo:%s, collectDate:%s, riskDay:%s", userNo, collectDate, riskDay));

		RpAccount account = this.getByUserNo_IsPessimist(userNo, true);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT.newInstance("账户�?存在,用户编�?�{%s}", userNo).print();
		}
		// 更新账户历�?�状�?
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", account.getAccountNo());
		params.put("statDate", collectDate);
		params.put("riskDay", riskDay);
		rpAccountHistoryDao.updateCompleteSettTo100(params);

		// 账户�?�结算金�?的累加
		account.setSettAmount(account.getSettAmount().add(totalAmount));
		rpAccountDao.update(account);
		LOG.info("==>settCollectSuccess<==");
	}
}
