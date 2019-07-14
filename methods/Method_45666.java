/** 
 * ?????
 * @param clientTransport   ClientTransport
 * @param disconnectTimeout disconnect timeout
 */
public static void releaseTransport(ClientTransport clientTransport,int disconnectTimeout){
  if (clientTransport == null) {
    return;
  }
  boolean needDestroy=CLIENT_TRANSPORT_HOLDER.removeClientTransport(clientTransport);
  if (needDestroy) {
    if (disconnectTimeout > 0) {
      int count=clientTransport.currentRequests();
      if (count > 0) {
        long start=RpcRuntimeContext.now();
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("There are {} outstanding call in transport, wait {}ms to end",count,disconnectTimeout);
        }
        while (clientTransport.currentRequests() > 0 && RpcRuntimeContext.now() - start < disconnectTimeout) {
          try {
            Thread.sleep(10);
          }
 catch (          InterruptedException ignore) {
          }
        }
      }
    }
    int count=clientTransport.currentRequests();
    if (count > 0) {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn("There are {} outstanding call in client transport, but shutdown now.",count);
      }
    }
    if (REVERSE_CLIENT_TRANSPORT_MAP != null) {
      String key=NetUtils.channelToString(clientTransport.remoteAddress(),clientTransport.localAddress());
      REVERSE_CLIENT_TRANSPORT_MAP.remove(key);
    }
    clientTransport.destroy();
  }
}
