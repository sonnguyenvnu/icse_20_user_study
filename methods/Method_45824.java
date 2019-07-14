@Override public void updateProviders(ProviderGroup providerGroup){
  wLock.lock();
  try {
    getProviderGroup(providerGroup.getName()).setProviderInfos(new ArrayList<ProviderInfo>(providerGroup.getProviderInfos()));
    addOrUpdateReversedRelation(this.reversedRelationMap,providerGroup);
  }
  finally {
    wLock.unlock();
  }
}
