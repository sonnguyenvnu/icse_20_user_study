@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.aof_current_size.getValue());
  if (object == null) {
    return null;
  }
  long aofCurrentSize=NumberUtils.toLong(object.toString());
  aofCurrentSize=changeByteToMB(aofCurrentSize);
  boolean compareRight=isCompareLongRight(instanceAlertConfig,aofCurrentSize);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(aofCurrentSize),instanceInfo.getAppId(),MB_STRING));
}
