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
package com.roncoo.pay.permission.dao.impl;

import com.roncoo.pay.permission.dao.PmsMenuRoleDao;
import com.roncoo.pay.permission.entity.PmsMenuRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * è?œå?•è§’è‰²
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Repository("pmsRoleMenuDao")
public class PmsMenuRoleDaoImpl extends PermissionBaseDaoImpl<PmsMenuRole> implements PmsMenuRoleDao {

	@Override
	public void deleteByRoleId(Long roleId) {
		super.getSessionTemplate().delete(getStatement("deleteByRoleId"), roleId);
	}

	/**
	 * æ ¹æ?®è§’è‰²IDç»Ÿè®¡å…³è?”åˆ°æ­¤è§’è‰²çš„è?œå?•æ•°.
	 * 
	 * @param roleId
	 *            è§’è‰²ID.
	 * @return count.
	 */
	@Override
	public List<PmsMenuRole> listByRoleId(Long roleId) {
		return super.getSessionTemplate().selectList(getStatement("listByRoleId"), roleId);
	}
}
