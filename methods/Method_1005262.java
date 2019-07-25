/** 
 * ???????
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(DynamicDataSourceEntity dbSource,HttpServletRequest request){
  AjaxJson j=new AjaxJson();
  dbSource=systemService.getEntity(DynamicDataSourceEntity.class,dbSource.getId());
  message=MutiLangUtil.paramDelSuccess("common.datasource.manage");
  systemService.delete(dbSource);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  j.setMsg(message);
  return j;
}
