private static String key(SocketAddress remoteAddress){
  requireNonNull(remoteAddress,"remoteAddress");
  if (!(remoteAddress instanceof InetSocketAddress)) {
    throw new IllegalArgumentException("remoteAddress: " + remoteAddress + " (expected: an " + InetSocketAddress.class.getSimpleName() + ')');
  }
  final InetSocketAddress raddr=(InetSocketAddress)remoteAddress;
  final String host=raddr.getHostString();
  return new StringBuilder(host.length() + 6).append(host).append(':').append(raddr.getPort()).toString();
}
