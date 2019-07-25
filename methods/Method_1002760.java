@Override public boolean test(InetAddress address){
  requireNonNull(address,"address");
  if (maskBits == 0) {
    return true;
  }
  final byte[] bytes;
  if (address instanceof Inet6Address) {
    bytes=ipv6ToIpv4Address((Inet6Address)address);
  }
 else {
    bytes=address.getAddress();
  }
  if (bytes == null) {
    return false;
  }
  final int addr=ipv4AddressToInt(bytes);
  return addr >= lowerBound && addr <= upperBound;
}
