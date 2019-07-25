public boolean contains(InetAddress address){
  if (address instanceof Inet4Address) {
    IP ip=new IP(address.getAddress());
    return contains(ip);
  }
  return false;
}
