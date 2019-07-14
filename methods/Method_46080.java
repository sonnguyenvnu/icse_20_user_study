@Override public void install(){
  subscriber=new LookoutSubscriber();
  EventBus.register(ClientEndInvokeEvent.class,subscriber);
  EventBus.register(ServerSendEvent.class,subscriber);
  EventBus.register(ServerStartedEvent.class,subscriber);
  EventBus.register(ProviderPubEvent.class,subscriber);
  EventBus.register(ConsumerSubEvent.class,subscriber);
}
