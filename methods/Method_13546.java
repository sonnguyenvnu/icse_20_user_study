@Override public PropertySource<?> locate(Environment env){
  ConfigService configService=nacosConfigProperties.configServiceInstance();
  if (null == configService) {
    log.warn("no instance of config service found, can't load config from nacos");
    return null;
  }
  long timeout=nacosConfigProperties.getTimeout();
  nacosPropertySourceBuilder=new NacosPropertySourceBuilder(configService,timeout);
  String name=nacosConfigProperties.getName();
  String dataIdPrefix=nacosConfigProperties.getPrefix();
  if (StringUtils.isEmpty(dataIdPrefix)) {
    dataIdPrefix=name;
  }
  if (StringUtils.isEmpty(dataIdPrefix)) {
    dataIdPrefix=env.getProperty("spring.application.name");
  }
  CompositePropertySource composite=new CompositePropertySource(NACOS_PROPERTY_SOURCE_NAME);
  loadSharedConfiguration(composite);
  loadExtConfiguration(composite);
  loadApplicationConfiguration(composite,dataIdPrefix,nacosConfigProperties,env);
  return composite;
}
