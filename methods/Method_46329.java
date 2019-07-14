@Override public void unRegisterProcessor(ProviderConfig providerConfig,boolean closeIfNoEntry){
  if (!isStarted()) {
    return;
  }
  if (LOGGER.isInfoEnabled()) {
    LOGGER.info("Unregister jaxrs service to port {} and base path is {}",serverConfig.getPort(),serverConfig.getContextPath());
  }
  try {
    httpServer.getDeployment().getRegistry().removeRegistrations(providerConfig.getRef().getClass(),serverConfig.getContextPath());
    invokerCnt.decrementAndGet();
  }
 catch (  Exception e) {
    LOGGER.error("Unregister jaxrs service error",e);
  }
  if (closeIfNoEntry && invokerCnt.get() == 0) {
    stop();
  }
}
