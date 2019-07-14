/** 
 * ??????
 * @return ????????
 */
protected Map<ProviderInfo,ClientTransport> clearProviders(){
  providerLock.lock();
  try {
    HashMap<ProviderInfo,ClientTransport> all=new HashMap<ProviderInfo,ClientTransport>(aliveConnections);
    all.putAll(subHealthConnections);
    all.putAll(retryConnections);
    all.putAll(uninitializedConnections);
    subHealthConnections.clear();
    aliveConnections.clear();
    retryConnections.clear();
    uninitializedConnections.clear();
    lastAddresses.clear();
    return all;
  }
  finally {
    providerLock.unlock();
  }
}
