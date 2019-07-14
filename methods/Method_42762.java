/** 
 * ?????????? .
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:role:edit") @RequestMapping("/edit") public String editPmsRole(HttpServletRequest req,Model model,PmsRole role,DwzAjax dwz){
  try {
    Long id=role.getId();
    PmsRole pmsRole=pmsRoleService.getDataById(id);
    if (pmsRole == null) {
      return operateError("??????????",model);
    }
    PmsRole roleNameCheck=pmsRoleService.getByRoleNameOrRoleCode(role.getRoleName(),null);
    if (roleNameCheck != null && !roleNameCheck.getId().equals(id)) {
      return operateError("????" + role.getRoleName() + "????",model);
    }
    PmsRole roleCodeCheck=pmsRoleService.getByRoleNameOrRoleCode(null,role.getRoleCode());
    if (roleCodeCheck != null && !roleCodeCheck.getId().equals(id)) {
      return operateError("?????" + role.getRoleCode() + "????",model);
    }
    pmsRole.setRoleName(role.getRoleName());
    pmsRole.setRoleCode(role.getRoleCode());
    pmsRole.setRemark(role.getRemark());
    String validateMsg=validatePmsRole(pmsRole);
    if (StringUtils.isNotBlank(validateMsg)) {
      return operateError(validateMsg,model);
    }
    pmsRoleService.updateData(pmsRole);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== editPmsRole exception:",e);
    return operateError("????",model);
  }
}
