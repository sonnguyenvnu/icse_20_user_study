/** 
 * ?????ID???????????
 * @param operatorId
 */
public Set<String> getPermissionsByOperatorId(Long operatorId){
  String roleIds=pmsOperatorRoleService.getRoleIdsByOperatorId(operatorId);
  String permissionIds=getActionIdsByRoleIds(roleIds);
  Set<String> permissionSet=new HashSet<String>();
  if (!StringUtils.isEmpty(permissionIds)) {
    List<PmsPermission> permissions=pmsPermissionDao.findByIds(permissionIds);
    for (    PmsPermission permission : permissions) {
      permissionSet.add(permission.getPermission());
    }
  }
  return permissionSet;
}
