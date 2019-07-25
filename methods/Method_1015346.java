@Override public Mono<Void> notify(InstanceEvent event){
  return super.notify(event).then(Mono.fromRunnable(() -> updateLastStatus(event)));
}
