@Override public void batchUnRegister(List<ProviderConfig> configs){
  for (  ProviderConfig config : configs) {
    String appName=config.getAppName();
    try {
      unRegister(config);
    }
 catch (    Exception e) {
      LOGGER.errorWithApp(appName,"Error when batch unregistry",e);
    }
  }
}
