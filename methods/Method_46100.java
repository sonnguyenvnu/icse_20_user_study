@Override public List<ProviderGroup> subscribe(ConsumerConfig config){
  String key=LocalRegistryHelper.buildListDataId(config,config.getProtocol());
  List<ConsumerConfig> listeners=notifyListeners.get(key);
  if (listeners == null) {
    listeners=new ArrayList<ConsumerConfig>();
    notifyListeners.put(key,listeners);
  }
  listeners.add(config);
  ProviderGroup group=memoryCache.get(key);
  if (group == null) {
    group=new ProviderGroup();
    memoryCache.put(key,group);
  }
  if (EventBus.isEnable(ConsumerSubEvent.class)) {
    ConsumerSubEvent event=new ConsumerSubEvent(config);
    EventBus.post(event);
  }
  return Collections.singletonList(group);
}
