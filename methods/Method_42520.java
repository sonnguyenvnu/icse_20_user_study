/** 
 * ?????id????????????????
 * @param operatorId
 * @return
 */
public Set<String> getRoleCodeByOperatorId(Long operatorId){
  List<PmsOperatorRole> rpList=pmsOperatorRoleDao.listByOperatorId(operatorId);
  Set<String> roleCodeSet=new HashSet<String>();
  for (  PmsOperatorRole rp : rpList) {
    Long roleId=rp.getRoleId();
    PmsRole role=pmsRoleDao.getById(roleId);
    if (role == null) {
      continue;
    }
    roleCodeSet.add(role.getRoleCode());
  }
  return roleCodeSet;
}
