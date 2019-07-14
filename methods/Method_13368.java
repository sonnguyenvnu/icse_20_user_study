private String getRestPortFromProtocolsProperties(ConfigurableEnvironment environment){
  String restPort=null;
  Map<String,Object> subProperties=getSubProperties(environment,PROTOCOLS_PROPERTY_NAME_PREFIX);
  Properties properties=new Properties();
  properties.putAll(subProperties);
  for (  String propertyName : properties.stringPropertyNames()) {
    if (propertyName.endsWith(PROTOCOL_NAME_PROPERTY_NAME_SUFFIX)) {
      String protocol=properties.getProperty(propertyName);
      if (isRestProtocol(protocol)) {
        String beanName=resolveBeanName(propertyName);
        if (StringUtils.hasText(beanName)) {
          restPort=properties.getProperty(beanName + PROTOCOL_PORT_PROPERTY_NAME_SUFFIX);
          break;
        }
      }
    }
  }
  return restPort;
}
