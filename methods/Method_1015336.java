@Override public ServiceDiscovery<ZookeeperInstance> customize(ServiceDiscoveryBuilder<ZookeeperInstance> builder){
  Map<String,String> metadata=super.properties.getMetadata();
  String processId=metaDataProvider.getProcessId();
  String instanceId=new StringBuilder(processId).append('@').append(properties.getInstanceHost()).append(':').append(metaDataProvider.getServerPort()).toString();
  super.properties.setInstanceId(instanceId);
  if (metadata != null) {
    metadata.put(MANAGEMENT_PORT,metaDataProvider.getManagementPort() + "");
    metadata.put(PID,processId);
  }
  ServiceDiscovery<ZookeeperInstance> customize=super.customize(builder);
  return customize;
}
