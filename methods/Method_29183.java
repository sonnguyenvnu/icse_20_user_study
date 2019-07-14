@Override public boolean inspect(Map<InspectParamEnum,Object> paramMap){
  Long appId=MapUtils.getLong(paramMap,InspectParamEnum.SPLIT_KEY);
  AppDetailVO appDetailVO=appStatsCenter.getAppDetail(appId);
  if (appDetailVO == null) {
    logger.warn("appId {} appDetailVO is empty",appId);
    return true;
  }
  List<InstanceInfo> appInstanceInfoList=(List<InstanceInfo>)paramMap.get(InspectParamEnum.INSTANCE_LIST);
  if (CollectionUtils.isEmpty(appInstanceInfoList)) {
    logger.warn("appId {} instanceList is empty",appId);
    return true;
  }
  int appClientConnThreshold=getClientConnThreshold(appDetailVO.getAppDesc());
  int appClientConnNum=appDetailVO.getConn();
  int instanceCount=appInstanceInfoList.size();
  if (appClientConnNum > appClientConnThreshold * instanceCount) {
    alertAppClientConn(appDetailVO,appClientConnThreshold,instanceCount);
  }
 else {
    for (    InstanceInfo instanceInfo : appInstanceInfoList) {
      if (instanceInfo == null) {
        continue;
      }
      if (instanceInfo.isOffline()) {
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
      double instanceClientConnNum=instanceStats.getCurrConnections();
      if (instanceClientConnNum > appClientConnThreshold) {
        alertInstanceClientConn(instanceStats,appDetailVO,appClientConnThreshold);
      }
    }
  }
  return true;
}
