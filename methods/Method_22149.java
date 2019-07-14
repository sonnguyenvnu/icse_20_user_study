private static boolean isV6IpAddress(final InetAddress ipAddress){
  return ipAddress.getHostAddress().contains(":");
}
