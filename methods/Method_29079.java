/** 
 * ?????????
 * @param appId ??id
 * @param request
 * @param model
 * @param type ????
 * @param isCheckAppKey ????appKey
 * @return
 */
private boolean handleRedisApp(long appId,HttpServletRequest request,Model model,int type,boolean isCheckAppKey){
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message",String.format("appId:%s ???",appId));
    return false;
  }
 else   if (appDesc.getType() != type) {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message",String.format("appId:%s ????,????:%s,????%s,??????!",appId,type,appDesc.getType()));
    return false;
  }
 else   if (isCheckAppKey) {
    String appKey=request.getParameter("appKey");
    if (StringUtils.isBlank(appKey)) {
      model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
      model.addAttribute("message",String.format("appId=%s,appKey????",appId));
      return false;
    }
    if (!appKey.equals(appDesc.getAppKey())) {
      model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
      model.addAttribute("message",String.format("appId=%s,appKey:%s??,???????",appId,appKey));
      return false;
    }
  }
  return true;
}
