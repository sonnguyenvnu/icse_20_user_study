private void acquire(ConfigMap configMap){
  LOGGER.debug("Trying to acquire leadership for '{}'",this.candidate);
  if (!isPodReady(this.candidate.getId())) {
    LOGGER.debug("Pod of '{}' is not ready at the moment, cannot acquire leadership",this.candidate);
    return;
  }
  try {
    Map<String,String> data=getLeaderData(this.candidate);
    if (configMap == null) {
      createConfigMap(data);
    }
 else {
      updateConfigMapEntry(configMap,data);
    }
    Leader newLeader=new Leader(this.candidate.getRole(),this.candidate.getId());
    handleLeaderChange(newLeader);
  }
 catch (  KubernetesClientException e) {
    LOGGER.warn("Failure when acquiring leadership for '{}': {}",this.candidate,e.getMessage());
    notifyOnFailedToAcquire();
  }
}
