public static String toAddressString(InetSocketAddress address){
  return address.getAddress().getHostAddress() + ":" + address.getPort();
}
