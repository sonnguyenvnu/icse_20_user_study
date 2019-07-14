/** 
 * ????????
 * @param host ??
 * @return ??????
 */
public static boolean isHostInNetworkCard(String host){
  try {
    InetAddress addr=InetAddress.getByName(host);
    return NetworkInterface.getByInetAddress(addr) != null;
  }
 catch (  Exception e) {
    return false;
  }
}
