/** 
 * ????ID?????????-??????.
 * @param roleIds
 * @return
 */
public List<PmsRolePermission> listByRoleIds(String roleIdsStr){
  List<String> roldIds=Arrays.asList(roleIdsStr.split(","));
  return super.getSessionTemplate().selectList(getStatement("listByRoleIds"),roldIds);
}
