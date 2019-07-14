private void notifyConsumerListeners(String serviceName,ProviderGroup providerGroup){
  List<ConsumerConfig> consumerConfigs=notifyListeners.get(serviceName);
  if (consumerConfigs != null) {
    for (    ConsumerConfig config : consumerConfigs) {
      ProviderInfoListener listener=config.getProviderInfoListener();
      if (listener != null) {
        listener.updateProviders(providerGroup);
      }
    }
  }
}
