protected SofaNettyJaxrsServer buildServer(){
  SofaNettyJaxrsServer httpServer=new SofaNettyJaxrsServer(serverConfig);
  int bossThreads=serverConfig.getIoThreads();
  if (bossThreads > 0) {
    httpServer.setIoWorkerCount(bossThreads);
  }
  httpServer.setExecutorThreadCount(serverConfig.getMaxThreads());
  httpServer.setMaxRequestSize(serverConfig.getPayload());
  httpServer.setHostname(serverConfig.getBoundHost());
  httpServer.setPort(serverConfig.getPort());
  ResteasyDeployment resteasyDeployment=httpServer.getDeployment();
  resteasyDeployment.start();
  ResteasyProviderFactory providerFactory=resteasyDeployment.getProviderFactory();
  registerProvider(providerFactory);
  return httpServer;
}
