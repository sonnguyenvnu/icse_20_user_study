protected void recoverRegistryData(){
  for (  ProviderConfig providerConfig : providerUrls.keySet()) {
    registerProviderUrls(providerConfig);
  }
  for (  ConsumerConfig consumerConfig : consumerUrls.keySet()) {
    subscribeConsumerUrls(consumerConfig);
  }
}
