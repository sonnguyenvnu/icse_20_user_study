public static boolean isLocalConnection(String hostname){
  InetAddress localhost=NetworkUtil.getLocalHost();
  return hostname.equalsIgnoreCase(NetworkUtil.getLoopbackAddress()) || hostname.equals(localhost.getHostAddress()) || hostname.equals(localhost.getHostName()) || hostname.equals(localhost.getCanonicalHostName());
}
