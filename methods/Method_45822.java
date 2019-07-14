@Override public void addProvider(ProviderGroup providerGroup){
  if (ProviderHelper.isEmpty(providerGroup)) {
    return;
  }
  wLock.lock();
  try {
    getProviderGroup(providerGroup.getName()).addAll(providerGroup.getProviderInfos());
    addOrUpdateReversedRelation(this.reversedRelationMap,providerGroup);
  }
  finally {
    wLock.unlock();
  }
}
