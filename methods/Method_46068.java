/** 
 * Create ProviderId
 * @return ProviderId
 */
public Id fetchProviderStatId(){
  if (providerId == null) {
synchronized (providerIdLock) {
      if (providerId == null) {
        providerId=Lookout.registry().createId("rpc.provider.service.stats");
      }
    }
  }
  return providerId;
}
