@Override public List<ProviderGroup> subscribe(final ConsumerConfig config){
  String appName=config.getAppName();
  if (!registryConfig.isSubscribe()) {
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
    }
    return null;
  }
  if (config.isSubscribe()) {
    String serviceName=NacosRegistryHelper.buildServiceName(config,config.getProtocol());
    if (LOGGER.isInfoEnabled()) {
      LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_SUB,serviceName));
    }
    try {
      if (providerObserver == null) {
        providerObserver=new NacosRegistryProviderObserver();
      }
      ProviderInfoListener providerInfoListener=config.getProviderInfoListener();
      providerObserver.addProviderListener(config,providerInfoListener);
      EventListener eventListener=new EventListener(){
        @Override public void onEvent(        Event event){
          if (event instanceof NamingEvent) {
            NamingEvent namingEvent=(NamingEvent)event;
            List<Instance> instances=namingEvent.getInstances();
            if (null == instances) {
              instances=new ArrayList<Instance>();
            }
            providerObserver.updateProviders(config,instances);
          }
        }
      }
;
      namingService.subscribe(serviceName,defaultCluster,eventListener);
      consumerListeners.put(config,eventListener);
      List<Instance> allInstances=namingService.getAllInstances(serviceName,defaultCluster);
      List<ProviderInfo> providerInfos=NacosRegistryHelper.convertInstancesToProviders(allInstances);
      List<ProviderInfo> matchProviders=RegistryUtils.matchProviderInfos(config,providerInfos);
      return Collections.singletonList(new ProviderGroup().addAll(matchProviders));
    }
 catch (    Exception e) {
      throw new SofaRpcRuntimeException("Failed to subscribe provider from nacosRegistry, service: " + serviceName,e);
    }
  }
  return null;
}
