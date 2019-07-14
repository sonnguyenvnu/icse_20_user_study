/** 
 * ServerConfig?ServerTransportConfig
 * @param serverConfig ?????
 * @return ServerTransportConfig ???????
 */
private static ServerTransportConfig convertConfig(ServerConfig serverConfig){
  ServerTransportConfig serverTransportConfig=new ServerTransportConfig();
  serverTransportConfig.setPort(serverConfig.getPort());
  serverTransportConfig.setProtocolType(serverConfig.getProtocol());
  serverTransportConfig.setHost(serverConfig.getBoundHost());
  serverTransportConfig.setContextPath(serverConfig.getContextPath());
  serverTransportConfig.setBizMaxThreads(serverConfig.getMaxThreads());
  serverTransportConfig.setBizPoolType(serverConfig.getThreadPoolType());
  serverTransportConfig.setIoThreads(serverConfig.getIoThreads());
  serverTransportConfig.setChannelListeners(serverConfig.getOnConnect());
  serverTransportConfig.setMaxConnection(serverConfig.getAccepts());
  serverTransportConfig.setPayload(serverConfig.getPayload());
  serverTransportConfig.setTelnet(serverConfig.isTelnet());
  serverTransportConfig.setUseEpoll(serverConfig.isEpoll());
  serverTransportConfig.setBizPoolQueueType(serverConfig.getQueueType());
  serverTransportConfig.setBizPoolQueues(serverConfig.getQueues());
  serverTransportConfig.setDaemon(serverConfig.isDaemon());
  serverTransportConfig.setParameters(serverConfig.getParameters());
  serverTransportConfig.setContainer(serverConfig.getTransport());
  return serverTransportConfig;
}
