/** 
 * Convert provider to url.
 * @param providerConfig the ProviderConfig
 * @return the url list
 */
public static List<String> convertProviderToUrls(ProviderConfig providerConfig){
  @SuppressWarnings("unchecked") List<ServerConfig> servers=providerConfig.getServer();
  if (servers != null && !servers.isEmpty()) {
    List<String> urls=new ArrayList<String>();
    for (    ServerConfig server : servers) {
      StringBuilder sb=new StringBuilder(200);
      String host=server.getVirtualHost();
      if (host == null) {
        host=server.getHost();
        if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
          host=SystemInfo.getLocalHost();
        }
      }
      Map<String,String> metaData=convertProviderToMap(providerConfig,server);
      sb.append(server.getProtocol()).append("://").append(host).append(":").append(server.getPort()).append(server.getContextPath()).append("?version=1.0").append(convertMap2Pair(metaData));
      urls.add(sb.toString());
    }
    return urls;
  }
  return null;
}
