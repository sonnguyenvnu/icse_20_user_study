/** 
 * ??????????????
 */
private void saveOrUpdateOperatorRole(Long operatorId,String roleIdsStr){
  List<PmsOperatorRole> listPmsOperatorRoles=pmsOperatorRoleDao.listByOperatorId(operatorId);
  Map<Long,PmsOperatorRole> delMap=new HashMap<Long,PmsOperatorRole>();
  for (  PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
    delMap.put(pmsOperatorRole.getRoleId(),pmsOperatorRole);
  }
  if (StringUtils.isNotBlank(roleIdsStr)) {
    String[] roleIds=roleIdsStr.split(",");
    for (int i=0; i < roleIds.length; i++) {
      Long roleId=Long.valueOf(roleIds[i]);
      if (delMap.get(roleId) == null) {
        PmsOperatorRole pmsOperatorRole=new PmsOperatorRole();
        pmsOperatorRole.setOperatorId(operatorId);
        pmsOperatorRole.setRoleId(roleId);
        pmsOperatorRoleDao.insert(pmsOperatorRole);
      }
 else {
        delMap.remove(roleId);
      }
    }
  }
  Iterator<Long> iterator=delMap.keySet().iterator();
  while (iterator.hasNext()) {
    Long roleId=iterator.next();
    pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId,operatorId);
  }
}
