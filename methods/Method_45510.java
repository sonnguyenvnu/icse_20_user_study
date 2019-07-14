/** 
 * ??????
 * @param local  ????
 * @param remote ????
 * @return ???????
 */
public static String connectToString(InetSocketAddress local,InetSocketAddress remote){
  return toAddressString(local) + " <-> " + toAddressString(remote);
}
