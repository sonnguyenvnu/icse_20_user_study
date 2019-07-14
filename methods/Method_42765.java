/** 
 * ??????
 */
@RequestMapping("/assignMenu") public String assignMenu(HttpServletRequest req,Model model,@RequestParam("roleId") Long roleId,DwzAjax dwz,@RequestParam("selectVal") String selectVal){
  try {
    String roleMenuStr=getRolePermissionStr(selectVal);
    pmsMenuRoleService.saveRoleMenu(roleId,roleMenuStr);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== assignPermission exception:",e);
    return operateError("????",model);
  }
}
