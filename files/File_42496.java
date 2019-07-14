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

import com.roncoo.pay.permission.dao.PmsMenuDao;
import com.roncoo.pay.permission.entity.PmsMenu;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * æ?ƒé™?è?œå?•
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Repository("pmsMenuDao")
public class PmsMenuDaoImpl extends PermissionBaseDaoImpl<PmsMenu> implements PmsMenuDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List listByRoleIds(String roleIdsStr) {
		List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
		return super.getSessionTemplate().selectList(getStatement("listByRoleIds"), roldIds);
	}

	/**
	 * æ ¹æ?®çˆ¶è?œå?•IDèŽ·å?–è¯¥è?œå?•ä¸‹çš„æ‰€æœ‰å­?å­™è?œå?•.<br/>
	 * 
	 * @param parentId
	 *            (å¦‚æžœä¸ºç©ºï¼Œåˆ™ä¸ºèŽ·å?–æ‰€æœ‰çš„è?œå?•).<br/>
	 * @return menuList.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List listByParent(Long parentId) {
		return super.getSessionTemplate().selectList(getStatement("listByParent"), parentId);
	}

	/**
	 * æ ¹æ?®è?œå?•IDæŸ¥æ‰¾è?œå?•ï¼ˆå?¯ç”¨äºŽåˆ¤æ–­è?œå?•ä¸‹æ˜¯å?¦è¿˜æœ‰å­?è?œå?•ï¼‰.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	@Override
	public List<PmsMenu> listByParentId(Long parentId) {
		return super.getSessionTemplate().selectList(getStatement("listByParentId"), parentId);
	}

	/***
	 * æ ¹æ?®å??ç§°å’Œæ˜¯å?¦å?¶å­?èŠ‚ç‚¹æŸ¥è¯¢æ•°æ?®
	 * 
	 * @param isLeaf
	 *            æ˜¯å?¦æ˜¯å?¶å­?èŠ‚ç‚¹
	 * @param name
	 *            èŠ‚ç‚¹å??ç§°
	 * @return
	 */
	public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
		return super.getSessionTemplate().selectList(getStatement("listBy"), map);
	}

}
