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

import com.roncoo.pay.permission.dao.PmsOperatorDao;
import com.roncoo.pay.permission.dao.PmsOperatorRoleDao;
import com.roncoo.pay.permission.dao.PmsRoleDao;
import com.roncoo.pay.permission.entity.PmsOperator;
import com.roncoo.pay.permission.entity.PmsOperatorRole;
import com.roncoo.pay.permission.entity.PmsRole;
import com.roncoo.pay.permission.service.PmsOperatorRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * �?作员角色service接�?�实现
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
@Service("pmsOperatorRoleService")
public class PmsOperatorRoleServiceImpl implements PmsOperatorRoleService {
	@Autowired
	private PmsOperatorRoleDao pmsOperatorRoleDao;

	@Autowired
	private PmsOperatorDao pmsOperatorDao;

	@Autowired
	private PmsRoleDao pmsRoleDao;

	/**
	 * 根�?��?作员ID获得该�?作员的所有角色id所拼�?的String，�?个ID用“,�?分隔
	 * 
	 * @param operatorId
	 *            �?作员ID
	 * @return roleIds
	 */
	public String getRoleIdsByOperatorId(Long operatorId) {
		// 得到�?作员和角色列表
		List<PmsOperatorRole> rpList = pmsOperatorRoleDao.listByOperatorId(operatorId);
		// 构建StringBuffer�?�拼字符串
		StringBuffer roleIdsBuf = new StringBuffer("");
		for (PmsOperatorRole rp : rpList) {
			roleIdsBuf.append(rp.getRoleId()).append(",");
		}
		String roleIds = roleIdsBuf.toString();
		// 截�?�字符串
		if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		}
		return roleIds;
	}

	/**
	 * 根�?��?作员id，获�?�该�?作员所有角色的编�?集�?�
	 * 
	 * @param operatorId
	 * @return
	 */
	public Set<String> getRoleCodeByOperatorId(Long operatorId) {
		// 得到�?作员和角色列表
		List<PmsOperatorRole> rpList = pmsOperatorRoleDao.listByOperatorId(operatorId);
		Set<String> roleCodeSet = new HashSet<String>();

		for (PmsOperatorRole rp : rpList) {
			Long roleId = rp.getRoleId();
			PmsRole role = pmsRoleDao.getById(roleId);
			if (role == null) {
				continue;
			}
			roleCodeSet.add(role.getRoleCode());
		}

		return roleCodeSet;

	}

	/**
	 * 根�?�角色ID统计有多少个�?作员关�?�到此角色.
	 * 
	 * @param roleId
	 *            .
	 * @return count.
	 */
	public int countOperatorByRoleId(Long roleId) {
		List<PmsOperatorRole> operatorList = pmsOperatorRoleDao.listByRoleId(roleId);
		if (operatorList == null || operatorList.isEmpty()) {
			return 0;
		} else {
			return operatorList.size();
		}
	}

	/**
	 * 根�?��?作员ID获得所有�?作员�?角色关�?�列表
	 */
	public List<PmsOperatorRole> listOperatorRoleByOperatorId(Long operatorId) {
		return pmsOperatorRoleDao.listByOperatorId(operatorId);
	}

	/**
	 * �?存�?作員信�?��?�其关�?�的角色.
	 * 
	 * @param pmsOperator
	 *            .
	 * @param OperatorRoleStr
	 *            .
	 */
	public void saveOperator(PmsOperator pmsOperator, String OperatorRoleStr) {
		// �?存�?作员信�?�
		pmsOperatorDao.insert(pmsOperator);
		// �?存角色关�?�信�?�
		if (StringUtils.isNotBlank(OperatorRoleStr) && OperatorRoleStr.length() > 0) {
			saveOrUpdateOperatorRole(pmsOperator.getId(), OperatorRoleStr);
		}
	}

	/**
	 * 根�?�角色ID查询用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PmsOperator> listOperatorByRoleId(Long roleId) {
		return pmsOperatorDao.listByRoleId(roleId);
	}

	/**
	 * 修改�?作員信�?��?�其关�?�的角色.
	 * 
	 * @param pmsOperator
	 *            .
	 * @param OperatorRoleStr
	 *            .
	 */
	public void updateOperator(PmsOperator pmsOperator, String OperatorRoleStr) {
		pmsOperatorDao.update(pmsOperator);
		// 更新角色信�?�
		saveOrUpdateOperatorRole(pmsOperator.getId(), OperatorRoleStr);
	}

	/**
	 * �?存用户和角色之间的关�?�关系
	 */
	private void saveOrUpdateOperatorRole(Long operatorId, String roleIdsStr) {
		// 删除原�?�的角色与�?作员关�?�
		List<PmsOperatorRole> listPmsOperatorRoles = pmsOperatorRoleDao.listByOperatorId(operatorId);
		Map<Long, PmsOperatorRole> delMap = new HashMap<Long, PmsOperatorRole>();
		for (PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
			delMap.put(pmsOperatorRole.getRoleId(), pmsOperatorRole);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// 创建新的关�?�
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				Long roleId = Long.valueOf(roleIds[i]);
				if (delMap.get(roleId) == null) {
					PmsOperatorRole pmsOperatorRole = new PmsOperatorRole();
					pmsOperatorRole.setOperatorId(operatorId);
					pmsOperatorRole.setRoleId(roleId);
					pmsOperatorRoleDao.insert(pmsOperatorRole);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			Long roleId = iterator.next();
			pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, operatorId);
		}
	}

}
