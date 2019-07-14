/** 
 * ????
 * @param appId
 * @return
 */
@RequestMapping(value="/offLine") public ModelAndView offLineApp(HttpServletRequest request,HttpServletResponse response,Model model,Long appId){
  AppUser userInfo=getUserInfo(request);
  logger.warn("user {} hope to offline appId: {}",userInfo.getName(),appId);
  if (ConstUtils.SUPER_MANAGER.contains(userInfo.getName())) {
    boolean result=appDeployCenter.offLineApp(appId);
    model.addAttribute("appId",appId);
    model.addAttribute("result",result);
    if (result) {
      model.addAttribute("msg","????");
    }
 else {
      model.addAttribute("msg","????");
    }
    logger.warn("user {} offline appId: {}, result is {}",userInfo.getName(),appId,result);
    appEmailUtil.noticeOfflineApp(userInfo,appId,result);
  }
 else {
    logger.warn("user {} hope to offline appId: {}, hasn't provilege",userInfo.getName(),appId);
    model.addAttribute("result",false);
    model.addAttribute("msg","????");
    appEmailUtil.noticeOfflineApp(userInfo,appId,false);
  }
  return new ModelAndView();
}
