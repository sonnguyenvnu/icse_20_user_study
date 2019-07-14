/** 
 * ?????Provider?????????????
 * @return ???Provider?? set
 */
public Map<String,Set<ProviderInfo>> currentProviderMap(){
  providerLock.lock();
  try {
    Map<String,Set<ProviderInfo>> tmp=new LinkedHashMap<String,Set<ProviderInfo>>();
    tmp.put("alive",new HashSet<ProviderInfo>(aliveConnections.keySet()));
    tmp.put("subHealth",new HashSet<ProviderInfo>(subHealthConnections.keySet()));
    tmp.put("retry",new HashSet<ProviderInfo>(retryConnections.keySet()));
    tmp.put("uninitialized",new HashSet<ProviderInfo>(uninitializedConnections.keySet()));
    tmp.put("all",new HashSet<ProviderInfo>(lastAddresses));
    return tmp;
  }
  finally {
    providerLock.unlock();
  }
}
