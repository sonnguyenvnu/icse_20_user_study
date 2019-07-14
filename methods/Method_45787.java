/** 
 * ?????????
 * @param providerInfo Provider
 * @param transport    ??
 */
protected void retryToAlive(ProviderInfo providerInfo,ClientTransport transport){
  providerLock.lock();
  try {
    if (retryConnections.remove(providerInfo) != null) {
      if (checkState(providerInfo,transport)) {
        aliveConnections.put(providerInfo,transport);
      }
    }
  }
  finally {
    providerLock.unlock();
  }
}
