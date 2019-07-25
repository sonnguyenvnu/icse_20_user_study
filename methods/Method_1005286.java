/** 
 * ????
 * @param roleId
 */
public void clearp(String roleId){
  List<TSRoleFunction> rFunctions=systemService.findByProperty(TSRoleFunction.class,"TSRole.id",roleId);
  if (rFunctions.size() > 0) {
    for (    TSRoleFunction tRoleFunction : rFunctions) {
      tRoleFunction.setOperation(null);
      systemService.saveOrUpdate(tRoleFunction);
    }
  }
}
