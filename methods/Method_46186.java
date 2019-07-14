/** 
 * ???????
 * @param config   provider/consumer config
 * @param listener config listener
 */
protected void subscribeConfig(final AbstractInterfaceConfig config,ConfigListener listener){
  try {
    if (configObserver == null) {
      configObserver=new ZookeeperConfigObserver();
    }
    configObserver.addConfigListener(config,listener);
    final String configPath=buildConfigPath(rootPath,config);
    PathChildrenCache pathChildrenCache=new PathChildrenCache(zkClient,configPath,true);
    pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener(){
      @Override public void childEvent(      CuratorFramework client1,      PathChildrenCacheEvent event) throws Exception {
        if (LOGGER.isDebugEnabled(config.getAppName())) {
          LOGGER.debug("Receive zookeeper event: " + "type=[" + event.getType() + "]");
        }
switch (event.getType()) {
case CHILD_ADDED:
          configObserver.addConfig(config,configPath,event.getData());
        break;
case CHILD_REMOVED:
      configObserver.removeConfig(config,configPath,event.getData());
    break;
case CHILD_UPDATED:
  configObserver.updateConfig(config,configPath,event.getData());
break;
default :
break;
}
}
}
);
pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
INTERFACE_CONFIG_CACHE.put(configPath,pathChildrenCache);
configObserver.updateConfigAll(config,configPath,pathChildrenCache.getCurrentData());
}
 catch (Exception e) {
throw new SofaRpcRuntimeException("Failed to subscribe provider config from zookeeperRegistry!",e);
}
}
