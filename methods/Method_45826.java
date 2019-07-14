public void removeReversedRelation(Map<ProviderInfo,ProviderGroup> tmpReversedRelationMap,ProviderGroup providerGroup){
  final List<ProviderInfo> providerInfos=providerGroup.getProviderInfos();
  if (providerInfos != null) {
    for (    ProviderInfo providerInfo : providerInfos) {
      reversedRelationMap.remove(providerInfo);
    }
  }
}
