public static String normalizeHostAddress(final InetAddress localHost){
  if (localHost instanceof Inet6Address) {
    return "[" + localHost.getHostAddress() + "]";
  }
 else {
    return localHost.getHostAddress();
  }
}
