/** 
 * ????????????
 * @param permissionId
 * @return
 */
public List<PmsRole> listByPermissionId(Long permissionId){
  return super.getSessionTemplate().selectList(getStatement("listByPermissionId"),permissionId);
}
