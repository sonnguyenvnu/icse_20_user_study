private void notifyListeners(ConsumerConfig config,String providerPath,List<ChildData> currentData,boolean add) throws UnsupportedEncodingException {
  List<ProviderInfoListener> providerInfoListeners=providerListenerMap.get(config);
  if (CommonUtils.isNotEmpty(providerInfoListeners)) {
    List<ProviderInfo> providerInfos=ZookeeperRegistryHelper.convertUrlsToProviders(providerPath,currentData);
    List<ProviderInfo> providerInfosForProtocol=RegistryUtils.matchProviderInfos(config,providerInfos);
    for (    ProviderInfoListener listener : providerInfoListeners) {
      if (add) {
        listener.addProvider(new ProviderGroup(providerInfosForProtocol));
      }
 else {
        listener.updateProviders(new ProviderGroup(providerInfosForProtocol));
      }
    }
  }
}
