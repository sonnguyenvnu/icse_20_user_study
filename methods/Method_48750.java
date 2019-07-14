private String getUid(Configuration config){
  final InetAddress localHost;
  try {
    localHost=Inet4Address.getLocalHost();
  }
 catch (  UnknownHostException e) {
    throw new JanusGraphConfigurationException("Cannot determine local host",e);
  }
  final String uid;
  if (config.has(GraphDatabaseConfiguration.UNIQUE_INSTANCE_ID_HOSTNAME) && config.get(GraphDatabaseConfiguration.UNIQUE_INSTANCE_ID_HOSTNAME)) {
    uid=localHost.getHostName();
  }
 else {
    final byte[] addrBytes=localHost.getAddress();
    uid=new String(Hex.encodeHex(addrBytes));
  }
  return uid;
}
