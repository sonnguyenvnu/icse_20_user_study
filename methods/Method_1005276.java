/** 
 * ?????
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(MutiLangEntity mutiLang,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  mutiLang=systemService.getEntity(MutiLangEntity.class,mutiLang.getId());
  message=MutiLangUtil.paramDelSuccess("common.language");
  systemService.delete(mutiLang);
  mutiLangService.initAllMutiLang();
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  j.setMsg(message);
  return j;
}
