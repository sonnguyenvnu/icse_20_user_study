/** 
 * Get InetAddress masked with prefixLength.  Will never return null.
 * @param IP           address which will be masked with specified prefixLength
 * @param prefixLength the prefixLength used to mask the IP
 */
public static InetAddress getNetworkPart(InetAddress address,int prefixLength){
  if (address == null) {
    throw new RuntimeException("getNetworkPart doesn't accept null address");
  }
  byte[] array=address.getAddress();
  if (prefixLength < 0 || prefixLength > array.length * 8) {
    throw new RuntimeException("getNetworkPart - bad prefixLength");
  }
  int offset=prefixLength / 8;
  int reminder=prefixLength % 8;
  byte mask=(byte)(0xFF << (8 - reminder));
  if (offset < array.length)   array[offset]=(byte)(array[offset] & mask);
  offset++;
  for (; offset < array.length; offset++) {
    array[offset]=0;
  }
  InetAddress netPart=null;
  try {
    netPart=InetAddress.getByAddress(array);
  }
 catch (  UnknownHostException e) {
    throw new RuntimeException("getNetworkPart error - " + e.toString());
  }
  return netPart;
}
