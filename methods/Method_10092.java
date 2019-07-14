/** 
 * Updates role permissions.
 * @param roleId the specified role id
 */
@Transactional public void updateRolePermissions(final String roleId,final Set<String> permissionIds){
  try {
    rolePermissionRepository.removeByRoleId(roleId);
    for (    final String permissionId : permissionIds) {
      final JSONObject rel=new JSONObject();
      rel.put(Role.ROLE_ID,roleId);
      rel.put(Permission.PERMISSION_ID,permissionId);
      rolePermissionRepository.add(rel);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Updates role permissions failed",e);
  }
}
