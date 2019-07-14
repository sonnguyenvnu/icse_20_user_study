/** 
 * ????ID????????????.
 * @param roleId ??ID.
 * @return count.
 */
public int countMenuByRoleId(Long roleId){
  List<PmsMenuRole> meunList=pmsMenuRoleDao.listByRoleId(roleId);
  if (meunList == null || meunList.isEmpty()) {
    return 0;
  }
 else {
    return meunList.size();
  }
}
