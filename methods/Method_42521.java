/** 
 * ????ID???????????????.
 * @param roleId .
 * @return count.
 */
public int countOperatorByRoleId(Long roleId){
  List<PmsOperatorRole> operatorList=pmsOperatorRoleDao.listByRoleId(roleId);
  if (operatorList == null || operatorList.isEmpty()) {
    return 0;
  }
 else {
    return operatorList.size();
  }
}
