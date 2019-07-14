/** 
 * Resolves host name from IP address bytes.
 */
public static String resolveHostName(final byte[] ip){
  try {
    InetAddress address=InetAddress.getByAddress(ip);
    return address.getHostName();
  }
 catch (  UnknownHostException ignore) {
    return null;
  }
}
