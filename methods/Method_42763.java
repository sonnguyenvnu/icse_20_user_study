/** 
 * ??????
 */
@RequiresPermissions("pms:role:assignpermission") @RequestMapping("/assignPermission") public String assignPermission(HttpServletRequest req,Model model,@RequestParam("roleId") Long roleId,DwzAjax dwz,@RequestParam("selectVal") String selectVal){
  try {
    String rolePermissionStr=getRolePermissionStr(selectVal);
    pmsRolePermissionService.saveRolePermission(roleId,rolePermissionStr);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== assignPermission exception:",e);
    return operateError("????",model);
  }
}
