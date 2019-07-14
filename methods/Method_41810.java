public ToolkitStore<String,FiredTrigger> getOrCreateFiredTriggersMap(){
  String firedTriggerMapName=generateName(FIRED_TRIGGER_MAP_PREFIX);
  ToolkitStore<String,FiredTrigger> temp=createStore(firedTriggerMapName);
  firedTriggersMapReference.compareAndSet(null,temp);
  return firedTriggersMapReference.get();
}
