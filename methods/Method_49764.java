/** 
 * Convert a network prefix length to an IPv4 netmask integer
 * @param prefixLength
 * @return the IPv4 netmask as an integer in network byte order
 */
public static int prefixLengthToNetmaskInt(int prefixLength) throws IllegalArgumentException {
  if (prefixLength < 0 || prefixLength > 32) {
    throw new IllegalArgumentException("Invalid prefix length (0 <= prefix <= 32)");
  }
  int value=0xffffffff << (32 - prefixLength);
  return Integer.reverseBytes(value);
}
