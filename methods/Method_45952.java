@Override public T refer(){
  if (proxyIns != null) {
    return proxyIns;
  }
synchronized (this) {
    if (proxyIns != null) {
      return proxyIns;
    }
    String key=consumerConfig.buildKey();
    String appName=consumerConfig.getAppName();
    checkParameters();
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,"Refer consumer config : {} with bean id {}",key,consumerConfig.getId());
    }
    AtomicInteger cnt=REFERRED_KEYS.get(key);
    if (cnt == null) {
      cnt=CommonUtils.putToConcurrentMap(REFERRED_KEYS,key,new AtomicInteger(0));
    }
    int c=cnt.incrementAndGet();
    int maxProxyCount=consumerConfig.getRepeatedReferLimit();
    if (maxProxyCount > 0) {
      if (c > maxProxyCount) {
        cnt.decrementAndGet();
        throw new SofaRpcRuntimeException("Duplicate consumer config with key " + key + " has been referred more than " + maxProxyCount + " times!" + " Maybe it's wrong config, please check it." + " Ignore this if you did that on purpose!");
      }
 else       if (c > 1) {
        if (LOGGER.isInfoEnabled(appName)) {
          LOGGER.infoWithApp(appName,"Duplicate consumer config with key {} has been referred!" + " Maybe it's wrong config, please check it." + " Ignore this if you did that on purpose!",key);
        }
      }
    }
    try {
      cluster=ClusterFactory.getCluster(this);
      consumerConfig.setConfigListener(buildConfigListener(this));
      consumerConfig.setProviderInfoListener(buildProviderInfoListener(this));
      cluster.init();
      proxyInvoker=buildClientProxyInvoker(this);
      proxyIns=(T)ProxyFactory.buildProxy(consumerConfig.getProxy(),consumerConfig.getProxyClass(),proxyInvoker);
      final String dynamicAlias=consumerConfig.getParameter(DynamicConfigKeys.DYNAMIC_ALIAS);
      if (StringUtils.isNotBlank(dynamicAlias)) {
        final DynamicConfigManager dynamicManager=DynamicConfigManagerFactory.getDynamicManager(consumerConfig.getAppName(),dynamicAlias);
        dynamicManager.initServiceConfiguration(consumerConfig.getInterfaceId());
      }
    }
 catch (    Exception e) {
      if (cluster != null) {
        cluster.destroy();
        cluster=null;
      }
      consumerConfig.setConfigListener(null);
      consumerConfig.setProviderInfoListener(null);
      cnt.decrementAndGet();
      if (e instanceof SofaRpcRuntimeException) {
        throw (SofaRpcRuntimeException)e;
      }
 else {
        throw new SofaRpcRuntimeException("Build consumer proxy error!",e);
      }
    }
    if (consumerConfig.getOnAvailable() != null && cluster != null) {
      cluster.checkStateChange(false);
    }
    RpcRuntimeContext.cacheConsumerConfig(this);
    return proxyIns;
  }
}
