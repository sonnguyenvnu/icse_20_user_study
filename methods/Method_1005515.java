private void revoke(ConfigMap configMap){
  LOGGER.debug("Trying to revoke leadership for '{}'",this.candidate);
  try {
    String leaderKey=getLeaderKey();
    removeConfigMapEntry(configMap,leaderKey);
    handleLeaderChange(null);
  }
 catch (  KubernetesClientException e) {
    LOGGER.warn("Failure when revoking leadership for '{}': {}",this.candidate,e.getMessage());
  }
}
