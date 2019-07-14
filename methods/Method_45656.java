/** 
 * ???Server?host?port
 * @param serverConfig ?????
 */
private static void resolveServerConfig(ServerConfig serverConfig){
  String boundHost=serverConfig.getBoundHost();
  if (boundHost == null) {
    String host=serverConfig.getHost();
    if (StringUtils.isBlank(host)) {
      host=SystemInfo.getLocalHost();
      serverConfig.setHost(host);
      boundHost=SystemInfo.isWindows() ? host : NetUtils.ANYHOST;
    }
 else {
      boundHost=host;
    }
    serverConfig.setBoundHost(boundHost);
  }
  if (serverConfig.isAdaptivePort()) {
    int oriPort=serverConfig.getPort();
    int port=NetUtils.getAvailablePort(boundHost,oriPort,RpcConfigs.getIntValue(RpcOptions.SERVER_PORT_END));
    if (port != oriPort) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Changed port from {} to {} because the config port is disabled",oriPort,port);
      }
      serverConfig.setPort(port);
    }
  }
}
