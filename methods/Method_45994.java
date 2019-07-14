public static String getServerHost(ServerConfig server){
  String host=server.getVirtualHost();
  if (host == null) {
    host=server.getHost();
    if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
      host=SystemInfo.getLocalHost();
    }
  }
  return host;
}
