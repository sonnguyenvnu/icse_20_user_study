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

import com.roncoo.pay.permission.dao.PmsOperatorDao;
import com.roncoo.pay.permission.entity.PmsOperator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * æ?ƒé™?æ“?ä½œå‘˜daoå®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Repository
public class PmsOperatorDaoImpl extends PermissionBaseDaoImpl<PmsOperator> implements PmsOperatorDao {

	/**
	 * æ ¹æ?®æ“?ä½œå‘˜ç™»å½•å??èŽ·å?–æ“?ä½œå‘˜ä¿¡æ?¯.
	 * 
	 * @param loginName
	 *            .
	 * @return operator .
	 */

	public PmsOperator findByLoginName(String loginName) {
		return super.getSessionTemplate().selectOne(getStatement("findByLoginName"), loginName);
	}

	@Override
	public List<PmsOperator> listByRoleId(Long roleId) {
		return super.getSessionTemplate().selectList(getStatement("listByRoleId"), roleId);
	}

}
