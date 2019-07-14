/** 
 * ???????
 * @param interfaceId  ????
 * @param providerInfo ???
 * @param transport    ??
 * @param e            ??
 */
protected void printDead(String interfaceId,ProviderInfo providerInfo,ClientTransport transport,Exception e){
  Throwable cause=e.getCause();
  if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
    LOGGER.warnWithApp(consumerConfig.getAppName(),"Connect to {} provider:{} failure !! The exception is " + ExceptionUtils.toShortString(e,1) + (cause != null ? ", cause by " + cause.getMessage() + "." : "."),interfaceId,providerInfo);
  }
}
