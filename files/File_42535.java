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
import com.roncoo.pay.permission.dao.PmsRoleDao;
import com.roncoo.pay.permission.entity.PmsRole;
import com.roncoo.pay.permission.service.PmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色service接�?�实现
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {
	@Autowired
	private PmsRoleDao pmsRoleDao;

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsRole pmsRole) {
		pmsRoleDao.insert(pmsRole);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsRole pmsRole) {
		pmsRoleDao.update(pmsRole);
	}

	/**
	 * 根�?�id获�?�数�?�pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsRole getDataById(Long id) {
		return pmsRoleDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, PmsRole pmsRole) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); // 业务�?�件查询�?�数
		paramMap.put("roleName", pmsRole.getRoleName()); // 角色�??称（模糊查询）
		return pmsRoleDao.listPage(pageParam, paramMap);
	}

	/**
	 * 获�?�所有角色列表，以供添加�?作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<PmsRole> listAllRole() {
		return pmsRoleDao.listAll();
	}

	/**
	 * 判断此�?��?是�?�关�?�有角色
	 * 
	 * @param permissionId
	 * @return
	 */
	public List<PmsRole> listByPermissionId(Long permissionId) {
		return pmsRoleDao.listByPermissionId(permissionId);
	}

	/**
	 * 根�?�角色�??或者角色编�?�查询角色
	 * 
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
		return pmsRoleDao.getByRoleNameOrRoleCode(roleName, roleCode);
	}

	/**
	 * 删除
	 * 
	 * @param roleId
	 */
	public void delete(Long roleId) {
		pmsRoleDao.delete(roleId);
	}
}
