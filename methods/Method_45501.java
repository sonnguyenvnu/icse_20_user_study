/** 
 * ???????????????IPv4???
 * @param address InetAddress
 * @return ????
 */
private static boolean isValidAddress(InetAddress address){
  if (address == null || address.isLoopbackAddress()) {
    return false;
  }
  String name=address.getHostAddress();
  return (name != null && !isAnyHost(name) && !isLocalHost(name) && isIPv4Host(name));
}
