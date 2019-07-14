@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object totalNetOutputBytesObject=getValueFromDiffInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.total_net_output_bytes.getValue());
  if (totalNetOutputBytesObject == null) {
    return null;
  }
  long totalNetOutputBytes=NumberUtils.toLong(totalNetOutputBytesObject.toString());
  totalNetOutputBytes=changeByteToMB(totalNetOutputBytes);
  boolean compareRight=isCompareLongRight(instanceAlertConfig,totalNetOutputBytes);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(totalNetOutputBytes),instanceInfo.getAppId(),MB_STRING));
}
