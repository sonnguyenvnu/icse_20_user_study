@Override public List<ProviderGroup> subscribe(ConsumerConfig config){
  ProviderInfoListener providerInfoListener=config.getProviderInfoListener();
  String appName=config.getAppName();
  if (!registryConfig.isSubscribe()) {
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
    }
    return null;
  }
  if (!config.isSubscribe()) {
    return null;
  }
  String serviceName=SofaRegistryHelper.buildListDataId(config,config.getProtocol());
  SofaRegistrySubscribeCallback callback;
  Subscriber listSubscriber=subscribers.get(serviceName);
  Configurator attrSubscriber;
  if (listSubscriber != null && providerInfoListener != null) {
    callback=(SofaRegistrySubscribeCallback)listSubscriber.getDataObserver();
    callback.addProviderInfoListener(serviceName,config,providerInfoListener);
    callback.handleDataToListener(serviceName,config,providerInfoListener);
  }
 else {
    callback=new SofaRegistrySubscribeCallback();
    callback.addProviderInfoListener(serviceName,config,providerInfoListener);
    SubscriberRegistration subscriberRegistration=new SubscriberRegistration(serviceName,callback);
    String groupId=config.getParameter(SofaRegistryConstants.SOFA_GROUP_KEY);
    groupId=groupId == null ? SofaRegistryHelper.SUBSCRIBER_LIST_GROUP_ID : groupId;
    addAttributes(subscriberRegistration,groupId);
    ConfiguratorRegistration configRegistration=new ConfiguratorRegistration(serviceName,callback);
    addAttributes(configRegistration,SofaRegistryHelper.SUBSCRIBER_CONFIG_GROUP_ID);
    listSubscriber=SofaRegistryClient.getRegistryClient(appName,registryConfig).register(subscriberRegistration);
    attrSubscriber=SofaRegistryClient.getRegistryClient(appName,registryConfig).register(configRegistration);
    subscribers.put(serviceName,listSubscriber);
    configurators.put(serviceName,attrSubscriber);
  }
  return null;
}
