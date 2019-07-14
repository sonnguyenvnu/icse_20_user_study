@Override public boolean needToLoad(FilterInvoker invoker){
  AbstractInterfaceConfig config=invoker.getConfig();
  if (!isConsumerSide(config)) {
    if (LOGGER.isWarnEnabled(config.getAppName())) {
      LOGGER.warnWithApp(config.getAppName(),"HystrixFilter is not allowed on provider, interfaceId: {}",config.getInterfaceId());
    }
    return false;
  }
  if (!isHystrixEnabled(config)) {
    return false;
  }
  if (!isHystrixOnClasspath()) {
    if (LOGGER.isWarnEnabled(config.getAppName())) {
      LOGGER.warnWithApp(config.getAppName(),"HystrixFilter is disabled because 'com.netflix.hystrix:hystrix-core' does not exist on the classpath");
    }
    return false;
  }
  return true;
}
