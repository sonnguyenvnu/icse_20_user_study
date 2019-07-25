/** 
 * ???????? operation 
 */
@RequestMapping(params="delrule") @ResponseBody public AjaxJson delrule(TSInterfaceDdataRuleEntity operation,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  operation=systemService.getEntity(TSInterfaceDdataRuleEntity.class,operation.getId());
  message=MutiLangUtil.paramDelSuccess("common.operation");
  userService.delete(operation);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  j.setMsg(message);
  return j;
}
