/** 
 * For convert provider to bolt url.
 * @param transportConfig ClientTransportConfig
 * @param providerInfo    ProviderInfo
 * @return Bolt Url
 */
protected Url convertProviderToUrl(ClientTransportConfig transportConfig,ProviderInfo providerInfo){
  Url boltUrl=new Url(providerInfo.toString(),providerInfo.getHost(),providerInfo.getPort());
  boltUrl.setConnectTimeout(transportConfig.getConnectTimeout());
  final int connectionNum=transportConfig.getConnectionNum();
  if (connectionNum > 0) {
    boltUrl.setConnNum(connectionNum);
  }
 else {
    boltUrl.setConnNum(1);
  }
  boltUrl.setConnWarmup(false);
  if (RpcConstants.PROTOCOL_TYPE_BOLT.equals(providerInfo.getProtocolType())) {
    boltUrl.setProtocol(RemotingConstants.PROTOCOL_BOLT);
  }
 else {
    boltUrl.setProtocol(RemotingConstants.PROTOCOL_TR);
  }
  return boltUrl;
}
