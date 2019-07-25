private static InetSocketAddress parse(String hostAndPort){
  return InetSocketAddressParser.parse(hostAndPort,ElasticsearchHost.DEFAULT_PORT);
}
