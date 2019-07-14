/** 
 * ??????
 * @param local1  ????
 * @param remote1 ????
 * @return ???????
 */
public static String channelToString(SocketAddress local1,SocketAddress remote1){
  try {
    InetSocketAddress local=(InetSocketAddress)local1;
    InetSocketAddress remote=(InetSocketAddress)remote1;
    return toAddressString(local) + " -> " + toAddressString(remote);
  }
 catch (  Exception e) {
    return local1 + "->" + remote1;
  }
}
