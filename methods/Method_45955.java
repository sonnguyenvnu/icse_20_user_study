@Override public List<ProviderGroup> subscribe(){
  List<ProviderGroup> result=null;
  String directUrl=consumerConfig.getDirectUrl();
  if (StringUtils.isNotEmpty(directUrl)) {
    result=subscribeFromDirectUrl(directUrl);
  }
 else {
    List<RegistryConfig> registryConfigs=consumerConfig.getRegistry();
    if (CommonUtils.isNotEmpty(registryConfigs)) {
      result=subscribeFromRegistries();
    }
  }
  return result;
}
