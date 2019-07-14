private void copyServerFields(ServerConfig serverConfig,ProtocolConfig protocolConfig){
  protocolConfig.setId(serverConfig.getId());
  protocolConfig.setName(serverConfig.getProtocol());
  protocolConfig.setHost(serverConfig.getHost());
  protocolConfig.setPort(serverConfig.getPort());
  protocolConfig.setAccepts(serverConfig.getAccepts());
  protocolConfig.setSerialization(serverConfig.getSerialization());
  if (!StringUtils.CONTEXT_SEP.equals(serverConfig.getContextPath())) {
    protocolConfig.setContextpath(serverConfig.getContextPath());
  }
  protocolConfig.setIothreads(serverConfig.getIoThreads());
  protocolConfig.setThreadpool(serverConfig.getThreadPoolType());
  protocolConfig.setThreads(serverConfig.getMaxThreads());
  protocolConfig.setPayload(serverConfig.getPayload());
  protocolConfig.setQueues(serverConfig.getQueues());
  protocolConfig.setParameters(serverConfig.getParameters());
}
