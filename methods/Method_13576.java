private Properties getNacosProperties(){
  Properties properties=new Properties();
  properties.put(SERVER_ADDR,serverAddr);
  properties.put(NAMESPACE,namespace);
  properties.put(UtilAndComs.NACOS_NAMING_LOG_NAME,logName);
  if (endpoint.contains(":")) {
    int index=endpoint.indexOf(":");
    properties.put(ENDPOINT,endpoint.substring(0,index));
    properties.put(ENDPOINT_PORT,endpoint.substring(index + 1));
  }
 else {
    properties.put(ENDPOINT,endpoint);
  }
  properties.put(ACCESS_KEY,accessKey);
  properties.put(SECRET_KEY,secretKey);
  properties.put(CLUSTER_NAME,clusterName);
  properties.put(NAMING_LOAD_CACHE_AT_START,namingLoadCacheAtStart);
  return properties;
}
