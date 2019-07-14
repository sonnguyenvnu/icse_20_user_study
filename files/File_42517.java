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

import com.roncoo.pay.permission.dao.PmsMenuDao;
import com.roncoo.pay.permission.dao.PmsMenuRoleDao;
import com.roncoo.pay.permission.entity.PmsMenu;
import com.roncoo.pay.permission.entity.PmsMenuRole;
import com.roncoo.pay.permission.service.PmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * è?œå?•serviceæŽ¥å?£å®žçŽ°
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Service("pmsMenuService")
public class PmsMenuServiceImpl implements PmsMenuService {

	@Autowired
	private PmsMenuDao pmsMenuDao;
	@Autowired
	private  PmsMenuRoleDao pmsMenuRoleDao;

	/**
	 * ä¿?å­˜è?œå?•PmsMenuDao
	 * 
	 * @param menu
	 */
	public void savaMenu(PmsMenu menu) {
		pmsMenuDao.insert(menu);
	}

	/**
	 * æ ¹æ?®çˆ¶è?œå?•IDèŽ·å?–è¯¥è?œå?•ä¸‹çš„æ‰€æœ‰å­?å­™è?œå?•.<br/>
	 * 
	 * @param parentId
	 *            (å¦‚æžœä¸ºç©ºï¼Œåˆ™ä¸ºèŽ·å?–æ‰€æœ‰çš„è?œå?•).<br/>
	 * @return menuList.
	 */
	@SuppressWarnings("rawtypes")
	public List getListByParent(Long parentId) {
		return pmsMenuDao.listByParent(parentId);
	}

	/**
	 * æ ¹æ?®idåˆ é™¤è?œå?•
	 */
	public void delete(Long id) {
		this.pmsMenuDao.delete(id);
	}

	/**
	 * æ ¹æ?®è§’è‰²idä¸²èŽ·å?–è?œå?•
	 * 
	 * @param roleIdsStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listByRoleIds(String roleIdsStr) {
		return this.pmsMenuDao.listByRoleIds(roleIdsStr);
	}

	/**
	 * æ ¹æ?®è?œå?•IDæŸ¥æ‰¾è?œå?•ï¼ˆå?¯ç”¨äºŽåˆ¤æ–­è?œå?•ä¸‹æ˜¯å?¦è¿˜æœ‰å­?è?œå?•ï¼‰.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	public List<PmsMenu> listByParentId(Long parentId) {
		return pmsMenuDao.listByParentId(parentId);
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
		return pmsMenuDao.getMenuByNameAndIsLeaf(map);
	}

	/**
	 * æ ¹æ?®è?œå?•IDèŽ·å?–è?œå?•.
	 * 
	 * @param pid
	 * @return
	 */
	public PmsMenu getById(Long pid) {
		return pmsMenuDao.getById(pid);
	}

	/**
	 * æ›´æ–°è?œå?•.
	 * 
	 * @param menu
	 */
	public void update(PmsMenu menu) {
		pmsMenuDao.update(menu);

	}

	/**
	 * æ ¹æ?®è§’è‰²æŸ¥æ‰¾è§’è‰²å¯¹åº”çš„è?œå?•IDé›†
	 * 
	 * @param roleId
	 * @return
	 */
	public String getMenuIdsByRoleId(Long roleId) {
		List<PmsMenuRole> menuList = pmsMenuRoleDao.listByRoleId(roleId);
		StringBuffer menuIds = new StringBuffer("");
		if (menuList != null && !menuList.isEmpty()) {
			for (PmsMenuRole rm : menuList) {
				menuIds.append(rm.getMenuId()).append(",");
			}
		}
		return menuIds.toString();

	}
}
