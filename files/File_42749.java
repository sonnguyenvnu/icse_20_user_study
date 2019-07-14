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
import com.roncoo.pay.permission.entity.PmsOperator;
import com.roncoo.pay.permission.entity.PmsOperatorRole;
import com.roncoo.pay.permission.enums.OperatorTypeEnum;
import com.roncoo.pay.permission.service.PmsOperatorRoleService;
import com.roncoo.pay.permission.service.PmsOperatorService;
import com.roncoo.pay.permission.service.PmsRoleService;
import com.roncoo.pay.permission.utils.PasswordHelper;
import com.roncoo.pay.permission.utils.ValidateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * æ?ƒé™?ç®¡ç?†æ¨¡å?—æ“?ä½œå‘˜ç®¡ç?†
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
@Controller
@RequestMapping("/pms/operator")
public class PmsOperatorController extends BaseController {

	private static Log log = LogFactory.getLog(PmsOperatorController.class);

	@Autowired
	private PmsOperatorService pmsOperatorService;
	@Autowired
	private PmsRoleService pmsRoleService;
	@Autowired
	private PmsOperatorRoleService pmsOperatorRoleService;

	/**
	 * åˆ†é¡µåˆ—å‡ºæ“?ä½œå‘˜ä¿¡æ?¯ï¼Œå¹¶å?¯æŒ‰ç™»å½•å??èŽ·å§“å??è¿›è¡ŒæŸ¥è¯¢.
	 * 
	 * @return listPmsOperator or operateError .
	 * 
	 */
	@RequiresPermissions("pms:operator:view")
	@RequestMapping("/list")
	public String listPmsOperator(HttpServletRequest req, PageParam pageParam, PmsOperator operator, Model model) {
		try {

			PageBean pageBean = pmsOperatorService.listPage(pageParam, operator);
			model.addAttribute(pageBean);
			model.addAttribute("OperatorStatusEnum", PublicStatusEnum.toMap());
			model.addAttribute("OperatorTypeEnum", OperatorTypeEnum.toMap());
			return "pms/pmsOperatorList";
		} catch (Exception e) {
			log.error("== listPmsOperator exception:", e);
			return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * æŸ¥çœ‹æ“?ä½œå‘˜è¯¦æƒ….
	 * 
	 * @return .
	 */
	@RequiresPermissions("pms:operator:view")
	@RequestMapping("/viewUI")
	public String viewPmsOperatorUI(HttpServletRequest req, Long id, Model model) {
		try {
			PmsOperator pmsOperator = pmsOperatorService.getDataById(id);
			if (pmsOperator == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?æŸ¥çœ‹çš„æ•°æ?®", model);
			}

			// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰æŸ¥çœ‹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
			if (OperatorTypeEnum.USER.name().equals(this.getPmsOperator().getType()) && OperatorTypeEnum.ADMIN.name().equals(pmsOperator.getType())) {
				return operateError("æ?ƒé™?ä¸?è¶³", model);
			}

			// å‡†å¤‡è§’è‰²åˆ—è¡¨
			model.addAttribute("rolesList", pmsRoleService.listAllRole());

			// å‡†å¤‡è¯¥ç”¨æˆ·æ‹¥æœ‰çš„è§’è‰²IDå­—ç¬¦ä¸²
			List<PmsOperatorRole> lisPmsOperatorRoles = pmsOperatorRoleService.listOperatorRoleByOperatorId(id);
			StringBuffer owenedRoleIdBuffer = new StringBuffer("");
			for (PmsOperatorRole pmsOperatorRole : lisPmsOperatorRoles) {
				owenedRoleIdBuffer.append(pmsOperatorRole.getRoleId());
				owenedRoleIdBuffer.append(",");
			}
			String owenedRoleIds = owenedRoleIdBuffer.toString();
			if (StringUtils.isNotBlank(owenedRoleIds) && owenedRoleIds.length() > 0) {
				owenedRoleIds = owenedRoleIds.substring(0, owenedRoleIds.length() - 1);
			}
			model.addAttribute("pmsOperator", pmsOperator);
			model.addAttribute("owenedRoleIds", owenedRoleIds);
			return "/pms/pmsOperatorView";
		} catch (Exception e) {
			log.error("== viewPmsOperatorUI exception:", e);
			return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * è½¬åˆ°æ·»åŠ æ“?ä½œå‘˜é¡µé?¢ .
	 * 
	 * @return addPmsOperatorUI or operateError .
	 */
	@RequiresPermissions("pms:operator:add")
	@RequestMapping("/addUI")
	public String addPmsOperatorUI(HttpServletRequest req, Model model) {
		try {
			model.addAttribute("rolesList", pmsRoleService.listAllRole());
			model.addAttribute("OperatorStatusEnumList", PublicStatusEnum.toList());
			return "/pms/pmsOperatorAdd";
		} catch (Exception e) {
			log.error("== addPmsOperatorUI exception:", e);
			return operateError("èŽ·å?–è§’è‰²åˆ—è¡¨æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * ä¿?å­˜ä¸€ä¸ªæ“?ä½œå‘˜
	 * 
	 */
	@RequiresPermissions("pms:operator:add")
	@RequestMapping("/add")
	public String addPmsOperator(HttpServletRequest req, PmsOperator pmsOperator, @RequestParam("selectVal") String selectVal, Model model, DwzAjax dwz) {
		try {
			pmsOperator.setType(OperatorTypeEnum.USER.name()); // ç±»åž‹ï¼ˆ
																// "0":'æ™®é€šæ“?ä½œå‘˜',"1":'è¶…çº§ç®¡ç?†å‘˜'ï¼‰ï¼Œå?ªèƒ½æ·»åŠ æ™®é€šæ“?ä½œå‘˜
			String roleOperatorStr = getRoleOperatorStr(selectVal);

			// è¡¨å?•æ•°æ?®æ ¡éªŒ
			String validateMsg = validatePmsOperator(pmsOperator, roleOperatorStr);


			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
			}

			// æ ¡éªŒæ“?ä½œå‘˜ç™»å½•å??æ˜¯å?¦å·²å­˜åœ¨
			PmsOperator loginNameCheck = pmsOperatorService.findOperatorByLoginName(pmsOperator.getLoginName());
			if (loginNameCheck != null) {
				return operateError("ç™»å½•å??ã€?" + pmsOperator.getLoginName() + "ã€‘å·²å­˜åœ¨", model);
			}

			PasswordHelper.encryptPassword(pmsOperator);
			pmsOperator.setCreater(getPmsOperator().getLoginName());
			pmsOperator.setCreateTime(new Date());
			pmsOperatorService.saveOperator(pmsOperator, roleOperatorStr);

			return operateSuccess(model, dwz);
		} catch (Exception e) {
			log.error("== addPmsOperator exception:", e);
			return operateError("ä¿?å­˜æ“?ä½œå‘˜ä¿¡æ?¯å¤±è´¥", model);
		}
	}

	/**
	 * éªŒè¯?è¾“å…¥çš„é‚®ç®±æ ¼å¼?æ˜¯å?¦ç¬¦å?ˆ
	 * 
	 * @param email
	 * @return æ˜¯å?¦å?ˆæ³•
	 */
	public static boolean emailFormat(String email) {
		// boolean tag = true;
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean result = Pattern.matches(check, email);
		return result;
	}

	/**
	 * éªŒè¯?è¾“å…¥çš„å¯†ç ?æ ¼å¼?æ˜¯å?¦ç¬¦å?ˆ
	 * 
	 * @param loginPwd
	 * @return æ˜¯å?¦å?ˆæ³•
	 */
	public static boolean loginPwdFormat(String loginPwd) {
		return loginPwd.matches(".*?[^a-zA-Z\\d]+.*?") && loginPwd.matches(".*?[a-zA-Z]+.*?") && loginPwd.matches(".*?[\\d]+.*?");
	}

	/**
	 * éªŒè¯?è¾“å…¥çš„æ“?ä½œå‘˜å§“å??æ ¼å¼?æ˜¯å?¦ç¬¦å?ˆ
	 * 
	 * @param loginPwd
	 * @return æ˜¯å?¦å?ˆæ³•
	 */
	public static boolean realNameFormat(String realName) {
		return realName.matches("[^\\x00-\\xff]+");
	}

	/**
	 * æ ¡éªŒPmsæ“?ä½œå‘˜è¡¨å?•æ•°æ?®.
	 * 
	 * @param PmsOperator
	 *            æ“?ä½œå‘˜ä¿¡æ?¯.
	 * @param roleOperatorStr
	 *            å…³è?”çš„è§’è‰²IDä¸².
	 * @return
	 */
	private String validatePmsOperator(PmsOperator operator, String roleOperatorStr) {
		String msg = ""; // ç”¨äºŽå­˜æ”¾æ ¡éªŒæ??ç¤ºä¿¡æ?¯çš„å?˜é‡?
		msg += ValidateUtils.lengthValidate("çœŸå®žå§“å??", operator.getRealName(), true, 2, 15);
		msg += ValidateUtils.lengthValidate("ç™»å½•å??", operator.getLoginName(), true, 3, 50);

		/*
		 * String specialChar = "`!@#$%^&*()_+\\/"; if
		 * (operator.getLoginName().contains(specialChar)) { msg +=
		 * "ç™»å½•å??ä¸?èƒ½åŒ…å?«ç‰¹æ®Šå­—ç¬¦ï¼Œ"; }
		 */
//		if (!realNameFormat(operator.getRealName())) {
//			msg += "æ“?ä½œå‘˜å§“å??å¿…é¡»ä¸ºä¸­æ–‡ï¼?";
//		}

		// if (!emailFormat(operator.getLoginName())) {
		// msg += "è´¦æˆ·å??æ ¼å¼?å¿…é¡»ä¸ºé‚®ç®±åœ°å?€ï¼?";
		// }

		// ç™»å½•å¯†ç ?
//		String loginPwd = operator.getLoginPwd();
//		String loginPwdMsg = ValidateUtils.lengthValidate("ç™»å½•å¯†ç ?", loginPwd, true, 6, 50);
//		/*
//		 * if (StringUtils.isBlank(loginPwdMsg) &&
//		 * !ValidateUtils.isAlphanumeric(loginPwd)) { loginPwdMsg +=
//		 * "ç™»å½•å¯†ç ?åº”ä¸ºå­—æ¯?æˆ–æ•°å­—ç»„æˆ?ï¼Œ"; }
//		 */
//		msg += loginPwdMsg;

		// æ‰‹æœºå?·ç ?
		String mobileNo = operator.getMobileNo();
		String mobileNoMsg = ValidateUtils.lengthValidate("æ‰‹æœºå?·", mobileNo, true, 0, 12);
		if (StringUtils.isBlank(mobileNoMsg) && !ValidateUtils.isMobile(mobileNo)) {
			mobileNoMsg += "æ‰‹æœºå?·æ ¼å¼?ä¸?æ­£ç¡®ï¼Œ";
		}
		msg += mobileNoMsg;

		// çŠ¶æ€?
		String status = operator.getStatus();
		if (status == null) {
			msg += "è¯·é€‰æ‹©çŠ¶æ€?ï¼Œ";
		} else if (!PublicStatusEnum.ACTIVE.name().equals(status) || PublicStatusEnum.UNACTIVE.name().equals(status)) {
			msg += "çŠ¶æ€?å€¼ä¸?æ­£ç¡®ï¼Œ";
		}

		msg += ValidateUtils.lengthValidate("æ??è¿°", operator.getRemark(), true, 3, 100);

		// æ–°å¢žæ“?ä½œå‘˜çš„æ?ƒé™?ä¸?èƒ½ä¸ºç©ºï¼Œä¸ºç©ºæ²¡æ„?ä¹‰
		if (StringUtils.isBlank(roleOperatorStr) && operator.getId() == null) {
			msg += "æ“?ä½œå‘˜å…³è?”çš„è§’è‰²ä¸?èƒ½ä¸ºç©º";
		}
		return msg;
	}

	/**
	 * åˆ é™¤æ“?ä½œå‘˜
	 * 
	 * @return
	 * */
	@RequestMapping("/delete")
	public String deleteOperatorStatus(HttpServletRequest req, Long id, Model model, DwzAjax dwz) {
		pmsOperatorService.deleteOperatorById(id);
		return this.operateSuccess(model, dwz);
	}

	/**
	 * è½¬åˆ°ä¿®æ”¹æ“?ä½œå‘˜ç•Œé?¢
	 * 
	 * @return PmsOperatorEdit or operateError .
	 */
	@RequiresPermissions("pms:operator:edit")
	@RequestMapping("/editUI")
	public String editPmsOperatorUI(HttpServletRequest req, Long id, Model model) {
		try {
			PmsOperator pmsOperator = pmsOperatorService.getDataById(id);
			if (pmsOperator == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?ä¿®æ”¹çš„æ•°æ?®", model);
			}

			// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
			if (OperatorTypeEnum.USER.name().equals(this.getPmsOperator().getType()) && OperatorTypeEnum.ADMIN.name().equals(pmsOperator.getType())) {
				return operateError("æ?ƒé™?ä¸?è¶³", model);
			}
			// å‡†å¤‡è§’è‰²åˆ—è¡¨
			model.addAttribute("rolesList", pmsRoleService.listAllRole());

			// å‡†å¤‡è¯¥ç”¨æˆ·æ‹¥æœ‰çš„è§’è‰²IDå­—ç¬¦ä¸²
			List<PmsOperatorRole> lisPmsOperatorRoles = pmsOperatorRoleService.listOperatorRoleByOperatorId(id);
			StringBuffer owenedRoleIdBuffer = new StringBuffer("");
			for (PmsOperatorRole pmsOperatorRole : lisPmsOperatorRoles) {
				owenedRoleIdBuffer.append(pmsOperatorRole.getRoleId());
				owenedRoleIdBuffer.append(",");
			}
			String owenedRoleIds = owenedRoleIdBuffer.toString();
			if (StringUtils.isNotBlank(owenedRoleIds) && owenedRoleIds.length() > 0) {
				owenedRoleIds = owenedRoleIds.substring(0, owenedRoleIds.length() - 1);
			}
			model.addAttribute("owenedRoleIds", owenedRoleIds);

			model.addAttribute("OperatorStatusEnum", PublicStatusEnum.toMap());
			model.addAttribute("OperatorTypeEnum", OperatorTypeEnum.toMap());
			model.addAttribute("pmsOperator", pmsOperator);
			return "pms/pmsOperatorEdit";
		} catch (Exception e) {
			log.error("== editPmsOperatorUI exception:", e);
			return operateError("èŽ·å?–ä¿®æ”¹æ•°æ?®å¤±è´¥", model);
		}
	}

	/**
	 * ä¿?å­˜ä¿®æ”¹å?Žçš„æ“?ä½œå‘˜ä¿¡æ?¯
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:operator:edit")
	@RequestMapping("/edit")
	public String editPmsOperator(HttpServletRequest req, PmsOperator operator, String selectVal, Model model, DwzAjax dwz) {
		try {
			Long id = operator.getId();

			PmsOperator pmsOperator = pmsOperatorService.getDataById(id);
			if (pmsOperator == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?ä¿®æ”¹çš„æ“?ä½œå‘˜ä¿¡æ?¯", model);
			}

			// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
			if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(pmsOperator.getType())) {
				return operateError("æ?ƒé™?ä¸?è¶³", model);
			}

			pmsOperator.setRemark(operator.getRemark());
			pmsOperator.setMobileNo(operator.getMobileNo());
			pmsOperator.setRealName(operator.getRealName());
			// ä¿®æ”¹æ—¶ä¸?èƒ½ä¿®çŠ¶æ€?
			// pmsOperator.setStatus(getInteger("status"));

			String roleOperatorStr = getRoleOperatorStr(selectVal);

			// è¡¨å?•æ•°æ?®æ ¡éªŒ
			String validateMsg = validatePmsOperator(pmsOperator, roleOperatorStr);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
			}

			pmsOperatorService.updateOperator(pmsOperator, roleOperatorStr);
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			log.error("== editPmsOperator exception:", e);
			return operateError("æ›´æ–°æ“?ä½œå‘˜ä¿¡æ?¯å¤±è´¥", model);
		}
	}

	/**
	 * æ ¹æ?®IDå†»ç»“æˆ–æ¿€æ´»æ“?ä½œå‘˜.
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:operator:changestatus")
	@RequestMapping("/changeStatus")
	public String changeOperatorStatus(HttpServletRequest req, PmsOperator operator, Model model, DwzAjax dwz) {
		try {
			Long operatorId = operator.getId();
			PmsOperator pmsOperator = pmsOperatorService.getDataById(operatorId);
			if (pmsOperator == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?æ“?ä½œçš„æ•°æ?®", model);
			}

			if (this.getPmsOperator().getId() == operatorId) {
				return operateError("ä¸?èƒ½ä¿®æ”¹è‡ªå·±è´¦æˆ·çš„çŠ¶æ€?", model);
			}

			// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
			if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(pmsOperator.getType())) {
				return operateError("æ?ƒé™?ä¸?è¶³", model);
			}

			// 2014-01-02,ç”±åˆ é™¤æ”¹ä¸ºä¿®æ”¹çŠ¶æ€?
			// pmsPermissionBiz.deleteOperator(id);
			// æ¿€æ´»çš„å?˜å†»ç»“ï¼Œå†»ç»“çš„åˆ™å?˜æ¿€æ´»
			if (pmsOperator.getStatus().equals(PublicStatusEnum.ACTIVE.name())) {
				if ("ADMIN".equals(pmsOperator.getType())) {
					return operateError("ã€?" + pmsOperator.getLoginName() + "ã€‘ä¸ºè¶…çº§ç®¡ç?†å‘˜ï¼Œä¸?èƒ½å†»ç»“", model);
				}
				pmsOperator.setStatus(PublicStatusEnum.UNACTIVE.name());
				pmsOperatorService.updateData(pmsOperator);
			} else {
				pmsOperator.setStatus(PublicStatusEnum.ACTIVE.name());
				pmsOperatorService.updateData(pmsOperator);
			}
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			log.error("== changeOperatorStatus exception:", e);
			return operateError("åˆ é™¤æ“?ä½œå‘˜å¤±è´¥:" + e.getMessage(), model);
		}
	}

	/***
	 * é‡?ç½®æ“?ä½œå‘˜çš„å¯†ç ?ï¼ˆæ³¨æ„?ï¼šä¸?æ˜¯ä¿®æ”¹å½“å‰?ç™»å½•æ“?ä½œå‘˜è‡ªå·±çš„å¯†ç ?ï¼‰ .
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:operator:resetpwd")
	@RequestMapping("/resetPwdUI")
	public String resetOperatorPwdUI(HttpServletRequest req, Long id, Model model) {
		PmsOperator operator = pmsOperatorService.getDataById(id);
		if (operator == null) {
			return operateError("æ— æ³•èŽ·å?–è¦?é‡?ç½®çš„ä¿¡æ?¯", model);
		}

		// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
		if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(operator.getType())) {
			return operateError("æ?ƒé™?ä¸?è¶³", model);
		}

		model.addAttribute("operator", operator);

		return "pms/pmsOperatorResetPwd";
	}

	/**
	 * é‡?ç½®æ“?ä½œå‘˜å¯†ç ?.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:operator:resetpwd")
	@RequestMapping("/resetPwd")
	public String resetOperatorPwd(HttpServletRequest req, Long id, String newPwd, String newPwd2, Model model, DwzAjax dwz) {
		try {
			PmsOperator operator = pmsOperatorService.getDataById(id);
			if (operator == null) {
				return operateError("æ— æ³•èŽ·å?–è¦?é‡?ç½®å¯†ç ?çš„æ“?ä½œå‘˜ä¿¡æ?¯", model);
			}

			// æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜çš„æ?ƒé™?
			if ("USER".equals(this.getPmsOperator().getType()) && "ADMIN".equals(operator.getType())) {
				return operateError("æ?ƒé™?ä¸?è¶³", model);
			}


			String validateMsg = validatePassword(newPwd, newPwd2);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
			}
			operator.setLoginPwd(newPwd);
			PasswordHelper.encryptPassword(operator);
			pmsOperatorService.updateData(operator);
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			log.error("== resetOperatorPwd exception:", e);
			return operateError("å¯†ç ?é‡?ç½®å‡ºé”™:" + e.getMessage(), model);
		}
	}

	/**
	 * å¾—åˆ°è§’è‰²å’Œæ“?ä½œå‘˜å…³è?”çš„IDå­—ç¬¦ä¸²
	 * 
	 * @return
	 */
	private String getRoleOperatorStr(String selectVal) throws Exception {
		String roleStr = selectVal;
		if (StringUtils.isNotBlank(roleStr) && roleStr.length() > 0) {
			roleStr = roleStr.substring(0, roleStr.length() - 1);
		}
		return roleStr;
	}

	/***
	 * éªŒè¯?é‡?ç½®å¯†ç ?
	 * 
	 * @param newPwd
	 * @param newPwd2
	 * @return
	 */
	private String validatePassword(String newPwd, String newPwd2) {
		String msg = ""; // ç”¨äºŽå­˜æ”¾æ ¡éªŒæ??ç¤ºä¿¡æ?¯çš„å?˜é‡?
		if (StringUtils.isBlank(newPwd)) {
			msg += "æ–°å¯†ç ?ä¸?èƒ½ä¸ºç©ºï¼Œ";
		} else if (newPwd.length() < 6) {
			msg += "æ–°å¯†ç ?ä¸?èƒ½å°‘äºŽ6ä½?é•¿åº¦ï¼Œ";
		}

		if (!newPwd.equals(newPwd2)) {
			msg += "ä¸¤æ¬¡è¾“å…¥çš„å¯†ç ?ä¸?ä¸€è‡´";
		}
		return msg;
	}
}
