public WireMockConfiguration proxyVia(String host,int port){
  this.proxySettings=new ProxySettings(host,port);
  return this;
}
