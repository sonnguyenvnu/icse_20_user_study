protected String getHost(InetAddress address){
  return instance.isPreferIp() ? address.getHostAddress() : address.getCanonicalHostName();
}
