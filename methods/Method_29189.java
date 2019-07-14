@Override public boolean inspect(Map<InspectParamEnum,Object> paramMap){
  Long appId=MapUtils.getLong(paramMap,InspectParamEnum.SPLIT_KEY);
  List<AppDesc> appDescList=new ArrayList<AppDesc>();
  AppDesc app=appDao.getAppDescById(appId);
  if (app != null) {
    appDescList.add(app);
  }
  if (CollectionUtils.isEmpty(appDescList)) {
    logger.error("appList is empty, appId={}",appId);
    return true;
  }
  for (  AppDesc appDesc : appDescList) {
    if (appDesc.getIsTest() == 1) {
      continue;
    }
    long checkAppId=appDesc.getAppId();
    AppDetailVO appDetailVO=appStatsCenter.getAppDetail(checkAppId);
    if (appDetailVO == null) {
      continue;
    }
    double appMemUsePercent=appDetailVO.getMemUsePercent();
    int appUseSetMemAlertValue=appDesc.getMemAlertValue();
    if (appMemUsePercent > appUseSetMemAlertValue) {
      alertAppMemUse(appDetailVO);
    }
 else {
      List<InstanceInfo> appInstanceInfoList=(List<InstanceInfo>)paramMap.get(InspectParamEnum.INSTANCE_LIST);
      if (CollectionUtils.isNotEmpty(appInstanceInfoList)) {
        for (        InstanceInfo instanceInfo : appInstanceInfoList) {
          if (instanceInfo == null) {
            continue;
          }
          if (!TypeUtil.isRedisType(instanceInfo.getType())) {
            continue;
          }
          if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
            continue;
          }
          long instanceId=instanceInfo.getId();
          InstanceStats instanceStats=instanceStatsCenter.getInstanceStats(instanceId);
          if (instanceStats == null) {
            continue;
          }
          double instanceMemUsePercent=instanceStats.getMemUsePercent();
          if (instanceMemUsePercent > appUseSetMemAlertValue) {
            alertInstanceMemUse(instanceStats,appDetailVO);
          }
        }
      }
    }
  }
  return true;
}
