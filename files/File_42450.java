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
import com.roncoo.pay.account.exception.AccountBizException;
import com.roncoo.pay.account.service.RpAccountQueryService;
import com.roncoo.pay.account.vo.DailyCollectAccountHistoryVo;
import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.common.core.exception.BizException;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.common.core.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * è´¦æˆ·æŸ¥è¯¢serviceå®žçŽ°ç±»
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * @authorï¼šzenghao
 */
@Service("rpAccountQueryService")
public class RpAccountQueryServiceImpl implements RpAccountQueryService {
	@Autowired
	private RpAccountDao rpAccountDao;
	@Autowired
	private RpAccountHistoryDao rpAccountHistoryDao;

	private static final Logger LOG = LoggerFactory.getLogger(RpAccountQueryServiceImpl.class);

	/**
	 * æ ¹æ?®è´¦æˆ·ç¼–å?·èŽ·å?–è´¦æˆ·ä¿¡æ?¯
	 * 
	 * @param accountNo
	 *            è´¦æˆ·ç¼–å?·
	 * @return
	 */
	public RpAccount getAccountByAccountNo(String accountNo) {
		LOG.info("æ ¹æ?®è´¦æˆ·ç¼–å?·æŸ¥è¯¢è´¦æˆ·ä¿¡æ?¯");
		RpAccount account = this.rpAccountDao.getByAccountNo(accountNo);
		// ä¸?æ˜¯å?Œä¸€å¤©ç›´æŽ¥æ¸…0
		if (!DateUtils.isSameDayWithToday(account.getEditTime())) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setEditTime(new Date());
			rpAccountDao.update(account);
		}
		return account;
	}

	/**
	 * æ ¹æ?®ç”¨æˆ·ç¼–å?·ç¼–å?·èŽ·å?–è´¦æˆ·ä¿¡æ?¯
	 * 
	 * @param userNO
	 *            ç”¨æˆ·ç¼–å?·
	 * @return
	 */
	public RpAccount getAccountByUserNo(String userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		LOG.info("æ ¹æ?®ç”¨æˆ·ç¼–å?·æŸ¥è¯¢è´¦æˆ·ä¿¡æ?¯");
		RpAccount account = this.rpAccountDao.getBy(map);
		if (account == null) {
			throw AccountBizException.ACCOUNT_NOT_EXIT;
		}
		// ä¸?æ˜¯å?Œä¸€å¤©ç›´æŽ¥æ¸…0
		if (!DateUtils.isSameDayWithToday(account.getEditTime())) {
			account.setTodayExpend(BigDecimal.ZERO);
			account.setTodayIncome(BigDecimal.ZERO);
			account.setEditTime(new Date());
			rpAccountDao.update(account);
		}
		return account;
	}

	/**
	 * åˆ†é¡µæŸ¥è¯¢è´¦æˆ·åŽ†å?²å?•ç”¨æˆ·
	 */
	public PageBean queryAccountHistoryListPage(PageParam pageParam, String accountNo){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		return rpAccountDao.listPage(pageParam, params);
	}

	/**
	 * åˆ†é¡µæŸ¥è¯¢è´¦æˆ·åŽ†å?²å?•è§’è‰²
	 */
	public PageBean queryAccountHistoryListPageByRole(PageParam pageParam, Map<String, Object> params){
		String accountType = (String) params.get("accountType");
		if (StringUtils.isBlank(accountType)) {
			throw AccountBizException.ACCOUNT_TYPE_IS_NULL;
		}
		return rpAccountDao.listPage(pageParam, params);

	}

	/**
	 * èŽ·å?–è´¦æˆ·åŽ†å?²å?•è§’è‰²
	 * 
	 * @param accountNo
	 *            è´¦æˆ·ç¼–å?·
	 * @param requestNo
	 *            è¯·æ±‚å?·
	 * @param trxType
	 *            ä¸šåŠ¡ç±»åž‹
	 * @return AccountHistory
	 */
	public RpAccountHistory getAccountHistoryByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
		map.put("requestNo", requestNo);
		map.put("trxType", trxType);
		return rpAccountHistoryDao.getBy(map);
	}

	/**
	 * æ—¥æ±‡æ€»è´¦æˆ·å¾…ç»“ç®—é‡‘é¢? .
	 * 
	 * @param accountNo
	 *            è´¦æˆ·ç¼–å?·
	 * @param statDate
	 *            ç»Ÿè®¡æ—¥æœŸ
	 * @param riskDay
	 *            é£Žé™©é¢„æµ‹æœŸ
	 * @param fundDirection
	 *            èµ„é‡‘æµ?å?‘
	 * @return
	 */
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay, Integer fundDirection) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNo", accountNo);
		params.put("statDate", statDate);
		params.put("riskDay", riskDay);
		params.put("fundDirection", fundDirection);
		return rpAccountHistoryDao.listDailyCollectAccountHistoryVo(params);

	}

	/**
	 * æ ¹æ?®å?‚æ•°åˆ†é¡µæŸ¥è¯¢è´¦æˆ·.
	 * 
	 * @param pageParam
	 *            åˆ†é¡µå?‚æ•°.
	 * @param params
	 *            æŸ¥è¯¢å?‚æ•°ï¼Œå?¯ä»¥ä¸ºnull.
	 * @return AccountList.
	 * @throws BizException
	 */
	public PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) {

		return rpAccountDao.listPage(pageParam, params);
	}

	/**
	 * æ ¹æ?®å?‚æ•°åˆ†é¡µæŸ¥è¯¢è´¦æˆ·åŽ†å?².
	 * 
	 * @param pageParam
	 *            åˆ†é¡µå?‚æ•°.
	 * @param params
	 *            æŸ¥è¯¢å?‚æ•°ï¼Œå?¯ä»¥ä¸ºnull.
	 * @return AccountHistoryList.
	 * @throws BizException
	 */
	public PageBean queryAccountHistoryListPage(PageParam pageParam, Map<String, Object> params) {

		return rpAccountHistoryDao.listPage(pageParam, params);
	}
	
    /**
	 * èŽ·å?–æ‰€æœ‰è´¦æˆ·
	 * @return
	 */
    @Override
    public List<RpAccount> listAll(){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpAccountDao.listBy(paramMap);
	}

}
