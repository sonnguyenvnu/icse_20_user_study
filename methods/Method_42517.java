/** 
 * ?????????????ID?
 * @param roleId
 * @return
 */
public String getMenuIdsByRoleId(Long roleId){
  List<PmsMenuRole> menuList=pmsMenuRoleDao.listByRoleId(roleId);
  StringBuffer menuIds=new StringBuffer("");
  if (menuList != null && !menuList.isEmpty()) {
    for (    PmsMenuRole rm : menuList) {
      menuIds.append(rm.getMenuId()).append(",");
    }
  }
  return menuIds.toString();
}
