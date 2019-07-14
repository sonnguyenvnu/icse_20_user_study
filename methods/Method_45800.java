/** 
 * ????????
 * @param interfaceId  ????
 * @param providerInfo ???
 * @param transport    ??
 */
protected void printFailure(String interfaceId,ProviderInfo providerInfo,ClientTransport transport){
  if (LOGGER.isInfoEnabled(consumerConfig.getAppName())) {
    LOGGER.infoWithApp(consumerConfig.getAppName(),"Connect to {} provider:{} failure !",interfaceId,providerInfo);
  }
}
