/** 
 * ??????????
 */
@RequestMapping(params="refresh") @ResponseBody public AjaxJson refresh(HttpServletRequest request,HttpServletResponse response){
  AjaxJson ajaxJson=new AjaxJson();
  try {
    cacheService.clean("sysAuthCache");
    logger.info("-----????????????--------[sysAuthCache]-----");
    ajaxJson.setMsg("????????");
  }
 catch (  Exception e) {
    ajaxJson.setMsg("????????");
    e.printStackTrace();
  }
  return ajaxJson;
}
