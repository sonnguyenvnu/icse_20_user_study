/** 
 * ?????????? .
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:role:add") @RequestMapping("/add") public String addPmsRole(HttpServletRequest req,Model model,@RequestParam("roleCode") String roleCode,@RequestParam("roleName") String roleName,@RequestParam("remark") String remark,DwzAjax dwz){
  try {
    PmsRole roleNameCheck=pmsRoleService.getByRoleNameOrRoleCode(roleName,null);
    if (roleNameCheck != null) {
      return operateError("????" + roleName + "????",model);
    }
    PmsRole roleCodeCheck=pmsRoleService.getByRoleNameOrRoleCode(null,roleCode);
    if (roleCodeCheck != null) {
      return operateError("?????" + roleCode + "????",model);
    }
    PmsRole pmsRole=new PmsRole();
    pmsRole.setRoleCode(roleCode);
    pmsRole.setRoleName(roleName);
    pmsRole.setRemark(remark);
    pmsRole.setCreateTime(new Date());
    String validateMsg=validatePmsRole(pmsRole);
    if (StringUtils.isNotBlank(validateMsg)) {
      return operateError(validateMsg,model);
    }
    pmsRoleService.saveData(pmsRole);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== addPmsRole exception:",e);
    return operateError("??????",model);
  }
}
