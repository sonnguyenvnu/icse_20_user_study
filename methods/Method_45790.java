@Override public void updateProviders(ProviderGroup providerGroup){
  try {
    if (ProviderHelper.isEmpty(providerGroup)) {
      if (CommonUtils.isNotEmpty(currentProviderList())) {
        if (LOGGER.isInfoEnabled(consumerConfig.getAppName())) {
          LOGGER.infoWithApp(consumerConfig.getAppName(),"Clear all providers, may be this consumer has been add to blacklist");
        }
        closeAllClientTransports(null);
      }
    }
 else {
      Collection<ProviderInfo> nowall=currentProviderList();
      List<ProviderInfo> oldAllP=providerGroup.getProviderInfos();
      List<ProviderInfo> nowAllP=new ArrayList<ProviderInfo>(nowall);
      ListDifference<ProviderInfo> diff=new ListDifference<ProviderInfo>(oldAllP,nowAllP);
      List<ProviderInfo> needAdd=diff.getOnlyOnLeft();
      List<ProviderInfo> needDelete=diff.getOnlyOnRight();
      if (!needAdd.isEmpty()) {
        addNode(needAdd);
      }
      if (!needDelete.isEmpty()) {
        removeNode(needDelete);
      }
    }
  }
 catch (  Exception e) {
    if (LOGGER.isErrorEnabled(consumerConfig.getAppName())) {
      LOGGER.errorWithApp(consumerConfig.getAppName(),"update " + consumerConfig.getInterfaceId() + " provider (" + providerGroup + ") from list error:",e);
    }
  }
}
