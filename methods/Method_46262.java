protected ThreadPoolExecutor initThreadPool(ServerConfig serverConfig){
  ThreadPoolExecutor threadPool=BusinessPool.initPool(serverConfig);
  threadPool.setThreadFactory(new NamedThreadFactory("SEV-" + serverConfig.getProtocol().toUpperCase() + "-BIZ-" + serverConfig.getPort(),serverConfig.isDaemon()));
  threadPool.setRejectedExecutionHandler(new SofaRejectedExecutionHandler());
  if (serverConfig.isPreStartCore()) {
    threadPool.prestartAllCoreThreads();
  }
  return threadPool;
}
