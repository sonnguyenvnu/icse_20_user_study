/** 
 * ???? ?????????????????  ????????oeration ???
 * @param operation
 * @return
 */
@RequestMapping(params="delop") @ResponseBody public AjaxJson delop(TSOperation operation,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  operation=systemService.getEntity(TSOperation.class,operation.getId());
  message=MutiLangUtil.paramDelSuccess("common.operation");
  userService.delete(operation);
  String operationId=operation.getId();
  String hql="from TSRoleFunction rolefun where rolefun.operation like '%" + operationId + "%'";
  List<TSRoleFunction> roleFunctions=userService.findByQueryString(hql);
  for (  TSRoleFunction roleFunction : roleFunctions) {
    String newOper=roleFunction.getOperation().replace(operationId + ",","");
    if (roleFunction.getOperation().length() == newOper.length()) {
      newOper=roleFunction.getOperation().replace(operationId,"");
    }
    roleFunction.setOperation(newOper);
    userService.updateEntitie(roleFunction);
  }
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  j.setMsg(message);
  return j;
}
