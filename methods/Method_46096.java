/** 
 * ????????
 * @param appName      ???
 * @param serviceName  ?????
 * @param providerInfo ???????
 */
protected void doRegister(String appName,String serviceName,ProviderInfo providerInfo){
  if (LOGGER.isInfoEnabled(appName)) {
    LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_PUB,serviceName));
  }
  ProviderGroup oldGroup=memoryCache.get(serviceName);
  if (oldGroup != null) {
    oldGroup.add(providerInfo);
  }
 else {
    List<ProviderInfo> news=new ArrayList<ProviderInfo>();
    news.add(providerInfo);
    memoryCache.put(serviceName,new ProviderGroup(news));
  }
  needBackup=true;
  doWriteFile();
  if (subscribe) {
    notifyConsumerListeners(serviceName,memoryCache.get(serviceName));
  }
}
