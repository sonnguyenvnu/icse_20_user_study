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
package com.roncoo.pay.permission.service.impl;

import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.PmsOperatorDao;
import com.roncoo.pay.permission.dao.PmsOperatorRoleDao;
import com.roncoo.pay.permission.entity.PmsOperator;
import com.roncoo.pay.permission.entity.PmsOperatorRole;
import com.roncoo.pay.permission.service.PmsOperatorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * æ“?ä½œå‘˜serviceæŽ¥å?£å®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Service("pmsOperatorService")
public class PmsOperatorServiceImpl implements PmsOperatorService {
	@Autowired
	private PmsOperatorDao pmsOperatorDao;

	@Autowired
	private PmsOperatorRoleDao pmsOperatorRoleDao;

	/**
	 * åˆ›å»ºpmsOperator
	 */
	public void saveData(PmsOperator pmsOperator) {
		pmsOperatorDao.insert(pmsOperator);
	}

	/**
	 * ä¿®æ”¹pmsOperator
	 */
	public void updateData(PmsOperator pmsOperator) {
		pmsOperatorDao.update(pmsOperator);
	}

	/**
	 * æ ¹æ?®idèŽ·å?–æ•°æ?®pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsOperator getDataById(Long id) {
		return pmsOperatorDao.getById(id);

	}

	/**
	 * åˆ†é¡µæŸ¥è¯¢pmsOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsOperator pmsOperator) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", pmsOperator.getLoginName()); // æ“?ä½œå‘˜ç™»å½•å??ï¼ˆç²¾ç¡®æŸ¥è¯¢ï¼‰
		paramMap.put("realName", pmsOperator.getRealName()); // æ“?ä½œå‘˜å§“å??ï¼ˆæ¨¡ç³ŠæŸ¥è¯¢ï¼‰
		paramMap.put("status", pmsOperator.getStatus()); // çŠ¶æ€?

		return pmsOperatorDao.listPage(pageParam, paramMap);
	}

	/**
	 * æ ¹æ?®IDåˆ é™¤ä¸€ä¸ªæ“?ä½œå‘˜ï¼Œå?Œæ—¶åˆ é™¤ä¸Žè¯¥æ“?ä½œå‘˜å…³è?”çš„è§’è‰²å…³è?”ä¿¡æ?¯. type="1"çš„è¶…çº§ç®¡ç?†å‘˜ä¸?èƒ½åˆ é™¤.
	 * 
	 * @param id
	 *            æ“?ä½œå‘˜ID.
	 */
	public void deleteOperatorById(Long operatorId) {
		PmsOperator pmsOperator = pmsOperatorDao.getById(operatorId);
		if (pmsOperator != null) {
			if ("admin".equals(pmsOperator.getType())) {
				throw new RuntimeException("ã€?" + pmsOperator.getLoginName() + "ã€‘ä¸ºè¶…çº§ç®¡ç?†å‘˜ï¼Œä¸?èƒ½åˆ é™¤ï¼?");
			}
			pmsOperatorDao.delete(operatorId);
			// åˆ é™¤åŽŸæ?¥çš„è§’è‰²ä¸Žæ“?ä½œå‘˜å…³è?”
			pmsOperatorRoleDao.deleteByOperatorId(operatorId);
		}
	}

	/**
	 * æ›´æ–°æ“?ä½œå‘˜ä¿¡æ?¯.
	 * 
	 * @param operator
	 */
	public void update(PmsOperator operator) {
		pmsOperatorDao.update(operator);

	}

	/**
	 * æ ¹æ?®æ“?ä½œå‘˜IDæ›´æ–°æ“?ä½œå‘˜å¯†ç ?.
	 * 
	 * @param operatorId
	 * @param newPwd
	 *            (å·²è¿›è¡ŒSHA1åŠ å¯†)
	 */
	public void updateOperatorPwd(Long operatorId, String newPwd) {
		PmsOperator pmsOperator = pmsOperatorDao.getById(operatorId);
		pmsOperator.setLoginPwd(newPwd);
		pmsOperatorDao.update(pmsOperator);
	}

	/**
	 * æ ¹æ?®ç™»å½•å??å?–å¾—æ“?ä½œå‘˜å¯¹è±¡
	 */
	public PmsOperator findOperatorByLoginName(String loginName) {
		return pmsOperatorDao.findByLoginName(loginName);
	}

	/**
	 * ä¿?å­˜æ“?ä½œå“¡ä¿¡æ?¯å?Šå…¶å…³è?”çš„è§’è‰².
	 * 
	 * @param pmsOperator
	 *            .
	 * @param roleOperatorStr
	 *            .
	 */

	@Transactional
	public void saveOperator(PmsOperator pmsOperator, String roleOperatorStr) {
		// ä¿?å­˜æ“?ä½œå‘˜ä¿¡æ?¯
		pmsOperatorDao.insert(pmsOperator);
		// ä¿?å­˜è§’è‰²å…³è?”ä¿¡æ?¯
		if (StringUtils.isNotBlank(roleOperatorStr) && roleOperatorStr.length() > 0) {
			saveOrUpdateOperatorRole(pmsOperator, roleOperatorStr);
		}
	}

	/**
	 * ä¿?å­˜ç”¨æˆ·å’Œè§’è‰²ä¹‹é—´çš„å…³è?”å…³ç³»
	 */
	private void saveOrUpdateOperatorRole(PmsOperator pmsOperator, String roleIdsStr) {
		// åˆ é™¤åŽŸæ?¥çš„è§’è‰²ä¸Žæ“?ä½œå‘˜å…³è?”
		List<PmsOperatorRole> listPmsOperatorRoles = pmsOperatorRoleDao.listByOperatorId(pmsOperator.getId());
		Map<Long, PmsOperatorRole> delMap = new HashMap<Long, PmsOperatorRole>();
		for (PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
			delMap.put(pmsOperatorRole.getRoleId(), pmsOperatorRole);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// åˆ›å»ºæ–°çš„å…³è?”
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				long roleId = Long.parseLong(roleIds[i]);
				if (delMap.get(roleId) == null) {
					PmsOperatorRole pmsOperatorRole = new PmsOperatorRole();
					pmsOperatorRole.setOperatorId(pmsOperator.getId());
					pmsOperatorRole.setRoleId(roleId);
					pmsOperatorRole.setCreater(pmsOperator.getCreater());
					pmsOperatorRole.setCreateTime(new Date());
					pmsOperatorRole.setStatus(PublicStatusEnum.ACTIVE.name());
					pmsOperatorRoleDao.insert(pmsOperatorRole);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			long roleId = iterator.next();
			pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, pmsOperator.getId());
		}
	}

	/**
	 * ä¿®æ”¹æ“?ä½œå“¡ä¿¡æ?¯å?Šå…¶å…³è?”çš„è§’è‰².
	 * 
	 * @param pmsOperator
	 *            .
	 * @param roleOperatorStr
	 *            .
	 */
	public void updateOperator(PmsOperator pmsOperator, String roleOperatorStr) {
		pmsOperatorDao.update(pmsOperator);
		// æ›´æ–°è§’è‰²ä¿¡æ?¯
		this.saveOrUpdateOperatorRole(pmsOperator, roleOperatorStr);
	}

}
