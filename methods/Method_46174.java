private void notifyListeners(AbstractInterfaceConfig config,String configPath,ChildData data,boolean removeType){
  List<ConfigListener> configListeners=configListenerMap.get(config);
  if (CommonUtils.isNotEmpty(configListeners)) {
    Map<String,String> attribute=ZookeeperRegistryHelper.convertConfigToAttribute(configPath,data,removeType);
    for (    ConfigListener listener : configListeners) {
      listener.configChanged(attribute);
    }
  }
}
