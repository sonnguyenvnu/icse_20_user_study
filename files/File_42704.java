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

import com.roncoo.pay.account.entity.RpAccount;
import com.roncoo.pay.account.service.RpAccountService;
import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.common.core.utils.EncryptUtil;
import com.roncoo.pay.common.core.utils.StringUtil;
import com.roncoo.pay.user.dao.RpUserInfoDao;
import com.roncoo.pay.user.entity.RpUserInfo;
import com.roncoo.pay.user.service.BuildNoService;
import com.roncoo.pay.user.service.RpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ç”¨æˆ·ä¿¡æ?¯serviceå®žçŽ°ç±»
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * @authorï¼šzenghao
 */
@Service("rpUserInfoService")
public class RpUserInfoServiceImpl implements RpUserInfoService{

	@Autowired
	private RpUserInfoDao rpUserInfoDao;
	
	@Autowired
	private BuildNoService buildNoService;
	
	@Autowired
	private RpAccountService rpAccountService;
	
	@Override
	public void saveData(RpUserInfo rpUserInfo) {
		rpUserInfoDao.insert(rpUserInfo);
	}

	@Override
	public void updateData(RpUserInfo rpUserInfo) {
		rpUserInfoDao.update(rpUserInfo);
	}

	@Override
	public RpUserInfo getDataById(String id) {
		return rpUserInfoDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpUserInfo rpUserInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo", rpUserInfo.getUserNo());
		return rpUserInfoDao.listPage(pageParam, paramMap);
	}
	
    /**
     * ç”¨æˆ·çº¿ä¸‹æ³¨å†Œ
     * 
     * @param userName
     *            ç”¨æˆ·å??
     * @param mobile
     *            æ‰‹æœºå?·
     * @param password
     *            å¯†ç ?
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerOffline(String userName, String mobile, String password) {
        String userNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();

        //ç”Ÿæˆ?ç”¨æˆ·ä¿¡æ?¯
        RpUserInfo rpUserInfo = new RpUserInfo();
        rpUserInfo.setAccountNo(accountNo);
        rpUserInfo.setCreateTime(new Date());
        rpUserInfo.setId(StringUtil.get32UUID());
        rpUserInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserInfo.setUserName(userName);
        rpUserInfo.setUserNo(userNo);
        rpUserInfo.setMobile(mobile);
        rpUserInfo.setPassword(EncryptUtil.encodeMD5String(password));
        rpUserInfo.setPayPwd(EncryptUtil.encodeMD5String("123456"));
        rpUserInfo.setVersion(0);
        this.saveData(rpUserInfo);

        // ç”Ÿæˆ?è´¦æˆ·ä¿¡æ?¯
        RpAccount rpAccount = new RpAccount();
        rpAccount.setAccountNo(accountNo);
        rpAccount.setAccountType("0");
        rpAccount.setCreateTime(new Date());
        rpAccount.setEditTime(new Date());
        rpAccount.setId(StringUtil.get32UUID());
        rpAccount.setStatus(PublicStatusEnum.ACTIVE.name());
        rpAccount.setUserNo(userNo);
        rpAccount.setBalance(new BigDecimal("0"));
        rpAccount.setSecurityMoney(new BigDecimal("0"));
        rpAccount.setSettAmount(new BigDecimal("0"));
        rpAccount.setTodayExpend(new BigDecimal("0"));
        rpAccount.setTodayIncome(new BigDecimal("0"));
        rpAccount.setUnbalance(new BigDecimal("0"));
        rpAccount.setTotalExpend(new BigDecimal("0"));
        rpAccount.setTotalIncome(new BigDecimal("0"));
        rpAccountService.saveData(rpAccount);
    }

    /**
     * æ ¹æ?®å•†æˆ·ç¼–å?·èŽ·å?–å•†æˆ·ä¿¡æ?¯
     *
     * @param merchantNo
     * @return
     */
    @Override
    public RpUserInfo getDataByMerchentNo(String merchantNo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userNo", merchantNo);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpUserInfoDao.getBy(paramMap);
    }
    
    /**
	 * æ ¹æ?®æ‰‹æœºå?·èŽ·å?–å•†æˆ·ä¿¡æ?¯
	 * @param mobile
	 * @return
	 */
    @Override
    public RpUserInfo getDataByMobile(String mobile){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mobile", mobile);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpUserInfoDao.getBy(paramMap);
    }
    
    /**
	 * èŽ·å?–æ‰€æœ‰ç”¨æˆ·
	 * @return
	 */
    @Override
    public List<RpUserInfo> listAll(){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpUserInfoDao.listBy(paramMap);
	}
}
