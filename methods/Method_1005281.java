/** 
 * ????
 * @param depart
 * @return
 */
@RequestMapping(params="save") @ResponseBody public AjaxJson save(TSDepart depart,HttpServletRequest request){
  String message=null;
  String pid=request.getParameter("TSPDepart.id");
  if (pid.equals("")) {
    depart.setTSPDepart(null);
  }
  AjaxJson j=new AjaxJson();
  if (StringUtil.isNotEmpty(depart.getId())) {
    message=MutiLangUtil.paramUpdSuccess("common.department");
    userService.saveOrUpdate(depart);
    systemService.addLog(message,Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
  }
 else {
    message=MutiLangUtil.paramAddSuccess("common.department");
    userService.save(depart);
    systemService.addLog(message,Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
  }
  j.setMsg(message);
  return j;
}
