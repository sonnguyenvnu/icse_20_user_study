/** 
 * ????????
 * @return operateSuccess or operateError .
 */
@RequiresPermissions("pms:permission:delete") @RequestMapping("/delete") public String deletePmsPermission(HttpServletRequest req,Long permissionId,Model model,DwzAjax dwz){
  try {
    PmsPermission permission=pmsPermissionService.getDataById(permissionId);
    if (permission == null) {
      return operateError("??????????",model);
    }
    List<PmsRole> roleList=pmsRoleService.listByPermissionId(permissionId);
    if (roleList != null && !roleList.isEmpty()) {
      return operateError("???" + permission.getPermission() + "?????" + roleList.size() + "??????????????????????????:" + roleList.get(0).getRoleName(),model);
    }
    pmsPermissionService.delete(permissionId);
    return operateSuccess(model,dwz);
  }
 catch (  Exception e) {
    log.error("== deletePmsPermission exception:",e);
    return operateError("??????",model);
  }
}
