protected Mono<Void> sendNotifications(InstanceEvent event){
  return this.notifier.notify(event).doOnError(e -> log.warn("Couldn't notify for event {} ",event,e)).onErrorResume(e -> Mono.empty());
}
