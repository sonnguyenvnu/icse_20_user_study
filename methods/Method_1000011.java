public boolean start(EventPluginConfig config){
  boolean success=false;
  if (Objects.isNull(config)) {
    return success;
  }
  this.triggerConfigList=config.getTriggerConfigList();
  useNativeQueue=config.isUseNativeQueue();
  if (config.isUseNativeQueue()) {
    return launchNativeQueue(config);
  }
  return launchEventPlugin(config);
}
