public ActualHttpServer newHttpServer(final int port,final MocoConfig[] configs){
  if (isResource()) {
    ActualRestServer server=new ActualRestServer(port,null,log(),configs);
    RestSetting[] settings=resource.getSettings();
    server.resource(resource.getName(),head(settings),tail(settings));
    return server;
  }
  ActualHttpServer server=ActualHttpServer.createLogServer(port,configs);
  bindTo(server);
  return server;
}
