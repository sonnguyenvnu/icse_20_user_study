@Override protected Publisher<Void> handle(Flux<InstanceEvent> publisher){
  Scheduler scheduler=Schedulers.newSingle("endpoint-detector");
  return publisher.subscribeOn(scheduler).filter(event -> event instanceof InstanceStatusChangedEvent || event instanceof InstanceRegistrationUpdatedEvent).flatMap(this::detectEndpoints).doFinally(s -> scheduler.dispose());
}
