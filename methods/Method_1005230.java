/** 
 * ??JS??
 * @param ids
 * @return
 */
@RequestMapping(params="save") @ResponseBody public AjaxJson save(CgformEnhanceJsEntity cgformenhanceJs,HttpServletRequest request){
  String message=null;
  AjaxJson j=new AjaxJson();
  if (StringUtil.isNotEmpty(cgformenhanceJs.getId())) {
    message="????";
    CgformEnhanceJsEntity t=cgformenhanceJsService.get(CgformEnhanceJsEntity.class,cgformenhanceJs.getId());
    try {
      MyBeanUtils.copyBeanNotNull2Bean(cgformenhanceJs,t);
      cgformenhanceJsService.saveOrUpdate(t);
      systemService.addLog(message,Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
 else {
    message="????";
    cgformenhanceJsService.save(cgformenhanceJs);
    systemService.addLog(message,Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
  }
  j.setMsg(message);
  return j;
}
