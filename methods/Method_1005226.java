/** 
 * ?????????
 * @return
 */
@RequestMapping(params="rem") @ResponseBody public AjaxJson rem(CgFormHeadEntity cgFormHead,HttpServletRequest request){
  AjaxJson j=new AjaxJson();
  cgFormHead=systemService.getEntity(CgFormHeadEntity.class,cgFormHead.getId());
  String message="????";
  cgFormFieldService.delete(cgFormHead);
  cgFormFieldService.removeSubTableStr4Main(cgFormHead);
  systemService.addLog(message,Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  logger.info("[" + IpUtil.getIpAddr(request) + "][online??????]" + message + "???" + cgFormHead.getTableName());
  j.setMsg(message);
  return j;
}
