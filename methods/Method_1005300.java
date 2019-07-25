/** 
 * ????
 * @param operation
 * @return
 */
@RequestMapping(params="saveop") @ResponseBody public AjaxJson saveop(TSOperation operation,HttpServletRequest request){
  String message=null;
  String pid=request.getParameter("TSFunction.id");
  if (pid.equals("")) {
    operation.setTSFunction(null);
  }
  AjaxJson j=new AjaxJson();
  if (StringUtil.isNotEmpty(operation.getId())) {
    message=MutiLangUtil.paramUpdSuccess("common.operation");
    userService.saveOrUpdate(operation);
    systemService.addLog(message,Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
  }
 else {
    message=MutiLangUtil.paramAddSuccess("common.operation");
    userService.save(operation);
    systemService.addLog(message,Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
  }
  j.setMsg(message);
  return j;
}
