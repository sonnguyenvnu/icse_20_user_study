public static String buildServiceId(ProviderConfig config,ServerConfig server){
  return String.join("-",buildUniqueName(config,server.getProtocol()),getServerHost(server),String.valueOf(server.getPort()));
}
