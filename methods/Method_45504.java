/** 
 * InetSocketAddress? host:port ???
 * @param address InetSocketAddress?
 * @return host:port ???
 */
public static String toAddressString(InetSocketAddress address){
  if (address == null) {
    return StringUtils.EMPTY;
  }
 else {
    return toIpString(address) + ":" + address.getPort();
  }
}
