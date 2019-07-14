private InstanceAlertConfig getInstanceAlertConfig(HttpServletRequest request){
  Date now=new Date();
  String alertConfig=request.getParameter("alertConfig");
  String alertValue=request.getParameter("alertValue");
  RedisAlertConfigEnum redisAlertConfigEnum=RedisAlertConfigEnum.getRedisAlertConfig(alertConfig);
  String configInfo=redisAlertConfigEnum == null ? "" : redisAlertConfigEnum.getInfo();
  int compareType=NumberUtils.toInt(request.getParameter("compareType"));
  int checkCycle=NumberUtils.toInt(request.getParameter("checkCycle"));
  int instanceId=0;
  int type=NumberUtils.toInt(request.getParameter("type"));
  if (InstanceAlertTypeEnum.INSTANCE_ALERT.getValue() == type) {
    String hostPort=request.getParameter("instanceHostPort");
    InstanceInfo instanceInfo=getInstanceInfo(hostPort);
    instanceId=instanceInfo.getId();
  }
  InstanceAlertConfig instanceAlertConfig=new InstanceAlertConfig();
  instanceAlertConfig.setAlertConfig(alertConfig);
  instanceAlertConfig.setAlertValue(alertValue);
  instanceAlertConfig.setConfigInfo(configInfo);
  instanceAlertConfig.setCompareType(compareType);
  instanceAlertConfig.setInstanceId(instanceId);
  instanceAlertConfig.setCheckCycle(checkCycle);
  instanceAlertConfig.setLastCheckTime(now);
  instanceAlertConfig.setType(type);
  instanceAlertConfig.setUpdateTime(now);
  instanceAlertConfig.setStatus(InstanceAlertStatusEnum.YES.getValue());
  return instanceAlertConfig;
}
