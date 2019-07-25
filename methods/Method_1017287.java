public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext servletContext){
  if (servletContext == null) {
    log.severe(format("Failed to deploy frontend endpoint %s: %s",WEBSOCKET_PATH,"ServletContext not available"));
    return;
  }
  ServerContainer serverContainer=(ServerContainer)servletContext.getAttribute("javax.websocket.server.ServerContainer");
  if (serverContainer == null) {
    log.severe(format("Failed to deploy frontend endpoint %s: %s",WEBSOCKET_PATH,"javax.websocket.server.ServerContainer ServerContainer not available"));
    return;
  }
  ServerEndpointConfig config=ServerEndpointConfig.Builder.create(Frontend.class,WEBSOCKET_PATH).configurator(new ServerEndpointConfig.Configurator(){
    @SuppressWarnings("unchecked") @Override public <T>T getEndpointInstance(    final Class<T> endpointClass) throws InstantiationException {
      if (endpointClass.isAssignableFrom(Frontend.class)) {
        return (T)Frontend.this;
      }
      return super.getEndpointInstance(endpointClass);
    }
  }
).build();
  try {
    serverContainer.addEndpoint(config);
  }
 catch (  DeploymentException e) {
    log.log(Level.SEVERE,format("Failed to deploy frontend endpoint %s: %s",WEBSOCKET_PATH,e.getMessage()),e);
  }
}
