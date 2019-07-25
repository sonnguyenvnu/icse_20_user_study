public static boolean ping(String ipStr){
  try {
    InetAddress inetAddress=InetAddress.getByName(ipStr);
    if (inetAddress.isReachable(TIMEOUT)) {
      return true;
    }
  }
 catch (  Exception e) {
  }
  return false;
}
