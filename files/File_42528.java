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

import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.PmsPermissionDao;
import com.roncoo.pay.permission.dao.PmsRolePermissionDao;
import com.roncoo.pay.permission.entity.PmsPermission;
import com.roncoo.pay.permission.entity.PmsRolePermission;
import com.roncoo.pay.permission.service.PmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * æ?ƒé™?serviceæŽ¥å?£å®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Service("pmsPermissionService")
public class PmsPermissionServiceImpl implements PmsPermissionService {
	@Autowired
	private PmsPermissionDao pmsPermissionDao;
	@Autowired
	private PmsRolePermissionDao pmsRolePermissionDao;

	/**
	 * åˆ›å»ºpmsOperator
	 */
	public void saveData(PmsPermission pmsPermission) {
		pmsPermissionDao.insert(pmsPermission);
	}

	/**
	 * ä¿®æ”¹pmsOperator
	 */
	public void updateData(PmsPermission pmsPermission) {
		pmsPermissionDao.update(pmsPermission);
	}

	/**
	 * æ ¹æ?®idèŽ·å?–æ•°æ?®pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsPermission getDataById(Long id) {
		return pmsPermissionDao.getById(id);

	}

	/**
	 * åˆ†é¡µæŸ¥è¯¢pmsOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsPermission pmsPermission) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", pmsPermission.getPermissionName()); // æ?ƒé™?å??ç§°ï¼ˆæ¨¡ç³ŠæŸ¥è¯¢ï¼‰
		paramMap.put("permission", pmsPermission.getPermission()); // æ?ƒé™?ï¼ˆç²¾ç¡®æŸ¥è¯¢ï¼‰
		return pmsPermissionDao.listPage(pageParam, paramMap);
	}

	/**
	 * æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨
	 * 
	 * @param trim
	 * @return
	 */
	public PmsPermission getByPermissionName(String permissionName) {
		return pmsPermissionDao.getByPermissionName(permissionName);
	}

	/**
	 * æ£€æŸ¥æ?ƒé™?æ˜¯å?¦å·²å­˜åœ¨
	 * 
	 * @param permission
	 * @return
	 */
	public PmsPermission getByPermission(String permission) {
		return pmsPermissionDao.getByPermission(permission);
	}

	/**
	 * æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨(å…¶ä»–id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		return pmsPermissionDao.getByPermissionNameNotEqId(permissionName, id);
	}

	/**
	 * pmsPermissionDao åˆ é™¤
	 * 
	 * @param permission
	 */
	public void delete(Long permissionId) {
		pmsPermissionDao.delete(permissionId);
	}

	/**
	 * æ ¹æ?®è§’è‰²æŸ¥æ‰¾è§’è‰²å¯¹åº”çš„åŠŸèƒ½æ?ƒé™?IDé›†
	 * 
	 * @param roleId
	 * @return
	 */
	public String getPermissionIdsByRoleId(Long roleId) {
		List<PmsRolePermission> rmList = pmsRolePermissionDao.listByRoleId(roleId);
		StringBuffer actionIds = new StringBuffer();
		if (rmList != null && !rmList.isEmpty()) {
			for (PmsRolePermission rm : rmList) {
				actionIds.append(rm.getPermissionId()).append(",");
			}
		}
		return actionIds.toString();
	}

	/**
	 * æŸ¥è¯¢æ‰€æœ‰çš„æ?ƒé™?
	 */
	public List<PmsPermission> listAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return pmsPermissionDao.listBy(paramMap);
	}
}
