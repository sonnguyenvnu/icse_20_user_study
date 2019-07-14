@Override public void batchUnSubscribe(List<ConsumerConfig> configs){
  for (  ConsumerConfig config : configs) {
    String appName=config.getAppName();
    try {
      unSubscribe(config);
    }
 catch (    Exception e) {
      LOGGER.errorWithApp(appName,"Error when batch unSubscribe",e);
    }
  }
}
