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
package com.roncoo.pay.app.settlement.biz;

import com.roncoo.pay.account.entity.RpAccount;
import com.roncoo.pay.account.service.RpSettHandleService;
import com.roncoo.pay.common.core.enums.PublicEnum;
import com.roncoo.pay.user.entity.RpUserPayConfig;
import com.roncoo.pay.user.service.RpUserPayConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 结算业务逻辑类.
 * 龙果学院：www.roncoo.com
 * @author zenghao
 */
@Component("settBiz")
public class SettBiz {

	private static final Log LOG = LogFactory.getLog(SettBiz.class);

	@Autowired
	private DailySettCollectBiz dailySettCollectBiz;
	@Autowired
	private RpUserPayConfigService rpUserPayConfigService;
	@Autowired
	private RpSettHandleService rpSettHandleService;

	/**
	 * �?�起�?日待结算数�?�统计汇总.<br/>
	 * 
	 * @param userEnterpriseList
	 *            结算商户.<br/>
	 * @param collectDate
	 *            统计截止日期(一般为昨天的日期)
	 */
	public void launchDailySettCollect(List<RpAccount> accountList, Date endDate) {

		if (accountList == null || accountList.isEmpty()) {
			return;
		}
		// �?�商户�?�起结算
		for (RpAccount rpAccount : accountList) {
			try {
				LOG.debug(rpAccount.getUserNo() + ":开始汇总");
				dailySettCollectBiz.dailySettCollect(rpAccount, endDate);
				LOG.debug(rpAccount.getUserNo() + ":汇总结�?�");
			} catch (Exception e) {
				LOG.error(rpAccount.getUserNo()+":汇总异常", e);
			}
		}
	}

	/**
	 * �?�起定期自动结算.<br/>
	 * 
	 * @param userEnterpriseList
	 *            结算商户.<br/>
	 */
	public void launchAutoSett(List<RpAccount> accountList) {
		if (accountList == null || accountList.isEmpty()) {
			return;
		}
		// �?�商户�?�起结算
		for (RpAccount rpAccount : accountList) {
			try {
				RpUserPayConfig rpUserPayConfig = rpUserPayConfigService.getByUserNo(rpAccount.getUserNo());
				if (rpUserPayConfig == null) {
					LOG.info(rpAccount.getUserNo() + "没有商家设置信�?�，�?进行结算");
					continue;
				}
				if (rpUserPayConfig.getIsAutoSett().equals(PublicEnum.YES.name())) {
					LOG.debug(rpAccount.getUserNo() + ":开始自动结算");
					rpSettHandleService.launchAutoSett(rpAccount.getUserNo());
					LOG.debug(rpAccount.getUserNo() + ":自动结算结�?�");
				} else {
					LOG.info(rpAccount.getUserNo() + ":�?�自动结算商家");
				}
			} catch (Exception e) {
				LOG.error("自动结算异常：" + rpAccount.getUserNo(), e);
			}
		}

	}
}
