/** 
 * Subscribe provider list from all registries, the providers will be merged.
 * @return Provider group list
 */
protected List<ProviderGroup> subscribeFromRegistries(){
  List<ProviderGroup> result=new ArrayList<ProviderGroup>();
  List<RegistryConfig> registryConfigs=consumerConfig.getRegistry();
  if (CommonUtils.isEmpty(registryConfigs)) {
    return result;
  }
  int addressWaitTime=consumerConfig.getAddressWait();
  int maxAddressWaitTime=SofaConfigs.getIntegerValue(consumerConfig.getAppName(),SofaOptions.CONFIG_MAX_ADDRESS_WAIT_TIME,SofaOptions.MAX_ADDRESS_WAIT_TIME);
  addressWaitTime=addressWaitTime < 0 ? maxAddressWaitTime : Math.min(addressWaitTime,maxAddressWaitTime);
  ProviderInfoListener listener=consumerConfig.getProviderInfoListener();
  respondRegistries=addressWaitTime == 0 ? null : new CountDownLatch(registryConfigs.size());
  Map<String,ProviderGroup> tmpProviderInfoList=new HashMap<String,ProviderGroup>();
  for (  RegistryConfig registryConfig : registryConfigs) {
    Registry registry=RegistryFactory.getRegistry(registryConfig);
    registry.init();
    registry.start();
    try {
      List<ProviderGroup> current;
      try {
        if (respondRegistries != null) {
          consumerConfig.setProviderInfoListener(new WrapperClusterProviderInfoListener(listener,respondRegistries));
        }
        current=registry.subscribe(consumerConfig);
      }
  finally {
        if (respondRegistries != null) {
          consumerConfig.setProviderInfoListener(listener);
        }
      }
      if (current == null) {
        continue;
      }
 else {
        if (respondRegistries != null) {
          respondRegistries.countDown();
        }
      }
      for (      ProviderGroup group : current) {
        String groupName=group.getName();
        if (!group.isEmpty()) {
          ProviderGroup oldGroup=tmpProviderInfoList.get(groupName);
          if (oldGroup != null) {
            oldGroup.addAll(group.getProviderInfos());
          }
 else {
            tmpProviderInfoList.put(groupName,group);
          }
        }
      }
    }
 catch (    SofaRpcRuntimeException e) {
      throw e;
    }
catch (    Throwable e) {
      String appName=consumerConfig.getAppName();
      if (LOGGER.isWarnEnabled(appName)) {
        LOGGER.warnWithApp(appName,"Catch exception when subscribe from registry: " + registryConfig.getId() + ", but you can ignore if it's called by JVM shutdown hook",e);
      }
    }
  }
  if (respondRegistries != null) {
    try {
      respondRegistries.await(addressWaitTime,TimeUnit.MILLISECONDS);
    }
 catch (    Exception ignore) {
    }
  }
  return new ArrayList<ProviderGroup>(tmpProviderInfoList.values());
}
