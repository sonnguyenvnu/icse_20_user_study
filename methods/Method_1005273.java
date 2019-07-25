/** 
 * ????
 * @param icon
 * @param request
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(TSIcon icon,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  icon=systemService.getEntity(TSIcon.class,icon.getId());
  boolean isPermit=isPermitDel(icon);
  if (isPermit) {
    systemService.delete(icon);
    message=MutiLangUtil.paramDelSuccess("common.icon");
    systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
  }
  message=MutiLangUtil.paramDelFail("common.icon,common.icon.isusing");
  j.setMsg(message);
  return j;
}
