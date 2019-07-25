/** 
 * ?????????
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(CgFormHeadEntity cgFormHead,HttpServletRequest request){
  AjaxJson j=new AjaxJson();
  cgFormHead=systemService.getEntity(CgFormHeadEntity.class,cgFormHead.getId());
  String message="????";
  cgFormFieldService.deleteCgForm(cgFormHead);
  cgFormFieldService.removeSubTableStr4Main(cgFormHead);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  logger.info("[" + IpUtil.getIpAddr(request) + "][online??????]" + message + "???" + cgFormHead.getTableName());
  j.setMsg(message);
  return j;
}
