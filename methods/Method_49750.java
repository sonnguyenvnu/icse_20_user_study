private int convertToInt(String addr){
  if (addr != null) {
    try {
      InetAddress inetAddress=NetworkUtilsHelper.numericToInetAddress(addr);
      if (inetAddress instanceof Inet4Address) {
        return NetworkUtilsHelper.inetAddressToInt(inetAddress);
      }
    }
 catch (    IllegalArgumentException e) {
    }
  }
  return 0;
}
