@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromClusterInfo(alertConfigBaseData.getStandardStats(),RedisClusterInfoEnum.cluster_slots_ok.getValue());
  if (object == null) {
    return null;
  }
  int clusterSlotsOk=NumberUtils.toInt(object.toString());
  boolean compareRight=isCompareIntRight(instanceAlertConfig,clusterSlotsOk);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(clusterSlotsOk),instanceInfo.getAppId(),EMPTY));
}
