@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object totalNetInputBytesObject=getValueFromDiffInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.total_net_input_bytes.getValue());
  if (totalNetInputBytesObject == null) {
    return null;
  }
  long totalNetInputBytes=NumberUtils.toLong(totalNetInputBytesObject.toString());
  totalNetInputBytes=changeByteToMB(totalNetInputBytes);
  boolean compareRight=isCompareLongRight(instanceAlertConfig,totalNetInputBytes);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(totalNetInputBytes),instanceInfo.getAppId(),MB_STRING));
}
