@Override public void uninstall(){
  if (subscriber != null) {
    EventBus.unRegister(ClientEndInvokeEvent.class,subscriber);
    EventBus.unRegister(ServerSendEvent.class,subscriber);
    EventBus.unRegister(ServerStartedEvent.class,subscriber);
    EventBus.unRegister(ProviderPubEvent.class,subscriber);
    EventBus.unRegister(ConsumerSubEvent.class,subscriber);
  }
}
