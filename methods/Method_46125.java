/** 
 * ??????????
 * @param config ???????
 * @param server ???
 * @return ?????????
 */
public static ProviderInfo convertProviderToProviderInfo(ProviderConfig config,ServerConfig server){
  ProviderInfo providerInfo=new ProviderInfo().setPort(server.getPort()).setWeight(config.getWeight()).setSerializationType(config.getSerialization()).setProtocolType(server.getProtocol()).setPath(server.getContextPath());
  String host=server.getHost();
  if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
    host=SystemInfo.getLocalHost();
  }
  providerInfo.setHost(host);
  return providerInfo;
}
