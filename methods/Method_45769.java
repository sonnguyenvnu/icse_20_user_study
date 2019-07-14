@Override public void updateAllProviders(List<ProviderGroup> providerGroups){
  List<ProviderGroup> oldProviderGroups=new ArrayList<ProviderGroup>(addressHolder.getProviderGroups());
  int count=0;
  if (providerGroups != null) {
    for (    ProviderGroup providerGroup : providerGroups) {
      checkProviderInfo(providerGroup);
      count+=providerGroup.size();
    }
  }
  if (count == 0) {
    Collection<ProviderInfo> currentProviderList=currentProviderList();
    addressHolder.updateAllProviders(providerGroups);
    if (CommonUtils.isNotEmpty(currentProviderList)) {
      if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
        LOGGER.warnWithApp(consumerConfig.getAppName(),"Provider list is emptied, may be all " + "providers has been closed, or this consumer has been add to blacklist");
        closeTransports();
      }
    }
  }
 else {
    addressHolder.updateAllProviders(providerGroups);
    connectionHolder.updateAllProviders(providerGroups);
  }
  if (EventBus.isEnable(ProviderInfoUpdateAllEvent.class)) {
    ProviderInfoUpdateAllEvent event=new ProviderInfoUpdateAllEvent(consumerConfig,oldProviderGroups,providerGroups);
    EventBus.post(event);
  }
}
