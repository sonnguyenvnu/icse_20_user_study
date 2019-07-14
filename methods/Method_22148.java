private static boolean isLocalIpAddress(final InetAddress ipAddress){
  return ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
}
