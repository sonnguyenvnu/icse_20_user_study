protected String getServiceHost(){
  InetAddress address=server.getAddress();
  if (address == null) {
    address=getLocalHost();
  }
  return getHost(address);
}
