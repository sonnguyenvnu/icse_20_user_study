@Override protected Publisher<Void> handle(Flux<InstanceEvent> publisher){
  Scheduler scheduler=Schedulers.newSingle("info-updater");
  return publisher.subscribeOn(scheduler).filter(event -> event instanceof InstanceEndpointsDetectedEvent || event instanceof InstanceStatusChangedEvent || event instanceof InstanceRegistrationUpdatedEvent).flatMap(this::updateInfo).doFinally(s -> scheduler.dispose());
}
