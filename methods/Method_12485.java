protected Mono<Void> detectEndpoints(InstanceEvent event){
  return this.endpointDetector.detectEndpoints(event.getInstance()).onErrorResume(e -> {
    log.warn("Unexpected error while detecting endpoints for {}",event.getInstance(),e);
    return Mono.empty();
  }
);
}
