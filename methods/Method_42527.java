/** 
 * ??????????????
 */
private void saveOrUpdateOperatorRole(PmsOperator pmsOperator,String roleIdsStr){
  List<PmsOperatorRole> listPmsOperatorRoles=pmsOperatorRoleDao.listByOperatorId(pmsOperator.getId());
  Map<Long,PmsOperatorRole> delMap=new HashMap<Long,PmsOperatorRole>();
  for (  PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
    delMap.put(pmsOperatorRole.getRoleId(),pmsOperatorRole);
  }
  if (StringUtils.isNotBlank(roleIdsStr)) {
    String[] roleIds=roleIdsStr.split(",");
    for (int i=0; i < roleIds.length; i++) {
      long roleId=Long.parseLong(roleIds[i]);
      if (delMap.get(roleId) == null) {
        PmsOperatorRole pmsOperatorRole=new PmsOperatorRole();
        pmsOperatorRole.setOperatorId(pmsOperator.getId());
        pmsOperatorRole.setRoleId(roleId);
        pmsOperatorRole.setCreater(pmsOperator.getCreater());
        pmsOperatorRole.setCreateTime(new Date());
        pmsOperatorRole.setStatus(PublicStatusEnum.ACTIVE.name());
        pmsOperatorRoleDao.insert(pmsOperatorRole);
      }
 else {
        delMap.remove(roleId);
      }
    }
  }
  Iterator<Long> iterator=delMap.keySet().iterator();
  while (iterator.hasNext()) {
    long roleId=iterator.next();
    pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId,pmsOperator.getId());
  }
}
