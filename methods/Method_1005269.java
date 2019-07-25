/** 
 */
@RequestMapping(params="saverule") @ResponseBody public AjaxJson saverule(TSDataRule operation,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  if (StringUtil.isNotEmpty(operation.getId())) {
    message=MutiLangUtil.paramUpdSuccess("common.operation");
    userService.saveOrUpdate(operation);
    systemService.addLog(message,Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
  }
 else {
    if (justHaveDataRule(operation) == 0) {
      message=MutiLangUtil.paramAddSuccess("common.operation");
      userService.save(operation);
      systemService.addLog(message,Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
    }
 else {
      message="?? ???????";
    }
  }
  j.setMsg(message);
  return j;
}
