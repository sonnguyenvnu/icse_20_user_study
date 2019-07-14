private void getRedisClusterInfo(HttpServletRequest request,long appId,Model model){
  String clientVersion=request.getParameter("clientVersion");
  if (!checkClientVersion(appId,clientVersion,model)) {
    return;
  }
  List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  if (instanceList == null || instanceList.isEmpty()) {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message","ERROR: appId:" + appId + "?????? ");
    return;
  }
  String shardsInfo=ObjectConvert.assembleInstance(instanceList);
  if (StringUtils.isBlank(shardsInfo)) {
    model.addAttribute("status",ClientStatusEnum.ERROR.getStatus());
    model.addAttribute("message","ERROR: appId:" + appId + "shardsInfo?? ");
    return;
  }
  int shardNum=shardsInfo.split(" ").length;
  model.addAttribute("appId",appId);
  model.addAttribute("shardNum",shardNum);
  model.addAttribute("shardInfo",shardsInfo);
  AppDesc appDesc=appDao.getAppDescById(appId);
  String password=appDesc.getPassword();
  if (StringUtils.isNotBlank(password)) {
    model.addAttribute("password",appDesc.getPassword());
  }
  try {
    clientVersionService.saveOrUpdateClientVersion(appId,IpUtil.getIpAddr(request),clientVersion);
  }
 catch (  Exception e) {
    logger.error("redisCluster heart error:" + e.getMessage(),e);
  }
}
