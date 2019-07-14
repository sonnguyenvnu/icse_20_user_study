@Override public List<ProviderInfo> getAvailableProviders(){
  ConcurrentMap<ProviderInfo,ClientTransport> map=aliveConnections.isEmpty() ? subHealthConnections : aliveConnections;
  return new ArrayList<ProviderInfo>(map.keySet());
}
