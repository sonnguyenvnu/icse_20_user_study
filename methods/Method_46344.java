@Override public void install(){
  Tracer tracer=TracerFactory.getTracer("sofaTracer");
  if (tracer != null) {
    subscriber=new SofaTracerSubscriber();
    EventBus.register(ClientStartInvokeEvent.class,subscriber);
    EventBus.register(ClientBeforeSendEvent.class,subscriber);
    EventBus.register(ClientAfterSendEvent.class,subscriber);
    EventBus.register(ServerReceiveEvent.class,subscriber);
    EventBus.register(ServerSendEvent.class,subscriber);
    EventBus.register(ServerEndHandleEvent.class,subscriber);
    EventBus.register(ClientSyncReceiveEvent.class,subscriber);
    EventBus.register(ClientAsyncReceiveEvent.class,subscriber);
    EventBus.register(ClientEndInvokeEvent.class,subscriber);
  }
}
