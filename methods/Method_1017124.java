public HttpConfiguration build(){
  final HttpConfiguration c=new HttpConfiguration();
  c.setSendServerVersion(sendServerVersion);
  return c;
}
