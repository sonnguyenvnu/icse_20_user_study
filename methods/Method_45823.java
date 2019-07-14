@Override public void removeProvider(ProviderGroup providerGroup){
  if (ProviderHelper.isEmpty(providerGroup)) {
    return;
  }
  wLock.lock();
  try {
    getProviderGroup(providerGroup.getName()).removeAll(providerGroup.getProviderInfos());
    removeReversedRelation(this.reversedRelationMap,providerGroup);
  }
  finally {
    wLock.unlock();
  }
}
