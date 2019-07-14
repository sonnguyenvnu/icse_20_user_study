/** 
 * ?????Provider?????????????
 * @return ???Provider?? set
 */
@Override public Set<ProviderInfo> currentProviderList(){
  providerLock.lock();
  try {
    ConcurrentHashSet<ProviderInfo> providerInfos=new ConcurrentHashSet<ProviderInfo>();
    providerInfos.addAll(lastAddresses);
    return providerInfos;
  }
  finally {
    providerLock.unlock();
  }
}
