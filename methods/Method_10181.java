/** 
 * Checks the specified hostname is an inner address.
 * @param host the specified hostname
 * @return {@code true} if it is, returns {@code false} otherwise
 */
public static boolean isInnerAddress(final String host){
  try {
    final int intAddress=ipToLong(host);
    return ipToLong("0.0.0.0") >> 24 == intAddress >> 24 || ipToLong("127.0.0.1") >> 24 == intAddress >> 24 || ipToLong("10.0.0.0") >> 24 == intAddress >> 24 || ipToLong("172.16.0.0") >> 20 == intAddress >> 20 || ipToLong("192.168.0.0") >> 16 == intAddress >> 16;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Checks inner address failed: " + e.getMessage());
    return true;
  }
}
