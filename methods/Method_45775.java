/** 
 * ??provider????
 * @param message      ????
 * @param providerInfo ??Provider
 * @return ?????transport??null
 */
protected ClientTransport selectByProvider(SofaRequest message,ProviderInfo providerInfo){
  ClientTransport transport=connectionHolder.getAvailableClientTransport(providerInfo);
  if (transport != null) {
    if (transport.isAvailable()) {
      lastProviderInfo=providerInfo;
      checkAlias(providerInfo,message);
      return transport;
    }
 else {
      connectionHolder.setUnavailable(providerInfo,transport);
    }
  }
  return null;
}
