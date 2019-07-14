protected Server createServer(Options options){
  final Server server=new Server(options.threadPoolFactory().buildThreadPool(options));
  final JettySettings jettySettings=options.jettySettings();
  final Optional<Long> stopTimeout=jettySettings.getStopTimeout();
  if (stopTimeout.isPresent()) {
    server.setStopTimeout(stopTimeout.get());
  }
  return server;
}
