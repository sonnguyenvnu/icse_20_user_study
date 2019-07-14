@Override public void unRegister(ProviderConfig config){
  String appName=config.getAppName();
  if (!registryConfig.isRegister()) {
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
    }
    return;
  }
  if (!config.isRegister()) {
    return;
  }
  List<ServerConfig> serverConfigs=config.getServer();
  if (CommonUtils.isNotEmpty(serverConfigs)) {
    for (    ServerConfig server : serverConfigs) {
      String serviceName=MeshRegistryHelper.buildMeshKey(config,server.getProtocol());
      ProviderInfo providerInfo=MeshRegistryHelper.convertProviderToProviderInfo(config,server);
      try {
        doUnRegister(serviceName,providerInfo);
        if (LOGGER.isInfoEnabled(appName)) {
          LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_UNPUB,serviceName,"1"));
        }
      }
 catch (      Exception e) {
        LOGGER.errorWithApp(appName,LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_UNPUB,serviceName,"0"),e);
      }
    }
  }
}
