protected void initClientTransport(String interfaceId,ProviderInfo providerInfo,ClientTransport transport){
  try {
    transport.connect();
    if (doubleCheck(interfaceId,providerInfo,transport)) {
      printSuccess(interfaceId,providerInfo,transport);
      addAlive(providerInfo,transport);
    }
 else {
      printFailure(interfaceId,providerInfo,transport);
      addRetry(providerInfo,transport);
    }
  }
 catch (  Exception e) {
    if (LOGGER.isDebugEnabled(consumerConfig.getAppName())) {
      LOGGER.debugWithApp(consumerConfig.getAppName(),"Failed to connect " + providerInfo,e);
    }
    printDead(interfaceId,providerInfo,transport,e);
    addRetry(providerInfo,transport);
  }
}
