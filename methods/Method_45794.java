@Override public ConcurrentMap<ProviderInfo,ClientTransport> getAvailableConnections(){
  return aliveConnections.isEmpty() ? subHealthConnections : aliveConnections;
}
