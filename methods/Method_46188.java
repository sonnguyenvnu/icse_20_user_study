@Override public void unRegister(ProviderConfig config){
  String appName=config.getAppName();
  if (!registryConfig.isRegister()) {
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
    }
    return;
  }
  if (config.isRegister()) {
    try {
      List<String> urls=providerUrls.remove(config);
      if (CommonUtils.isNotEmpty(urls)) {
        String providerPath=buildProviderPath(rootPath,config);
        for (        String url : urls) {
          url=URLEncoder.encode(url,"UTF-8");
          getAndCheckZkClient().delete().forPath(providerPath + CONTEXT_SEP + url);
        }
        if (LOGGER.isInfoEnabled(appName)) {
          LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_UNPUB,providerPath,"1"));
        }
      }
    }
 catch (    Exception e) {
      if (!RpcRunningState.isShuttingDown()) {
        throw new SofaRpcRuntimeException("Failed to unregister provider to zookeeperRegistry!",e);
      }
    }
  }
  if (config.isSubscribe()) {
    try {
      if (null != configObserver) {
        configObserver.removeConfigListener(config);
      }
      if (null != overrideObserver) {
        overrideObserver.removeConfigListener(config);
      }
    }
 catch (    Exception e) {
      if (!RpcRunningState.isShuttingDown()) {
        throw new SofaRpcRuntimeException("Failed to unsubscribe provider config from zookeeperRegistry!",e);
      }
    }
  }
}
