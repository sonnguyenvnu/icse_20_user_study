@Override public String fetchGroupName(ProviderInfo providerInfo){
  rLock.lock();
  try {
    final ProviderGroup providerGroup=reversedRelationMap.get(providerInfo);
    if (providerGroup != null) {
      return providerGroup.getName();
    }
 else {
      return "";
    }
  }
  finally {
    rLock.unlock();
  }
}
