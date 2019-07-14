@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromDiffInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.rejected_connections.getValue());
  if (object == null) {
    return null;
  }
  long minuteRejectedConnections=NumberUtils.toLong(object.toString());
  boolean compareRight=isCompareLongRight(instanceAlertConfig,minuteRejectedConnections);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(minuteRejectedConnections),instanceInfo.getAppId(),EMPTY));
}
