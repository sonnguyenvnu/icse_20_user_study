public void markAsChecked(InstanceId instanceId){
  this.lastChecked.put(instanceId,Instant.now());
}
