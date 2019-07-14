private AbstractBeanDefinition buildZookeeperConfigurationBeanDefinition(final Element element){
  BeanDefinitionBuilder configuration=BeanDefinitionBuilder.rootBeanDefinition(ZookeeperConfiguration.class);
  configuration.addConstructorArgValue(element.getAttribute("server-lists"));
  configuration.addConstructorArgValue(element.getAttribute("namespace"));
  addPropertyValueIfNotEmpty("base-sleep-time-milliseconds","baseSleepTimeMilliseconds",element,configuration);
  addPropertyValueIfNotEmpty("max-sleep-time-milliseconds","maxSleepTimeMilliseconds",element,configuration);
  addPropertyValueIfNotEmpty("max-retries","maxRetries",element,configuration);
  addPropertyValueIfNotEmpty("session-timeout-milliseconds","sessionTimeoutMilliseconds",element,configuration);
  addPropertyValueIfNotEmpty("connection-timeout-milliseconds","connectionTimeoutMilliseconds",element,configuration);
  addPropertyValueIfNotEmpty("digest","digest",element,configuration);
  return configuration.getBeanDefinition();
}
