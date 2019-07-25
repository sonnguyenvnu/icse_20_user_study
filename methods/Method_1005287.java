/** 
 * ????
 * @param roleid
 */
public void clearp(String roleid){
  String hql="from TRoleFunction t where" + " t.TSRole.id=" + roleid;
  List<TSRoleFunction> rFunctions=systemService.findByQueryString(hql);
  if (rFunctions.size() > 0) {
    for (    TSRoleFunction tRoleFunction : rFunctions) {
      tRoleFunction.setOperation(null);
      systemService.saveOrUpdate(tRoleFunction);
    }
  }
}
