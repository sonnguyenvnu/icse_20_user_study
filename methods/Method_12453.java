@Override protected Publisher<Void> handle(Flux<InstanceEvent> publisher){
  Scheduler scheduler=Schedulers.newSingle("notifications");
  return publisher.subscribeOn(scheduler).flatMap(this::sendNotifications).doFinally(s -> scheduler.dispose());
}
