/** 
 * ????ID????????????.
 * @param roleId ??ID.
 * @return count.
 */
@Override public List<PmsMenuRole> listByRoleId(Long roleId){
  return super.getSessionTemplate().selectList(getStatement("listByRoleId"),roleId);
}
