/** 
 * ??????????
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:permission:edit") @RequestMapping("/edit") public String editPmsPermission(HttpServletRequest req,PmsPermission permission,Model model,DwzAjax dwz){
  try {
    Long id=permission.getId();
    PmsPermission pmsPermission=pmsPermissionService.getDataById(id);
    if (pmsPermission == null) {
      return operateError("??????????",model);
    }
 else {
      String permissionName=permission.getPermissionName();
      String remark=permission.getRemark();
      pmsPermission.setPermissionName(permissionName);
      pmsPermission.setRemark(remark);
      String validateMsg=validatePmsPermission(pmsPermission);
      if (StringUtils.isNotBlank(validateMsg)) {
        return operateError(validateMsg,model);
      }
      PmsPermission checkName=pmsPermissionService.getByPermissionNameNotEqId(permissionName,id);
      if (checkName != null) {
        return operateError("?????" + permissionName + "????",model);
      }
      pmsPermissionService.updateData(pmsPermission);
      return operateSuccess(model,dwz);
    }
  }
 catch (  Exception e) {
    log.error("== editPmsPermission exception:",e);
    return operateError("????",model);
  }
}
