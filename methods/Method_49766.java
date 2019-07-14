/** 
 * Check if IP address type is consistent between two InetAddress.
 * @return true if both are the same type.  False otherwise.
 */
public static boolean addressTypeMatches(InetAddress left,InetAddress right){
  return (((left instanceof Inet4Address) && (right instanceof Inet4Address)) || ((left instanceof Inet6Address) && (right instanceof Inet6Address)));
}
