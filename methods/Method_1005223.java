/** 
 * ??Word?Ftl
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(CgformFtlEntity cgformFtl,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  cgformFtl=systemService.getEntity(CgformFtlEntity.class,cgformFtl.getId());
  message="????";
  cgformFtlService.delete(cgformFtl);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  logger.info("[" + IpUtil.getIpAddr(request) + "][online??????]" + message);
  j.setMsg(message);
  return j;
}
