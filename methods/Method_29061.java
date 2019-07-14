@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.rdb_last_bgsave_status.getValue());
  if (object == null) {
    return null;
  }
  String rdbLastBgsaveStatus=object.toString();
  boolean compareRight=isCompareStringRight(instanceAlertConfig,rdbLastBgsaveStatus);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(rdbLastBgsaveStatus),instanceInfo.getAppId(),EMPTY));
}
