/** 
 * ????id?????
 * @param roleIdsStr
 * @return
 */
@SuppressWarnings("rawtypes") public List listByRoleIds(String roleIdsStr){
  return this.pmsMenuDao.listByRoleIds(roleIdsStr);
}
