private void getRedisSentinelInfo(HttpServletRequest request,long appId,Model model){
  String clientVersion=request.getParameter("clientVersion");
  if (!checkClientVersion(appId,clientVersion,model)) {
    return;
  }
  List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  if (instanceList == null || instanceList.isEmpty()) {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message","appId: " + appId + " ?????? ");
    return;
  }
  String masterName=null;
  List<String> sentinelList=new ArrayList<String>();
  for (  InstanceInfo instance : instanceList) {
    if (instance.isOffline()) {
      continue;
    }
    if (instance.getType() == ConstUtils.CACHE_REDIS_SENTINEL && masterName == null && StringUtils.isNotBlank(instance.getCmd())) {
      masterName=instance.getCmd();
    }
    if (instance.getType() == ConstUtils.CACHE_REDIS_SENTINEL) {
      sentinelList.add(instance.getIp() + ":" + instance.getPort());
    }
  }
  String sentinels=StringUtils.join(sentinelList," ");
  model.addAttribute("sentinels",sentinels);
  model.addAttribute("masterName",masterName);
  model.addAttribute("appId",appId);
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
    logger.error("redisSentinel heart error:" + e.getMessage(),e);
  }
}
