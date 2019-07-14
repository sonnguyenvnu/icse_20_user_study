@Override public void updateAllProviders(List<ProviderGroup> providerGroups){
  ConcurrentHashSet<ProviderInfo> tmpDirectUrl=new ConcurrentHashSet<ProviderInfo>();
  ConcurrentHashSet<ProviderInfo> tmpRegistry=new ConcurrentHashSet<ProviderInfo>();
  Map<ProviderInfo,ProviderGroup> tmpReversedRelationMap=new ConcurrentHashMap<>();
  for (  ProviderGroup providerGroup : providerGroups) {
    if (!ProviderHelper.isEmpty(providerGroup)) {
      if (RpcConstants.ADDRESS_DIRECT_GROUP.equals(providerGroup.getName())) {
        tmpDirectUrl.addAll(providerGroup.getProviderInfos());
      }
 else {
        tmpRegistry.addAll(providerGroup.getProviderInfos());
      }
      addOrUpdateReversedRelation(tmpReversedRelationMap,providerGroup);
    }
  }
  wLock.lock();
  try {
    this.directUrlGroup.setProviderInfos(new ArrayList<ProviderInfo>(tmpDirectUrl));
    this.registryGroup.setProviderInfos(new ArrayList<ProviderInfo>(tmpRegistry));
    this.reversedRelationMap=tmpReversedRelationMap;
  }
  finally {
    wLock.unlock();
  }
}
