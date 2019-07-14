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

import com.alibaba.druid.util.StringUtils;
import com.roncoo.pay.permission.dao.PmsMenuRoleDao;
import com.roncoo.pay.permission.entity.PmsMenuRole;
import com.roncoo.pay.permission.service.PmsMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * è?œå?•è§’è‰²serviceæŽ¥å?£å®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Service("pmsMenuRoleService")
public class PmsMenuRoleServiceImpl implements PmsMenuRoleService {

	@Autowired
	private PmsMenuRoleDao pmsMenuRoleDao;

	/**
	 * æ ¹æ?®è§’è‰²IDç»Ÿè®¡å…³è?”åˆ°æ­¤è§’è‰²çš„è?œå?•æ•°.
	 * 
	 * @param roleId
	 *            è§’è‰²ID.
	 * @return count.
	 */
	public int countMenuByRoleId(Long roleId) {
		List<PmsMenuRole> meunList = pmsMenuRoleDao.listByRoleId(roleId);
		if (meunList == null || meunList.isEmpty()) {
			return 0;
		} else {
			return meunList.size();
		}
	}

	/**
	 * æ ¹æ?®è§’è‰²idï¼Œåˆ é™¤è¯¥è§’è‰²å…³è?”çš„æ‰€æœ‰è?œå?•æ?ƒé™?
	 * 
	 * @param roleId
	 */
	public void deleteByRoleId(Long roleId) {
		pmsMenuRoleDao.deleteByRoleId(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveRoleMenu(Long roleId, String roleMenuStr){
		// åˆ é™¤åŽŸæ?¥çš„è§’è‰²ä¸Žæ?ƒé™?å…³è?”
		pmsMenuRoleDao.deleteByRoleId(roleId);
		if (!StringUtils.isEmpty(roleMenuStr)) {
			// åˆ›å»ºæ–°çš„å…³è?”
			String[] menuIds = roleMenuStr.split(",");
			for (int i = 0; i < menuIds.length; i++) {
				Long menuId = Long.valueOf(menuIds[i]);
				PmsMenuRole item = new PmsMenuRole();
				item.setMenuId(menuId);
				item.setRoleId(roleId);
				pmsMenuRoleDao.insert(item);
			}
		}
	}
}
