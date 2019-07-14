@Override public void destroy(){
  for (  ProviderConfig providerConfig : providerInstances.keySet()) {
    unRegister(providerConfig);
  }
  for (  ConsumerConfig consumerConfig : consumerListeners.keySet()) {
    unSubscribe(consumerConfig);
  }
  namingService=null;
  providerObserver=null;
}
