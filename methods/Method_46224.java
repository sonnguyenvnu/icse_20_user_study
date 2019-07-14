protected ThreadPoolExecutor initThreadPool(ServerConfig serverConfig){
  ThreadPoolExecutor threadPool=BusinessPool.initPool(serverConfig);
  threadPool.setThreadFactory(new NamedThreadFactory("SEV-BOLT-BIZ-" + serverConfig.getPort(),serverConfig.isDaemon()));
  threadPool.setRejectedExecutionHandler(new SofaRejectedExecutionHandler());
  if (serverConfig.isPreStartCore()) {
    threadPool.prestartAllCoreThreads();
  }
  return threadPool;
}
