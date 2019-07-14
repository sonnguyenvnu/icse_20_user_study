private void getRedisStandaloneInfo(HttpServletRequest request,long appId,Model model){
  String clientVersion=request.getParameter("clientVersion");
  if (!checkClientVersion(appId,clientVersion,model)) {
    return;
  }
  List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  String standalone=null;
  for (  InstanceInfo instanceInfo : instanceList) {
    if (instanceInfo.isOffline()) {
      continue;
    }
    standalone=instanceInfo.getIp() + ":" + instanceInfo.getPort();
  }
  model.addAttribute("standalone",standalone);
  model.addAttribute("status",ClientStatusEnum.GOOD.getStatus());
  AppDesc appDesc=appDao.getAppDescById(appId);
  String password=appDesc.getPassword();
  if (StringUtils.isNotBlank(password)) {
    model.addAttribute("password",appDesc.getPassword());
  }
  try {
    clientVersionService.saveOrUpdateClientVersion(appId,IpUtil.getIpAddr(request),clientVersion);
  }
 catch (  Exception e) {
    logger.error("redisStandalone heart error:" + e.getMessage(),e);
  }
}
