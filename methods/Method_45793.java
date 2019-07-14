public void removeNode(List<ProviderInfo> providerInfos){
  lastAddresses.removeAll(providerInfos);
  String interfaceId=consumerConfig.getInterfaceId();
  String appName=consumerConfig.getAppName();
  if (LOGGER.isInfoEnabled(appName)) {
    LOGGER.infoWithApp(appName,"Remove provider of {}, size is : {}",interfaceId,providerInfos.size());
  }
  for (  ProviderInfo providerInfo : providerInfos) {
    try {
      ClientTransport transport=remove(providerInfo);
      if (LOGGER.isInfoEnabled(appName)) {
        LOGGER.infoWithApp(appName,"Remove provider of {}: {} from list success !",interfaceId,providerInfo);
      }
      if (transport != null) {
        ClientTransportFactory.releaseTransport(transport,consumerConfig.getDisconnectTimeout());
      }
    }
 catch (    Exception e) {
      LOGGER.errorWithApp(appName,"Remove provider of " + consumerConfig.getInterfaceId() + ": " + providerInfo + " from list error:",e);
    }
  }
}
