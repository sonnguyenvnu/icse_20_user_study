/** 
 * Convert a IPv4 address from an integer to an InetAddress.
 * @param hostAddress an int corresponding to the IPv4 address in network byte order
 */
public static InetAddress intToInetAddress(int hostAddress){
  byte[] addressBytes={(byte)(0xff & hostAddress),(byte)(0xff & (hostAddress >> 8)),(byte)(0xff & (hostAddress >> 16)),(byte)(0xff & (hostAddress >> 24))};
  try {
    return InetAddress.getByAddress(addressBytes);
  }
 catch (  UnknownHostException e) {
    throw new AssertionError();
  }
}
