private List<ProviderInfo> lookupHealthService(ConsumerConfig config){
  String uniqueName=buildUniqueName(config,config.getProtocol());
  String serviceName=buildServiceName(config);
  String informerKey=String.join("-",serviceName,uniqueName);
  HealthServiceInformer informer=healthServiceInformers.get(informerKey);
  if (informer == null) {
    informer=new HealthServiceInformer(serviceName,uniqueName,consulClient,properties);
    informer.init();
    healthServiceInformers.put(informerKey,informer);
  }
  informer.addListener(config.getProviderInfoListener());
  return informer.currentProviders();
}
