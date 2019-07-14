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
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.permission.dao.PmsPermissionDao;
import com.roncoo.pay.permission.dao.PmsRolePermissionDao;
import com.roncoo.pay.permission.entity.PmsPermission;
import com.roncoo.pay.permission.entity.PmsRolePermission;
import com.roncoo.pay.permission.service.PmsOperatorRoleService;
import com.roncoo.pay.permission.service.PmsRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色�?��?service接�?�实现
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("pmsRolePermissionService")
public class PmsRolePermissionServiceImpl implements PmsRolePermissionService {
	@Autowired
	private PmsRolePermissionDao pmsRolePermissionDao;

	@Autowired
	private PmsPermissionDao pmsPermissionDao;
	@Autowired
	private PmsOperatorRoleService pmsOperatorRoleService;

	/**
	 * 根�?��?作员ID，获�?�所有的功能�?��?集
	 * 
	 * @param operatorId
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId) {
		// 根�?��?作员Id查询出关�?�的所有角色id
		String roleIds = pmsOperatorRoleService.getRoleIdsByOperatorId(operatorId);

		String permissionIds = getActionIdsByRoleIds(roleIds);
		Set<String> permissionSet = new HashSet<String>();

		// 根�?�角色ID字符串得到该用户的所有�?��?拼�?的字符串
		if (!StringUtils.isEmpty(permissionIds)) {
			List<PmsPermission> permissions = pmsPermissionDao.findByIds(permissionIds);
			for (PmsPermission permission : permissions) {
				permissionSet.add(permission.getPermission());
			}
		}
		return permissionSet;
	}

	/**
	 * 根�?�角色ID集得到所有�?��?ID集
	 * 
	 * @param roleIds
	 * @return actionIds
	 */
	private String getActionIdsByRoleIds(String roleIds) {
		// 得到角色�?�?��?表中roleiId在ids中的所有关�?�对象
		List<PmsRolePermission> listRolePermission = pmsRolePermissionDao.listByRoleIds(roleIds); // 构建StringBuffer
		StringBuffer actionIdsBuf = new StringBuffer("");
		// 拼接字符串
		for (PmsRolePermission pmsRolePermission : listRolePermission) {
			actionIdsBuf.append(pmsRolePermission.getPermissionId()).append(",");
		}
		String actionIds = actionIdsBuf.toString();
		// 截�?�字符串
		if (StringUtils.isEmpty(actionIds) && actionIds.length() > 0) {
			actionIds = actionIds.substring(0, actionIds.length() - 1); // 去掉最�?�一个逗�?�
		}
		return actionIds;
	}

	// /////////////////////////////下�?�：基本�?作方法///////////////////////////////////////////////

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsRolePermission pmsRolePermission) {
		pmsRolePermissionDao.insert(pmsRolePermission);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsRolePermission pmsRolePermission) {
		pmsRolePermissionDao.update(pmsRolePermission);
	}

	/**
	 * 根�?�id获�?�数�?�pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsRolePermission getDataById(Long id) {
		return pmsRolePermissionDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsRolePermission pmsRolePermission) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return pmsRolePermissionDao.listPage(pageParam, paramMap);
	}
	
	/**
	 * �?存角色和�?��?之间的关�?�关系
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveRolePermission(Long roleId, String rolePermissionStr){
		// 删除原�?�的角色与�?��?关�?�
		pmsRolePermissionDao.deleteByRoleId(roleId);
		if (!StringUtils.isEmpty(rolePermissionStr)) {
			// 创建新的关�?�
			String[] permissionIds = rolePermissionStr.split(",");
			for (int i = 0; i < permissionIds.length; i++) {
				Long permissionId = Long.valueOf(permissionIds[i]);
				PmsRolePermission item = new PmsRolePermission();
				item.setPermissionId(permissionId);
				item.setRoleId(roleId);
				pmsRolePermissionDao.insert(item);
			}
		}
	}

}
