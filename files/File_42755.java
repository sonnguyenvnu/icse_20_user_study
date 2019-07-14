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
import com.roncoo.pay.common.core.enums.PublicStatusEnum;
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.controller.common.BaseController;
import com.roncoo.pay.permission.entity.PmsPermission;
import com.roncoo.pay.permission.entity.PmsRole;
import com.roncoo.pay.permission.service.PmsPermissionService;
import com.roncoo.pay.permission.service.PmsRoleService;
import com.roncoo.pay.permission.utils.ValidateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * æ?ƒé™?ç®¡ç?†æ¨¡å?—çš„Permissionç±»ï¼ŒåŒ…æ‹¬æ?ƒé™?ç‚¹ç®¡ç?†ã€?è§’è‰²ç®¡ç?†ã€?æ“?ä½œå‘˜ç®¡ç?†.<br/>
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Controller
@RequestMapping("/pms/permission")
public class PmsPermissionController extends BaseController {

	@Autowired
	private PmsPermissionService pmsPermissionService;
	@Autowired
	private PmsRoleService pmsRoleService;

	private static Log log = LogFactory.getLog(PmsPermissionController.class);

	/**
	 * åˆ†é¡µåˆ—å‡ºpmsæ?ƒé™?ï¼Œä¹Ÿå?¯æ ¹æ?®æ?ƒé™?èŽ·æ?ƒé™?å??ç§°è¿›è¡ŒæŸ¥è¯¢.
	 * 
	 * @return PmsPermissionList or operateError.
	 */
	@RequiresPermissions("pms:permission:view")
	@RequestMapping("/list")
	public String listPmsPermission(HttpServletRequest req, PageParam pageParam, PmsPermission pmsPermission, Model model) {
		try {
			PageBean pageBean = pmsPermissionService.listPage(pageParam, pmsPermission);
			model.addAttribute(pageBean);
			model.addAttribute("pageParam", pageParam);
			return "pms/pmsPermissionList";
		} catch (Exception e) {
			log.error("== listPmsPermission exception:", e);
			return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * è¿›å…¥æ·»åŠ Pmsæ?ƒé™?é¡µé?¢ .
	 * 
	 * @return addPmsPermissionUI .
	 */
	@RequiresPermissions("pms:permission:add")
	@RequestMapping("/addUI")
	public String addPmsPermissionUI() {
		return "pms/pmsPermissionAdd";
	}

	/**
	 * å°†æ?ƒé™?ä¿¡æ?¯ä¿?å­˜åˆ°æ•°æ?®åº“ä¸­
	 * 
	 * @return operateSuccess or operateError.
	 */
	@RequiresPermissions("pms:permission:add")
	@RequestMapping("/add")
	public String addPmsPermission(HttpServletRequest req, PmsPermission pmsPermission, Model model, DwzAjax dwz) {
		try {

			// è¡¨å?•æ•°æ?®æ ¡éªŒ
			String validateMsg = validatePmsPermission(pmsPermission);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
			}

			String permissionName = pmsPermission.getPermissionName().trim();
			String permission = pmsPermission.getPermission();
			// æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨
			PmsPermission checkName = pmsPermissionService.getByPermissionName(permissionName);
			if (checkName != null) {
				return operateError("æ?ƒé™?å??ç§°ã€?" + permissionName + "ã€‘å·²å­˜åœ¨", model);
			}
			// æ£€æŸ¥æ?ƒé™?æ˜¯å?¦å·²å­˜åœ¨
			PmsPermission checkPermission = pmsPermissionService.getByPermission(permission);
			if (checkPermission != null) {
				return operateError("æ?ƒé™?ã€?" + permission + "ã€‘å·²å­˜åœ¨", model);
			}
			pmsPermission.setStatus(PublicStatusEnum.ACTIVE.name());
			pmsPermission.setCreater(getPmsOperator().getLoginName());
			pmsPermission.setCreateTime(new Date());
			pmsPermissionService.saveData(pmsPermission);

			return operateSuccess(model, dwz); // è¿”å›žoperateSuccessè§†å›¾,å¹¶æ??ç¤ºâ€œæ“?ä½œæˆ?åŠŸâ€?
		} catch (Exception e) {
			log.error("== addPmsPermission exception:", e);
			return operateError("ä¿?å­˜å¤±è´¥", model);
		}
	}

	/**
	 * æ ¡éªŒPmsæ?ƒé™?ä¿¡æ?¯.
	 * 
	 * @param pmsPermission
	 *            .
	 * @return msg .
	 */
	private String validatePmsPermission(PmsPermission pmsPermission) {
		String msg = ""; // ç”¨äºŽå­˜æ”¾æ ¡éªŒæ??ç¤ºä¿¡æ?¯çš„å?˜é‡?
		String permissionName = pmsPermission.getPermissionName(); // æ?ƒé™?å??ç§°
		String permission = pmsPermission.getPermission(); // æ?ƒé™?æ ‡è¯†
		String desc = pmsPermission.getRemark(); // æ?ƒé™?æ??è¿°
		// æ?ƒé™?å??ç§° permissionName
		msg += ValidateUtils.lengthValidate("æ?ƒé™?å??ç§°", permissionName, true, 3, 90);
		// æ?ƒé™?æ ‡è¯† permission
		msg += ValidateUtils.lengthValidate("æ?ƒé™?æ ‡è¯†", permission, true, 3, 100);
		// æ??è¿° desc
		msg += ValidateUtils.lengthValidate("æ??è¿°", desc, true, 3, 60);
		return msg;
	}

	/**
	 * è½¬åˆ°æ?ƒé™?ä¿®æ”¹é¡µé?¢ .
	 * 
	 * @return editPmsPermissionUI or operateError .
	 */
	@RequiresPermissions("pms:permission:edit")
	@RequestMapping("/editUI")
	public String editPmsPermissionUI(HttpServletRequest req, Long id, Model model) {
		try {
			PmsPermission pmsPermission = pmsPermissionService.getDataById(id);
			model.addAttribute("pmsPermission", pmsPermission);
			return "pms/pmsPermissionEdit";
		} catch (Exception e) {
			log.error("== editPmsPermissionUI exception:", e);
			return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * ä¿?å­˜ä¿®æ”¹å?Žçš„æ?ƒé™?ä¿¡æ?¯
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:permission:edit")
	@RequestMapping("/edit")
	public String editPmsPermission(HttpServletRequest req, PmsPermission permission, Model model, DwzAjax dwz) {
		try {
			Long id = permission.getId();
			PmsPermission pmsPermission = pmsPermissionService.getDataById(id);
			if (pmsPermission == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?ä¿®æ”¹çš„æ•°æ?®", model);
			} else {

				String permissionName = permission.getPermissionName();
				String remark = permission.getRemark();

				pmsPermission.setPermissionName(permissionName);
				pmsPermission.setRemark(remark);

				// è¡¨å?•æ•°æ?®æ ¡éªŒ
				String validateMsg = validatePmsPermission(pmsPermission);
				if (StringUtils.isNotBlank(validateMsg)) {
					return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
				}

				// æ£€æŸ¥æ?ƒé™?å??ç§°æ˜¯å?¦å·²å­˜åœ¨
				PmsPermission checkName = pmsPermissionService.getByPermissionNameNotEqId(permissionName, id);
				if (checkName != null) {
					return operateError("æ?ƒé™?å??ç§°ã€?" + permissionName + "ã€‘å·²å­˜åœ¨", model);
				}

				pmsPermissionService.updateData(pmsPermission);

				return operateSuccess(model, dwz);
			}
		} catch (Exception e) {
			log.error("== editPmsPermission exception:", e);
			return operateError("ä¿®æ”¹å¤±è´¥", model);
		}
	}

	/**
	 * åˆ é™¤ä¸€æ?¡æ?ƒé™?è®°å½•
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:permission:delete")
	@RequestMapping("/delete")
	public String deletePmsPermission(HttpServletRequest req, Long permissionId, Model model, DwzAjax dwz) {
		try {
			PmsPermission permission = pmsPermissionService.getDataById(permissionId);
			if (permission == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?åˆ é™¤çš„æ•°æ?®", model);
			}
			// åˆ¤æ–­æ­¤æ?ƒé™?æ˜¯å?¦å…³è?”æœ‰è§’è‰²ï¼Œè¦?å…ˆè§£é™¤ä¸Žè§’è‰²çš„å…³è?”å?Žæ‰?èƒ½åˆ é™¤è¯¥æ?ƒé™?
			List<PmsRole> roleList = pmsRoleService.listByPermissionId(permissionId);
			if (roleList != null && !roleList.isEmpty()) {
				return operateError("æ?ƒé™?ã€?" + permission.getPermission() + "ã€‘å…³è?”äº†ã€?" + roleList.size() + "ã€‘ä¸ªè§’è‰²ï¼Œè¦?è§£é™¤æ‰€æœ‰å…³è?”å?Žæ‰?èƒ½åˆ é™¤ã€‚å…¶ä¸­ä¸€ä¸ªè§’è‰²å??ä¸º:" + roleList.get(0).getRoleName(), model);
			}
			pmsPermissionService.delete(permissionId);
			return operateSuccess(model, dwz); // è¿”å›žoperateSuccessè§†å›¾,å¹¶æ??ç¤ºâ€œæ“?ä½œæˆ?åŠŸâ€?
		} catch (Exception e) {
			log.error("== deletePmsPermission exception:", e);
			return operateError("åˆ é™¤é™?æ?ƒå¼‚å¸¸", model);
		}
	}
}
