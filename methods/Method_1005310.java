/** 
 * ????
 * @return
 */
@RequestMapping(params="saveportrait") @ResponseBody public AjaxJson saveportrait(HttpServletRequest request,String fileName){
  AjaxJson j=new AjaxJson();
  TSUser user=ResourceUtil.getSessionUser();
  user.setPortrait(fileName);
  j.setMsg("????");
  try {
    systemService.updateEntitie(user);
  }
 catch (  Exception e) {
    j.setMsg("????");
    e.printStackTrace();
  }
  return j;
}
