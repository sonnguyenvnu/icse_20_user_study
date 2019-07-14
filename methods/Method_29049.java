@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.client_biggest_input_buf.getValue());
  if (object == null) {
    return null;
  }
  long clientBiggestInputBuf=NumberUtils.toLong(object.toString());
  clientBiggestInputBuf=changeByteToMB(clientBiggestInputBuf);
  boolean compareRight=isCompareLongRight(instanceAlertConfig,clientBiggestInputBuf);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(clientBiggestInputBuf),instanceInfo.getAppId(),MB_STRING));
}
