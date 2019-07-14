@Override public ClientTransport getAvailableClientTransport(ProviderInfo providerInfo){
  ClientTransport transport=aliveConnections.get(providerInfo);
  if (transport != null) {
    return transport;
  }
  transport=subHealthConnections.get(providerInfo);
  if (transport != null) {
    return transport;
  }
  transport=uninitializedConnections.get(providerInfo);
  if (transport != null) {
synchronized (this) {
      transport=uninitializedConnections.get(providerInfo);
      if (transport != null) {
        initClientTransport(consumerConfig.getInterfaceId(),providerInfo,transport);
        uninitializedConnections.remove(providerInfo);
      }
      return getAvailableClientTransport(providerInfo);
    }
  }
  return null;
}
