private HttpServer createBaseHttpServer(final Iterable<? extends RunnerSetting> settings,final StartArgs startArgs){
  HttpServer targetServer=createHttpServer(startArgs);
  for (  RunnerSetting setting : settings) {
    HttpServer parsedServer=httpParser.parseServer(setting.getStreams(),startArgs.getPort(),toConfigs(setting));
    targetServer=mergeServer(targetServer,parsedServer);
  }
  return targetServer;
}
