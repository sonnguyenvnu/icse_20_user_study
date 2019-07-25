public static boolean match(IpAddress sender,InetSocketAddress addr){
  return !(sender == null || addr == null) && addr.getAddress().equals(sender.getIpAddress()) && (addr.getPort() == 0 || addr.getPort() == sender.getPort());
}
