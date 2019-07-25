static String format(InetAddress address,int port){
  Objects.requireNonNull(address);
  StringBuilder builder=new StringBuilder();
  if (port != -1 && address instanceof Inet6Address) {
    builder.append(InetAddresses.toUriString(address));
  }
 else {
    builder.append(InetAddresses.toAddrString(address));
  }
  if (port != -1) {
    builder.append(':');
    builder.append(port);
  }
  return builder.toString();
}
