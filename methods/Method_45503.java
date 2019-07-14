/** 
 * ????IPv4??
 * @return ip??
 */
public static String getLocalIpv4(){
  InetAddress address=getLocalAddress();
  return address == null ? null : address.getHostAddress();
}
