private static InetSocketAddress convert(String s){
  final URI u;
  try {
    u=new URI("custom://" + s);
  }
 catch (  URISyntaxException e) {
    throw new IllegalArgumentException("invalid seed address '" + s + "'",e);
  }
  final String host=u.getHost();
  final int port=u.getPort() != -1 ? u.getPort() : DEFAULT_PORT;
  if (host == null) {
    throw new IllegalArgumentException("invalid seed address '" + s + "', no host specified");
  }
  return new InetSocketAddress(host,port);
}
