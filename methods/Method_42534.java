/** 
 * ??????????????
 */
@Transactional(rollbackFor=Exception.class) public void saveRolePermission(Long roleId,String rolePermissionStr){
  pmsRolePermissionDao.deleteByRoleId(roleId);
  if (!StringUtils.isEmpty(rolePermissionStr)) {
    String[] permissionIds=rolePermissionStr.split(",");
    for (int i=0; i < permissionIds.length; i++) {
      Long permissionId=Long.valueOf(permissionIds[i]);
      PmsRolePermission item=new PmsRolePermission();
      item.setPermissionId(permissionId);
      item.setRoleId(roleId);
      pmsRolePermissionDao.insert(item);
    }
  }
}
