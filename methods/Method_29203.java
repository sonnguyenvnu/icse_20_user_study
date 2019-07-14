@Override public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
  long userId=userLoginStatusService.getUserIdFromLoginStatus(request);
  AppUser user=userService.get(userId);
  if (AppUserTypeEnum.ADMIN_USER.value().equals(user.getType())) {
    return true;
  }
  String appId=request.getParameter("appId");
  if (StringUtils.isNotBlank(appId)) {
    checkUserAppPower(response,request.getSession(true),user,NumberUtils.toLong(appId));
  }
  String instanceId=request.getParameter("instanceId");
  if (StringUtils.isNotBlank(instanceId)) {
    InstanceInfo instanceInfo=instanceStatsCenter.getInstanceInfo(Long.parseLong(instanceId));
    checkUserAppPower(response,request.getSession(true),user,instanceInfo.getAppId());
  }
  return true;
}
