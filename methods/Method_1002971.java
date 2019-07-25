@Override public Connector apply(String hostname){
  final Class<?> protocolType=TomcatService.PROTOCOL_HANDLER_CLASS;
  final Connector connector=new Connector(protocolType.getName());
  connector.setPort(0);
  final StandardServer server=newServer(hostname,connector,config);
  final Service service=server.findServices()[0];
  final Engine engine=TomcatUtil.engine(service,hostname);
  final StandardHost host=(StandardHost)engine.findChildren()[0];
  final Context context=(Context)host.findChildren()[0];
  try {
    config.configurators().forEach(c -> c.accept(server));
  }
 catch (  Throwable t) {
    throw new TomcatServiceException("failed to configure an embedded Tomcat",t);
  }
  checkConfiguration(server,service,connector,engine,host,context);
  assert connector.getService().getServer() != null;
  return connector;
}
