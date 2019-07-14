@Override public void unSubscribe(ConsumerConfig config){
  String appName=config.getAppName();
  if (!registryConfig.isSubscribe()) {
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(config.getAppName(),LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
    }
  }
  if (!config.isSubscribe()) {
    return;
  }
  String uniqueName=buildUniqueName(config,config.getProtocol());
  HealthServiceInformer informer=healthServiceInformers.get(uniqueName);
  if (informer == null) {
    return;
  }
  informer.removeListener(config.getProviderInfoListener());
  if (informer.getListenerSize() == 0) {
    healthServiceInformers.remove(uniqueName);
    informer.shutdown();
  }
}
