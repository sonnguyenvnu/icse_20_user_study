public ConfigService configServiceInstance(){
  if (null != configService) {
    return configService;
  }
  Properties properties=new Properties();
  properties.put(SERVER_ADDR,Objects.toString(this.serverAddr,""));
  properties.put(ENCODE,Objects.toString(this.encode,""));
  properties.put(NAMESPACE,Objects.toString(this.namespace,""));
  properties.put(ACCESS_KEY,Objects.toString(this.accessKey,""));
  properties.put(SECRET_KEY,Objects.toString(this.secretKey,""));
  properties.put(CONTEXT_PATH,Objects.toString(this.contextPath,""));
  properties.put(CLUSTER_NAME,Objects.toString(this.clusterName,""));
  String endpoint=Objects.toString(this.endpoint,"");
  if (endpoint.contains(":")) {
    int index=endpoint.indexOf(":");
    properties.put(ENDPOINT,endpoint.substring(0,index));
    properties.put(ENDPOINT_PORT,endpoint.substring(index + 1));
  }
 else {
    properties.put(ENDPOINT,endpoint);
  }
  try {
    configService=NacosFactory.createConfigService(properties);
    return configService;
  }
 catch (  Exception e) {
    log.error("create config service error!properties={},e=,",this,e);
    return null;
  }
}
