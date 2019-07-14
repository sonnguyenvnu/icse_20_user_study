/** 
 * ???????
 * @param serviceName  ?????
 * @param providerInfo ???????
 */
protected void doUnRegister(String serviceName,ProviderInfo providerInfo){
  ProviderGroup oldGroup=memoryCache.get(serviceName);
  if (oldGroup != null) {
    oldGroup.remove(providerInfo);
  }
 else {
    return;
  }
  needBackup=true;
  doWriteFile();
  if (subscribe) {
    notifyConsumerListeners(serviceName,memoryCache.get(serviceName));
  }
}
