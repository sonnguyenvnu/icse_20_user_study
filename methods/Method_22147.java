private static boolean isPublicIpAddress(final InetAddress ipAddress){
  return !ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
}
