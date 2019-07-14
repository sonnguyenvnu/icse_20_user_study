public Id fetchProviderPubId(){
  if (providerConfigId == null) {
synchronized (providerConfigIdLock) {
      if (providerConfigId == null) {
        providerConfigId=Lookout.registry().createId("rpc.provider.info.stats");
      }
    }
  }
  return providerConfigId;
}
