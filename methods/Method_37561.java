/** 
 * Resolves IP address from a hostname.
 */
public static String resolveIpAddress(final String hostname){
  try {
    InetAddress netAddress;
    if (hostname == null || hostname.equalsIgnoreCase(LOCAL_HOST)) {
      netAddress=InetAddress.getLocalHost();
    }
 else {
      netAddress=Inet4Address.getByName(hostname);
    }
    return netAddress.getHostAddress();
  }
 catch (  UnknownHostException ignore) {
    return null;
  }
}
