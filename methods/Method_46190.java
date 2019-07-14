@Override public void unSubscribe(ConsumerConfig config){
  if (config.isRegister()) {
    try {
      String url=consumerUrls.remove(config);
      if (url != null) {
        String consumerPath=buildConsumerPath(rootPath,config);
        url=URLEncoder.encode(url,"UTF-8");
        getAndCheckZkClient().delete().forPath(consumerPath + CONTEXT_SEP + url);
      }
    }
 catch (    Exception e) {
      if (!RpcRunningState.isShuttingDown()) {
        throw new SofaRpcRuntimeException("Failed to unregister consumer to zookeeperRegistry!",e);
      }
    }
  }
  if (config.isSubscribe()) {
    try {
      providerObserver.removeProviderListener(config);
    }
 catch (    Exception e) {
      if (!RpcRunningState.isShuttingDown()) {
        throw new SofaRpcRuntimeException("Failed to unsubscribe provider from zookeeperRegistry!",e);
      }
    }
    try {
      configObserver.removeConfigListener(config);
    }
 catch (    Exception e) {
      if (!RpcRunningState.isShuttingDown()) {
        throw new SofaRpcRuntimeException("Failed to unsubscribe consumer config from zookeeperRegistry!",e);
      }
    }
    PathChildrenCache childrenCache=INTERFACE_PROVIDER_CACHE.remove(config);
    if (childrenCache != null) {
      try {
        childrenCache.close();
      }
 catch (      Exception e) {
        if (!RpcRunningState.isShuttingDown()) {
          throw new SofaRpcRuntimeException("Failed to unsubscribe consumer config from zookeeperRegistry!",e);
        }
      }
    }
  }
}
