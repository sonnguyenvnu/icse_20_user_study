private Runner newRunner(final Server server){
  if (server instanceof HttpServer) {
    return Runner.runner((HttpServer)server);
  }
  if (server instanceof SocketServer) {
    return Runner.runner((SocketServer)server);
  }
  throw new IllegalArgumentException("Unknown server type:" + server.getClass().getName());
}
