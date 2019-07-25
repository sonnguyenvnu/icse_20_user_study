@Override public Mono<Void> notify(InstanceEvent event){
  return Flux.fromIterable(delegates).flatMap(d -> d.notify(event).onErrorResume(error -> {
    log.warn("Unexpected exception while triggering notifications. Notification might not be sent.",error);
    return Mono.empty();
  }
)).then();
}
