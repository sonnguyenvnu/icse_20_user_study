private void notifyListeners(AbstractInterfaceConfig config,String overridePath,ChildData data,boolean removeType,AbstractInterfaceConfig interfaceConfig) throws Exception {
  List<ConfigListener> configListeners=configListenerMap.get(config);
  if (CommonUtils.isNotEmpty(configListeners)) {
    Map<String,String> attribute=ZookeeperRegistryHelper.convertOverrideToAttribute(overridePath,data,removeType,interfaceConfig);
    for (    ConfigListener listener : configListeners) {
      listener.attrUpdated(attribute);
    }
  }
}
