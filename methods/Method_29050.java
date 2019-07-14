@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromRedisInfo(alertConfigBaseData.getStandardStats(),RedisInfoEnum.client_longest_output_list.getValue());
  if (object == null) {
    return null;
  }
  long clientLongestOutputList=NumberUtils.toLong(object.toString());
  boolean compareRight=isCompareLongRight(instanceAlertConfig,clientLongestOutputList);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(clientLongestOutputList),instanceInfo.getAppId(),EMPTY));
}
