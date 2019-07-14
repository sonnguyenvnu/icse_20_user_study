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
package com.roncoo.pay.permission.controller;

import com.roncoo.pay.common.core.dwz.DwzAjax;
import com.roncoo.pay.common.core.enums.PublicEnum;
import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.controller.common.BaseController;
import com.roncoo.pay.permission.biz.PmsMenuBiz;
import com.roncoo.pay.permission.entity.PmsMenu;
import com.roncoo.pay.permission.service.PmsMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * æ?ƒé™?-è?œå?•æŽ§åˆ¶å™¨
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Controller
@RequestMapping("/pms/menu")
public class PmsMenuController extends BaseController {

	private static final Log log = LogFactory.getLog(PmsMenuController.class);

	@Autowired
	private PmsMenuService pmsMenuService;
	@Autowired
	private PmsMenuBiz pmsMenuBiz;

	/**
	 * åˆ—å‡ºè¦?ç®¡ç?†çš„è?œå?•.
	 * 
	 * @return PmsMenuList .
	 */
	@RequiresPermissions("pms:menu:view")
	@RequestMapping("/list")
	public String listPmsMenu(HttpServletRequest req, Model model) {
		String editMenuController = "pms/menu/editUI";
		String str = pmsMenuBiz.getTreeMenu(editMenuController);
		model.addAttribute("tree", str);
		return "pms/pmsMenuList";
	}

	/**
	 * è¿›å…¥æ–°è?œå?•æ·»åŠ é¡µé?¢.
	 * 
	 * @return PmsMenuAdd .
	 */
	@RequiresPermissions("pms:menu:add")
	@RequestMapping("/addUI")
	public String addPmsMenuUI(HttpServletRequest req, PmsMenu pmsMenu, Model model, Long pid) {
		if (null != pid) {
			PmsMenu parentMenu = pmsMenuService.getById(pid);
			pmsMenu.setParent(parentMenu);
			model.addAttribute(pmsMenu);
		}
		return "pms/pmsMenuAdd";
	}

	/**
	 * ä¿?å­˜æ–°å¢žè?œå?•.
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:menu:add")
	@RequestMapping("/add")
	public String addPmsMenu(HttpServletRequest req, PmsMenu pmsMenu, Model model, DwzAjax dwz) {
		try {
			String name = pmsMenu.getName();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isLeaf", "YES");
			map.put("name", name);
			List<PmsMenu> list = pmsMenuService.getMenuByNameAndIsLeaf(map);
			if (list.size() > 0) {
				return operateError("å?Œçº§è?œå?•å??ç§°ä¸?èƒ½é‡?å¤?", model);
			}
			pmsMenu.setCreater(getPmsOperator().getLoginName());
			pmsMenu.setStatus(PublicStatusEnum.ACTIVE.name());
			pmsMenu.setIsLeaf("YES");
			if (null != pmsMenu.getParent().getId()) {
				pmsMenu.setLevel(pmsMenu.getParent().getLevel()+1);
			}else{
				pmsMenu.setLevel(1L);
				PmsMenu parent = new PmsMenu();
				parent.setId(0l);
				pmsMenu.setParent(parent);
			}
			pmsMenuService.savaMenu(pmsMenu);
		} catch (Exception e) {
			// è®°å½•ç³»ç»Ÿæ“?ä½œæ—¥å¿—
			log.error("== addPmsMenu exception:", e);
			return operateError("æ·»åŠ è?œå?•å‡ºé”™", model);
		}
		return operateSuccess(model, dwz);
	}

	/**
	 * è¿›å…¥è?œå?•ä¿®æ”¹é¡µé?¢.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:edit")
	@RequestMapping("/editUI")
	public String editPmsMenuUI(HttpServletRequest req, Long id, Model model) {
		if (null != id) {
			PmsMenu pmsMenu = pmsMenuService.getById(id);
			model.addAttribute(pmsMenu);
		}
		return "pms/pmsMenuEdit";
	}

	/**
	 * ä¿?å­˜è¦?ä¿®æ”¹çš„è?œå?•.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:edit")
	@RequestMapping("/edit")
	public String editPmsMenu(HttpServletRequest req, PmsMenu menu, Model model, DwzAjax dwz) {
		try {
			PmsMenu parentMenu = menu.getParent();
			if (null == parentMenu) {
				parentMenu = new PmsMenu();
				parentMenu.setId(0L);
			}
			menu.setParent(parentMenu);
			pmsMenuService.update(menu);
			// è®°å½•ç³»ç»Ÿæ“?ä½œæ—¥å¿—
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			// è®°å½•ç³»ç»Ÿæ“?ä½œæ—¥å¿—
			log.error("== editPmsMenu exception:", e);
			return operateError("ä¿?å­˜è?œå?•å‡ºé”™", model);
		}

	}

	/**
	 * åˆ é™¤è?œå?•.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:delete")
	@RequestMapping("/delete")
	public String delPmsMenu(HttpServletRequest req, Long menuId, Model model, DwzAjax dwz) {
		try {
			if (menuId == null || menuId == 0) {
				return operateError("æ— æ³•èŽ·å?–è¦?åˆ é™¤çš„æ•°æ?®", model);
			}
			PmsMenu menu = pmsMenuService.getById(menuId);
			if (menu == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?åˆ é™¤çš„æ•°æ?®", model);
			}
			Long parentId = menu.getParent().getId(); // èŽ·å?–çˆ¶è?œå?•ID

			// å…ˆåˆ¤æ–­æ­¤è?œå?•ä¸‹æ˜¯å?¦æœ‰å­?è?œå?•
			List<PmsMenu> childMenuList = pmsMenuService.listByParentId(menuId);
			if (childMenuList != null && !childMenuList.isEmpty()) {
				return operateError("æ­¤è?œå?•ä¸‹å…³è?”æœ‰ã€?" + childMenuList.size() + "ã€‘ä¸ªå­?è?œå?•ï¼Œä¸?èƒ½æ”¯æŽ¥åˆ é™¤!", model);
			}

			// åˆ é™¤æŽ‰è?œå?•
			pmsMenuService.delete(menuId);

			// åˆ é™¤è?œå?•å?Žï¼Œè¦?åˆ¤æ–­å…¶çˆ¶è?œå?•æ˜¯å?¦è¿˜æœ‰å­?è?œå?•ï¼Œå¦‚æžœæ²¡æœ‰å­?è?œå?•äº†å°±è¦?è£…å…¶çˆ¶è?œå?•è®¾ä¸ºå?¶å­?èŠ‚ç‚¹
			List<PmsMenu> childList = pmsMenuService.listByParentId(parentId);
			if (childList == null || childList.isEmpty()) {
				// æ­¤æ—¶è¦?å°†çˆ¶è?œå?•è®¾ä¸ºå?¶å­?
				PmsMenu parent = pmsMenuService.getById(parentId);
				parent.setIsLeaf(PublicEnum.YES.name());
				pmsMenuService.update(parent);
			}
			// è®°å½•ç³»ç»Ÿæ“?ä½œæ—¥å¿—
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			// è®°å½•ç³»ç»Ÿæ“?ä½œæ—¥å¿—
			log.error("== delPmsMenu exception:", e);
			return operateError("åˆ é™¤è?œå?•å‡ºé”™", model);
		}
	}

}
