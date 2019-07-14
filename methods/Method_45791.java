@Override public void updateAllProviders(List<ProviderGroup> providerGroups){
  List<ProviderInfo> mergePs=new ArrayList<ProviderInfo>();
  if (CommonUtils.isNotEmpty(providerGroups)) {
    for (    ProviderGroup providerGroup : providerGroups) {
      if (!ProviderHelper.isEmpty(providerGroup)) {
        mergePs.addAll(providerGroup.getProviderInfos());
      }
    }
  }
  updateProviders(new ProviderGroup().addAll(mergePs));
}
