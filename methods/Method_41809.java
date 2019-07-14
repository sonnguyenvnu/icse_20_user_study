public SerializedToolkitStore<TriggerKey,TriggerWrapper> getOrCreateTriggersMap(){
  String triggersMapName=generateName(TRIGGERS_MAP_PREFIX);
  SerializedToolkitStore<TriggerKey,TriggerWrapper> temp=new SerializedToolkitStore<TriggerKey,TriggerWrapper>(createStore(triggersMapName));
  triggersMapReference.compareAndSet(null,temp);
  return triggersMapReference.get();
}
