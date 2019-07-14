/** 
 * Convert a IPv4 address from an InetAddress to an integer
 * @param inetAddr is an InetAddress corresponding to the IPv4 address
 * @return the IP address as an integer in network byte order
 */
public static int inetAddressToInt(InetAddress inetAddr) throws IllegalArgumentException {
  byte[] addr=inetAddr.getAddress();
  if (addr.length != 4) {
    throw new IllegalArgumentException("Not an IPv4 address");
  }
  return ((addr[3] & 0xff) << 24) | ((addr[2] & 0xff) << 16) | ((addr[1] & 0xff) << 8) | (addr[0] & 0xff);
}
