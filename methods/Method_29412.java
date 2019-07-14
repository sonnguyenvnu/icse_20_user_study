/** 
 * ????
 * @param instanceAlertConfig
 * @param instanceInfo
 * @param standardStatMap
 * @param currentTime
 */
private List<InstanceAlertValueResult> dealInstanceAlert(List<InstanceAlertConfig> specialInstanceAlertConfigList,InstanceAlertConfig instanceAlertConfig,InstanceInfo instanceInfo,Map<String,StandardStats> standardStatMap,Date currentTime){
  if (instanceInfo.isOffline()) {
    return null;
  }
  String hostPort=instanceInfo.getHostPort();
  StandardStats standardStats=standardStatMap.get(hostPort);
  if (standardStats == null) {
    return null;
  }
  InstanceAlertConfig finalInstanceConfig=filterSpecial(specialInstanceAlertConfigList,instanceAlertConfig,instanceInfo);
  if (!instanceAlertConfig.isSpecail() && finalInstanceConfig.getId() != instanceAlertConfig.getId()) {
    return null;
  }
  boolean isInCycle=checkInCycle(finalInstanceConfig);
  if (!isInCycle) {
    return null;
  }
  String alertConfig=finalInstanceConfig.getAlertConfig();
  RedisAlertConfigEnum redisAlertConfigEnum=RedisAlertConfigEnum.getRedisAlertConfig(alertConfig);
  if (redisAlertConfigEnum == null) {
    logger.warn("alertConfig {} is not in RedisAlertConfigEnum",alertConfig);
    return null;
  }
  AlertConfigStrategy alertConfigStrategy=alertConfigStrategyMap.get(redisAlertConfigEnum);
  if (alertConfigStrategy == null) {
    return null;
  }
  AlertConfigBaseData alertConfigBaseData=new AlertConfigBaseData();
  alertConfigBaseData.setInstanceInfo(instanceInfo);
  alertConfigBaseData.setStandardStats(standardStats);
  try {
    return alertConfigStrategy.checkConfig(finalInstanceConfig,alertConfigBaseData);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return null;
  }
}
