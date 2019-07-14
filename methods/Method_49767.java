/** 
 * Create a string array of host addresses from a collection of InetAddresses
 * @param addrs a Collection of InetAddresses
 * @return an array of Strings containing their host addresses
 */
public static String[] makeStrings(Collection<InetAddress> addrs){
  String[] result=new String[addrs.size()];
  int i=0;
  for (  InetAddress addr : addrs) {
    result[i++]=addr.getHostAddress();
  }
  return result;
}
