/** 
 * ?????????Data??
 * @param config      ????
 * @param configPath  ??Path
 * @param currentData ???Data??
 */
public void updateConfigAll(AbstractInterfaceConfig config,String configPath,List<ChildData> currentData){
  if (CommonUtils.isEmpty(currentData)) {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive updateAll data is null");
    }
  }
 else {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      for (      ChildData data : currentData) {
        LOGGER.infoWithApp(config.getAppName(),"Receive updateAll data: path=[" + data.getPath() + "], data=[" + StringSerializer.decode(data.getData()) + "]" + ", stat=[" + data.getStat() + "]");
      }
    }
    List<ConfigListener> configListeners=configListenerMap.get(config);
    if (CommonUtils.isNotEmpty(configListeners)) {
      List<Map<String,String>> attributes=ZookeeperRegistryHelper.convertConfigToAttributes(configPath,currentData);
      for (      ConfigListener listener : configListeners) {
        for (        Map<String,String> attribute : attributes) {
          listener.configChanged(attribute);
        }
      }
    }
  }
}
