private void covert(ProviderConfig<T> providerConfig,ServiceConfig<T> serviceConfig){
  copyApplication(providerConfig,serviceConfig);
  DubboConvertor.copyRegistries(providerConfig,serviceConfig);
  copyServers(providerConfig,serviceConfig);
  copyProvider(providerConfig,serviceConfig);
  copyMethods(providerConfig,serviceConfig);
}
