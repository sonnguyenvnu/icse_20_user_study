public void addOrUpdateReversedRelation(Map<ProviderInfo,ProviderGroup> tmpReversedRelationMap,ProviderGroup providerGroup){
  final List<ProviderInfo> providerInfos=providerGroup.getProviderInfos();
  if (providerInfos != null) {
    for (    ProviderInfo providerInfo : providerInfos) {
      tmpReversedRelationMap.put(providerInfo,providerGroup);
    }
  }
}
