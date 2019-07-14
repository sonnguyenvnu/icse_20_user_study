@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromDiffInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.sync_full.getValue());
  if (object == null) {
    return null;
  }
  long minuteSyncFull=NumberUtils.toLong(object.toString());
  boolean compareRight=isCompareLongRight(instanceAlertConfig,minuteSyncFull);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(minuteSyncFull),instanceInfo.getAppId(),EMPTY));
}
