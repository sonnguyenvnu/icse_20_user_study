/** 
 * ???????
 * @param appName     ??
 * @param serviceName ?????
 * @param group       ????
 */
protected void doUnRegister(String appName,String serviceName,String group){
  SofaRegistryClient.getRegistryClient(appName,registryConfig).unregister(serviceName,group,RegistryType.PUBLISHER);
}
