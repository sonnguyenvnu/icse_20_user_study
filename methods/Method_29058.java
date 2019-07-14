@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromDiffInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.sync_partial_ok.getValue());
  if (object == null) {
    return null;
  }
  long minuteSyncPartialOk=NumberUtils.toLong(object.toString());
  boolean compareRight=isCompareLongRight(instanceAlertConfig,minuteSyncPartialOk);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(minuteSyncPartialOk),instanceInfo.getAppId(),EMPTY));
}
