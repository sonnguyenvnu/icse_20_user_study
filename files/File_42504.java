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

import com.roncoo.pay.permission.dao.PmsPermissionDao;
import com.roncoo.pay.permission.entity.PmsPermission;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * æ?ƒé™?åŠŸèƒ½ç‚¹daoå®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Repository
public class PmsPermissionDaoImpl extends PermissionBaseDaoImpl<PmsPermission> implements PmsPermissionDao {

	/**
	 * æ ¹æ?®å®žä½“IDé›†å­—ç¬¦ä¸²èŽ·å?–å¯¹è±¡åˆ—è¡¨.
	 * 
	 * @param idStr
	 * @return
	 */
	public List<PmsPermission> findByIds(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		return this.getSessionTemplate().selectList(getStatement("findByIds"), ids);
	}

	/**
	 * æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨
	 * 
	 * @param trim
	 * @return
	 */
	public PmsPermission getByPermissionName(String permissionName) {
		return this.getSessionTemplate().selectOne(getStatement("getByPermissionName"), permissionName);

	}

	/**
	 * æ£€æŸ¥æ?ƒé™?æ˜¯å?¦å·²å­˜åœ¨
	 * 
	 * @param permission
	 * @return
	 */
	public PmsPermission getByPermission(String permission) {
		return this.getSessionTemplate().selectOne(getStatement("getByPermission"), permission);
	}

	/**
	 * æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨(å…¶ä»–id)
	 * 
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", permissionName);
		paramMap.put("id", id);
		return this.getSessionTemplate().selectOne(getStatement("getByPermissionNameNotEqId"), paramMap);
	}

	/**
	 * èŽ·å?–å?¶å­?è?œå?•ä¸‹æ‰€æœ‰çš„åŠŸèƒ½æ?ƒé™?
	 * 
	 * @param valueOf
	 * @return
	 */
	public List<PmsPermission> listAllByMenuId(Long menuId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuId", menuId);
		return this.getSessionTemplate().selectList(getStatement("listAllByMenuId"), param);
	}
}
