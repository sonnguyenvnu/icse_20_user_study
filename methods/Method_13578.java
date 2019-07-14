@PostConstruct public void init(){
  Map<String,String> metadata=nacosDiscoveryProperties.getMetadata();
  Environment env=context.getEnvironment();
  String endpointBasePath=env.getProperty(MANAGEMENT_ENDPOINT_BASE_PATH);
  if (!StringUtils.isEmpty(endpointBasePath)) {
    metadata.put(MANAGEMENT_ENDPOINT_BASE_PATH,endpointBasePath);
  }
  Integer managementPort=ManagementServerPortUtils.getPort(context);
  if (null != managementPort) {
    metadata.put(MANAGEMENT_PORT,managementPort.toString());
    String contextPath=env.getProperty("management.server.servlet.context-path");
    String address=env.getProperty("management.server.address");
    if (!StringUtils.isEmpty(contextPath)) {
      metadata.put(MANAGEMENT_CONTEXT_PATH,contextPath);
    }
    if (!StringUtils.isEmpty(address)) {
      metadata.put(MANAGEMENT_ADDRESS,address);
    }
  }
}
