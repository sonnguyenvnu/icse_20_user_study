private Server newServer(final Iterable<? extends RunnerSetting> settings,final StartArgs startArgs){
  if (startArgs.isSocket()) {
    return createSocketServer(settings,startArgs);
  }
  return createHttpServer(settings,startArgs);
}
