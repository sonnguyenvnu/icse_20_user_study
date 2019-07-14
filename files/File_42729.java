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
package com.roncoo.pay.controller.login;

import com.roncoo.pay.common.core.dwz.DWZ;
import com.roncoo.pay.common.core.dwz.DwzAjax;
import com.roncoo.pay.common.core.utils.StringUtil;
import com.roncoo.pay.controller.common.BaseController;
import com.roncoo.pay.permission.entity.PmsOperator;
import com.roncoo.pay.permission.exception.PermissionException;
import com.roncoo.pay.permission.service.PmsMenuService;
import com.roncoo.pay.permission.service.PmsOperatorRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šAlong
 */
@Controller
public class LoginController extends BaseController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private PmsOperatorRoleService pmsOperatorRoleService;
	@Autowired
	private PmsMenuService pmsMenuService;

	/**
	 * å‡½æ•°åŠŸèƒ½è¯´æ˜Ž ï¼š è¿›å…¥å?Žå?°ç™»é™†é¡µé?¢.
	 * 
	 * @å?‚æ•°ï¼š @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {

		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "ç”¨æˆ·å??/å¯†ç ?é”™è¯¯";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "ç”¨æˆ·å??/å¯†ç ?é”™è¯¯";
		} else if (PermissionException.class.getName().equals(exceptionClassName)) {
			error = "ç½‘ç»œå¼‚å¸¸,è¯·è?”ç³»é¾™æžœç®¡ç?†å‘˜";
		} else if (exceptionClassName != null) {
			error = "é”™è¯¯æ??ç¤ºï¼š" + exceptionClassName;
		}
		model.addAttribute("message", error);
		return "system/login";
	}

	/**
	 * å‡½æ•°åŠŸèƒ½è¯´æ˜Ž ï¼š ç™»é™†å?Žå?°ç®¡ç?†ç³»ç»Ÿ. ä¿®æ”¹è€…å??å­—ï¼š ä¿®æ”¹æ—¥æœŸï¼š ä¿®æ”¹å†…å®¹ï¼š
	 * 
	 * @å?‚æ•°ï¼š @param request
	 * @å?‚æ•°ï¼š @param model
	 * @å?‚æ•°ï¼š @return
	 * @return String
	 * @throws PermissionException
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest req, Model model) {
		PmsOperator pmsOperator = (PmsOperator) this.getSession().getAttribute("PmsOperator");
		try {
			String tree = this.buildOperatorPermissionMenu(pmsOperator);
			model.addAttribute("tree", tree);
		} catch (PermissionException e) {
			LOG.error("ç™»å½•å¼‚å¸¸:" + e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "system/login";
		}
		return "system/index";

	}

	/**
	 * å‡½æ•°åŠŸèƒ½è¯´æ˜Ž ï¼šè¿›å…¥é€€å‡ºç³»ç»Ÿç¡®è®¤é¡µé?¢. ä¿®æ”¹è€…å??å­—ï¼š ä¿®æ”¹æ—¥æœŸï¼š ä¿®æ”¹å†…å®¹ï¼š
	 * 
	 * @å?‚æ•°ï¼š @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/admin/confirm", method = RequestMethod.GET)
	public String confirm() {
		return "system/confirm";
	}

	/**
	 * å‡½æ•°åŠŸèƒ½è¯´æ˜Ž ï¼š é€€å‡ºç³»ç»Ÿ. ä¿®æ”¹è€…å??å­—ï¼š ä¿®æ”¹æ—¥æœŸï¼š ä¿®æ”¹å†…å®¹ï¼š
	 * 
	 * @å?‚æ•°ï¼š @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) {
		// ä¸?æ˜¯ä»¥formçš„å½¢å¼?æ??äº¤çš„æ•°æ?®,è¦?newä¸€ä¸ªDwzAjaxå¯¹è±¡
		DwzAjax dwz = new DwzAjax();
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("employee");
			LOG.info("***clean session success!***");
		} catch (Exception e) {
			LOG.error(e);
			dwz.setStatusCode(DWZ.ERROR);
			dwz.setMessage("é€€å‡ºç³»ç»Ÿæ—¶ç³»ç»Ÿå‡ºçŽ°å¼‚å¸¸ï¼Œè¯·é€šçŸ¥ç³»ç»Ÿç®¡ç?†å‘˜ï¼?");
			model.addAttribute("dwz", dwz);
			return "admin.common.ajaxDone";
		}
		return "admin.login";
	}

	/**
	 * èŽ·å?–ç”¨æˆ·çš„è?œå?•æ?ƒé™?
	 * 
	 * @param pmsOperator
	 * @return
	 * @throws PermissionException
	 * @throws Exception
	 */
	private String buildOperatorPermissionMenu(PmsOperator pmsOperator) throws PermissionException {
		// æ ¹æ?®ç”¨æˆ·IDå¾—åˆ°è¯¥ç”¨æˆ·çš„æ‰€æœ‰è§’è‰²æ‹¼æˆ?çš„å­—ç¬¦ä¸²
		String roleIds = pmsOperatorRoleService.getRoleIdsByOperatorId(pmsOperator.getId());
		if (StringUtils.isBlank(roleIds)) {
			LOG.error("==>ç”¨æˆ·[" + pmsOperator.getLoginName() + "]æ²¡æœ‰é…?ç½®å¯¹åº”çš„æ?ƒé™?è§’è‰²");
			throw new RuntimeException("è¯¥å¸?å?·å·²è¢«å?–æ¶ˆæ‰€æœ‰ç³»ç»Ÿæ?ƒé™?");
		}
		// æ ¹æ?®æ“?ä½œå‘˜æ‹¥æœ‰çš„è§’è‰²ID,æž„å»ºç®¡ç?†å?Žå?°çš„æ ‘å½¢æ?ƒé™?åŠŸèƒ½è?œå?•
		return this.buildPermissionTree(roleIds);
	}

	/**
	 * æ ¹æ?®æ“?ä½œå‘˜æ‹¥æœ‰çš„è§’è‰²ID,æž„å»ºç®¡ç?†å?Žå?°çš„æ ‘å½¢æ?ƒé™?åŠŸèƒ½è?œå?•
	 * 
	 * @param roleIds
	 * @return
	 * @throws PermissionException
	 */
	@SuppressWarnings("rawtypes")
	public String buildPermissionTree(String roleIds) throws PermissionException {
		List treeData = null;
		try {
			treeData = pmsMenuService.listByRoleIds(roleIds);
			if (StringUtil.isEmpty(treeData)) {
				LOG.error("ç”¨æˆ·æ²¡æœ‰åˆ†é…?è?œå?•æ?ƒé™?");
				throw new PermissionException(PermissionException.PERMISSION_USER_NOT_MENU, "è¯¥ç”¨æˆ·æ²¡æœ‰åˆ†é…?è?œå?•æ?ƒé™?"); // è¯¥ç”¨æˆ·æ²¡æœ‰åˆ†é…?è?œå?•æ?ƒé™?
			}
		} catch (Exception e) {
			LOG.error("æ ¹æ?®è§’è‰²æŸ¥è¯¢è?œå?•å‡ºçŽ°é”™è¯¯", e);
			throw new PermissionException(PermissionException.PERMISSION_QUERY_MENU_BY_ROLE_ERROR, "æ ¹æ?®è§’è‰²æŸ¥è¯¢è?œå?•å‡ºçŽ°é”™è¯¯"); // æŸ¥è¯¢å½“å‰?è§’è‰²çš„
		}
		StringBuffer strJson = new StringBuffer();
		buildAdminPermissionTree("0", strJson, treeData);
		return strJson.toString();
	}

	/**
	 * æž„å»ºç®¡ç?†å?Žå?°çš„æ ‘å½¢æ?ƒé™?åŠŸèƒ½è?œå?•
	 * 
	 * @param pId
	 * @param treeBuf
	 * @param menuList
	 */
	@SuppressWarnings("rawtypes")
	private void buildAdminPermissionTree(String pId, StringBuffer treeBuf, List menuList) {

		List<Map> listMap = getSonMenuListByPid(pId.toString(), menuList);
		for (Map map : listMap) {
			String id = map.get("id").toString();// id
			String name = map.get("name").toString();// å??ç§°
			String isLeaf = map.get("isLeaf").toString();// æ˜¯å?¦å?¶å­?
			String level = map.get("level").toString();// è?œå?•å±‚çº§ï¼ˆ1ã€?2ã€?3ã€?4ï¼‰
			String url = map.get("url").toString(); // ACTIONè®¿é—®åœ°å?€
			String navTabId = "";
			if (!StringUtil.isEmpty(map.get("targetName"))) {
				navTabId = map.get("targetName").toString(); // ç”¨äºŽåˆ·æ–°æŸ¥è¯¢é¡µé?¢
			}

			if ("1".equals(level)) {
				treeBuf.append("<div class='accordionHeader'>");
				treeBuf.append("<h2> <span>Folder</span> " + name + "</h2>");
				treeBuf.append("</div>");
				treeBuf.append("<div class='accordionContent'>");
			}

			if ("YES".equals(isLeaf)) {
				treeBuf.append("<li><a href='" + url + "' target='navTab' rel='" + navTabId + "'>" + name + "</a></li>");
			} else {

				if ("1".equals(level)) {
					treeBuf.append("<ul class='tree treeFolder'>");
				} else {
					treeBuf.append("<li><a>" + name + "</a>");
					treeBuf.append("<ul>");
				}

				buildAdminPermissionTree(id, treeBuf, menuList);

				if ("1".equals(level)) {
					treeBuf.append("</ul>");
				} else {
					treeBuf.append("</ul></li>");
				}

			}

			if ("1".equals(level)) {
				treeBuf.append("</div>");
			}
		}

	}

	/**
	 * æ ¹æ?®(pId)èŽ·å?–(menuList)ä¸­çš„æ‰€æœ‰å­?è?œå?•é›†å?ˆ.
	 * 
	 * @param pId
	 *            çˆ¶è?œå?•ID.
	 * @param menuList
	 *            è?œå?•é›†å?ˆ.
	 * @return sonMenuList.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getSonMenuListByPid(String pId, List menuList) {
		List sonMenuList = new ArrayList<Object>();
		for (Object menu : menuList) {
			Map map = (Map) menu;
			if (map != null) {
				String parentId = map.get("pId").toString();// çˆ¶id
				if (parentId.equals(pId)) {
					sonMenuList.add(map);
				}
			}
		}
		return sonMenuList;
	}

}
