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
import com.roncoo.pay.common.core.page.PageBean;
import com.roncoo.pay.common.core.page.PageParam;
import com.roncoo.pay.controller.common.BaseController;
import com.roncoo.pay.permission.entity.PmsOperator;
import com.roncoo.pay.permission.entity.PmsPermission;
import com.roncoo.pay.permission.entity.PmsRole;
import com.roncoo.pay.permission.enums.OperatorTypeEnum;
import com.roncoo.pay.permission.service.*;
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

/**
 * æ?ƒé™?ç®¡ç?†æ¨¡å?—è§’è‰²ç®¡ç?†ã€?.<br/>
 * <p>
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 *
 * @authorï¼šshenjialong
 */
@Controller
@RequestMapping("/pms/role")
public class PmsRoleController extends BaseController {

    @Autowired
    private PmsRoleService pmsRoleService;
    @Autowired
    private PmsMenuService pmsMenuService;
    @Autowired
    private PmsMenuRoleService pmsMenuRoleService;
    @Autowired
    private PmsPermissionService pmsPermissionService;
    @Autowired
    private PmsRolePermissionService pmsRolePermissionService;
    @Autowired
    private PmsOperatorRoleService pmsOperatorRoleService;

    private static Log log = LogFactory.getLog(PmsRoleController.class);

    /**
     * èŽ·å?–è§’è‰²åˆ—è¡¨
     *
     * @return listPmsRole or operateError .
     */
    @RequiresPermissions("pms:role:view")
    @RequestMapping("/list")
    public String listPmsRole(HttpServletRequest req, PageParam pageParam, PmsRole pmsRole, Model model) {
        try {
            PageBean pageBean = pmsRoleService.listPage(pageParam, pmsRole);
            model.addAttribute(pageBean);
            model.addAttribute("pageParam", pageParam);
            model.addAttribute("pmsRole", pmsRole);
            return "pms/pmsRoleList";
        } catch (Exception e) {
            log.error("== listPmsRole exception:", e);
            return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
        }
    }

    /**
     * è½¬åˆ°æ·»åŠ è§’è‰²é¡µé?¢ .
     *
     * @return addPmsRoleUI or operateError .
     */
    @RequiresPermissions("pms:role:add")
    @RequestMapping("/addUI")
    public String addPmsRoleUI(HttpServletRequest req, Model model) {
        try {
            return "pms/pmsRoleAdd";
        } catch (Exception e) {
            log.error("== addPmsRoleUI get data exception:", e);
            return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
        }
    }

    /**
     * ä¿?å­˜æ–°æ·»åŠ çš„ä¸€ä¸ªè§’è‰² .
     *
     * @return operateSuccess or operateError .
     */
    @RequiresPermissions("pms:role:add")
    @RequestMapping("/add")
    public String addPmsRole(HttpServletRequest req, Model model, @RequestParam("roleCode") String roleCode, @RequestParam("roleName") String roleName, @RequestParam("remark") String remark, DwzAjax dwz) {
        try {
            PmsRole roleNameCheck = pmsRoleService.getByRoleNameOrRoleCode(roleName, null);
            if (roleNameCheck != null) {
                return operateError("è§’è‰²å??ã€?" + roleName + "ã€‘å·²å­˜åœ¨", model);
            }

            PmsRole roleCodeCheck = pmsRoleService.getByRoleNameOrRoleCode(null, roleCode);
            if (roleCodeCheck != null) {
                return operateError("è§’è‰²ç¼–ç ?ã€?" + roleCode + "ã€‘å·²å­˜åœ¨", model);
            }

            // ä¿?å­˜åŸºæœ¬è§’è‰²ä¿¡æ?¯
            PmsRole pmsRole = new PmsRole();
            pmsRole.setRoleCode(roleCode);
            pmsRole.setRoleName(roleName);
            pmsRole.setRemark(remark);
            pmsRole.setCreateTime(new Date());

            // è¡¨å?•æ•°æ?®æ ¡éªŒ
            String validateMsg = validatePmsRole(pmsRole);
            if (StringUtils.isNotBlank(validateMsg)) {
                return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
            }
            pmsRoleService.saveData(pmsRole);
            return operateSuccess(model, dwz);
        } catch (Exception e) {
            log.error("== addPmsRole exception:", e);
            return operateError("ä¿?å­˜æ•°æ?®å¤±è´¥", model);
        }
    }

    /**
     * æ ¡éªŒè§’è‰²è¡¨å?•æ•°æ?®.
     *
     * @param pmsRole è§’è‰²ä¿¡æ?¯.
     * @return msg .
     */
    private String validatePmsRole(PmsRole pmsRole) {
        String msg = ""; // ç”¨äºŽå­˜æ”¾æ ¡éªŒæ??ç¤ºä¿¡æ?¯çš„å?˜é‡?
        String roleName = pmsRole.getRoleName(); // è§’è‰²å??ç§°
        String desc = pmsRole.getRemark(); // æ??è¿°
        // è§’è‰²å??ç§° permissionName
        msg += ValidateUtils.lengthValidate("è§’è‰²å??ç§°", roleName, true, 3, 90);
        // æ??è¿° desc
        msg += ValidateUtils.lengthValidate("æ??è¿°", desc, true, 3, 300);
        return msg;
    }

    /**
     * è½¬åˆ°è§’è‰²ä¿®æ”¹é¡µé?¢ .
     *
     * @return editPmsRoleUI or operateError .
     */
    @RequiresPermissions("pms:role:edit")
    @RequestMapping("/editUI")
    public String editPmsRoleUI(HttpServletRequest req, Model model, Long roleId) {
        try {
            PmsRole pmsRole = pmsRoleService.getDataById(roleId);
            if (pmsRole == null) {
                return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
            }

            model.addAttribute(pmsRole);
            return "/pms/pmsRoleEdit";
        } catch (Exception e) {
            log.error("== editPmsRoleUI exception:", e);
            return operateError("èŽ·å?–æ•°æ?®å¤±è´¥", model);
        }
    }

    /**
     * ä¿?å­˜ä¿®æ”¹å?Žçš„è§’è‰²ä¿¡æ?¯ .
     *
     * @return operateSuccess or operateError .
     */
    @RequiresPermissions("pms:role:edit")
    @RequestMapping("/edit")
    public String editPmsRole(HttpServletRequest req, Model model, PmsRole role, DwzAjax dwz) {
        try {
            Long id = role.getId();

            PmsRole pmsRole = pmsRoleService.getDataById(id);
            if (pmsRole == null) {
                return operateError("æ— æ³•èŽ·å?–è¦?ä¿®æ”¹çš„æ•°æ?®", model);
            }

            PmsRole roleNameCheck = pmsRoleService.getByRoleNameOrRoleCode(role.getRoleName(), null);
            if (roleNameCheck != null && !roleNameCheck.getId().equals(id)) {
                return operateError("è§’è‰²å??ã€?" + role.getRoleName() + "ã€‘å·²å­˜åœ¨", model);
            }

            PmsRole roleCodeCheck = pmsRoleService.getByRoleNameOrRoleCode(null, role.getRoleCode());
            if (roleCodeCheck != null && !roleCodeCheck.getId().equals(id)) {
                return operateError("è§’è‰²ç¼–ç ?ã€?" + role.getRoleCode() + "ã€‘å·²å­˜åœ¨", model);
            }

            pmsRole.setRoleName(role.getRoleName());
            pmsRole.setRoleCode(role.getRoleCode());
            pmsRole.setRemark(role.getRemark());

            // è¡¨å?•æ•°æ?®æ ¡éªŒ
            String validateMsg = validatePmsRole(pmsRole);
            if (StringUtils.isNotBlank(validateMsg)) {
                return operateError(validateMsg, model); // è¿”å›žé”™è¯¯ä¿¡æ?¯
            }
            pmsRoleService.updateData(pmsRole);
            return operateSuccess(model, dwz);
        } catch (Exception e) {
            log.error("== editPmsRole exception:", e);
            return operateError("ä¿?å­˜å¤±è´¥", model);
        }
    }

    /**
     * åˆ é™¤ä¸€ä¸ªè§’è‰²
     *
     * @return operateSuccess or operateError .
     */
    @RequiresPermissions("pms:role:delete")
    @RequestMapping("/delete")
    public String deletePmsRole(HttpServletRequest req, Model model, Long roleId, DwzAjax dwz) {
        try {

            PmsRole role = pmsRoleService.getDataById(roleId);
            if (role == null) {
                return operateError("æ— æ³•èŽ·å?–è¦?åˆ é™¤çš„è§’è‰²", model);
            }
            String msg = "";
            // åˆ¤æ–­æ˜¯å?¦æœ‰æ“?ä½œå‘˜å…³è?”åˆ°æ­¤è§’è‰²
            int operatorCount = pmsOperatorRoleService.countOperatorByRoleId(roleId);
            if (operatorCount > 0) {
                msg += "æœ‰ã€?" + operatorCount + "ã€‘ä¸ªæ“?ä½œå‘˜å…³è?”åˆ°æ­¤è§’è‰²ï¼Œè¦?å…ˆè§£é™¤æ‰€æœ‰å…³è?”å?Žæ‰?èƒ½åˆ é™¤!";
                return operateError(msg, model);
            }

            pmsRoleService.delete(roleId);
            return operateSuccess(model, dwz);
        } catch (Exception e) {
            log.error("== deletePmsRole exception:", e);
            return operateError("åˆ é™¤å¤±è´¥", model);
        }
    }

    /**
     * åˆ†é…?æ?ƒé™?UI
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("pms:role:assignpermission")
    @RequestMapping("/assignPermissionUI")
    public String assignPermissionUI(HttpServletRequest req, Model model, Long roleId) {

        PmsRole role = pmsRoleService.getDataById(roleId);
        if (role == null) {
            return operateError("æ— æ³•èŽ·å?–è§’è‰²ä¿¡æ?¯", model);
        }
        // æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜è§’è‰²çš„æ?ƒé™?
        if (OperatorTypeEnum.USER.name().equals(this.getPmsOperator().getType()) && "admin".equals(role.getRoleName())) {
            return operateError("æ?ƒé™?ä¸?è¶³", model);
        }

        String permissionIds = pmsPermissionService.getPermissionIdsByRoleId(roleId); // æ ¹æ?®è§’è‰²æŸ¥æ‰¾è§’è‰²å¯¹åº”çš„åŠŸèƒ½æ?ƒé™?IDé›†
        List<PmsPermission> permissionList = pmsPermissionService.listAll();
        List<PmsOperator> operatorList = pmsOperatorRoleService.listOperatorByRoleId(roleId);

        model.addAttribute("permissionIds", permissionIds);
        model.addAttribute("permissionList", permissionList);
        model.addAttribute("operatorList", operatorList);
        model.addAttribute("role", role);
        return "/pms/assignPermissionUI";
    }

    /**
     * åˆ†é…?è§’è‰²æ?ƒé™?
     */
    @RequiresPermissions("pms:role:assignpermission")
    @RequestMapping("/assignPermission")
    public String assignPermission(HttpServletRequest req, Model model, @RequestParam("roleId") Long roleId, DwzAjax dwz, @RequestParam("selectVal") String selectVal) {
        try {
            String rolePermissionStr = getRolePermissionStr(selectVal);
            pmsRolePermissionService.saveRolePermission(roleId, rolePermissionStr);
            return operateSuccess(model, dwz);
        } catch (Exception e) {
            log.error("== assignPermission exception:", e);
            return operateError("ä¿?å­˜å¤±è´¥", model);
        }
    }

    /**
     * åˆ†é…?è?œå?•UI
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/assignMenuUI")
    public String assignMenuUI(HttpServletRequest req, Model model, Long roleId) {
        PmsRole role = pmsRoleService.getDataById(roleId);
        if (role == null) {
            return operateError("æ— æ³•èŽ·å?–è§’è‰²ä¿¡æ?¯", model);
        }
        // æ™®é€šæ“?ä½œå‘˜æ²¡æœ‰ä¿®æ”¹è¶…çº§ç®¡ç?†å‘˜è§’è‰²çš„æ?ƒé™?
        if (OperatorTypeEnum.USER.name().equals(this.getPmsOperator().getType()) && "admin".equals(role.getRoleName())) {
            return operateError("æ?ƒé™?ä¸?è¶³", model);
        }

        String menuIds = pmsMenuService.getMenuIdsByRoleId(roleId); // æ ¹æ?®è§’è‰²æŸ¥æ‰¾è§’è‰²å¯¹åº”çš„è?œå?•IDé›†
        List menuList = pmsMenuService.getListByParent(null);
        List<PmsOperator> operatorList = pmsOperatorRoleService.listOperatorByRoleId(roleId);

        model.addAttribute("menuIds", menuIds);
        model.addAttribute("menuList", menuList);
        model.addAttribute("operatorList", operatorList);
        model.addAttribute("role", role);
        return "/pms/assignMenuUI";
    }

    /**
     * åˆ†é…?è§’è‰²è?œå?•
     */
    @RequestMapping("/assignMenu")
    public String assignMenu(HttpServletRequest req, Model model, @RequestParam("roleId") Long roleId, DwzAjax dwz, @RequestParam("selectVal") String selectVal) {
        try {
            String roleMenuStr = getRolePermissionStr(selectVal);
            pmsMenuRoleService.saveRoleMenu(roleId, roleMenuStr);
            return operateSuccess(model, dwz);
        } catch (Exception e) {
            log.error("== assignPermission exception:", e);
            return operateError("ä¿?å­˜å¤±è´¥", model);
        }
    }

    /**
     * å¾—åˆ°è§’è‰²å’Œæ?ƒé™?å…³è?”çš„IDå­—ç¬¦ä¸²
     *
     * @return
     */
    private String getRolePermissionStr(String selectVal) throws Exception {
        String roleStr = selectVal;
        if (StringUtils.isNotBlank(roleStr) && roleStr.length() > 0) {
            roleStr = roleStr.substring(0, roleStr.length() - 1);
        }
        return roleStr;
    }
}
