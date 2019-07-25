@Override public void prepare(Map stormClusterConfiguration){
  LOG.debug("Receiving cluster configuration");
  clusterSettings=new StormSettings(stormClusterConfiguration);
}
