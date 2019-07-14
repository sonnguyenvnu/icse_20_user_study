/** 
 * Convert provider to instances list.
 * @param providerConfig the provider config 
 * @return the list
 */
static List<Instance> convertProviderToInstances(ProviderConfig providerConfig){
  @SuppressWarnings("unchecked") List<ServerConfig> servers=providerConfig.getServer();
  if (servers != null && !servers.isEmpty()) {
    List<Instance> instances=new ArrayList<Instance>();
    for (    ServerConfig server : servers) {
      String serviceName=buildServiceName(providerConfig,server.getProtocol());
      Instance instance=new Instance();
      instance.setClusterName(DEFAULT_CLUSTER);
      instance.setServiceName(serviceName);
      String host=server.getVirtualHost();
      if (host == null) {
        host=server.getHost();
        if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
          host=SystemInfo.getLocalHost();
        }
      }
      instance.setIp(host);
      instance.setPort(server.getPort());
      Map<String,String> metaData=RegistryUtils.convertProviderToMap(providerConfig,server);
      instance.setMetadata(metaData);
      instances.add(instance);
    }
    return instances;
  }
  return null;
}
