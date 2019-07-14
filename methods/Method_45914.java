/** 
 * ????????list
 * @return urls urls
 */
public List<String> buildUrls(){
  if (exported) {
    List<ServerConfig> servers=providerConfig.getServer();
    if (servers != null && !servers.isEmpty()) {
      List<String> urls=new ArrayList<String>();
      for (      ServerConfig server : servers) {
        StringBuilder sb=new StringBuilder(200);
        sb.append(server.getProtocol()).append("://").append(server.getHost()).append(":").append(server.getPort()).append(server.getContextPath()).append(providerConfig.getInterfaceId()).append("?uniqueId=").append(providerConfig.getUniqueId()).append(getKeyPairs("version","1.0")).append(getKeyPairs("delay",providerConfig.getDelay())).append(getKeyPairs("weight",providerConfig.getWeight())).append(getKeyPairs("register",providerConfig.isRegister())).append(getKeyPairs("maxThreads",server.getMaxThreads())).append(getKeyPairs("ioThreads",server.getIoThreads())).append(getKeyPairs("threadPoolType",server.getThreadPoolType())).append(getKeyPairs("accepts",server.getAccepts())).append(getKeyPairs("dynamic",providerConfig.isDynamic())).append(getKeyPairs(RpcConstants.CONFIG_KEY_RPC_VERSION,Version.RPC_VERSION));
        urls.add(sb.toString());
      }
      return urls;
    }
  }
  return null;
}
