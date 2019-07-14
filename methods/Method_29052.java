@Override public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig,AlertConfigBaseData alertConfigBaseData){
  Object object=getValueFromClusterInfo(alertConfigBaseData.getStandardStats(),RedisClusterInfoEnum.cluster_state.getValue());
  if (object == null) {
    return null;
  }
  String clusterState=object.toString();
  boolean compareRight=isCompareStringRight(instanceAlertConfig,clusterState);
  if (compareRight) {
    return null;
  }
  InstanceInfo instanceInfo=alertConfigBaseData.getInstanceInfo();
  return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig,instanceInfo,String.valueOf(clusterState),instanceInfo.getAppId(),EMPTY));
}
