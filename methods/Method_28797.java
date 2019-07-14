public static String getNodeKey(HostAndPort hnp){
  return hnp.getHost() + ":" + hnp.getPort();
}
