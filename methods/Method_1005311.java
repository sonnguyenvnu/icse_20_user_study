/** 
 * ????
 * @author pu.chen
 */
@RequestMapping(params="lock") @ResponseBody public AjaxJson lock(String id,HttpServletRequest req){
  AjaxJson j=new AjaxJson();
  String message=null;
  TSUser user=systemService.getEntity(TSUser.class,id);
  if ("admin".equals(user.getUserName())) {
    message="?????[admin]????";
    j.setMsg(message);
    return j;
  }
  String lockValue=req.getParameter("lockvalue");
  user.setStatus(new Short(lockValue));
  try {
    userService.updateEntitie(user);
    if ("0".equals(lockValue)) {
      message="???" + user.getUserName() + "????!";
    }
 else     if ("1".equals(lockValue)) {
      message="???" + user.getUserName() + "????!";
    }
    systemService.addLog(message,Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
    logger.info("[" + IpUtil.getIpAddr(req) + "][????]" + message);
  }
 catch (  Exception e) {
    message="????!";
  }
  j.setMsg(message);
  return j;
}
