@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.instantaneous_ops_per_sec.getValue());
  if (object == null) {
    return null;
  }
  long instantaneousOpsPerSec=NumberUtils.toLong(object.toString());
  boolean compareRight=isCompareLongRight(instanceAlertConfig,instantaneousOpsPerSec);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(instantaneousOpsPerSec),instanceInfo.getAppId(),EMPTY));
}
