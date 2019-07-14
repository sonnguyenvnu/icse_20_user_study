/** 
 * ??ip??
 * @param address InetSocketAddress
 * @return ip??
 */
public static String toIpString(InetSocketAddress address){
  if (address == null) {
    return null;
  }
 else {
    InetAddress inetAddress=address.getAddress();
    return inetAddress == null ? address.getHostName() : inetAddress.getHostAddress();
  }
}
