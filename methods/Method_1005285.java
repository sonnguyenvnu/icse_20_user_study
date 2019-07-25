/** 
 * ????
 * @param roleId
 * @param functionid
 * @param ids
 */
public void savep(String roleId,String functionid,String ids){
  CriteriaQuery cq=new CriteriaQuery(TSRoleFunction.class);
  cq.eq("TSRole.id",roleId);
  cq.eq("TSFunction.id",functionid);
  cq.add();
  List<TSRoleFunction> rFunctions=systemService.getListByCriteriaQuery(cq,false);
  if (rFunctions.size() > 0) {
    TSRoleFunction roleFunction=rFunctions.get(0);
    roleFunction.setOperation(ids);
    systemService.saveOrUpdate(roleFunction);
  }
}
