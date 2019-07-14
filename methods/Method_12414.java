protected final String getLastStatus(InstanceId instanceId){
  return lastStatuses.getOrDefault(instanceId,"UNKNOWN");
}
