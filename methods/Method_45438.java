private SocketServer createSocketServer(final Iterable<? extends RunnerSetting> settings,final StartArgs startArgs){
  SocketServer socketServer=ActualSocketServer.createLogServer(startArgs.getPort().or(0));
  for (  RunnerSetting setting : settings) {
    SocketServer parsedServer=socketParser.parseServer(setting.getStreams(),startArgs.getPort(),toConfigs(setting));
    socketServer=mergeServer(socketServer,parsedServer);
  }
  return socketServer;
}
