@Override public boolean test(InetAddress address){
  requireNonNull(address,"address");
  if (address instanceof Inet4Address) {
    return false;
  }
  if (address instanceof Inet6Address) {
    if (maskBits == 0) {
      return true;
    }
    final long[] value=ipv6AddressToLongArray((Inet6Address)address);
    return (skipCompare[0] || (value[0] >= lowerBound[0] && value[0] <= upperBound[0])) && (skipCompare[1] || (value[1] >= lowerBound[1] && value[1] <= upperBound[1]));
  }
  return false;
}
