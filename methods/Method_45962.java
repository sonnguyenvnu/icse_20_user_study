/** 
 * ?????
 */
protected void unregister(){
  if (providerConfig.isRegister()) {
    List<RegistryConfig> registryConfigs=providerConfig.getRegistry();
    if (registryConfigs != null) {
      for (      RegistryConfig registryConfig : registryConfigs) {
        Registry registry=RegistryFactory.getRegistry(registryConfig);
        try {
          registry.unRegister(providerConfig);
        }
 catch (        Exception e) {
          String appName=providerConfig.getAppName();
          if (LOGGER.isWarnEnabled(appName)) {
            LOGGER.warnWithApp(appName,"Catch exception when unRegister from registry: " + registryConfig.getId() + ", but you can ignore if it's called by JVM shutdown hook",e);
          }
        }
      }
    }
  }
}
