/** 
 * ??IP?????????????????,??????ConsumerConfig?????
 * @param config   consumer config
 * @param listener config listener
 */
protected void subscribeOverride(final ConsumerConfig config,ConfigListener listener){
  try {
    if (overrideObserver == null) {
      overrideObserver=new ZookeeperOverrideObserver();
    }
    overrideObserver.addConfigListener(config,listener);
    final String overridePath=buildOverridePath(rootPath,config);
    final AbstractInterfaceConfig registerConfig=getRegisterConfig(config);
    PathChildrenCache pathChildrenCache=new PathChildrenCache(zkClient,overridePath,true);
    pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener(){
      @Override public void childEvent(      CuratorFramework client1,      PathChildrenCacheEvent event) throws Exception {
        if (LOGGER.isDebugEnabled(config.getAppName())) {
          LOGGER.debug("Receive zookeeper event: " + "type=[" + event.getType() + "]");
        }
switch (event.getType()) {
case CHILD_ADDED:
          overrideObserver.addConfig(config,overridePath,event.getData());
        break;
case CHILD_REMOVED:
      overrideObserver.removeConfig(config,overridePath,event.getData(),registerConfig);
    break;
case CHILD_UPDATED:
  overrideObserver.updateConfig(config,overridePath,event.getData());
break;
default :
break;
}
}
}
);
pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
INTERFACE_OVERRIDE_CACHE.put(overridePath,pathChildrenCache);
overrideObserver.updateConfigAll(config,overridePath,pathChildrenCache.getCurrentData());
}
 catch (Exception e) {
throw new SofaRpcRuntimeException("Failed to subscribe provider config from zookeeperRegistry!",e);
}
}
