private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult){
  String host=getMasterAddrByNameResult.get(0);
  int port=Integer.parseInt(getMasterAddrByNameResult.get(1));
  return new HostAndPort(host,port);
}
