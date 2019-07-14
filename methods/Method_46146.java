public static synchronized RegistryClient getRegistryClient(String appName,RegistryConfig registryConfig){
  if (registryClient == null) {
    String address=registryConfig.getAddress();
    final String portStr=StringUtils.substringAfter(address,":");
    RegistryClientConfig config=DefaultRegistryClientConfigBuilder.start().setAppName(appName).setDataCenter(LOCAL_DATACENTER).setZone(LOCAL_REGION).setRegistryEndpoint(StringUtils.substringBefore(address,":")).setRegistryEndpointPort(Integer.parseInt(portStr)).build();
    registryClient=new DefaultRegistryClient(config);
    ((DefaultRegistryClient)registryClient).init();
  }
  return registryClient;
}
