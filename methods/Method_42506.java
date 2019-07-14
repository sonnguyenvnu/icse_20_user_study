/** 
 * ????ID??????????.
 * @param roleId .
 * @return rolePermissionList .
 */
public List<PmsRolePermission> listByRoleId(final long roleId){
  return super.getSessionTemplate().selectList(getStatement("listByRoleId"),roleId);
}
