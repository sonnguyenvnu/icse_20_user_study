/** 
 * ??JS??
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  cgformenhanceJs=systemService.getEntity(CgformEnhanceJsEntity.class,cgformenhanceJs.getId());
  message="????";
  cgformenhanceJsService.delete(cgformenhanceJs);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  j.setMsg(message);
  return j;
}
